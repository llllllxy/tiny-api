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
 * 反馈建议表
 *
 * @TableName t_feedback
 */
@Getter
@Setter
@TableName("t_feedback")
public class TFeedback implements Serializable {
    private static final long serialVersionUID = -1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 租户id
     */
    @TableField("tenant_id")
    private Long tenantId;

    /**
     * 反馈类型，0建议，1问题
     */
    @TableField("feed_type")
    private Integer feedType;

    /**
     * 内容
     */
    @TableField("content")
    private String content;

    /**
     * 邮箱
     */
    @TableField("email")
    private String email;

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
}
