package com.maoshen.echo.task;

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
		LOGGER.warn("echo task warning test 在负载均衡条件下，是否只有一台服务器运行");
	}

}
