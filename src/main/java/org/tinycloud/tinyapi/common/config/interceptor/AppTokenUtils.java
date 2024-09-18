package org.tinycloud.tinyapi.common.config.interceptor;

import org.tinycloud.tinyapi.common.constant.GlobalConstant;
import org.tinycloud.tinyapi.common.utils.web.ServletUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * </p>
 *
 * @author liuxingyu01
 * @since 2024-09-18 16:17
 */
public class AppTokenUtils {
    /**
     * 获取App会话token
     *
     * @param request HttpServletRequest
     * @return String
     */
    public static String getToken(HttpServletRequest request) {
        String token = "";
        if (request != null) {
            token = request.getHeader(GlobalConstant.APP_TOKEN_KEY);
        }
        return token;
    }

    /**
     * 获取App会话token
     *
     * @return String
     */
    public static String getToken() {
        return getToken(ServletUtils.getRequest());
    }
}
