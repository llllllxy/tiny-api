package org.tinycloud.tinyapi.modules.bean.entity;

import lombok.Getter;
import lombok.Setter;
import org.tinycloud.jdbc.annotation.Id;
import org.tinycloud.jdbc.annotation.IdType;
import org.tinycloud.jdbc.annotation.Table;
import org.tinycloud.jdbc.annotation.Column;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * 系统邮箱配置表
 *
 * @TableName t_mail_config
 */
@Getter
@Setter
@Table("t_mail_config")
public class TMailConfig implements Serializable {
    private static final long serialVersionUID = -1L;

    /**
     * 自增主键
     */
    @Id(idType = IdType.ASSIGN_ID)
    @Column(value = "id")
    private Long id;

    /**
     * smtp服务器地址
     */
    @Column(value = "smtp_address")
    private String smtpAddress;

    /**
     * smtp端口
     */
    @Column(value = "smtp_port")
    private String smtpPort;

    /**
     * 邮箱账号
     */
    @Column(value = "email_account")
    private String emailAccount;

    /**
     * 邮箱密码
     */
    @Column(value = "email_password")
    private String emailPassword;

    /**
     * 默认收件邮箱地址(多个用逗号隔开)
     */
    @Column(value = "receive_email")
    private String receiveEmail;

    /**
     * 删除标志（0--未删除1--已删除）
     */
    @Column(value = "del_flag")
    private Integer delFlag;

    /**
     * 备注描述信息
     */
    @Column(value = "remark")
    private String remark;

    /**
     * 创建时间
     */
    @Column(value = "created_at")
    private LocalDateTime createdAt;

    /**
     * 创建时间
     */
    @Column(value = "updated_at")
    private LocalDateTime updatedAt;

    /**
     * 创建人-对应t_user.id
     */
    @Column(value = "created_by")
    private Long createdBy;

    /**
     * 更新人-对应t_user.id
     */
    @Column(value = "updated_by")
    private Long updatedBy;
}
