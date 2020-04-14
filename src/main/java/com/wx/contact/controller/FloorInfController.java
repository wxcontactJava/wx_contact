package com.wx.contact.controller;

import com.wx.contact.domain.result.ResultModel;
import com.wx.contact.service.FloorInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 获取楼层 楼号、小区房号信息
 * @Email: honghyuan@163.com
 * @Author: Hong Hao Yuan
 * @Date : 2020/4/5 11:45
 */
@RestController
@RequestMapping(value = "/api/floor")
public class FloorInfController {

    @Autowired
    private FloorInfoService floorInfoService;
    /**
     * 获取所有小区
     */
    @GetMapping("/getAllProject")
    public ResultModel getAllProject() {
        return ResultModel.success(floorInfoService.getAllProject());
    }

    /**
     * 根据项目id（或者pid）和level获取楼层信息
     */
    @GetMapping("/getFloorInfoByPidAndLevel")
    public ResultModel getFloorInfoByPidAndLevel(@RequestParam("pid") String pid, @RequestParam("level") Byte level) {
        return ResultModel.success(floorInfoService.getFloorInfoByPid(pid, level));
    }

}
