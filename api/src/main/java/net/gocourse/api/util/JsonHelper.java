package net.gocourse.api.util;

import org.json.JSONException;
import org.json.JSONObject;

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
}
