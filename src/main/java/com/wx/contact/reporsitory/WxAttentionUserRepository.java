package com.wx.contact.reporsitory;

import com.wx.contact.domain.user.WxAttentionUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Email: honghyuan@163.com
 * @Author: Hong Hao Yuan
 * @Date : 2020/4/6 11:40
 */
public interface WxAttentionUserRepository  extends JpaRepository<WxAttentionUser, String> {
    /**
     * 根据openId查询微信订阅信息
     * @param openId
     * @return
     */
    WxAttentionUser findByOpenId(String openId);
}
