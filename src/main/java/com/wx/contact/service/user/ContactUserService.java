package com.wx.contact.service.user;

import com.wx.contact.domain.user.ContactUser;
import com.wx.contact.reporsitory.userMapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;

@Service
public class ContactUserService {
    @Resource
    private UserMapper userMapper;

    /**
     * 登录
     * @param phone
     * @param password
     * @return
     */
    public ContactUser login(String phone , String password){
        HashMap hashMap = new HashMap();
        hashMap.put("phone",phone);
        hashMap.put("password",password);
        return userMapper.login(hashMap);
    }

    /**
     * 根据手机号查询
     * @param phone
     * @return
     */
    public ContactUser getByPhone(String phone){
        return userMapper.getByPhone(phone);
    }

    /**
     * 注册
     * @param contactUser
     * @return
     */
    public int register(ContactUser contactUser){
        return userMapper.register(contactUser);
    }
}
