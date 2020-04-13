package com.wx.contact.utils;

import java.util.Random;

/**
 * @Description: 生成唯一主键
 * @Email: honghyuan@163.com
 * @Author: Hong Hao Yuan
 * @Date : 2020/4/6 11:48
 */
public class UniqueKeyUtil {
    /**
     * 生成唯一主键id
     */
    public static synchronized String generateId() {
        Random random = new Random();
        Integer number = random.nextInt(90000) + 10000;
        return System.currentTimeMillis() + String.valueOf(number);
    }
}
