package com.huahuo.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huahuo.app.mapper.UserMapper;
import com.huahuo.app.po.User;
import com.huahuo.app.service.UserService;
import com.huahuo.app.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.huahuo.app.utils.ConstellationUtil.constellationScoreMap;

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
    @Autowired
    BaiduServiceImpl baiduServiceImpl;

    @Override
    public Map<String, Object> login(User user) {
        User one = userMapper.selectByUserName(user.getUsername());
        Map<String, Object> map = new HashMap<>();
        if (one == null) {
            map.put("user", null);
            map.put("token", "username_error");
            return map;
        } else {
            String password = user.getPassword();
            if (one.getPassword().equals(password)) {
                String token = JWTUtils.createToken(one.getUsername().toString());
                map.put("user", one);
                map.put("token", token);
                return map;
            } else {
                map.put("user", null);
                map.put("token", "password_error");
                return map;
            }
        }

    }

    @Override
    public User matchUser(User user) {
        String url = user.getSingleImgIdUrl();
        ArrayList<HashMap<String, String>> list = baiduServiceImpl.similarSearch(url);
        ArrayList<Map<String, Object>> result = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            HashMap<String, String> temp = list.get(i);
            if (temp.get("id").equals(user.getSingleImgId())) continue;
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
            map.put("singleid", id);
            map.put("finalnum", finalNum);
            result.add(map);
        }

        String id = null;
        Double max = -0.1;
        for (int i = 0; i < 5; i++) {
            Map<String, Object> map = new HashMap<>();
            map = result.get(i);
            Double douNumber = Double.parseDouble(map.get("finalnum").toString());
            if (douNumber > max) {
                max = douNumber;
                id = map.get("singleid").toString();
            }
        }

        return userMapper.selectBySingleId(id);
    }
}





