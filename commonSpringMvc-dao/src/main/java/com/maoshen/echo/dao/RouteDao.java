package com.maoshen.echo.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.maoshen.component.mybatis.route.TableSeg;
import com.maoshen.echo.domain.Route;

@Repository
@TableSeg(shardBy = "id",tableName="route" )
public interface RouteDao {
    public Route selectById(@Param("id")Long id);
    
    public List<Route> selectAllByMap(Map<String,Object> map);
    
    public List<Route> selectAll(@Param("start")Long start,@Param("end")Long end);  
    
    public void insert(Route route);
}
