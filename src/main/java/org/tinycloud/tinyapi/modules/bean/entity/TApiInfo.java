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
 * @TableName t_api_info
 */
@Getter
@Setter
@TableName("t_api_info")
public class TApiInfo implements Serializable {
    private static final long serialVersionUID = -1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 租户ID
     */
    @TableField(value = "tenant_id")
    private Long tenantId;

    /**
     * 应用ID
     */
    @TableField(value = "app_id")
    private Long appId;

    /**
     * 接口名称
     */
    @TableField(value = "api_name")
    private String apiName;

    /**
     * 接口类型（0--sql,1--mock,2--groovy）
     */
    @TableField(value = "api_type")
    private Integer apiType;

    /**
     * 结果类型（0--列表,1--对象,2--值）
     */
    @TableField(value = "result_type")
    private Integer resultType;

    /**
     * 请求方式（GET,HEAD,POST,PUT, PATCH, DELETE,OPTIONS,TRACE）
     */
    @TableField(value = "method")
    private String method;

    /**
     * mock数据
     */
    @TableField(value = "mock_data")
    private String mockData;

    /**
     * sql脚本
     */
    @TableField(value = "sql_script")
    private String sqlScript;

    /**
     * groovy脚本
     */
    @TableField(value = "groovy_script")
    private String groovyScript;

    /**
     * URL
     */
    @TableField(value = "url")
    private String url;

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

    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;

    /**
     * 是否分页（0--关闭,1--开启分页）
     */
    @TableField(value = "paging")
    private Integer paging;

    /**
     * 数据源ID
     */
    @TableField(value = "datasource_id")
    private Long datasourceId;

    /**
     * 发布时间
     */
    @TableField(value = "release_time")
    private LocalDateTime releaseTime;

    /**
     * API状态：0-新增、1-发布、2-销毁
     */
    @TableField(value = "api_status")
    private Integer apiStatus;
}
