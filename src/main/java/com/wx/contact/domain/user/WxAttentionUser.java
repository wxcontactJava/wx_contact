package com.wx.contact.domain.user;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @Description: 小程序订阅实体类
 * @Email: honghyuan@163.com
 * @Author: Hong Hao Yuan
 * @Date : 2020/4/6 11:31
 */
@Data
@Entity
@Table(name = "wx_attention_user" )
@DynamicInsert
@DynamicUpdate
public class WxAttentionUser {
    /**
     * 主键ID
     */
    @Id
    private String id;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 公众号主ID
     */
    private String wxMainId;

    /**
     * 用户OpenID
     */
    private String openId;

    /**
     * 用户唯一标识
     */
    private String unionId;

    /**
     * 用户实际客户编号
     */
    private String projectId;

    /**
     * 微信昵称
     */
    private String nickName;

    /**
     * 性别1男2女
     */
    private Byte gender;

    /**
     * 语言
     */
    private String language;

    /**
     *城市
     */
    private String city;

    /**
     * 省份
     */
    private String province;

    /**
     * 国家
     */
    private String country;

    /**
     * 头像url
     */
    private String avatarUrl;

    /**
     * 关注状态
     */
    private Byte subscribeStatus;

    /**
     * 关注时间
     */
    private Date subscribeTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     *
     */
    private String remark;

    /**
     * 备用字段
     */
    private String remark1;

    /**
     * 备用字段
     */
    private String remark2;

    /**
     *
     */
    private String groupId;
}
