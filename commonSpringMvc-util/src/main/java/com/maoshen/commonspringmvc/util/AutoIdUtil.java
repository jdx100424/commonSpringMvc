package com.maoshen.commonspringmvc.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maoshen.idautocreate.dubbo.ProjectTableIdDubbo;
import com.maoshen.idautocreate.duo.ProjectTableIdDuo;

/**
 * 
 * @author jdx
 *
 */
@Service
public class AutoIdUtil implements InitializingBean{
	private static Long routeStartId;
	private static Long routeEndId;
	public static final String projectName="commonSpringMvc" ;
	public static final String routeTableName="route" ;
	@Autowired
	private ProjectTableIdDubbo projectTableIdDubbo;
	private static final Logger LOGGER = LoggerFactory.getLogger(AutoIdUtil.class);
	private static final Boolean IS_TEST = true;
	
	public Long getAutoId(String tableName) throws Exception{
		if(StringUtils.isBlank(tableName)){
			throw new Exception("getAutoId tableName is null");
		}
		switch(tableName){
			case routeTableName:
				synchronized(this){
					if(routeStartId==null || routeEndId==null){
						throw new Exception("routeStartId routeEndId is null");
					}
					if(routeStartId>=routeEndId){
						//need get new id
						initRouteAutoId();
					}
					Long resultId = routeStartId;
					routeStartId = routeStartId+1;
					
					if(IS_TEST){
						Thread.sleep(5000);
					}
					LOGGER.info("projectName:{},routeTableName:{},getId:{}",projectName,routeTableName,resultId);
					return resultId;
				}
			default:
				throw new Exception("getAutoId tableName is error");
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		try{
			initRouteAutoId();
		}catch(Exception e){
			LOGGER.error(e.getMessage(),e);
		}
	}
	
	private void initRouteAutoId() throws Exception{
		ProjectTableIdDuo routeProjectTableIdDuo = projectTableIdDubbo.getProjectTableIdDto(projectName, routeTableName);
		if(routeProjectTableIdDuo!=null && routeProjectTableIdDuo.getId()!=null){
			Long startId = routeProjectTableIdDuo.getStartId();
			Long endId =routeProjectTableIdDuo.getEndId();
			routeStartId = startId;
			routeEndId = endId;
		}else{
			throw new Exception(projectName+"," +routeTableName+",getProjectTableIdDto fail");
		}
	}
}
