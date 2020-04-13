package com.wx.contact.redis.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Description: redis 配置
 * @Email: honghyuan@163.com
 * @Author: 剑创信息
 * @Date : 2019/6/26
 */
@Component
@ConfigurationProperties(prefix = "redis")
@Data
public class RedisConfig {

    private int database;

    private String host;

    private int port;
    /**
     * 秒
     */
    private int timeout;
    private String password;
    private int poolMaxTotal;
    private int poolMaxIdle;
    /**
     * 连接最大等待 单位（秒）
     */
    private int poolMaxWait;


}
