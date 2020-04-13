package com.wx.contact.domain.project;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Description: 楼层信息bean
 * @Email: honghyuan@163.com
 * @Author: Hong Hao Yuan
 * @Date : 2020/4/1 23:27
 */
@Entity
@Table(name = "floor_info" )
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FloorInfo {

    @Id
    private String id;
    private String name;
    private Byte level;
    @Column(name = "p_id")
    private String pId;
    private String projectId;

}
