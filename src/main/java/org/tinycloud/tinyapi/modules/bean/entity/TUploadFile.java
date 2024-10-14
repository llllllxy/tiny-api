package org.tinycloud.tinyapi.modules.bean.entity;

import lombok.Getter;
import lombok.Setter;
import org.tinycloud.jdbc.annotation.Id;
import org.tinycloud.jdbc.annotation.Column;
import org.tinycloud.jdbc.annotation.IdType;
import org.tinycloud.jdbc.annotation.Table;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * 文件表
 *
 * @TableName t_upload_file
 */
@Getter
@Setter
@Table("t_upload_file")
public class TUploadFile implements Serializable {
    private static final long serialVersionUID = -1L;

    /**
     * 主键id
     */
    @Id(idType = IdType.ASSIGN_ID)
    @Column(value = "id")
    private Long id;

    /**
     * 文件原名称
     */
    @Column("file_name_old")
    private String fileNameOld;

    /**
     * 文件新名称
     */
    @Column("file_name_new")
    private String fileNameNew;

    /**
     * 文件路径
     */
    @Column("file_path")
    private String filePath;

    /**
     * 文件大小（单位B）
     */
    @Column("file_size")
    private Long fileSize;

    /**
     * 文件大小（带单位, 单位KB）
     */
    @Column("file_size_str")
    private String fileSizeStr;

    /**
     * 文件后缀
     */
    @Column("file_suffix")
    private String fileSuffix;

    /**
     * 文件MD5
     */
    @Column("file_md5")
    private String fileMd5;

    /**
     * 文件SHA1
     */
    @Column("file_sha1")
    private String fileSha1;

    /**
     * 上传时间
     */
    @Column("created_at")
    private LocalDateTime createdAt;
}
