package org.tinycloud.tinyapi.common.factory.sqlformat;

/**
 * 转换器
 */
public class DefaultParamGhost implements ParamGhost {

    @Override
    public String recover(Object value) {
        // 如果是数值类型，则原样替换
        if (Number.class.isAssignableFrom(value.getClass())) {
            return String.valueOf(value);
        }
        // 如果是字符类型，则两端加上单引号之后替换
        return " \'" + value.toString() + "\' ";
    }

}
