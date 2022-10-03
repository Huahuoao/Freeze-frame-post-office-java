package com.huahuo.app.service;

import com.huahuo.app.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
* @author Administrator
* @description 针对表【user】的数据库操作Service
* @createDate 2022-10-03 23:21:50
*/
public interface UserService extends IService<User> {
    public Map<String,Object> login(User user);
}
