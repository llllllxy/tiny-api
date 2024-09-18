package org.tinycloud.tinyapi.modules.bean.dto;

import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>
 *     rest服务-租户身份认证
 * </p>
 *
 * @author liuxingyu01
 * @since 2024-02-27 14:29
 */
@Getter
@Setter
public class AuthenticationDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "授权码不能为空")
    private String authCode;

    @NotEmpty(message = "appCode不能为空")
    private String appCode;

    @NotEmpty(message = "签名不能为空（使用sm3-hmac算法）")
    private String signature;
}
