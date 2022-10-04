package com.huahuo.app.service;

import com.baidu.aip.imagesearch.AipImageSearch;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * @作者 花火
 * @创建日期 2022/10/4 11:03
 */
@Service
public class BaiduService {
    public String similarAdd(AipImageSearch client,String url) {
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        String brief = "{\"name\":\"周杰伦\", \"id\":\"666\"}";
        options.put("brief","{\"name\":\"周杰伦\", \"id\":\"666\"}");
        options.put("method","post");
        options.put("Content-Type","application/x-www-form-urlencoded");
        // 相同图检索—入库, 图片参数为远程url图片
        JSONObject res = client.similarAddUrl(url,brief, options);
       return res.toString(2);

    }
    public String similarSearch(AipImageSearch client,String url) {
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
//        options.put("tags", "100,11");
//        options.put("tag_logic", "0");
//        options.put("pn", "100");
//        options.put("rn", "250");


       JSONObject res = client.similarSearchUrl(url, options);
          return res.toString(2);

    }


}
