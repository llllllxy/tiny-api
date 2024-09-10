package org.tinycloud.tinyapi.common.factory.sqltemplate.script;

import org.tinycloud.tinyapi.common.factory.sqltemplate.Context;
import org.tinycloud.tinyapi.common.factory.sqltemplate.token.GenericTokenParser;
import org.tinycloud.tinyapi.common.factory.sqltemplate.token.TokenHandler;

public class TextFragment implements SqlFragment {

	private String sql;

	public TextFragment(String sql) {
		this.sql = sql;
	}

	@Override
	public boolean apply(final Context context) {

		GenericTokenParser parser2 = new GenericTokenParser("${", "}",
				new TokenHandler() {
					@Override
					public String handleToken(String content) {

						Object value = OgnlCache.getValue(content,
								context.getBinding());

						return value == null ? "" : value.toString();
					}
				});

		context.appendSql(parser2.parse(sql));
		return true;
	}

}
