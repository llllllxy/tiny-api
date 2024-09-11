package org.tinycloud.tinyapi.common.factory.sqlformat;

import org.tinycloud.tinyapi.common.factory.sqltemplate.exception.SqlTemplateException;

import java.io.Serial;

/**
 * <p>
 *  sql编译错误
 * </p>
 *
 * @author liuxingyu01
 * @since 2024-09-11 10:42
 */
public class CompileException extends SqlTemplateException {
	@Serial
	private static final long serialVersionUID = 1L;

	public CompileException(String msg){
		this(null, msg);
	}
	
	public CompileException(Exception source, String msg){
		super(msg, source);
	}
}
