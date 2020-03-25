package com.wx.contact.service.dept;

import com.wx.contact.domain.dept.ContactDept;
import com.wx.contact.domain.user.ContactUser;
import com.wx.contact.reporsitory.deptMapper.ContactDeptMapper;
import com.wx.contact.reporsitory.userMapper.UserMapper;
import com.wx.contact.view.dept.DeptVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Service
public class ContactDeptService {
    @Resource
    private ContactDeptMapper deptMapper;

    public List<DeptVo> getDeptList(){
        HashMap hashMap = new HashMap();
        return deptMapper.getDeptList(hashMap);
    }
}
