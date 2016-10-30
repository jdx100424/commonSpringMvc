package com.maoshen.echo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.maoshen.echo.domain.Echo;
import com.maoshen.echo.service.EchoService;

@Service("echoServiceImpl")
public class EchoServiceImpl implements EchoService {
	@Autowired
	private com.maoshen.echo.dao.EchoDao echoDao;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean checkEchoIsExist(Long id) {
		Echo echo = echoDao.selectById(id);
		boolean result = false;
		if (echo != null) {
			result = true;
		}
		return result;
	}

}
