package org.tinycloud.tinyapi.modules.bean.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * 文件表
 *
 * @TableName t_upload_file
 */
@Getter
@Setter
@TableName("t_upload_file")
public class TUploadFile implements Serializable {
    private static final long serialVersionUID = -1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 文件原名称
     */
    @TableField("file_name_old")
    private String fileNameOld;

    /**
     * 文件新名称
     */
    @TableField("file_name_new")
    private String fileNameNew;

    /**
     * 文件路径
     */
    @TableField("file_path")
    private String filePath;

    /**
     * 文件大小（单位B）
     */
    @TableField("file_size")
    private Long fileSize;

    /**
     * 文件大小（带单位, 单位KB）
     */
    @TableField("file_size_str")
    private String fileSizeStr;

    /**
     * 文件后缀
     */
    @TableField("file_suffix")
    private String fileSuffix;

    /**
     * 文件MD5
     */
    @TableField("file_md5")
    private String fileMd5;

    /**
     * 文件SHA1
     */
    @TableField("file_sha1")
    private String fileSha1;

    /**
     * 上传时间
     */
    @TableField("created_at")
    private LocalDateTime createdAt;
}
