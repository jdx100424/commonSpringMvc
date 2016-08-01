/**   
 * @Description:(用一句话描述该类做什么)
 * @author Daxian.jiang
 * @Email  Daxian.jiang@vipshop.com
 * @Date 2015年7月29日 上午10:05:58
 * @Version V1.0   
 */
package com.maoshen.component.redis.service;

import java.util.concurrent.TimeUnit;

public interface RedisService {
    /**
     * 
     * @Description: value为字符串的插入
     * @author Daxian.jiang
     * @Email  Daxian.jiang@vipshop.com
     * @Date 2015年9月25日 上午10:05:13
     * @param key
     * @param value
     * @param timeOut
     * @param timeUnit
     * @throws Exception
     */
    public void insertByValue(Object key, Object value, long timeOut, TimeUnit timeUnit)
            throws Exception;

    /**
     * 
     * @Description: value为hash的插入
     * @author Daxian.jiang
     * @Email  Daxian.jiang@vipshop.com
     * @Date 2015年9月25日 上午10:05:30
     * @param key
     * @param hashKey
     * @param value
     * @throws Exception
     */
    public void insertByHash(Object key, Object hashKey, Object value) throws Exception;

    /**
     * 
     * @Description: 从redis获取value值
     * @author Daxian.jiang
     * @Email  Daxian.jiang@vipshop.com
     * @Date 2015年9月25日 上午10:05:47
     * @param key
     * @return
     * @throws Exception
     */
    public Object getByValue(Object key) throws Exception;

    /**
     * 
     * @Description: 从redis获取Hash值
     * @author Daxian.jiang
     * @Email  Daxian.jiang@vipshop.com
     * @Date 2015年9月25日 上午10:06:12
     * @param key
     * @param hashKey
     * @return
     * @throws Exception
     */
    public Object getByHash(Object key, Object hashKey) throws Exception;
}