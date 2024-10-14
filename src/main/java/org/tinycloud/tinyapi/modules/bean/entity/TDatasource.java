package org.tinycloud.tinyapi.modules.bean.entity;

import lombok.Getter;
import lombok.Setter;
import org.tinycloud.jdbc.annotation.Column;
import org.tinycloud.jdbc.annotation.Id;
import org.tinycloud.jdbc.annotation.IdType;
import org.tinycloud.jdbc.annotation.Table;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * 数据源表
 *
 * @TableName t_datasource
 */
@Getter
@Setter
@Table("t_datasource")
public class TDatasource implements Serializable {

    /**
     * 业务主键
     */
    @Id(idType = IdType.ASSIGN_ID)
    @Column(value = "id")
    private Long id;

    /**
     * 租户ID
     */
    @Column(value = "tenant_id")
    private Long tenantId;

    /**
     * 数据源地址
     */
    @Column(value = "datasource_url")
    private String datasourceUrl;

    /**
     * 数据源用户名
     */
    @Column(value = "datasource_username")
    private String datasourceUsername;

    /**
     * 数据源密码
     */
    @Column(value = "datasource_password")
    private String datasourcePassword;

    /**
     * 数据库类型，10 mysql, 11 postgresql
     */
    @Column(value = "datasource_type")
    private Integer datasourceType;

    /**
     * 数据源名称
     */
    @Column(value = "datasource_name")
    private String datasourceName;

    /**
     * 密码解密密钥（sm2私钥，用于解密密码）
     */
    @Column(value = "secret_key")
    private String secretKey;

    /**
     * 状态标志（0--启用1--禁用）
     */
    @Column(value = "status")
    private Integer status;

    /**
     * 删除标志（0--未删除1--已删除）
     */
    @Column(value = "del_flag")
    private Integer delFlag;

    /**
     * 创建时间
     */
    @Column(value = "created_at")
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    @Column(value = "updated_at")
    private LocalDateTime updatedAt;
}
