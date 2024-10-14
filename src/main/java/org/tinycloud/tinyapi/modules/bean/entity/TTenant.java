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
* 
* @TableName t_tenant
*/
@Getter
@Setter
@Table("t_tenant")
public class TTenant implements Serializable {
    private static final long serialVersionUID = -1L;

    /**
    * 业务主键
    */
    @Id(idType = IdType.ASSIGN_ID)
    @Column(value = "id")
    private Long id;

    /**
    * 租户账号
    */
    @Column("tenant_account")
    private String tenantAccount;

    /**
    * 租户密码
    */
    @Column("tenant_password")
    private String tenantPassword;

    /**
    * 租户名称
    */
    @Column("tenant_name")
    private String tenantName;

    /**
    * 租户电话
    */
    @Column("tenant_phone")
    private String tenantPhone;

    /**
    * 租户邮箱
    */
    @Column("tenant_email")
    private String tenantEmail;

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
    * 注册邀请码（8位随机字符）
    */
    @Column("invitation_code")
    private String invitationCode;

    /**
    * 租户头像
    */
    @Column("tenant_avatar")
    private Long tenantAvatar;

    /**
    * 更新时间
    */
    @Column("updated_at")
    private LocalDateTime updatedAt;
}
