package com.maoshen.echo.service;

import com.maoshen.db.DataSource;

public interface EchoService {
	@DataSource("slave")
	public boolean checkEchoIsExist(Long id);
}
