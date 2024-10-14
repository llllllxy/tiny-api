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
 * 反馈建议表
 *
 * @TableName t_feedback
 */
@Getter
@Setter
@Table("t_feedback")
public class TFeedback implements Serializable {
    private static final long serialVersionUID = -1L;

    /**
     * 主键id
     */
    @Id(idType = IdType.ASSIGN_ID)
    @Column(value = "id")
    private Long id;

    /**
     * 租户id
     */
    @Column("tenant_id")
    private Long tenantId;

    /**
     * 反馈类型，0建议，1问题
     */
    @Column("feed_type")
    private Integer feedType;

    /**
     * 内容
     */
    @Column("content")
    private String content;

    /**
     * 邮箱
     */
    @Column("email")
    private String email;

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
}
