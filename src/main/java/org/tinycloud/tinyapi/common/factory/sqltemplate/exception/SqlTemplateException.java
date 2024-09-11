package org.tinycloud.tinyapi.common.factory.sqltemplate.exception;

import java.io.Serial;

/**
 * <p>
 * 自定义sql模板异常类
 * </p>
 *
 * @author liuxingyu01
 * @since 2024-09-11 10:42
 */
public class SqlTemplateException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = -1L;

    public SqlTemplateException(String msg) {
        super(msg);
    }

    public SqlTemplateException(Throwable cause) {
        super(cause);
    }

    public SqlTemplateException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
