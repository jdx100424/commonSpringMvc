package com.maoshen.echo.service.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.maoshen.echo.domain.CheckRouteDb;

@Service("checkRouteDbServiceImpl")
public class CheckRouteDbServiceImpl{
	@Autowired
	private com.maoshen.echo.dao.CheckRouteDbDao checkRouteDbDao;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CheckRouteDbServiceImpl.class);
	
	public boolean selectById(Long id) {
		CheckRouteDb c = checkRouteDbDao.selectById(id);
		boolean result = false;
		if (c != null) {
			result = true;
		}
		return result;
	}

	@Transactional(rollbackFor = Exception.class)
	public void insert(CheckRouteDb checkRouteDb) throws Exception {
		try{
			checkRouteDbDao.insert(checkRouteDb);
			if(checkRouteDb.getId()==5){
				throw new Exception("zxxxxx");
			}
		}catch(Exception e){
			LOGGER.error("CheckRouteDb_insert fail",e);
			throw new Exception(e.getMessage());
		}
	}
}
