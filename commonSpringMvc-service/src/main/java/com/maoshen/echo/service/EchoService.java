package com.maoshen.echo.service;

import com.maoshen.db.DataSource;
import com.maoshen.echo.domain.Echo;

public interface EchoService {
	@DataSource("slave")
	public boolean checkEchoIsExist(Long id);

	@DataSource("master")
	public void insert(Echo echo) throws Exception;
}
