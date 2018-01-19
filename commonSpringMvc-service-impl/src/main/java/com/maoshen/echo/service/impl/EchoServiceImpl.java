package com.maoshen.echo.service.impl;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.maoshen.component.mybatis.Slave;
import com.maoshen.component.redis.RedisService;
import com.maoshen.echo.async.EchoProcesser;
import com.maoshen.echo.domain.Echo;
import com.maoshen.echo.dubbo.EchoDubbo;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service("echoServiceImpl")
public class EchoServiceImpl{
	@Autowired
	private com.maoshen.echo.dao.EchoDao echoDao;
	
	@Autowired
	private RedisService redisService;
	
	@Autowired
	@Qualifier("echoDubboImpl")
	private EchoDubbo echoDubbo;
	
	@Autowired
	private EchoProcesser echoProcesser;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EchoServiceImpl.class);
	
	@Slave
	public boolean checkEchoIsExist(Long id) {
		Echo echo = echoDao.selectById(id);
		boolean result = false;
		if (echo != null) {
			result = true;
		}
		return result;
	}

	@Transactional(rollbackFor = Exception.class)
	public void insert(Echo echo) throws Exception {
		try{
			echoDao.insert(echo);
		}catch(Exception e){
			if(e instanceof DuplicateKeyException){
				System.out.println("DuplicateKeyException");
			}
			LOGGER.error("EchoServiceImpl_insert fail",e);
			throw new Exception(e.getMessage());
		}
	}
	@Transactional(rollbackFor = Exception.class)
	public void insertAboutId(Echo echo) throws Exception {
		try{
			echoDao.insertAboutId(echo);
		}catch(Exception e){
			if(e instanceof DuplicateKeyException){
				System.out.println("DuplicateKeyException");
			}
			LOGGER.error("EchoServiceImpl_insert fail",e);
			throw new Exception(e.getMessage());
		}
	}

	public boolean checkRedis() throws Exception{
		try{
			String compareStr = "true";
			String randomKey = UUID.randomUUID().toString();
			redisService.insertByValue(randomKey, compareStr, 10, TimeUnit.SECONDS);
			Object result = redisService.getByValue(randomKey);
			if(compareStr.equals(result)){
				return true;
			}else{
				return false;
			}
		}catch(Exception e){
			LOGGER.error("EchoServiceImpl_checkRedis fail",e);
			throw e;
		}
	}

	@HystrixCommand(groupKey = "checkDubboGroupKey", commandKey = "checkDubbo", fallbackMethod = "checkDubboFallback",
	        commandProperties = {
	                @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000"),//指定多久超时，单位毫秒。超时进fallback
	                @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "3"),//判断熔断的最少请求数，默认是10；只有在一个统计窗口内处理的请求数量达到这个阈值，才会进行熔断与否的判断
	                @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),//判断熔断的阈值，默认值50，表示在一个统计窗口内有50%的请求处理失败，会触发熔断
	        }
	)
	public boolean checkDubbo(Long id) {
		try{
			return echoDubbo.checkEchoIsExistByDubbo(id);
		}catch(Exception e){
			LOGGER.error(e.getMessage(),e);
			echoProcesser.submit();
			return false;
		}
	}
	
	public boolean checkDubboFallback(Long id) {
		LOGGER.warn("checkDubbo被熔断，默认返回TRUE");
		return true;
	}

}
