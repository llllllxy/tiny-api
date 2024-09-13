package org.tinycloud.tinyapi.modules.bean.enums;

/**
 * <p>
 *     定义API状态枚举
 * </p>
 *
 * @author liuxingyu01
 * @since 2024-09-2024/9/13 20:49
 */
public enum ApiStatusEnum {
    ADD(0, "新增"),
    RELEASE(1, "发布"),
    DESTROY(2, "销毁"),
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

    private ApiStatusEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private ApiStatusEnum() {
    }
}
