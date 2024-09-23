package org.tinycloud.tinyapi.modules.bean.enums;

/**
 * <p>
 *     应用授权方式枚举
 * </p>
 *
 * @author liuxingyu01
 * @since 2024-09-22 21:23
 */
public enum AppAuthType {
    SIMPLE(0, "简单认证"),
    SIGNATURE(1, "签名认证"),
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

    private AppAuthType(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private AppAuthType() {
    }
}
