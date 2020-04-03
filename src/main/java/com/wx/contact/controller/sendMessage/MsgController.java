package com.wx.contact.controller.sendMessage;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaSubscribeData;
import cn.binarywang.wx.miniapp.bean.WxMaSubscribeMessage;
import com.wx.contact.sendMessage.WxMaConfiguration;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * @ClaaaName MsgController
 * @Description TODO
 * @Author Liuhy
 * @Date 2020/4/215:25
 * @Version 1.0
 */
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/wx/msg")
public class MsgController {

    /**
     * 根据code获取openid
     * create By KingYiFan 2020/01/06
     */

    @GetMapping("/getOpenidByCode")
    public String login(@RequestParam String appId, @RequestParam String code) {
        if (StringUtils.isBlank(code)) {
            return "code都没有给俺，你哪啥换俺的openid啊？？？？？";
        }
        try {
            final WxMaService wxService = WxMaConfiguration.getMaService(appId);
            //根据code获取openid
            WxMaJscode2SessionResult session = wxService.getUserService().getSessionInfo(code);
            return session.getOpenid();
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        return "我猜你code肯定失效了........要不就是过期了呢.......您觉得呢？？？";
    }

    /**
     * 微信小程序推送订阅消息
     * create By KingYiFan on 2020/01/06
     */
    @GetMapping( "/sendDYTemplateMessage")
    public Object sendDYTemplateMessage(@RequestParam String appId, @RequestParam String openId) throws Exception {

        WxMaSubscribeMessage subscribeMessage = new WxMaSubscribeMessage();

        //跳转小程序页面路径
        subscribeMessage.setPage("pages/index/index");
        //模板消息id
        subscribeMessage.setTemplateId("ooaZWfK6liHpqDAcnR2hgObdQuh2JqQP2Z_UR6vvraU");
        //给谁推送 用户的openid （可以调用根据code换openid接口)
        subscribeMessage.setToUser(openId);
        //==========================================创建一个参数集合========================================================
        ArrayList<WxMaSubscribeData> wxMaSubscribeData = new ArrayList<>();

//        订阅消息参数值内容限制说明
//              ---摘自微信小程序官方：https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/subscribe-message/subscribeMessage.send.html
//        参数类别 	参数说明 	参数值限制 	   说明
//        thing.DATA 	事物 	20个以内字符 	可汉字、数字、字母或符号组合
//        number.DATA 	数字 	32位以内数字 	只能数字，可带小数
//        letter.DATA 	字母 	32位以内字母 	只能字母
//        symbol.DATA 	符号 	5位以内符号 	只能符号
//        character_string.DATA 	字符串 	32位以内数字、字母或符号 	可数字、字母或符号组合
//        time.DATA 	时间 	24小时制时间格式（支持+年月日） 	例如：15:01，或：2019年10月1日 15:01
//        date.DATA 	日期 	年月日格式（支持+24小时制时间） 	例如：2019年10月1日，或：2019年10月1日 15:01
//        amount.DATA 	金额 	1个币种符号+10位以内纯数字，可带小数，结尾可带“元” 	可带小数
//        phone_number.DATA 	电话 	17位以内，数字、符号 	电话号码，例：+86-0766-66888866
//        car_number.DATA 	车牌 	8位以内，第一位与最后一位可为汉字，其余为字母或数字 	车牌号码：粤A8Z888挂
//        name.DATA 	姓名 	10个以内纯汉字或20个以内纯字母或符号 	中文名10个汉字内；纯英文名20个字母内；中文和字母混合按中文名算，10个字内
//        phrase.DATA 	汉字 	5个以内汉字 	5个以内纯汉字，例如：配送中

        //第一个内容： 奖品名称
        WxMaSubscribeData wxMaSubscribeData1 = new WxMaSubscribeData();
        wxMaSubscribeData1.setName("thing1");
        wxMaSubscribeData1.setValue("充气娃娃豪华版");
        //每个参数 存放到大集合中
        wxMaSubscribeData.add(wxMaSubscribeData1);

        // 第二个内容：用户昵称
        WxMaSubscribeData wxMaSubscribeData2 = new WxMaSubscribeData();
        wxMaSubscribeData2.setName("name6");
        wxMaSubscribeData2.setValue("非苍老鸡不娶");
        wxMaSubscribeData.add(wxMaSubscribeData2);

        // 第三个内容：领取方式
        WxMaSubscribeData wxMaSubscribeData3 = new WxMaSubscribeData();
        wxMaSubscribeData3.setName("thing7");
        wxMaSubscribeData3.setValue("请联系您的专属老鸡");
        wxMaSubscribeData.add(wxMaSubscribeData3);

        // 第四个内容：专属老师
        WxMaSubscribeData wxMaSubscribeData4 = new WxMaSubscribeData();
        wxMaSubscribeData4.setName("name3");
        wxMaSubscribeData4.setValue("小泽玛利亚老鸡");
        wxMaSubscribeData.add(wxMaSubscribeData4);

        // 第五个内容：温馨提醒
        WxMaSubscribeData wxMaSubscribeData5 = new WxMaSubscribeData();
        wxMaSubscribeData5.setName("thing4");
        wxMaSubscribeData5.setValue("小撸伤身，强撸灰飞烟灭~");
        wxMaSubscribeData.add(wxMaSubscribeData5);

        //把集合给大的data
        subscribeMessage.setData(wxMaSubscribeData);
        //=========================================封装参数集合完毕========================================================

        try {
            //获取微信小程序配置：
            final WxMaService wxService = WxMaConfiguration.getMaService(appId);
            //进行推送
            wxService.getMsgService().sendSubscribeMsg(subscribeMessage);
            return "推送成功";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "推送失败";
    }
}
