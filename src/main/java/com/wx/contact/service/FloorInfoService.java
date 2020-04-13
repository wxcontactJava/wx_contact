package com.wx.contact.service;

import com.wx.contact.domain.project.FloorInfo;
import com.wx.contact.domain.project.Project;
import com.wx.contact.reporsitory.project.FloorInfoRepository;
import com.wx.contact.reporsitory.project.ProjectRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: 获取楼层 楼号、小区房号信息 业务层
 * @Email: honghyuan@163.com
 * @Author: Hong Hao Yuan
 * @Date : 2020/4/5 11:47
 */
@Service
@Slf4j
public class FloorInfoService {
    @Autowired
    private FloorInfoRepository floorInfoRepository;
    @Autowired
    private ProjectRepository projectRepository;

    public List<Project> getProjectByLikeName(String name){
        return projectRepository.findByNameIsLike(name);
    }

    /**
     * 根据pid和层级获取楼层信息
     */
    public List<FloorInfo> getFloorInfoByPid(String pid, Byte level){
        return floorInfoRepository.findBypIdAndLevel(pid,level);
    }
}
