package com.huahuo.app.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 
 * @TableName user
 */
@TableName(value ="user")
@Data
public class User implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "single_img_id")
    private String singleImgId;
    /**
     * 用户账号
     */
    @TableField(value = "username")
    private String username;

    /**
     * 密码
     */
    @TableField(value = "password")
    private String password;

    /**
     * 性别

     */
    @TableField(value = "sex")
    private Integer sex;

    /**
     * 配对图片的url
     */
    @TableField(value = "single_img_id_url")
    private String singleImgIdUrl;
    /**
     * 特征一
     */
    @TableField(value = "first_character")
    private Integer firstCharacter;

    /**
     * 特征二
     */
    @TableField(value = "second_character")
    private Integer secondCharacter;

    /**
     * 特征三
     */
    @TableField(value = "third_character")
    private Integer thirdCharacter;

    /**
     * 特征四
     */
    @TableField(value = "forth_character")
    private Integer forthCharacter;

    /**
     * 星座
     */
    @TableField(value = "constellation")
    private String constellation;

    /**
     * 用户等级
     */
    @TableField(value = "level")
    private Integer level;

    /**
     * 邮票数量
     */
    @TableField(value = "stamp_num")
    private Integer stampNum;

    /**
     * 心理年龄
     */
    @TableField(value = "psychological_age")
    private Integer psychologicalAge;

    /**
     * 发件数量
     */
    @TableField(value = "send_msg_num")
    private Integer sendMsgNum;

    /**
     * 收件数量

     */
    @TableField(value = "get_msg_num")
    private Integer getMsgNum;

    /**
     * 头像图片url
     */
    @TableField(value = "head_img")
    private String headImg;

    /**
     * 个性签名
     */
    @TableField(value = "signature")
    private String signature;

    /**
     * 地址
     */
    @TableField(value = "address")
    private String address;

    /**
     * 昵称
     */
    @TableField(value = "nick_name")
    private String nickName;

    /**
     * 金币数量
     */
    @TableField(value = "coin_num")
    private Integer coinNum;

    /**
     * 年龄
     */
    @TableField(value = "age")
    private Integer age;

    /**
     * 创建时间
     */
    @TableField(value = "crete_time")
    private LocalDateTime creteTime;

    /**
     * 修改时间
     */
    @TableField(value = "update_time")
    private LocalDateTime updateTime;

    /**
     * 邮票包id
     */
    @TableField(value = "stamp_bag_id")
    private Integer stampBagId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;


}