package org.tinycloud.tinyapi.common.config.interceptor;

import java.util.Objects;

/**
 * <p>
 * </p>
 *
 * @author liuxingyu01
 * @since 2024-09-16 22:03
 */
public class AppAuthHolder {

    private final static ThreadLocal<AppAuthCache> authCache = new ThreadLocal<>();

    public static AppAuthCache getAppAuthCache() {
        return authCache.get();
    }

    public static Long getAppId() {
        AppAuthCache cache = getAppAuthCache();
        if (Objects.isNull(cache)) {
            return null;
        } else {
            return cache.getId();
        }
    }

    public static Long getTenantId() {
        AppAuthCache cache = getAppAuthCache();
        if (Objects.isNull(cache)) {
            return null;
        } else {
            return cache.getTenantId();
        }
    }

    public static void setAppAuthCache(AppAuthCache a) {
        authCache.set(a);
    }

    public static void clearAppAuthCache() {
        authCache.remove();
    }
}
