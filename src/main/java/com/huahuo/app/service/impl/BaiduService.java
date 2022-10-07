package com.huahuo.app.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baidu.aip.imagesearch.AipImageSearch;
import com.huahuo.app.utils.JSONUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @作者 花火
 * @创建日期 2022/10/4 11:03
 */

/**
 * 返回id
 */
@Service
public class BaiduService {
    public static final String APP_ID ="27724400";
    public static final String API_KEY ="Oumu51qBYNYBOhoelLn5h3OH";
    public static final String SECRET_KEY ="KBKRvMa23mbXPzEHAGqfoGVOWjTOveOw";

    private AipImageSearch client = new AipImageSearch(APP_ID, API_KEY, SECRET_KEY);
    public String similarAdd(String url) {
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        String brief = "default";
        options.put("brief","default");
        options.put("method","post");
        options.put("Content-Type","application/x-www-form-urlencoded");
        // 相同图检索—入库, 图片参数为远程url图片
        JSONObject res = client.similarAddUrl(url,brief, options);
        HashMap<String, String> hashMap = JSONUtils.JsonObjectToHashMap(res);
        String cont_sign = hashMap.get("cont_sign");
        return  cont_sign;

    }

    /**
     * 返回一个list(3)
     * list里面是个map
     * map是   ”id“：
     *        ”value“：
     * @param url
     * @return
     */
    public ArrayList<HashMap<String,String>> similarSearch(String url) {
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
//        options.put("tags", "100,11");
//        options.put("tag_logic", "0");
//        options.put("pn", "100");
//        options.put("rn", "250");
        ArrayList<HashMap<String,String>> arrayList = new ArrayList<>();
        JSONObject res = client.similarSearchUrl(url, options);
        String text=res.toString(2);
        JSONArray jsonArray = JSON.parseObject(text).getJSONArray("result");
        //填充初始数据，此处过程省略
        List<com.alibaba.fastjson.JSONObject> jsonObjectList = jsonArray.toJavaList(com.alibaba.fastjson.JSONObject.class);
        Map<String, String> map = jsonObjectList.stream().filter(Objects::nonNull)
                .collect(Collectors.toMap(item -> item.getString("score"), item -> item.getString("cont_sign")));
        int i=6;
        for(Map.Entry<String, String> entry : map.entrySet()){
            if(i==0) break;
            i--;
            String mapKey = entry.getKey();
            String mapValue = entry.getValue();
            HashMap<String,String> temp = new HashMap<>();
            temp.put("id",mapValue);
            temp.put("value",mapKey);
            arrayList.add(temp);
        }
        return arrayList;
    }


}