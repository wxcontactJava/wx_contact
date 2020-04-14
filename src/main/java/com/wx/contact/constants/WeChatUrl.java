package com.wx.contact.constants;

/**
 * @Description: 微信UR常量
 * @Email: honghyuan@163.com
 * @Author: Hong Hao Yuan
 * @Date : 2020/4/14 23:32
 */
public interface WeChatUrl {
    /**
     * 获取用户信息
     * GET
     */
    String USER_URL = "https://api.weixin.qq.com/sns/jscode2session?";
    /**
     * 获取小程序全局唯一后台接口调用凭据
     * GET
     */
    String ACCESS_TOKEN = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    /**
     * 发送消息模板
     * POST
     */
    String send_template_ = "https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token=ACCESS_TOKEN";

}
