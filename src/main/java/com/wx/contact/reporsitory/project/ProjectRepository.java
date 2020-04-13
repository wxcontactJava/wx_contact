package com.wx.contact.reporsitory.project;

import com.wx.contact.domain.project.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Email: honghyuan@163.com
 * @Author: Hong Hao Yuan
 * @Date : 2020/4/1 22:30
 */
public interface ProjectRepository extends JpaRepository<Project, String> {
    /**
     * 根据项目名称模糊搜索
     * @param name
     * @return
     */
    List<Project> findByNameIsLike(String name);
}
