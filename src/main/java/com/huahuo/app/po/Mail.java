package com.huahuo.app.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 
 * @TableName mail
 */
@TableName(value ="mail")
@Data
public class Mail implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 是否送出
     */
    @TableField(value = "is_send")
    private Integer isSend;

    /**
     * 送出的用户的id
     */
    @TableField(value = "send_user_id")
    private Integer sendUserId;

    /**
     * 收到的用户的id
     */
    @TableField(value = "get_user_id")
    private Integer getUserId;

    /**
     * 使用的邮票id
     */
    @TableField(value = "stamp_id")
    private Integer stampId;

    /**
     * 文本
     */
    @TableField(value = "msg")
    private String msg;
    @TableField(value = "wyw")
    private String wyw;
    /**
     * 创建时间
     */
    @TableField(value = "crete_time")
    private LocalDateTime creteTime;

    /**
     * 
     */
    @TableField(value = "mailbox_id")
    private Integer mailboxId;

    /**
     * 送达时间
     */
    @TableField(value = "send_time")
    private LocalDateTime sendTime;

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
        Mail other = (Mail) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getIsSend() == null ? other.getIsSend() == null : this.getIsSend().equals(other.getIsSend()))
            && (this.getSendUserId() == null ? other.getSendUserId() == null : this.getSendUserId().equals(other.getSendUserId()))
            && (this.getGetUserId() == null ? other.getGetUserId() == null : this.getGetUserId().equals(other.getGetUserId()))
            && (this.getStampId() == null ? other.getStampId() == null : this.getStampId().equals(other.getStampId()))
            && (this.getMsg() == null ? other.getMsg() == null : this.getMsg().equals(other.getMsg()))
            && (this.getCreteTime() == null ? other.getCreteTime() == null : this.getCreteTime().equals(other.getCreteTime()))
            && (this.getMailboxId() == null ? other.getMailboxId() == null : this.getMailboxId().equals(other.getMailboxId()))
            && (this.getSendTime() == null ? other.getSendTime() == null : this.getSendTime().equals(other.getSendTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getIsSend() == null) ? 0 : getIsSend().hashCode());
        result = prime * result + ((getSendUserId() == null) ? 0 : getSendUserId().hashCode());
        result = prime * result + ((getGetUserId() == null) ? 0 : getGetUserId().hashCode());
        result = prime * result + ((getStampId() == null) ? 0 : getStampId().hashCode());
        result = prime * result + ((getMsg() == null) ? 0 : getMsg().hashCode());
        result = prime * result + ((getCreteTime() == null) ? 0 : getCreteTime().hashCode());
        result = prime * result + ((getMailboxId() == null) ? 0 : getMailboxId().hashCode());
        result = prime * result + ((getSendTime() == null) ? 0 : getSendTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", isSend=").append(isSend);
        sb.append(", sendUserId=").append(sendUserId);
        sb.append(", getUserId=").append(getUserId);
        sb.append(", stampId=").append(stampId);
        sb.append(", msg=").append(msg);
        sb.append(", creteTime=").append(creteTime);
        sb.append(", mailboxId=").append(mailboxId);
        sb.append(", sendTime=").append(sendTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}