package com.wx.contact.controller;

import com.wx.contact.domain.enums.ResponsesStatus;
import com.wx.contact.domain.result.ResultModel;
import com.wx.contact.domain.user.ContactUser;
import com.wx.contact.service.user.ContactUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.ReportAsSingleViolation;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/login")
public class ContactUserController {
    @Resource
    private ContactUserService contactUserService;

    /**
     * 登录
     * @param phone
     * @param password
     * @return
     */
    @RequestMapping("/login")
    public ResultModel login(@RequestParam String phone , @RequestParam String password){
        ResultModel resultModel = new ResultModel();
        ContactUser contactUser = new ContactUser();
        contactUser = contactUserService.login(phone,password);
        if(null == contactUser.getName()){
            resultModel.setResult(ResponsesStatus.NODATA);
        }else
            resultModel.setResult(contactUser);
        return resultModel;
    }

    /**
     * 注册
     * @param contactUser
     * @return
     */
    @RequestMapping("/register")
    public ResultModel register(@RequestBody ContactUser contactUser){
        ResultModel resultModel = new ResultModel();
        ContactUser contactUser1 = contactUserService.getByPhone(contactUser.getPhone());
        if (null != contactUser1){
            resultModel.setResult(ResponsesStatus.EXIST);
            return resultModel;
        }
        int result = contactUserService.register(contactUser);
        if (result > 0){
            resultModel.setResult(ResponsesStatus.SUCCESS);
        }else
            resultModel.setResult(ResponsesStatus.INSERTERROR);
        return resultModel;
    }
}
