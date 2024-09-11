package org.tinycloud.tinyapi.common.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  定义默认内置参数
 * </p>
 *
 * @author liuxingyu01
 * @since 2024-09-11 10:42
 */
public class BuiltInVariable {
    private static final Logger logger = LoggerFactory.getLogger(BuiltInVariable.class);

    public static Map<String, String> getBuiltInVariable() {
        Map<String, String> variables = new HashMap<>();
        try {
            variables.put("BUILTIN_USER_ID", "admin");
        } catch (Exception e) {
            variables.put("BUILTIN_USER_ID", "test");
            logger.error("初始化内置变量出错", e);
        }
        variables.put("BUILTIN_NOW_DATE_YYYYMMDD", LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
        variables.put("BUILTIN_NOW_DATE_YYYYMM", LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMM")));
        variables.put("BUILTIN_NOW_DATE_YYYY-MM-DD", LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        variables.put("BUILTIN_NOW_DATE_YYYY-MM", LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM")));
        variables.put("BUILTIN_NOW_DATE_YYYY", LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy")));
        // variables.put("BUILTIN_SAME_DATE_YYYYMMDD", DateUtils.getSameDate(DateUtils.getToday()));
        // variables.put("BUILTIN_SAME_DATE_YYYYMM", DateUtils.getSameDate(DateUtils.getCurMonth()));
        // variables.put("BUILTIN_SAME_DATE_YYYY", DateUtils.getSameDate(DateUtils.getYear()));

        return variables;
    }
}
