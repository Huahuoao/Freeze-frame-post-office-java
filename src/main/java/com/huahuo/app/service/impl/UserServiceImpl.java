package com.huahuo.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huahuo.app.mapper.UserMapper;
import com.huahuo.app.pojo.User;
import com.huahuo.app.service.UserService;
import com.huahuo.app.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
* @author Administrator
* @description 针对表【user】的数据库操作Service实现
* @createDate 2022-10-03 23:21:50
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService {

    @Autowired
    UserService userService;
    @Autowired
    UserMapper userMapper;
    @Override
    public Map<String, Object> login(User user) {
           User one = userMapper.selectByUserName(user.getUsername());
            Map<String, Object> map = new HashMap<>();
            if (one == null) {
                map.put("user", null);
                map.put("token", "username_error");
                return map;
            }
            else {
                String password = user.getPassword();
                if(one.getPassword().equals(password))
                {
                    String token = JWTUtils.createToken(one.getUsername().toString());
                    map.put("user", one);
                    map.put("token", token);
                    return map;}
                else
                {
                    map.put("user",null);
                    map.put("token","password_error");
                    return map;
                }
            }

        }
    }





