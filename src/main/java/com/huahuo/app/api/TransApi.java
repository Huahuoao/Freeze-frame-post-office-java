package com.huahuo.app.api;

import com.huahuo.app.utils.HttpGet;
import com.huahuo.app.utils.MD5;
import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.Map;

@Service
public class TransApi {
    private static final String TRANS_API_HOST = "http://api.fanyi.baidu.com/api/trans/vip/translate";

    private String appid = "20221011001383914";
    private String securityKey = "RxT9a6moOjZpBxHsFTl4";

    public TransApi() {
    }

    public String getTransResult(String query, String from, String to) {
        Map<String, String> params = buildParams(query, from, to);
        return HttpGet.get(TRANS_API_HOST, params);
    }

    private Map<String, String> buildParams(String query, String from, String to) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("q", query);
        params.put("from", from);
        params.put("to", to);

        params.put("appid", appid);

        // 随机数
        String salt = String.valueOf(System.currentTimeMillis());
        params.put("salt", salt);

        // 签名
        String src = appid + query + salt + securityKey; // 加密前的原文
        params.put("sign", MD5.md5(src));

        return params;
    }

}
