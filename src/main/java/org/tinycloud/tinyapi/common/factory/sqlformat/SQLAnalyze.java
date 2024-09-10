package org.tinycloud.tinyapi.common.factory.sqlformat;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * SQL分析
 */
public class SQLAnalyze {
    private SQLCompiler compiler;
    private final static String CONTEXT_REGULAR = "(([\\s(=]|(like\\s)|(\\|\\|))\\?[\\s),])|(\\?[,)\n])";

    public String compile(String sql, List<Object> params) {
        if (compiler == null) {
            throw new NullPointerException("编译器为null");
        }
        if (sql == null || sql.trim().isEmpty()) {
            throw new NullPointerException("SQL语句为空");
        }
        sql += " ";
        Pattern contextPattern = Pattern.compile(CONTEXT_REGULAR);
        Matcher matcher = contextPattern.matcher(sql);
        List<String> contextLists = new ArrayList<>();
        List<String> restLists = new ArrayList<>();

        int start = 0, end = -1;
        while (matcher.find()) {
            contextLists.add(matcher.group(0));
            end = matcher.start();
            restLists.add(sql.substring(start, end));
            start = matcher.end();
        }
        restLists.add(sql.substring(start));

       List<Object> compileLists = compiler.compile(contextLists, params);

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < restLists.size(); i++) {
            result.append(restLists.get(i));
            if (i != restLists.size() - 1) {
                result.append(compileLists.get(i));
            }
        }
        return result.toString();
    }

    public SQLCompiler getCompiler() {
        return compiler;
    }

    public void setCompiler(SQLCompiler compiler) {
        this.compiler = compiler;
    }
}
