package org.tinycloud.tinyapi.common.enums;


/**
 * <p>
 *     租户错误码枚举
 *     30**  --  99**
 * </p>
 *
 * @author liuxingyu01
 * @since 2024-09-13 22:25
 */
public enum TenantErrorCode {
    TENANT_NOT_LOGIN(3001, "会话已过期，请重新登录！"),
    TENANT_USERNAME_OR_PASSWORD_MISMATCH(3002, "用户名或密码错误！"),
    TENANT_IS_DISABLE(3003, "租户已被禁用，请联系工作人员！"),
    CAPTCHA_IS_MISMATCH(3004, "验证码错误或已过期！"),
    EMAILCODE_IS_MISMATCH(3005, "邮箱验证码错误或已过期！"),
    INVITATIONCODE_IS_NOT_EXIST(3006, "邀请码不正确或不存在！"),
    TENANT_IS_NOT_EXIST(3006, "租户不存在！"),
    TENANT_PASSWORD_IS_ENTERED_INCONSISTENTLY(3007, "新密码前后输入不一致，请检查！"),
    TENANT_OLD_PASSWORD_IS_WRONG(3008, "旧密码不正确，请检查！"),


    APP_AUTHCODE_NOT_EXIST_OR_EXPIRED(4001, "authCode不存在或已过期，请检查！"),
    APP_CODE_NOT_EXIST(4002, "APPCODE不存在，请检查！"),
    APP_CODE_IS_EXPIRED(4003, "ak已过期，请检查！"),
    APP_IS_DISABLED(4004, "应用已被禁用，请检查！"),
    APP_SIGNATURE_CANNOT_EMPTY(4005, "签名值不能为空（使用sm3-hmac算法）！"),
    APP_SIGNATURE_CHECK_FAILED(4006, "签名验签失败，请检查！"),
    APP_IP_IS_NOT_IN_WHITELIST(4007, "源IP不在白名单内，禁止访问！"),
    APP_IP_IS_IN_BLACK_LIST(4008, "源IP在黑名单内，禁止访问！"),
    APP_TOKEN_CAN_NOT_BE_NULL(4098, "app_token不能为空，禁止访问！"),
    APP_RESTFUL_IS_NOT_LOGIN(4099, "应用会话已过期，请重新登录！"),



    CONNECTION_TEST_QUERY_FAILED(5001, "数据库测试连接失败，请检查输入内容！"),


    ;

    private Integer code;
    private String desc;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    private TenantErrorCode(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private TenantErrorCode() {
    }
}
