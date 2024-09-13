package org.tinycloud.tinyapi.modules.bean.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * 数据源表
 *
 * @TableName t_datasource
 */
@Getter
@Setter
@TableName("t_datasource")
public class TDatasource implements Serializable {

    /**
     * 业务主键
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 租户ID
     */
    @TableField(value = "tenant_id")
    private Long tenantId;

    /**
     * 数据源地址
     */
    @TableField(value = "datasource_url")
    private String datasourceUrl;

    /**
     * 数据源用户名
     */
    @TableField(value = "datasource_username")
    private String datasourceUsername;

    /**
     * 数据源密码
     */
    @TableField(value = "datasource_password")
    private String datasourcePassword;

    /**
     * 数据源驱动
     */
    @TableField(value = "datasource_driver")
    private String datasourceDriver;

    /**
     * 数据库类型，10 mysql, 11 postgre
     */
    @TableField(value = "datasource_type")
    private Integer datasourceType;

    /**
     * 数据源名称
     */
    @TableField(value = "datasource_name")
    private String datasourceName;

    /**
     * 密码解密密钥（sm2私钥，用于解密密码）
     */
    @TableField(value = "secret_key")
    private String secretKey;

    /**
     * 状态标志（0--启用1--禁用）
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 删除标志（0--未删除1--已删除）
     */
    @TableField(value = "del_flag")
    private Integer delFlag;

    /**
     * 创建时间
     */
    @TableField(value = "created_at")
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    @TableField(value = "updated_at")
    private LocalDateTime updatedAt;
}
