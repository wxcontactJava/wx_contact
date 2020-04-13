package com.wx.contact.redis;

import com.alibaba.fastjson.JSON;
import com.wx.contact.redis.prefix.KeyPrefix;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;
import java.util.Map;

/**
 * @Description: redis服务
 * @Email: honghyuan@163.com
 * @Author: 剑创信息
 * @Date : 2019/6/21
 */
@Service
@Slf4j
public class RedisService {

    @Autowired
    private JedisPool jedisPool;

    /**
     * 获取单个对象
     */
    public <T> T get(KeyPrefix prefix, String key, Class<T> clazz) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            //生成真正的key
            String realKey = prefix.getPrefix() + key;
            String str = jedis.get(realKey);
            T t = stringToBean(str, clazz);
            return t;
        } finally {
            returnToPool(jedis);
        }
    }

    public String get(KeyPrefix prefix, String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            //生成真正的key
            String realKey = prefix.getPrefix() + key;
            return jedis.get(realKey);
        } finally {
            returnToPool(jedis);
        }
    }

    /**
     * 设置对象
     */
    public <T> boolean set(KeyPrefix prefix, String key, T value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String str = beanToString(value);
            if (str == null || str.length() <= 0) {
                return false;
            }
            //生成真正的key
            String realKey = prefix.getPrefix() + key;
            int seconds = prefix.expireSeconds();
            if (seconds <= 0) {
                jedis.set(realKey, str);
            } else {
                jedis.setex(realKey, seconds, str);
            }
            return true;
        } finally {
            returnToPool(jedis);
        }
    }

    /**
     * 判断key是否存在
     */
    public <T> boolean exists(KeyPrefix prefix, String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            //生成真正的key
            String realKey = prefix.getPrefix() + key;
            return jedis.exists(realKey);
        } finally {
            returnToPool(jedis);
        }
    }

    /**
     * 增加值
     */
    public <T> Long incr(KeyPrefix prefix, String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            //生成真正的key
            String realKey = prefix.getPrefix() + key;
            return jedis.incr(realKey);
        } finally {
            returnToPool(jedis);
        }
    }

    /**
     * 减少值
     */
    public <T> Long decr(KeyPrefix prefix, String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            //生成真正的key
            String realKey = prefix.getPrefix() + key;
            return jedis.decr(realKey);
        } finally {
            returnToPool(jedis);
        }
    }


    /**
     * 删除
     */
    public boolean delete(KeyPrefix prefix, String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            //生成真正的key
            String realKey = prefix.getPrefix() + key;
            long ret = jedis.del(realKey);
            return ret > 0;
        } finally {
            returnToPool(jedis);
        }
    }

    public Long hSet(final String key, String field, String value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            Long count = jedis.hset(key, field, value);
            return count;
        } finally {
            returnToPool(jedis);
        }
    }

    public <T> T hGet(final String key, String field, Class<T> clazz) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String hget = jedis.hget(key, field);
            return stringToBean(hget, clazz);
        } finally {
            returnToPool(jedis);
        }
    }


    public Map<String, String> hGetAll(final String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.hgetAll(key);
        } finally {
            returnToPool(jedis);
        }
    }

    /**
     * 一个或多个值插入到列表头部
     */
    public long lPush(KeyPrefix prefix, String value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            long count = jedis.lpush(prefix.getPrefix(), value);
            return count;
        } finally {
            returnToPool(jedis);
        }
    }
    /**
     * 将一个或多个值插入到列表的尾部(最右边)
     */
    public long rPush(final String key, Object obj) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String str = beanToString(obj);
            if (str == null || str.length() <= 0) {
                return 0;
            }
            return jedis.rpush(key, str);
        } finally {
            returnToPool(jedis);
        }
    }
    /**
     * 移除并返回列表的第一个元素。
     */
    public String lPop(final String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.lpop(key);
        } finally {
            returnToPool(jedis);
        }
    }

    /**
     * 移除列表的最后一个元素
     */
    public String rPop(final String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.rpop(key);
        } finally {
            returnToPool(jedis);
        }
    }
    public List<String> lRange(KeyPrefix prefix, Long start, Long stop) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.lrange(prefix.getPrefix(), start, stop);
        } finally {
            returnToPool(jedis);
        }
    }

    public Long lLen(KeyPrefix prefix) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.llen(prefix.getPrefix());
        } finally {
            returnToPool(jedis);
        }
    }



    @SuppressWarnings("unchecked")
    public static <T> T stringToBean(String str, Class<T> clazz) {
        if (str == null || str.length() <= 0 || clazz == null) {
            return null;
        }
        if (clazz == int.class || clazz == Integer.class) {
            return (T) Integer.valueOf(str);
        } else if (clazz == String.class) {
            return (T) str;
        } else if (clazz == long.class || clazz == Long.class) {
            return (T) Long.valueOf(str);
        } else if (clazz == List.class) {
            return JSON.parseObject(str, clazz);
        } else {
            return JSON.toJavaObject(JSON.parseObject(str), clazz);
        }
    }

    public static <T> T stringToList(String str, Class<T> clazz) {
        if (str == null || str.length() <= 0 || clazz == null) {
            return null;
        }
        return JSON.parseObject(str, clazz);
    }

    /**
     * 设置redis 中的value
     */
    public static <T> String beanToString(T value) {
        if (value == null) {
            log.error("【存入redis序列化失败】存入值为空");
            return null;
        }
        Class<?> clazz = value.getClass();
        if (clazz == int.class || clazz == Integer.class) {
            return "" + value;
        } else if (clazz == String.class) {
            return (String) value;
        } else if (clazz == long.class || clazz == Long.class) {
            return "" + value;
        } else {
            return JSON.toJSONString(value);
        }
    }

    private void returnToPool(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }
}

