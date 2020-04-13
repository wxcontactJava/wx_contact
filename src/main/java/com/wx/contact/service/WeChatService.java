package com.wx.contact.service;

import com.alibaba.fastjson.JSONObject;
import com.wx.contact.domain.user.WxAttentionUser;
import com.wx.contact.reporsitory.WxAttentionUserRepository;
import com.wx.contact.utils.UniqueKeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @Description: 微信订阅（小程序、公众号、小游戏等）业务层
 * @Email: honghyuan@163.com
 * @Author: Hong Hao Yuan
 * @Date : 2020/4/6 11:38
 */
@Service
@Slf4j
public class WeChatService {
    @Autowired
    private WxAttentionUserRepository wxAttentionUserRepository;

    /**
     * 第一次进入小程序保存用户信息
     */
    public WxAttentionUser insertWeChatUser(JSONObject jsonObject) {
        WxAttentionUser weChatUser = new WxAttentionUser();
        weChatUser.setId(UniqueKeyUtil.generateId());
        weChatUser.setOpenId(jsonObject.getString("openId"));
        weChatUser.setNickName(jsonObject.getString("nickName"));
        weChatUser.setGender(jsonObject.getByteValue("gender"));
        weChatUser.setAvatarUrl(jsonObject.getString("avatarUrl"));
        weChatUser.setUnionId(jsonObject.getString("unionId"));
        weChatUser.setLanguage(jsonObject.getString("language"));
        weChatUser.setSubscribeStatus((byte) 1);
        weChatUser.setRemark("小程序");
        WxAttentionUser attentionUser = wxAttentionUserRepository.findByOpenId(weChatUser.getOpenId());
        if (attentionUser == null) {
            wxAttentionUserRepository.save(weChatUser);
        } else {
            weChatUser.setId(attentionUser.getId());
            wxAttentionUserRepository.save(weChatUser);
        }
        return weChatUser;
    }
}
