package org.tinycloud.tinyapi.common.factory;

import org.tinycloud.tinyapi.common.factory.sqlformat.AnalyzeContext;
import org.tinycloud.tinyapi.common.factory.sqltemplate.SqlMeta;
import org.tinycloud.tinyapi.common.factory.sqltemplate.SqlTemplate;
import org.tinycloud.tinyapi.common.factory.sqltemplate.SqlTemplateEngin;

import java.util.Map;


public class SqlFactory {


    public static String generateSql(String sqlStr,Map paramMap){

        if(sqlStr == null){
            throw new RuntimeException("sql模板为空");
        }

        SqlTemplateEngin sqlTemplateEngin = new SqlTemplateEngin();
        SqlTemplate sqlTemplate = sqlTemplateEngin.getSqlTemplate(sqlStr) ;


        SqlMeta sqlMeta = sqlTemplate.process(paramMap) ;


        String result = AnalyzeContext.getContext().analyze(sqlMeta.getSql(),sqlMeta.getParameter());

        return result;
    }
}
