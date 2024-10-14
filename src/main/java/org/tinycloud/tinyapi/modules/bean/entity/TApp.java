package org.tinycloud.tinyapi.modules.bean.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import org.tinycloud.jdbc.annotation.Column;
import org.tinycloud.jdbc.annotation.Id;
import org.tinycloud.jdbc.annotation.IdType;
import org.tinycloud.jdbc.annotation.Table;

/**
 * 应用表-t_app
 */
@Getter
@Setter
@Table("t_app")
public class TApp implements Serializable {
    private static final long serialVersionUID = -1L;

    /**
     * 主键id
     */
    @Id(idType = IdType.ASSIGN_ID)
    @Column(value = "id")
    private Long id;

    /**
     * 租户ID
     */
    @Column("tenant_id")
    private Long tenantId;

    /**
     * 应用名称
     */
    @Column("app_name")
    private String appName;

    /**
     * 状态标志（0--启用1--禁用）
     */
    @Column("status")
    private Integer status;

    /**
     * 删除标志（0--未删除1--已删除）
     */
    @Column("del_flag")
    private Integer delFlag;

    /**
     * 创建时间
     */
    @Column("created_at")
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    @Column("updated_at")
    private LocalDateTime updatedAt;

    /**
     * 备注
     */
    @Column("remark")
    private String remark;

    /**
     * 应用访问码
     */
    @Column("app_code")
    private String appCode;

    /**
     * api访问key（签名认证时需要对用此对authCode进行签名）
     */
    @Column("app_key")
    private String appKey;

    /**
     * 认证方式（0--不需认证1--简单认证2--签名认证）
     */
    @Column("auth_type")
    private Integer authType;

    /**
     * 控制策略类型：（0--不控制1--白名单2-黑名单）
     */
    @Column("ip_strategy_type")
    private Integer ipStrategyType;

    /**
     * IP地址，多个分号隔开
     */
    @Column("ip_list")
    private String ipList;
}
