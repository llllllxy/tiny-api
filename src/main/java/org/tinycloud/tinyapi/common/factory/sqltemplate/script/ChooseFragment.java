package org.tinycloud.tinyapi.common.factory.sqltemplate.script;

import java.util.List;

import org.tinycloud.tinyapi.common.factory.sqltemplate.Context;


/**
 * <p>
 *  处理<choose></choose>
 * </p>
 *
 * @author liuxingyu01
 * @since 2024-09-11 10:42
 */
public class ChooseFragment implements SqlFragment {

	private SqlFragment defaultSqlFragment;
	private List<SqlFragment> ifSqlFragments;

	public ChooseFragment(List<SqlFragment> ifSqlFragments, SqlFragment defaultSqlFragment) {
		this.ifSqlFragments = ifSqlFragments;
		this.defaultSqlFragment = defaultSqlFragment;
	}

	@Override
	public boolean apply(Context context) {
		for (SqlFragment sqlNode : ifSqlFragments) {
			if (sqlNode.apply(context)) {
				return true;
			}
		}
		if (defaultSqlFragment != null) {
			defaultSqlFragment.apply(context);
			return true;
		}
		return false;
	}

}
