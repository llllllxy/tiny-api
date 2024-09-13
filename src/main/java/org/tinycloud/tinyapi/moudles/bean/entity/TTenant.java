package org.tinycloud.tinyapi.moudles.bean.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

import java.time.LocalDateTime;


/**
* 
* @TableName t_tenant
*/
@Getter
@Setter
@TableName("t_tenant")
public class TTenant implements Serializable {
    private static final long serialVersionUID = -1L;

    /**
    * 业务主键
    */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
    * 租户账号
    */
    @TableField("tenant_account")
    private String tenantAccount;

    /**
    * 租户密码
    */
    @TableField("tenant_password")
    private String tenantPassword;

    /**
    * 租户名称
    */
    @TableField("tenant_name")
    private String tenantName;

    /**
    * 租户电话
    */
    @TableField("tenant_phone")
    private String tenantPhone;

    /**
    * 租户邮箱
    */
    @TableField("tenant_email")
    private String tenantEmail;

    /**
    * 状态标志（0--启用1--禁用）
    */
    @TableField("status")
    private Integer status;

    /**
    * 删除标志（0--未删除1--已删除）
    */
    @TableField("del_flag")
    private Integer delFlag;

    /**
    * 创建时间
    */
    @TableField("created_at")
    private LocalDateTime createdAt;

    /**
    * 注册邀请码（8位随机字符）
    */
    @TableField("invitation_code")
    private String invitationCode;

    /**
    * 租户头像
    */
    @TableField("tenant_avatar")
    private Long tenantAvatar;

    /**
    * 更新时间
    */
    @TableField("updated_at")
    private LocalDateTime updatedAt;
}
