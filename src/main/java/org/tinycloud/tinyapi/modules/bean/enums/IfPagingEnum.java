package org.tinycloud.tinyapi.modules.bean.enums;

/**
 * <p>
 * </p>
 *
 * @author liuxingyu01
 * @since 2024-09-2024/9/13 20:51
 */
public enum IfPagingEnum {
    CLOSE(0, "关闭"),
    OPEN(1, "开启"),
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

    private IfPagingEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private IfPagingEnum() {
    }
}
