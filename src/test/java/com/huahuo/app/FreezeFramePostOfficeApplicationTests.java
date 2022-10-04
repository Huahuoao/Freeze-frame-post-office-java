package com.huahuo.app;

import com.baidu.aip.imagesearch.AipImageSearch;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

@SpringBootTest
class FreezeFramePostOfficeApplicationTests {

    @Test
    void contextLoads() {
    }
    public static final String APP_ID ="27724400";
    public static final String API_KEY ="Oumu51qBYNYBOhoelLn5h3OH";
    public static final String SECRET_KEY ="KBKRvMa23mbXPzEHAGqfoGVOWjTOveOw";
    @Test

    public void Sample() {
        //设置APPID/AK/SK
            // 初始化一个AipImageSearch
            AipImageSearch client = new AipImageSearch(APP_ID, API_KEY, SECRET_KEY);

            // 可选：设置网络连接参数
            client.setConnectionTimeoutInMillis(2000);
            client.setSocketTimeoutInMillis(60000);

        HashMap<String, String> options = new HashMap<String, String>();
        options.put("tags", "100,11");

        String brief = "{\"name\":\"周杰伦\", \"id\":\"666\"}";

            // 调用接口
            String image = "G:\\IdeaProjects\\FreezeFramePostOffice\\src\\main\\resources\\imgs\\123.jpg";
           JSONObject res = client.similarAdd(image, brief, options);
            System.out.println(res.toString(2));

        }
    }

