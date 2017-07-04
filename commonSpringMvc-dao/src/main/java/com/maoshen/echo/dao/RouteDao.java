package com.maoshen.echo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.maoshen.component.mybatis.route.TableSeg;
import com.maoshen.echo.domain.Route;

@Repository
@TableSeg(shardBy = "id",tableName="route" )
public interface RouteDao {
    public Route selectById(@Param("id")Long id);
    
    public List<Route> selectAll();  
    
    public void insert(Route route);
}
