package com.huahuo.app.mapper;

import com.huahuo.app.po.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
* @author Administrator
* @description 针对表【user】的数据库操作Mapper
* @createDate 2022-10-03 23:21:50
* @Entity com.huahuo.pojo.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("Select * from user where username = #{userName}")
    User selectByUserName(String userName);

    @Select("select * from user where single_img_id = #{singleId}")
    User selectBySingleId(String singleId);
}




