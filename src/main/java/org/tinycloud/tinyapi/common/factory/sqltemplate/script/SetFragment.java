package org.tinycloud.tinyapi.common.factory.sqltemplate.script;

import java.util.Arrays;
import java.util.List;


public class SetFragment extends TrimFragment {

	private static final List<String> suffixList = Arrays.asList(",");

	public SetFragment(SqlFragment contents) {
		super(contents, "SET", null, null, suffixList);
	}

}
