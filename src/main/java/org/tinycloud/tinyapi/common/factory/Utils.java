package org.tinycloud.tinyapi.common.factory;

import com.common.utils.lang.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;


public class Utils {

    private static Logger logger = LoggerFactory.getLogger(Utils.class);

    public static String convertLike(String value) {
        return "%" + value + "%";
    }

    public static String nextDay(String day,int n) {
        return DateUtils.getNextDay(day,n);
    }

    public static String nextMonth(String month,int n) {
        return DateUtils.getNextMonth(month,n);
    }

    public static String getSamePeriod(String date){
        return DateUtils.getPreYearSamePeriod(date,1);
    }

    public static String getSamePeriod(String date,int n){
        return DateUtils.getPreYearSamePeriod(date,-n);
    }

    public static String getFirstDayOfMonth(String date){
        return DateUtils.getFirstDayOfMonth(date);
    }

    public static Object objectKeyToggleCase(Object object,String caseToogle){
        Object newObject = new Object();
        if(null!=object && null!=caseToogle && !("").equals(caseToogle)){
            if(object instanceof Map){
                newObject = getMapToggleCase((Map)object,caseToogle);
            } if(object instanceof List){
                List newDataObj = new ArrayList();
                List dataList = (List)object;
                for(int i=0;i<dataList.size();i++){
                    if(null!=dataList.get(i)){
                        newDataObj.add(getMapToggleCase((Map)dataList.get(i),caseToogle));
                    }
                }

                newObject = newDataObj;
            }
        }else{
            newObject = object;
        }

        return newObject;
    }

    private static Map getMapToggleCase(Map mapData,String caseToogle){
        Map newMapData = new HashMap();

        Iterator entries = mapData.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry entry = (Map.Entry) entries.next();
            if(("lowercase").equals(caseToogle)){
                newMapData.put(((String)entry.getKey()).toLowerCase(),entry.getValue());
            }else if(("uppercase").equals(caseToogle)){
                newMapData.put(((String)entry.getKey()).toUpperCase(),entry.getValue());
            }
        }

        return newMapData;
    }
}
