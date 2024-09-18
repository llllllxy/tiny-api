package org.tinycloud.tinyapi.common.config.interceptor;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>
 * </p>
 *
 * @author liuxingyu01
 * @since 2024-09-16 22:05
 */
@Getter
@Setter
public class AppAuthCache  implements Serializable {
    private static final long serialVersionUID = -1L;

    /**
     * 主键id
     */
    private Long id;

    /**
     * 租户ID
     */
    private Long tenantId;

    /**
     * 应用名称
     */
    private String appName;

    /**
     * 用户唯一标识
     */
    private String appToken;

    /**
     * 登录时间
     */
    private Long loginTime;

    /**
     * 登录过期时间
     */
    private Long loginExpireTime;
}
