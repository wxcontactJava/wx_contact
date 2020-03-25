package com.wx.contact.reporsitory.userMapper;

import com.wx.contact.domain.user.ContactUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;

@Mapper
public interface UserMapper {
    /**
     * 登录
     * @param hashMap
     * @return
     */
    ContactUser login(HashMap hashMap);

    /**
     * 注册
     * @param contactUser
     * @return
     */
    int register(ContactUser contactUser);

    /**
     * 根据手机号查询
     * @param phone
     * @return
     */
    ContactUser getByPhone(@Param("phone") String phone);

}
