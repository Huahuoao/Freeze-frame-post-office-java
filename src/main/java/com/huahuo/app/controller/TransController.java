package com.huahuo.app.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huahuo.app.api.TransApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Map;

/**
 * @作者 花火
 * @创建日期 2022/10/11 0:39
 */
@RestController
@RequestMapping("/trans")
public class TransController {
    @Autowired
    TransApi transApi;
    @GetMapping("/wyw")
    public String transwyw(String text)
    {

        String transResult = transApi.getTransResult(text, "auto", "wyw");
        com.alibaba.fastjson.JSONObject jsonObject = com.alibaba.fastjson.JSONObject.parseObject(transResult);
        String trans_result = jsonObject.getString("trans_result");
        ArrayList<Map<String,String>> list = JSON.parseObject(trans_result,ArrayList.class);
        return  list.get(0).get("dst");
    }
}
