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

        String sql = "select\n" +
                "        id,\n" +
                "        username,\n" +
                "        log_id,\n" +
                "        session_id,\n" +
                "        login_ip,\n" +
                "        login_address,\n" +
                "        user_agent,\n" +
                "        note,\n" +
                "        status,\n" +
                "        created_at,\n" +
                "        updated_at\n" +
                "        from\n" +
                "        sys_login_log\n" +
                "        <where>\n" +
                "            <if test=\"username != null and username != ''\">\n" +
                "                and username like CONCAT('%',#{username},'%')\n" +
                "            </if>\n" +
                "            <if test=\"status != null and status != ''\">\n" +
                "                and status = #{status}\n" +
                "            </if>\n" +
                "        </where>";


        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("username", "admin");
        paramMap.put("status", 1);

        String result = SqlFactory.generateSql(sql, paramMap);

        System.out.println(result);
    }
}
