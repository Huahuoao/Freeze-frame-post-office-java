package com.huahuo.app.controller;

import com.huahuo.app.common.R;
import com.huahuo.app.pojo.User;
import com.huahuo.app.service.UserService;
import com.huahuo.app.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @作者 花火
 * @创建日期 2022/10/3 23:26
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;
    @PostMapping("/login")
    public R<Object> login(@RequestBody User user, HttpServletResponse response) {
        Map<String, Object> map = userService.login(user);

        //将token存入Http的header中
        if(map.get("user")==null)
        {
            return R.error(map.get("token").toString());
        }




        response.setHeader(JWTUtils.USER_LOGIN_TOKEN, (String) map.get("token"));
        return R.success((User)map.get("user"));   // 返回一个admin
    }
}
