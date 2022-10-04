package com.huahuo.app.controller;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.baidu.aip.imagesearch.AipImageSearch;
import com.huahuo.app.common.R;
import com.huahuo.app.service.BaiduService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.stream.Record;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @作者 花火
 * @创建日期 2022/10/4 10:59
 */
@RestController
@RequestMapping("/baidu")
public class BaiduController {
    @Autowired
    BaiduService baiduService;
    //设置APPID/AK/SK
    public static final String APP_ID ="27724400";
    public static final String API_KEY ="Oumu51qBYNYBOhoelLn5h3OH";
    public static final String SECRET_KEY ="KBKRvMa23mbXPzEHAGqfoGVOWjTOveOw";

    private AipImageSearch client = new AipImageSearch(APP_ID, API_KEY, SECRET_KEY);
    @PostMapping("/upload")
    public void upload(@RequestParam String url)
    {
        String text =  baiduService.similarAdd(client,url);
        System.out.println(text);
    }
    @PostMapping("/search")
    public void search(@RequestParam String url)
    {

        String text = baiduService.similarSearch(client,url);
        JSONArray jsonArray = JSON.parseObject(text).getJSONArray("result");
        //填充初始数据，此处过程省略
        List<JSONObject> jsonObjectList = jsonArray.toJavaList(JSONObject.class);
        Map<String, String> map = jsonObjectList.stream().filter(Objects::nonNull)
                .collect(Collectors.toMap(item -> item.getString("score"), item -> item.getString("cont_sign")));
        System.out.println(map);
        int i=3;
        for(Map.Entry<String, String> entry : map.entrySet()){
            if(i==0) break;
            i--;
            String mapKey = entry.getKey();
            String mapValue = entry.getValue();
            System.out.println(mapKey+":"+mapValue);
        }

//        JSONObject jsonObject= (JSONObject) JSON.parse("result");
//        System.out.println(jsonObject);
    }
}
