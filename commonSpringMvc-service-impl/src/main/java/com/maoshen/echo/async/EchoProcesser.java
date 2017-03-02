package com.maoshen.echo.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.maoshen.component.async.AsyncTaskProcesser;

/**
 * 异步更新身高和体重
 *
 */
@Service("echoProcesser")
public class EchoProcesser extends AsyncTaskProcesser {

	private final static Logger logger = LoggerFactory.getLogger(EchoProcesser.class);

	public EchoProcesser() {
		super(10);
	}

	public void submit() {
		executor.submit(new asyncTask());
	}

	public void close() {
		executor.shutdown();
	}

	private class asyncTask implements Runnable {
		public asyncTask() {

		}
		
		@Override
		public void run() {
			try{
				logger.info("echoPrecesser running");
			}catch(Exception e){
				logger.error(e.getMessage(),e);
			}
		}
	}

}