package com.wx.contact.controller;

import com.alibaba.fastjson.JSONObject;
import com.wx.contact.domain.enums.ResponsesStatus;
import com.wx.contact.domain.result.ResultModel;
import com.wx.contact.domain.user.WxAttentionUser;
import com.wx.contact.service.WeChatService;
import com.wx.contact.utils.AesUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 小程序控制管理器
 * @Email: honghyuan@163.com
 * @Author: Hong Hao Yuan
 * @Date : 2020/4/5 20:00
 */
@RestController
@RequestMapping(value = "/api/weChat")
@Slf4j
public class WeChatController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private WeChatService weChatService;
    @Value("${wx.appId}")
    private String appId;
    @Value("${wx.appSecret}")
    private String appSecret;
    @Value("${wx.grantType}")
    private String grantType;
    @Value("${wx.userUrl}")
    private String userUrl;
    /**
     * 小程序登录获取用户信息
     *
     * @param encryptedData    授权后的敏感信息（包含：用户基本信息、unionId（用户多平台下唯一标识））
     * @param iv
     * @param code             临时会话code（默认五分钟）
     * @return
     */
    @GetMapping("/getWeChatUserInfo")
    public ResultModel getWxUserInfo(@RequestParam(value = "encryptedData") String encryptedData,
                                     @RequestParam(value = "iv") String iv,
                                     @RequestParam(value = "code") String code) {
        //登录凭证不能为空
        if (StringUtils.isEmpty(code) || StringUtils.isEmpty(encryptedData) || StringUtils.isEmpty(iv)) {
            return ResultModel.error(ResponsesStatus.NODATA);
        }
        // 向微信服务器 使用登录凭证 code 获取 session_key 和 openid
        String params = "appid=" + appId + "&secret=" + appSecret + "&js_code=" + code + "&grant_type=" + grantType;
        ResponseEntity<String> sr;
        try {
            sr = restTemplate.getForEntity(userUrl + params,String.class) ;
            log.info("【请求weChat api返回数据】sr={}", sr);
        } catch (Exception e) {
            log.error("【请求weChat api出错】e={}", e);
            return ResultModel.error();
        }
        //解析相应内容（转换成json对象）
        JSONObject json = JSONObject.parseObject(sr.getBody());
        if (!StringUtils.isEmpty(json.getString("errcode"))) {
            log.error("【errcode不是合法值】", json.getInteger("errcode"));
            return ResultModel.error();
        }
        //获取会话密钥（session_key）
        String sessionKey = json.getString("session_key");
        //用户的唯一标识（openid）
        String openId = json.getString("openid");
        try {
            String result = AesUtil.decrypt(encryptedData, sessionKey, iv, "UTF-8");
            if (!StringUtils.isEmpty(result)) {
                JSONObject userInfo = JSONObject.parseObject(result);
                log.info("解密后的用户信息={}", userInfo);
                if (!StringUtils.isEmpty(openId) || !StringUtils.isEmpty(userInfo.get("unionId"))) {
                    WxAttentionUser weChatUser = weChatService.insertWeChatUser(userInfo);
                    return ResultModel.success(weChatUser);
                }
            }
        } catch (Exception e) {
            log.error("【解密失败】result={}", e);
        }
        return ResultModel.error();
    }
}
