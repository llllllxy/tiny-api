package org.tinycloud.tinyapi.common.enums;

public enum CoreErrorCode {
    API_ADDRESS_IS_NULL_ERROR(1001, "服务地址url不能为空！"),
    API_URL_PATH_IS_NOT_EXIST(1002, "服务地址不存在，请检查！"),
    API_URL_PATH_ARE_DUPLICATES(1003, "服务地址存在重复，请检查！"),


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
