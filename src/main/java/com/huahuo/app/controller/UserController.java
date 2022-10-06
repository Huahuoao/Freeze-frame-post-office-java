package com.huahuo.app.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.huahuo.app.common.R;
import com.huahuo.app.mapper.UserMapper;
import com.huahuo.app.pojo.User;
import com.huahuo.app.service.UserService;
import com.huahuo.app.service.impl.BaiduService;
import com.huahuo.app.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.huahuo.app.utils.ConstellationUtil.constellationScoreMap;

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
    BaiduService baiduService;

    @PostMapping("/has")
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
        return R.success(map.get("user"));   // 返回一个admin
    }

    @PostMapping("/match")
    public User matchUserToUser(@RequestBody User user) {
        String url = user.getSingleImgIdUrl();
        ArrayList<HashMap<String, String>> list = baiduService.similarSearch(url);
        ArrayList<Map<String, Object>> result = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            HashMap<String, String> temp = list.get(i);
            String id = temp.get("id");
            String va = temp.get("value");
            Double value = Double.valueOf(va);
            String cons = user.getConstellation();
            User userCmp = userMapper.selectBySingleId(id);
            String consCmp = userCmp.getConstellation();
            String key = cons + "-" + consCmp;
            Double num = constellationScoreMap.get(key) * 0.01;
            double random = Math.random() * 0.1;
            Double finalNum = num + random + value;
            Map<String, Object> map = new HashMap<>();
            map.put("singleid",id);
            map.put("finalnum",finalNum);
            result.add(map);
        }
        String id = null;
        for (int i = 0; i < 3; i++) {
            Map<String,Object> stringDoubleMap = result.get(i);

            Double max = -1.0;
       if(  (Double)stringDoubleMap.get("finalnum") > max)
       {
           max = (Double)stringDoubleMap.get("finalnum");
           id  = (String)stringDoubleMap.get("singleid");
           System.out.println(stringDoubleMap);
       }
        }
        return userService.getById(id);
    }
}
