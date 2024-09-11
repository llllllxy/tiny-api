package org.tinycloud.tinyapi.common.factory;

import com.google.common.collect.Lists;
import org.tinycloud.tinyapi.common.factory.sqlformat.AnalyzeContext;
import org.tinycloud.tinyapi.common.factory.sqltemplate.SqlMeta;
import org.tinycloud.tinyapi.common.factory.sqltemplate.SqlTemplate;
import org.tinycloud.tinyapi.common.factory.sqltemplate.SqlTemplateEngin;
import org.tinycloud.tinyapi.common.factory.sqltemplate.exception.SqlTemplateException;

import java.util.HashMap;
import java.util.Map;


public class SqlFactory {

    public static String generateSql(String sqlStr, Map<String, Object> paramMap) {
        SqlMeta sqlMeta = generate(sqlStr, paramMap);
        String result = AnalyzeContext.getContext().analyze(sqlMeta.getSql(), sqlMeta.getParameter());
        return result;
    }


    public static SqlMeta generate(String sqlStr, Map<String, Object> paramMap) {
        if (sqlStr == null) {
            throw new SqlTemplateException("sqlStr cannot be empty!");
        }
        SqlTemplateEngin sqlTemplateEngin = new SqlTemplateEngin();
        SqlTemplate sqlTemplate = sqlTemplateEngin.getSqlTemplate(sqlStr);
        SqlMeta sqlMeta = sqlTemplate.process(paramMap);
        return sqlMeta;
    }

    public static void main(String[] args) {

        String sql = " SELECT\n" +
                "                dates.dateStr as dateStr,\n" +
                "                COUNT(t_mock_access_log.access_time) AS accessCount\n" +
                "                FROM\n" +
                "                (\n" +
                "                <foreach collection=\"dayList\" item=\"item\" separator=\" \" index=\"idx\" open=\"\" close=\"\">\n" +
                "                    <choose>\n" +
                "                        <when test=\"idx == dayList.size() - 1\">\n" +
                "                            SELECT #{item} AS dateStr\n" +
                "                        </when>\n" +
                "                        <otherwise>\n" +
                "                            SELECT #{item} AS dateStr UNION ALL\n" +
                "                        </otherwise>\n" +
                "                    </choose>\n" +
                "                </foreach>\n" +
                "                ) AS dates\n" +
                "                LEFT JOIN t_mock_access_log ON DATE(t_mock_access_log.access_time) = dates.dateStr AND t_mock_access_log.tenant_id = #{tenantId}\n" +
                "                GROUP BY dates.dateStr\n" +
                "                ORDER BY dates.dateStr";


        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("mockName", "测试");
        paramMap.put("tenantId", 22278282828L);

        paramMap.put("dayList", Lists.newArrayList("2024-09-11", "2024-09-12", "2024-09-13", "2024-09-14", "2024-09-14", "2024-09-16", "2024-09-17"));

        String result = SqlFactory.generateSql(sql, paramMap);

        System.out.println(result);
    }
}
