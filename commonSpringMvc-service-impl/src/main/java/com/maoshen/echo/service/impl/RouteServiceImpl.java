package com.maoshen.echo.service.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.maoshen.echo.service.dto.RouteTestDto;

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
		Long start=0L;
		Long end=2L;
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("start", start);
		map.put("end", end);
		map.put("aaa", 1);
		List<Route> routeByMap = routeDao.selectAllByMap(map);
		
		List<Route> route = routeDao.selectAll(start,end);
		List<RouteDto> resultList = null;
		if(route!=null && route.isEmpty()==false){
			resultList = new ArrayList<RouteDto>();
			for(Route r:route){
				RouteDto routeDto = new RouteDto();
				RouteTestDto routeTestDto = new RouteTestDto();
				routeTestDto.setId(r.getId());
				routeTestDto.setTestDouble(33.33334445D);
				BeanUtils.copyProperties(routeTestDto, routeDto);
				System.out.println(routeDto.getTestDoubleNext());
				routeDto.setTestDoubleNext(2D);
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
