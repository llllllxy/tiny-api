package org.tinycloud.tinyapi.modules.bean.enums;

/**
 * <p>
 * 结果类型枚举
 * </p>
 *
 * @author liuxingyu01
 * @since 2024-09-14 15:13
 */
public enum ResultTypeEnum {
    LIST(0, "列表"),
    OBJECT(1, "对象"),
    VALUE(2, "值");

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

    ResultTypeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
