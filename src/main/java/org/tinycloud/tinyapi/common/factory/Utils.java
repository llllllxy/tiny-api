package org.tinycloud.tinyapi.common.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;


public class Utils {
    private static final Logger logger = LoggerFactory.getLogger(Utils.class);


    public static Object objectKeyToggleCase(Object object, String caseToogle) {
        Object newObject = new Object();
        if (null != object && null != caseToogle && !("").equals(caseToogle)) {
            if (object instanceof Map) {
                newObject = getMapToggleCase((Map) object, caseToogle);
            }
            if (object instanceof List) {
                List newDataObj = new ArrayList();
                List dataList = (List) object;
                for (int i = 0; i < dataList.size(); i++) {
                    if (null != dataList.get(i)) {
                        newDataObj.add(getMapToggleCase((Map) dataList.get(i), caseToogle));
                    }
                }

                newObject = newDataObj;
            }
        } else {
            newObject = object;
        }

        return newObject;
    }

    private static Map getMapToggleCase(Map mapData, String caseToogle) {
        Map newMapData = new HashMap();

        Iterator entries = mapData.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry entry = (Map.Entry) entries.next();
            if (("lowercase").equals(caseToogle)) {
                newMapData.put(((String) entry.getKey()).toLowerCase(), entry.getValue());
            } else if (("uppercase").equals(caseToogle)) {
                newMapData.put(((String) entry.getKey()).toUpperCase(), entry.getValue());
            }
        }

        return newMapData;
    }
}
