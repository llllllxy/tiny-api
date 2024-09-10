package org.tinycloud.tinyapi.common.factory;

import org.tinycloud.tinyapi.common.factory.sqlformat.AnalyzeContext;
import org.tinycloud.tinyapi.common.factory.sqltemplate.SqlMeta;
import org.tinycloud.tinyapi.common.factory.sqltemplate.SqlTemplate;
import org.tinycloud.tinyapi.common.factory.sqltemplate.SqlTemplateEngin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SqlFactory {

    public static String generateSql(String sqlStr, Map<String, Object> paramMap) {
        if (sqlStr == null) {
            throw new RuntimeException("sql模板为空");
        }
        SqlTemplateEngin sqlTemplateEngin = new SqlTemplateEngin();
        SqlTemplate sqlTemplate = sqlTemplateEngin.getSqlTemplate(sqlStr);

        SqlMeta sqlMeta = sqlTemplate.process(paramMap);

        String result = AnalyzeContext.getContext().analyze(sqlMeta.getSql(), sqlMeta.getParameter());
        return result;
    }

    public static void main(String[] args) {

        String sql = """
                select datasource_id, db_type, db_driver, db_url, db_user, db_pwd
                        from sys_datasync_datasource
                        where datasource_id = #{_parameter} and line_no = #{lineNo} and hhh in
                <foreach item="item" index="index" collection="idList" open = "(" separator = "," close = ")">#{item}</foreach>
                """;
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("_parameter", "1");
        paramMap.put("lineNo", 222);

        paramMap.put("idList", List.of(1, 2, 3));

        String result = SqlFactory.generateSql(sql, paramMap);

        System.out.println(result);
    }
}
