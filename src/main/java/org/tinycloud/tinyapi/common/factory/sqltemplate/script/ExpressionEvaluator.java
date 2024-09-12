package org.tinycloud.tinyapi.common.factory.sqltemplate.script;

import org.tinycloud.tinyapi.common.factory.sqltemplate.exception.SqlTemplateException;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  处理if和choose的test内容判断的
 * </p>
 *
 * @author liuxingyu01
 * @since 2024-09-11 10:42
 */
public class ExpressionEvaluator {

    public boolean evaluateBoolean(String expression, Object parameterObject) {
        Object value = OgnlCache.getValue(expression, parameterObject);
        if (value instanceof Boolean) {
            return (Boolean) value;
        }
        if (value instanceof Number) {
            return !new BigDecimal(String.valueOf(value)).equals(BigDecimal.ZERO);
        }
        return value != null;
    }

    public Iterable<?> evaluateIterable(String expression, Object parameterObject) {
        Object value = OgnlCache.getValue(expression, parameterObject);
        if (value == null) {
            throw new SqlTemplateException("The expression '" + expression + "' evaluated to a null value.");
        }
        if (value instanceof Iterable) {
            return (Iterable<?>) value;
        }
        if (value.getClass().isArray()) {
            // the array may be primitive, so Arrays.asList() may throw
            // a ClassCastException (issue 209). Do the work manually
            // Curse primitives! :) (JGB)
            int size = Array.getLength(value);
            List<Object> answer = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                Object o = Array.get(value, i);
                answer.add(o);
            }
            return answer;
        }
        if (value instanceof Map) {
            return ((Map) value).entrySet();
        }
        if (value instanceof String) {
            List<Object> answer = new ArrayList<>();
            String[] arr = ((String) value).split(",");
            for (int i = 0; i < arr.length; i++) {
                answer.add(arr[i]);
            }
            return answer;
        }
        throw new SqlTemplateException("Error evaluating expression '" + expression + "'.  Return value (" + value + ") was not iterable.");
    }

}
