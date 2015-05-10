package net.gocourse.api.util;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by 徐灿 on 2015/5/4
 * Contact 384454501@qq.com
 * Introduce json处理类
 */
public class JsonHelper {
    //jsonStr转成Map
    public static Map<String,Object> jsonStrToMap(String jsonStr) throws JSONException {
        //生成JSONObject
        JSONObject jsonObject = new JSONObject(jsonStr);
        //解析后的键值对
        Map result = new HashMap();
        //对JSONObject遍历提取数据
        Iterator iterator = jsonObject.keys();
        String key = null;
        Object value = null;
        while (iterator.hasNext()) {
            key = (String) iterator.next();
            value = jsonObject.opt(key);
            result.put(key, value);
        }
        return result;
    }

    /**
     *
     * @param javabean
     * @param data
     * @return
     */
    public static Object mapToBean(Object javabean, Map data) {
        Method[] methods = javabean.getClass().getDeclaredMethods();
        for (Method method : methods) {
            try {
                if (method.getName().startsWith("set")) {
                    String field = method.getName();
                    field = field.substring(field.indexOf("set") + 3);
                    field = field.toLowerCase().charAt(0) + field.substring(1);
                    //判断参数类型
                    if(method.getParameterTypes()[0].getName().equals("int")){
                        method.invoke(javabean,Integer.valueOf((String)data.get(field)));
                    }
                    else if(method.getParameterTypes()[0].getName().equals(String.class.getName())){
                        method.invoke(javabean, (String)(data.get(field)));
                    }
                    else if(method.getParameterTypes()[0].getName().equals("boolean")){
                        method.invoke(javabean, (boolean)(data.get(field)));
                    }
                    else if(method.getParameterTypes()[0].getName().equals(JSONObject.class.getName())){
                        method.invoke(javabean, (JSONObject)(data.get(field)));
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return javabean;
    }

    /**
     *
     * @param javabean
     * @param jsonStr
     * @return
     * @throws JSONException
     */
    public static Object jsonStrToBean(Object javabean,String jsonStr) throws JSONException{
        return mapToBean(javabean,jsonStrToMap(jsonStr));
    }

}
