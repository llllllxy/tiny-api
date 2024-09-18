package org.tinycloud.tinyapi.modules.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.tinycloud.tinyapi.common.config.ApplicationConfig;
import org.tinycloud.tinyapi.common.constant.GlobalConstant;
import org.tinycloud.tinyapi.common.enums.TenantErrorCode;
import org.tinycloud.tinyapi.common.exception.TenantException;
import org.tinycloud.tinyapi.common.utils.IpGetUtils;

import org.tinycloud.tinyapi.common.utils.JacksonUtils;
import org.tinycloud.tinyapi.common.utils.StrUtils;
import org.tinycloud.tinyapi.common.utils.cipher.SM3Utils;
import org.tinycloud.tinyapi.modules.bean.dto.AuthenticationDto;
import org.tinycloud.tinyapi.modules.bean.dto.SignatureDto;
import org.tinycloud.tinyapi.modules.bean.entity.TApp;
import org.tinycloud.tinyapi.modules.mapper.AppMapper;

import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * </p>
 *
 * @author liuxingyu01
 * @since 2024-02-27 14:14
 */
@Service
public class ApiAuthService {

    @Autowired
    private AppMapper appMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private ApplicationConfig applicationConfig;

    public String authCode() {
        String authCode = UUID.randomUUID().toString().replace("-", "");
        // 缓存redis里60秒，表示有效期
        this.stringRedisTemplate.opsForValue().set(GlobalConstant.APP_API_AUTHCODE_REDIS_KEY + authCode, authCode, 60, TimeUnit.SECONDS);
        return authCode;
    }

    public String signature(SignatureDto dto) {
        return SM3Utils.hmac(dto.getAuthCode(), dto.getAppKey());
    }

    public String authentication(AuthenticationDto dto, HttpServletRequest request) {
        String appCode = dto.getAuthCode();
        String authCode = dto.getAuthCode();
        String signature = dto.getSignature();

        // 第一步、校验authCode是否存在
        boolean hasKey = this.stringRedisTemplate.hasKey(GlobalConstant.APP_API_AUTHCODE_REDIS_KEY + authCode);
        if (!hasKey) {
            throw new TenantException(TenantErrorCode.AUTHCODE_NOT_EXIST_OR_EXPIRED);
        }

        // 第二步、校验appCode是否存在
        TApp entity = this.appMapper.selectOne(
                Wrappers.<TApp>lambdaQuery().eq(TApp::getAppCode, appCode)
                        .eq(TApp::getDelFlag, GlobalConstant.NOT_DELETED));
        if (Objects.isNull(entity)) {
            throw new TenantException(TenantErrorCode.APP_CODE_NOT_EXIST);
        }

        // 第三步、判断应用是否启用
        if (entity.getStatus().equals(GlobalConstant.DISABLED)) {
            throw new TenantException(TenantErrorCode.APP_IS_DISABLED);
        }

        // 第四步、签名校验
        String accessKeySecret = entity.getAppKey();
        String nowSignature = SM3Utils.hmac(authCode, accessKeySecret);
        if (!signature.equals(nowSignature)) {
            throw new TenantException(TenantErrorCode.SIGNATURE_CHECK_FAILED);
        }

        // 第五步，白名单(或者黑名单)校验  TODO

        Integer ipStrategyType = entity.getIpStrategyType();
        Integer checkIpFlag = entity.getCheckIpFlag();
        if (checkIpFlag == 1) {

            if (StrUtils.isNotBlank(ipWhitelist)) {
                if (!ipWhitelist.contains(IpGetUtils.getIpAddr(request))) {
                    throw new TenantException(TenantErrorCode.IP_IS_NOT_IN_WHITELIST);
                }
            }
        }
        String token = "tinyapi_" + UUID.randomUUID().toString().replace("-", "");
        // 缓存redis里60秒，表示有效期
        entity.setTenantPassword(null);
        this.stringRedisTemplate.opsForValue().set(GlobalConstant.APP_API_TOKEN_REDIS_KEY + token,
                JacksonUtils.toJsonString(entity), applicationConfig.getApiAuthTimeout(), TimeUnit.SECONDS);
        return token;
    }
}
