package com.wx.contact.controller;

import com.wx.contact.domain.dept.ContactDept;
import com.wx.contact.domain.enums.ResponsesStatus;
import com.wx.contact.domain.result.ResultModel;
import com.wx.contact.domain.user.ContactUser;
import com.wx.contact.service.dept.ContactDeptService;
import com.wx.contact.service.user.ContactUserService;
import com.wx.contact.view.dept.DeptVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/dept")
public class ContactDeptController {
    @Resource
    private ContactDeptService contactDeptService;
    @RequestMapping("/deptList")
    public ResultModel deptList(){
        ResultModel resultModel = new ResultModel();
        Optional<List<DeptVo>> list =
                Optional.ofNullable(contactDeptService.getDeptList());
        if (list.isPresent()){
            resultModel.setResult(list.get());
        }else
            resultModel.setResult(ResponsesStatus.NODATA);
        return resultModel;
    }
}
