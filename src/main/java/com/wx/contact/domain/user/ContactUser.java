package com.wx.contact.domain.user;

import lombok.Data;

@Data
public class ContactUser {
    private String id;
    private String name;
    private String gender;
    private String phone;
    private String password;
    private String deptId;
    private String subjectId;
    private String communityName;
    private String flowNum;
    private String unitNum;
    private String roomNum;
    private String photo;
    private String wxChat;
    private String openId;

}
