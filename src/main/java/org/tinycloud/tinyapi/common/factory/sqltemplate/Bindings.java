package org.tinycloud.tinyapi.common.factory.sqltemplate;

import java.io.Serial;
import java.util.HashMap;

public class Bindings extends HashMap<Object, Object> {

    @Serial
    private static final long serialVersionUID = -7290846439659491933L;

    public Bindings bind(Object key, Object value) {
        this.put(key, value);
        return this;
    }

}
