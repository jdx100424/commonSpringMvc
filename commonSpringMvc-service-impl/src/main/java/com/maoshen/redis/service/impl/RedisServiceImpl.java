/**   
 * @Description:(用一句话描述该类做什么)
 * @author Daxian.jiang
 * @Email  Daxian.jiang@vipshop.com
 * @Date 2015年7月29日 上午10:06:31
 * @Version V1.0   
 */
package com.maoshen.redis.service.impl;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.maoshen.component.redis.service.RedisService;

@Service("redisServiceImpl")
public class RedisServiceImpl implements RedisService {

    @SuppressWarnings("rawtypes")
    @Autowired
    private RedisTemplate          jedisTemplate;

    @Autowired
    @Qualifier("jedisFactory")
    private JedisConnectionFactory jedisConnectionFactory;

    @SuppressWarnings("unchecked")
    @Override
    public void insertByValue(Object key, Object value, long timeOut, TimeUnit timeUnit)
            throws Exception {
        jedisTemplate.opsForValue().set(key, value, timeOut, timeUnit);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void insertByHash(Object key, Object hashKey, Object value) throws Exception {
        jedisTemplate.opsForHash().put(key, hashKey, value);
    }

    @Override
    public Object getByValue(Object key) throws Exception {
        return jedisTemplate.opsForValue().get(key);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Object getByHash(Object key, Object hashKey) throws Exception {
        return jedisTemplate.opsForHash().get(key, hashKey);
    }
}