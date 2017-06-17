package com.maoshen.echo.service;

import com.maoshen.component.db.DataSource;
import com.maoshen.component.mybatis.Master;
import com.maoshen.component.mybatis.Slave;
import com.maoshen.echo.domain.Echo;

public interface EchoService {
	/**
	 * dubbo健康检测
	 * @return
	 */
	public boolean checkDubbo(Long id);
	/**
	 * 从库健康检测查询
	 * @param id
	 * @return
	 */
	//@DataSource("slave")
	public boolean checkEchoIsExist(Long id);
    
	/**
	 * 主库健康检测插入
	 * @param echo
	 * @throws Exception
	 */
	//@DataSource("master")
	@Master
	public void insert(Echo echo) throws Exception;
	
	/**
	 * 缓存健康检测
	 * @return
	 */
	public boolean checkRedis() throws Exception;
	
}
