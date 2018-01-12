package com.maoshen.echo.test;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;
import com.maoshen.echo.domain.Echo;
import com.maoshen.echo.service.impl.EchoServiceImpl;

/**
 * @Title: business 层测试基类
 * @Description: 该层的测试不启用dubbo服务，仅用于测试BusinessService，同时这层的测试不是必须的，根据情况使用
 * @Company:
 * @Author:jintao
 * @Created Date:2016年7月27日
 */
@ContextConfiguration(locations = { "classpath*:/applicationContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class BaseTest extends TestCase{
	@Autowired
	private EchoServiceImpl echoServiceImpl;
	
	@Test
	public void testService(){
		boolean result1 = echoServiceImpl.checkEchoIsExist(1L);
		System.out.println(result1);
		boolean result2 = echoServiceImpl.checkEchoIsExist(2L);
		System.out.println(result2);
	}
}
