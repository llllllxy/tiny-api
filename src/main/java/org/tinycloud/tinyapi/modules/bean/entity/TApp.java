package org.tinycloud.tinyapi.modules.bean.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

/**
 * 应用表-t_app
 */
@Getter
@Setter
@TableName("t_app")
public class TApp implements Serializable {
    private static final long serialVersionUID = -1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 租户ID
     */
    @TableField("tenant_id")
    private Long tenantId;

    /**
     * 应用名称
     */
    @TableField("app_name")
    private String appName;

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
     * 更新时间
     */
    @TableField("updated_at")
    private LocalDateTime updatedAt;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;

    /**
     * 应用访问码
     */
    @TableField("app_code")
    private String appCode;

    /**
     * api访问key（签名认证时需要对用此对authCode进行签名）
     */
    @TableField("app_key")
    private String appKey;

    /**
     * 认证方式（0--不需认证1--简单认证2--签名认证）
     */
    @TableField("auth_type")
    private Integer authType;

    /**
     * 控制策略类型：（0--不控制1--白名单2-黑名单）
     */
    @TableField("ip_strategy_type")
    private Integer ipStrategyType;

    /**
     * IP地址，多个分号隔开
     */
    @TableField("ip_list")
    private String ipList;
}
