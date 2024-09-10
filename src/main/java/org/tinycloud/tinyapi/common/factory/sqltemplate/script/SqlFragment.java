package org.tinycloud.tinyapi.common.factory.sqltemplate.script;

import org.tinycloud.tinyapi.common.factory.sqltemplate.Context;



public interface SqlFragment {
	boolean apply(Context context) ;

}
