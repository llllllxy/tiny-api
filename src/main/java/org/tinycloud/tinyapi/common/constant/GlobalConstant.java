package org.tinycloud.tinyapi.common.constant;

/**
 * <p>
 *  全局常量类
 * </p>
 *
 * @author liuxingyu01
 * @since 2023-03-07 15:47:38
 */
public class GlobalConstant {

    /**
     * 10分钟时间常量
     */
    public static final Long MILLIS_MINUTE_TEN = 10 * 60 * 1000L;

    /**
     * 已删除标记
     */
    public static final Integer DELETED = 1;

    /**
     * 未删除标记
     */
    public static final Integer NOT_DELETED = 0;

    /**
     * 正常在用
     */
    public static final Integer ENABLED = 0;

    /**
     * 已停用
     */
    public static final Integer DISABLED = 1;

    /**
     * 限流 redis key
     */
    public static final String LIMIT_REDIS_KEY = "tinyapi:limit:";

    /**
     * 租户图形验证码 redis key
     */
    public static final String TENANT_CAPTCHA_CODE_REDIS_KEY = "tinyapi:tenant:captcha:";

    /**
     * 租户注册邮箱验证码 redis key
     */
    public static final String TENANT_EMAIL_CODE_REDIS_KEY = "tinyapi:tenant:email:";

    /**
     * 租户token redis key
     */
    public static final String TENANT_TOKEN_REDIS_KEY = "tinyapi:tenant:token:";

    /**
     * 租户token key
     */
    public static final String TENANT_TOKEN_KEY = "token";

    /**
     * 应用token key
     */
    public static final String APP_TOKEN_KEY = "app_token";

    /**
     * 应用 authCode 缓存 redis key
     */
    public static final String APP_API_AUTHCODE_REDIS_KEY = "tinyapi:app:authcode:";

    /**
     * 租户RESTFUL token 缓存 redis key
     */
    public static final String APP_API_TOKEN_REDIS_KEY = "tinyurl:app:token:";
}
