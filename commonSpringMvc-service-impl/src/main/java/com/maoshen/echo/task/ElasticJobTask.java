package com.maoshen.echo.task;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;

public class ElasticJobTask implements SimpleJob {
	private static final Logger LOGGER = LoggerFactory.getLogger(ElasticJobTask.class);
	@Override
	public void execute(ShardingContext context) {
		LOGGER.info("{},shardingItem:{},totalCount:{}",context.getJobName(),context.getShardingItem(),context.getShardingTotalCount());
		LOGGER.info("{},{}",context.getJobName(),context.toString());
		switch (context.getShardingItem()) {
		case 0:
			LOGGER.info("ElasticJobTask name:{} is start",context.getJobName());
		default:
			break;
		}
	}

}
