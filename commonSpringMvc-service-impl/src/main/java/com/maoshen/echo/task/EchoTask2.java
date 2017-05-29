package com.maoshen.echo.task;

import java.util.Date;

import org.apache.log4j.Logger;

import com.maoshen.component.task.BaseRedisTask;

/**
 * 健康检查的定时器负载均衡实例，每次只运行一台
 * @author jdx
 *
 */
public class EchoTask2 extends BaseRedisTask{
	private static final Logger LOGGER = Logger.getLogger(EchoTask2.class);
	
	private static final String NAME = "ECHO_TASK2";
	
	private static final Long EXPIRE_TIME = 30L;

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public void timeTaskRun() {
		LOGGER.warn(getName() +"," +Thread.currentThread().getName() + "_" + "echo task warning test start 在负载均衡条件下，是否只有一台服务器运行_" + new Date());
		try {
			Thread.sleep(20 * 1000);
		} catch (InterruptedException e) {
			LOGGER.error(this.getClass() + "_" + this.getName() + " run interruptedException", e);
		}
		LOGGER.warn(getName() +"," +Thread.currentThread().getName() + "_" + "echo task warning test end 在负载均衡条件下，是否只有一台服务器运行_" + new Date());
	}

	@Override
	public Long getExpireTime() {
		return EXPIRE_TIME;
	}

}
