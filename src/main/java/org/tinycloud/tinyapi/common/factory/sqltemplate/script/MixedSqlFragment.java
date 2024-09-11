package org.tinycloud.tinyapi.common.factory.sqltemplate.script;

import java.util.List;

import org.tinycloud.tinyapi.common.factory.sqltemplate.Context;

/**
 * <p>
 *
 * </p>
 *
 * @author liuxingyu01
 * @since 2024-09-11 10:42
 */
public class MixedSqlFragment implements SqlFragment {
	
	private List<SqlFragment> contents ;
	
	public MixedSqlFragment(List<SqlFragment> contents){
		this.contents  = contents ;
	}

	@Override
	public boolean apply(Context context) {
		for(SqlFragment sf : contents){
			sf.apply(context);
		}
		return true;
	}
}
