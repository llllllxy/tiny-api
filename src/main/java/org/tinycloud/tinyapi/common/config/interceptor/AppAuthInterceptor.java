package org.tinycloud.tinyapi.common.config.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.tinycloud.tinyapi.common.config.ApplicationConfig;
import org.tinycloud.tinyapi.common.constant.GlobalConstant;
import org.tinycloud.tinyapi.common.enums.TenantErrorCode;
import org.tinycloud.tinyapi.common.exception.TenantException;
import org.tinycloud.tinyapi.common.utils.JacksonUtils;
import org.tinycloud.tinyapi.common.utils.StrUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;
import java.util.concurrent.TimeUnit;


/**
 * <p>
 * </p>
 *
 * @author liuxingyu01
 * @since 2024-09-16 22:03
 */
@Slf4j
@Component
public class AppAuthInterceptor implements HandlerInterceptor {


    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private ApplicationConfig applicationConfig;

    /*
     * 进入controller层之前拦截请求
     * 返回值：表示是否将当前的请求拦截下来  false：拦截请求，请求别终止。true：请求不被拦截，继续执行
     * Object obj:表示被拦的请求的目标对象（controller中方法）
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 判断请求类型，如果是OPTIONS，直接返回
        String options = HttpMethod.OPTIONS.toString();
        if (options.equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }

        // 先判断token是否为空
        String token = AppTokenUtils.getToken(request);
        if (StrUtils.isBlank(token)) {
            throw new TenantException(TenantErrorCode.APP_RESTFUL_IS_NOT_LOGIN);
        }
        // 再判断token是否存在
        String tenantInfoString = redisTemplate.opsForValue().get(GlobalConstant.APP_API_TOKEN_REDIS_KEY + token);
        if (StrUtils.isBlank(tenantInfoString)) {
            throw new TenantException(TenantErrorCode.APP_RESTFUL_IS_NOT_LOGIN);
        }

        // 再判断token是否合法
        AppAuthCache appAuthCache = JacksonUtils.readValue(tenantInfoString, AppAuthCache.class);
        if (Objects.isNull(appAuthCache)) {
            throw new TenantException(TenantErrorCode.APP_RESTFUL_IS_NOT_LOGIN);
        }
        long expireTime = appAuthCache.getLoginExpireTime();
        long currentTime = System.currentTimeMillis();
        if (expireTime - currentTime <= GlobalConstant.MILLIS_MINUTE_TEN) {
            // 刷新会话缓存时长
            appAuthCache.setLoginExpireTime(currentTime + applicationConfig.getApiAuthTimeout() * 1000);
            redisTemplate.opsForValue().set(GlobalConstant.TENANT_TOKEN_REDIS_KEY + token, JacksonUtils.toJsonString(appAuthCache), applicationConfig.getApiAuthTimeout(), TimeUnit.SECONDS);
        }
        AppAuthHolder.setAppAuthCache(appAuthCache);

        // 合格不需要拦截，放行
        return true;
    }

    /*
     * 处理请求完成后视图渲染之前的处理操作
     * 通过ModelAndView参数改变显示的视图，或发往视图的方法
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
    }

    /*
     * 视图渲染之后的操作
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) throws Exception {
        TenantHolder.clearTenant();
    }
}
