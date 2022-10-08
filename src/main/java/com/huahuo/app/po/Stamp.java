package com.huahuo.app.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 邮票
 * @TableName stamp
 */
@TableName(value ="stamp")
@Data
public class Stamp implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 图片
     */
    @TableField(value = "img")
    private String img;

    /**
     * 等级
     */
    @TableField(value = "level")
    private Integer level;

    /**
     * 邮包id
     */
    @TableField(value = "stamp_bag_id")
    private Integer stampBagId;

    /**
     * 特性
     */
    @TableField(value = "character")
    private Integer character;

    /**
     * 信息
     */
    @TableField(value = "msg")
    private String msg;

    /**
     * 自定义签名
     */
    @TableField(value = "signature")
    private String signature;

    /**
     * 是否删除
     */
    @TableField(value = "is_delete")
    private Integer isDelete;

    /**
     * 磨损度
     */
    @TableField(value = "life")
    private Double life;

    /**
     * 名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 价值
     */
    @TableField(value = "value")
    private Integer value;

    /**
     * 用户id
     */
    @TableField(value = "user_id")
    private Integer userId;

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

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Stamp other = (Stamp) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getImg() == null ? other.getImg() == null : this.getImg().equals(other.getImg()))
            && (this.getLevel() == null ? other.getLevel() == null : this.getLevel().equals(other.getLevel()))
            && (this.getStampBagId() == null ? other.getStampBagId() == null : this.getStampBagId().equals(other.getStampBagId()))
            && (this.getCharacter() == null ? other.getCharacter() == null : this.getCharacter().equals(other.getCharacter()))
            && (this.getMsg() == null ? other.getMsg() == null : this.getMsg().equals(other.getMsg()))
            && (this.getSignature() == null ? other.getSignature() == null : this.getSignature().equals(other.getSignature()))
            && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()))
            && (this.getLife() == null ? other.getLife() == null : this.getLife().equals(other.getLife()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getValue() == null ? other.getValue() == null : this.getValue().equals(other.getValue()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getCreteTime() == null ? other.getCreteTime() == null : this.getCreteTime().equals(other.getCreteTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getImg() == null) ? 0 : getImg().hashCode());
        result = prime * result + ((getLevel() == null) ? 0 : getLevel().hashCode());
        result = prime * result + ((getStampBagId() == null) ? 0 : getStampBagId().hashCode());
        result = prime * result + ((getCharacter() == null) ? 0 : getCharacter().hashCode());
        result = prime * result + ((getMsg() == null) ? 0 : getMsg().hashCode());
        result = prime * result + ((getSignature() == null) ? 0 : getSignature().hashCode());
        result = prime * result + ((getIsDelete() == null) ? 0 : getIsDelete().hashCode());
        result = prime * result + ((getLife() == null) ? 0 : getLife().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getValue() == null) ? 0 : getValue().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getCreteTime() == null) ? 0 : getCreteTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", img=").append(img);
        sb.append(", level=").append(level);
        sb.append(", stampBagId=").append(stampBagId);
        sb.append(", character=").append(character);
        sb.append(", msg=").append(msg);
        sb.append(", signature=").append(signature);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", life=").append(life);
        sb.append(", name=").append(name);
        sb.append(", value=").append(value);
        sb.append(", userId=").append(userId);
        sb.append(", creteTime=").append(creteTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}