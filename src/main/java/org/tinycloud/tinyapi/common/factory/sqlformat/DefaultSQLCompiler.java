package org.tinycloud.tinyapi.common.factory.sqlformat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DefaultSQLCompiler implements SQLCompiler {

    private ParamGhost ghost;

    @Override
    public List<Object> compile(List<String> contexts, List<Object> values) throws CompileException {
        if (contexts == null) {
            return Collections.emptyList();
        }
        if (contexts.size() != values.size()) {
            throw new CompileException("contexts的长度与values的长度不一致");
        }
        List<Object> results = new ArrayList<>();
        for (int i = 0; i < contexts.size(); i++) {
            results.add(replaceValue(contexts.get(i), values.get(i)));
        }
        return results;
    }

    private Object replaceValue(String old, Object value) {
        return old.replace("?", ghost.recover(value));
    }

    @Override
    public void setParamGhost(ParamGhost ghost) {
        this.ghost = ghost;
    }

}
