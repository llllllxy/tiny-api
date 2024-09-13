package org.tinycloud.tinyapi.modules.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.tinycloud.tinyapi.common.constant.GlobalConstant;
import org.tinycloud.tinyapi.common.enums.CoreErrorCode;
import org.tinycloud.tinyapi.common.exception.CoreException;
import org.tinycloud.tinyapi.modules.bean.entity.TApiInfo;
import org.tinycloud.tinyapi.modules.mapper.ApiInfoMapper;
import org.tinycloud.tinyapi.modules.mapper.AppMapper;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 核心实现类
 * </p>
 *
 * @author liuxingyu01
 * @since 2024-09-2024/9/11 22:36
 */
@Slf4j
@Service
public class ApiClientService {

    @Autowired
    private ApiInfoMapper apiInfoMapper;

    @Autowired
    private AppMapper appMapper;

    public Object queryByUrl(HttpServletRequest request) {
        TApiInfo restSetting = getApiInfoByUrl(request.getRequestURI());
        // Map<String, String> paramMap = getParamsMap(restSetting, request);


        return null;
    }


    /**
     * 根据url获取api信息
     *
     * @param url 请求路径
     * @return api信息
     */
    private TApiInfo getApiInfoByUrl(String url) {
        if (url == null) {
            throw new CoreException(CoreErrorCode.API_ADDRESS_IS_NULL_ERROR);
        }
        url = url.replaceAll(".*" + GlobalConstant.URL_PREFIX, "");
        // 这里还应该过滤app_id（后续需要在拦截器里鉴权时加上ThreadLocal传递过来）
        // 同应用下的地址路径不允许重复
        List<TApiInfo> apiInfoList = this.apiInfoMapper.selectList(Wrappers.<TApiInfo>lambdaQuery()
                .eq(TApiInfo::getUrl, url.trim()));
        if (CollectionUtils.isEmpty(apiInfoList)) {
            throw new CoreException(CoreErrorCode.API_URL_PATH_IS_NOT_EXIST);
        } else {
            if (apiInfoList.size() > 1) {
                throw new CoreException(CoreErrorCode.API_URL_PATH_ARE_DUPLICATES);
            }
            return apiInfoList.get(0);
        }
    }


    public static void main(String[] args) {
        String url = "/api/rest/jsjsjs/isisina/sssss";
        url = url.replaceAll(".*" + GlobalConstant.URL_PREFIX, "");

        System.out.println(url);
    }
}
