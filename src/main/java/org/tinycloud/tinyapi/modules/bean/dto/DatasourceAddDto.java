package org.tinycloud.tinyapi.modules.bean.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Getter
@Setter
@ToString
public class DatasourceAddDto implements Serializable {

    /**
     * 数据源地址
     */
    @NotBlank(message = "数据源地址不能为空！")
    @Length(max = 255, message = "数据源地址不能超过255个字符")
    private String datasourceUrl;

    /**
     * 数据源用户名
     */
    @NotBlank(message = "数据源用户名不能为空！")
    @Length(max = 64, message = "数据源用户名不能超过64个字符")
    private String datasourceUsername;

    /**
     * 数据源密码
     */
    @NotBlank(message = "数据源密码地址不能为空！")
    @Length(max = 64, message = "数据源密码不能超过64个字符")
    private String datasourcePassword;

    /**
     * 数据库类型，10 mysql, 11 postgres
     */
    @NotNull(message = "数据库类型不能为空")
    private Integer datasourceType;

    /**
     * 数据源名称
     */
    @NotBlank(message = "数据源名称不能为空！")
    @Length(max = 64, message = "数据源名称不能超过64个字符")
    private String datasourceName;

    /**
     * 密码解密密钥（sm2私钥，hex格式，用于解密密码）
     */
    @Length(max = 255, min = 0, message = "密码解密密钥不能超过255个字符")
    private String secretKey;
}
