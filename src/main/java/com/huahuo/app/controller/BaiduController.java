package com.huahuo.app.controller;

import com.baidu.aip.imagesearch.AipImageSearch;
import com.huahuo.app.common.R;
import com.huahuo.app.service.BaiduService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

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
    public R<String> upload(@RequestParam String url)
    {
       return R.success( baiduService.similarAdd(new AipImageSearch(APP_ID, API_KEY, SECRET_KEY),url));
    }
    @PostMapping("/search")
    public String search(@RequestParam String url)
    {
        return baiduService.similarSearch(client,url);
    }
}
