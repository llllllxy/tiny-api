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
 * @TableName t_api_info
 */
@Getter
@Setter
@Table("t_api_info")
public class TApiInfo implements Serializable {
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
    @Column(value = "tenant_id")
    private Long tenantId;

    /**
     * 应用ID
     */
    @Column(value = "app_id")
    private Long appId;

    /**
     * 接口名称
     */
    @Column(value = "api_name")
    private String apiName;

    /**
     * 接口类型（0--sql,1--mock,2--groovy）
     */
    @Column(value = "api_type")
    private Integer apiType;

    /**
     * 结果类型（0--列表,1--对象,2--值）
     */
    @Column(value = "result_type")
    private Integer resultType;

    /**
     * 请求方式（GET,HEAD,POST,PUT, PATCH, DELETE,OPTIONS,TRACE）
     */
    @Column(value = "method")
    private String method;

    /**
     * mock数据
     */
    @Column(value = "mock_data")
    private String mockData;

    /**
     * sql脚本
     */
    @Column(value = "sql_script")
    private String sqlScript;

    /**
     * groovy脚本
     */
    @Column(value = "groovy_script")
    private String groovyScript;

    /**
     * URL
     */
    @Column(value = "url")
    private String url;

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

    /**
     * 备注
     */
    @Column(value = "remark")
    private String remark;

    /**
     * 是否分页（0--关闭,1--开启分页）
     */
    @Column(value = "paging")
    private Integer paging;

    /**
     * 数据源ID
     */
    @Column(value = "datasource_id")
    private Long datasourceId;

    /**
     * 发布时间
     */
    @Column(value = "release_time")
    private LocalDateTime releaseTime;

    /**
     * API状态：0-新增、1-发布、2-销毁
     */
    @Column(value = "api_status")
    private Integer apiStatus;
}
