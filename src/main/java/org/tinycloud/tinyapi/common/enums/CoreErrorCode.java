package org.tinycloud.tinyapi.common.enums;


/**
 * <p>
 *     核心错误码枚举
 *     10**  --  29**
 * </p>
 *
 * @author liuxingyu01
 * @since 2024-09-13 22:25
 */
public enum CoreErrorCode {
    API_ADDRESS_IS_NULL_ERROR(1001, "服务地址url不能为空！"),
    API_URL_PATH_IS_NOT_EXIST(1002, "服务地址不存在，请检查！"),
    API_URL_PATH_ARE_DUPLICATES(1003, "服务地址存在重复，请检查！"),
    API_METHOD_IS_NOT_MATCHED(1004, "HTTP Method不匹配！"),
    API_METHOD_IS_NOT_SUPPORT(1005, "HTTP Method不不支持！"),

    API_TYPE_IS_NOT_SUPPORT(1006, "接口类型不不支持！"),
    THE_PAGINATION_PARAMETER_PAGENO_IS_MISSING(1007, "该服务的分页参数已开启，但是缺少pageNo分页参数！"),
    THE_PAGINATION_PARAMETER_PAGESIZE_IS_MISSING(1008, "该服务的分页参数已开启，但是缺少pageSize分页参数！"),



    FILE_UPLOAD_FAILED(2001, "文件上传失败，请联系系统管理员！"),
    FILE_NOT_EXIST(2002, "文件不存在！"),
    FILE_DOWNLOAD_FAILED(2003, "文件下载失败，请联系系统管理员！"),
    FILE_DELETE_FAILED(2004, "文件删除失败，请联系系统管理员！"),

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

    private CoreErrorCode(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private CoreErrorCode() {
    }
}
