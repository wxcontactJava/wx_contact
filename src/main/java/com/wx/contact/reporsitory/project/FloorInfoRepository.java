package com.wx.contact.reporsitory.project;

import com.wx.contact.domain.project.FloorInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Email: honghyuan@163.com
 * @Author: Hong Hao Yuan
 * @Date : 2020/4/1 23:29
 */
public interface FloorInfoRepository extends JpaRepository<FloorInfo, String> {
    List<FloorInfo> findByLevelEquals(Byte level);
}
