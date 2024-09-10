package org.tinycloud.tinyapi.common.factory.sqlformat;

import java.io.Serial;

/**
 * 编译错误
 */
public class CompileException extends RuntimeException {
	@Serial
	private static final long serialVersionUID = 1L;

	public CompileException(String msg){
		this(null, msg);
	}
	
	public CompileException(Exception source, String msg){
		super(msg, source);
	}
}
