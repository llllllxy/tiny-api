package org.tinycloud.tinyapi.modules.bean.enums;

/**
 * <p>
 *     ip策略类型枚举
 * </p>
 *
 * @author liuxingyu01
 * @since 2024-09-18 18:55
 */
public enum IpStrategyTypeEnum {
    NOT(0, "不控制"),
    WHITE(1, "白名单"),
    BLACK(2, "黑名单"),
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

    private IpStrategyTypeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private IpStrategyTypeEnum() {
    }
}
