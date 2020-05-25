package com.sxquan.core.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.*;
 
/**
 * @author: lucifer
 * @date: 2019/8/21
 * @description:
 */
public class StringJsonUtils {
 
    public static Map<String, Object> jsonStringToMap(String jsonString) {
        Map<String, Object> map = new HashMap<>();
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        for (Object k : jsonObject.keySet()) {
            Object o = jsonObject.get(k);
            if (o instanceof JSONArray) {
                List<Map<String, Object>> list = new ArrayList<>();
                Iterator<Object> it = ((JSONArray) o).iterator();
                while (it.hasNext()) {
                    Object obj = it.next();
                    list.add(jsonStringToMap(obj.toString()));
                }
                map.put(k.toString(), list);
            } else if (o instanceof JSONObject) {
                // 如果内层是json对象的话，继续解析
                map.put(k.toString(), jsonStringToMap(o.toString()));
            } else {
                // 如果内层是普通对象的话，直接放入map中
               // map.put(k.toString(), o.toString().trim()); 
                 if (o instanceof String) {
                   map.put(k.toString(), o.toString().trim());
                } else {
                    map.put(k.toString(), o);
                }   
            }
        }
        return map;
    }
 
}