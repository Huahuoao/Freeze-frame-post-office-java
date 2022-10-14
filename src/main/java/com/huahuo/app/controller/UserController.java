package com.huahuo.app.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.huahuo.app.common.R;
import com.huahuo.app.mapper.UserMapper;
import com.huahuo.app.po.User;
import com.huahuo.app.service.UserService;
import com.huahuo.app.service.impl.BaiduServiceImpl;
import com.huahuo.app.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.text.NumberFormat;
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
    @Autowired
    UserMapper userMapper;
    @Autowired
    BaiduServiceImpl baiduServiceImpl;

    @GetMapping("/has")
    public boolean exists(String username) {
        User user = userMapper.selectByUserName(username);
        if (user == null) return false;
        return true;
    }

    @PostMapping("/login")
    public R<Object> login(@RequestBody User user, HttpServletResponse response) {
        String username = user.getUsername();
        String psw = user.getPassword();
        if (userMapper.selectByUserName(username) == null) {
            return R.error("用户名不存在！");
        } else {
            LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(User::getUsername, username);
            User one = userService.getOne(queryWrapper);
            if (!one.getPassword().equals(psw)) {
                return R.error("密码错误！");
            }
        }
        Map<String, Object> map = userService.login(user);
        //将token存入Http的header中
        if (map.get("user") == null) {
            return R.error(map.get("token").toString());
        }
        response.setHeader(JWTUtils.USER_LOGIN_TOKEN, (String) map.get("token"));
        return R.success(map.get("user"));   // 返回一user
    }

    @PostMapping("/match")
    public R<User> matchUserToUser(@RequestBody User user) {
         String percent = null;
         NumberFormat num = NumberFormat.getPercentInstance();
         return R.success( userService.matchUser(user),"匹配对象成功！");
    }


    @PostMapping("/register")
    public R<String> register(@RequestBody User user)
    {
        userService.save(user);
        return R.success("注册成功");
    }
}

