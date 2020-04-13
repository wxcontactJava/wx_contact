package com.wx.contact.redis.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;

/**
 * @Description: jedis配置
 * @Email: honghyuan@163.com
 * @Author: 剑创信息
 * @Date : 2019/11/22 11:09
 */
@Component
public class JedisPoolConfig {
    @Autowired
    private RedisConfig redisBean;

    @Bean
    public JedisPool jedisPool() {
        redis.clients.jedis.JedisPoolConfig poolConfig = new redis.clients.jedis.JedisPoolConfig();
        poolConfig.setMaxIdle(redisBean.getPoolMaxIdle());
        poolConfig.setMaxTotal(redisBean.getPoolMaxTotal());
        poolConfig.setMaxWaitMillis(redisBean.getPoolMaxWait() * 1000);
        return new JedisPool(poolConfig, redisBean.getHost(), redisBean.getPort(),
                redisBean.getTimeout() * 1000, redisBean.getPassword(), redisBean.getDatabase());
    }
}
