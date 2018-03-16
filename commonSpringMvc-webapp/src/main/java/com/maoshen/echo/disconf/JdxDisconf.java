package com.maoshen.echo.disconf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import com.baidu.disconf.client.common.annotations.DisconfFile;
import com.baidu.disconf.client.common.annotations.DisconfFileItem;
import com.baidu.disconf.client.common.annotations.DisconfUpdateService;
import com.baidu.disconf.client.common.update.IDisconfUpdate;
import com.maoshen.component.disconf.Log4jDisconf;
import com.maoshen.version.controller.VersionController;

@Configuration
@DisconfFile(filename = "jdx.properties") 
@DisconfUpdateService(classes = { JdxDisconf.class })
public class JdxDisconf implements IDisconfUpdate {
	private static final Logger LOGGER = LoggerFactory.getLogger(JdxDisconf.class);
	private String jdxName;
	@DisconfFileItem(name = "name", associateField = "jdxName")
	public String getJdxName() {
		return jdxName;
	}
	public void setJdxName(String jdxName) {
		this.jdxName = jdxName;
	}
	@Override
	public void reload() throws Exception {
		LOGGER.warn("猫神JDX到此一游，name="+jdxName);
	}
}
