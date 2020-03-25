package com.wx.contact.reporsitory.deptMapper;

import com.wx.contact.domain.dept.ContactDept;
import com.wx.contact.domain.user.ContactUser;
import com.wx.contact.view.dept.DeptVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface ContactDeptMapper {
    /**
     * 获取部门集合
     * @param hashMap
     * @return
     */
    List<DeptVo> getDeptList(HashMap hashMap);
}
