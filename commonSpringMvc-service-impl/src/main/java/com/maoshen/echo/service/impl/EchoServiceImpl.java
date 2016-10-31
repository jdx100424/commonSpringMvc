package com.maoshen.echo.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.maoshen.echo.domain.Echo;
import com.maoshen.echo.service.EchoService;

@Service("echoServiceImpl")
public class EchoServiceImpl implements EchoService {
	@Autowired
	private com.maoshen.echo.dao.EchoDao echoDao;
	
	private static final Logger LOGGER = Logger.getLogger(EchoServiceImpl.class);

	@Override
	public boolean checkEchoIsExist(Long id) {
		Echo echo = echoDao.selectById(id);
		boolean result = false;
		if (echo != null) {
			result = true;
		}
		return result;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void insert(Echo echo) throws Exception {
		try{
			echoDao.insert(echo);
		}catch(Exception e){
			LOGGER.error("EchoServiceImpl_insert fail",e);
			throw new Exception(e.getMessage());
		}
	}

}
