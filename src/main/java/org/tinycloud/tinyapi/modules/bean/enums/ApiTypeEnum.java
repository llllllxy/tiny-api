package org.tinycloud.tinyapi.modules.bean.enums;

/**
 * <p>
 * 接口类型枚举
 * </p>
 *
 * @author liuxingyu01
 * @since 2024-09-14 14:06
 */
public enum ApiTypeEnum {
    SQL(0, "sql"),
    MOCK(1, "mock"),
    GROOVY(2, "groovy");

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

    ApiTypeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
