package com.wx.contact.domain.project;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Description: 项目表
 * @Email: honghyuan@163.com
 * @Author: HongHaoYuan
 * @Date : 2020/4/1 22:26
 */
@Entity
@Table(name = "project" )
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Project {
    @Id
    private String id;
    private String name;
    private String tgmc;
}
