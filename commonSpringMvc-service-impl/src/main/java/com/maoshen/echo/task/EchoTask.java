package com.maoshen.echo.task;

import java.util.Date;

import org.apache.log4j.Logger;

import com.maoshen.component.task.BaseRedisTask;

public class EchoTask extends BaseRedisTask{
	private static final Logger LOGGER = Logger.getLogger(EchoTask.class);
	
	private static final String NAME = "ECHO_TASK";

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public void timeTaskRun() {
		LOGGER.warn(Thread.currentThread().getName() + "_" + "echo task warning test start 在负载均衡条件下，是否只有一台服务器运行_" + new Date());
		try {
			Thread.sleep(20 * 1000);
		} catch (InterruptedException e) {
			LOGGER.error(this.getClass() + "_" + this.getName() + " run interruptedException", e);
		}
		LOGGER.warn(Thread.currentThread().getName() + "_" + "echo task warning test end 在负载均衡条件下，是否只有一台服务器运行_" + new Date());
	}

}
