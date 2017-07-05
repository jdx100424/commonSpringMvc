package com.maoshen.echo.service.impl;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.maoshen.commonspringmvc.util.AutoIdUtil;
import com.maoshen.component.mybatis.Master;
import com.maoshen.echo.domain.Route;
import com.maoshen.echo.service.dto.RouteDto;

@Service("routeServiceImpl")
public class RouteServiceImpl{
	@Autowired
	private com.maoshen.echo.dao.RouteDao routeDao;
	@Autowired
	private AutoIdUtil autoIdUtil;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RouteServiceImpl.class);
	
	@Master
	public RouteDto selectById(Long id) {
		Route route = routeDao.selectById(id);
		RouteDto routeDto = new RouteDto();
		BeanUtils.copyProperties(route, routeDto);
		return routeDto;
	}
	
	@Master
	public List<RouteDto> selectAll() {
		List<Route> route = routeDao.selectAll();
		List<RouteDto> resultList = null;
		if(route!=null && route.isEmpty()==false){
			resultList = new ArrayList<RouteDto>();
			for(Route r:route){
				RouteDto routeDto = new RouteDto();
				BeanUtils.copyProperties(r, routeDto);
				resultList.add(routeDto);
			}
		}
		return resultList;
	}

	@Transactional(rollbackFor = Exception.class)
	public void insert(RouteDto routeDto) throws Exception {
		try{
			Route route = new Route();
			BeanUtils.copyProperties(routeDto,route);
			Long id = autoIdUtil.getAutoId(AutoIdUtil.routeTableName);
			route.setId(id);
			routeDao.insert(route);
		}catch(Exception e){
			LOGGER.error("route_insert fail",e);
			throw new Exception(e.getMessage());
		}
	}
}
