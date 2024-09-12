package org.tinycloud.tinyapi.common.factory.sqltemplate.script;

import java.io.StringReader;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import ognl.ExpressionSyntaxException;
import ognl.Node;
import ognl.Ognl;
import ognl.OgnlException;
import ognl.OgnlParser;
import ognl.ParseException;
import ognl.TokenMgrError;

/**
 * <p>
 *  Ognl缓存工具类
 * </p>
 *
 * @author liuxingyu01
 * @since 2024-09-11 10:42
 */
public class OgnlCache {
	private static final Map<String, Node> expressionCache = new ConcurrentHashMap<>();

	public static Object getValue(String expression, Object root) {
		try {
			return Ognl.getValue(parseExpression(expression), root);
		} catch (OgnlException e) {
			throw new RuntimeException("Error evaluating expression '" + expression + "'. Cause: " + e, e);
		}
	}

	private static Object parseExpression(String expression) throws OgnlException {
		try {
			Node node = expressionCache.get(expression);
			if (node == null) {
				node = new OgnlParser(new StringReader(expression)).topLevelExpression();
				expressionCache.put(expression, node);
			}
			return node;
		} catch (ParseException | TokenMgrError e) {
			throw new ExpressionSyntaxException(expression, e);
		}
    }
}
