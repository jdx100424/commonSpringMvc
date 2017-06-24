package com.maoshen.echo.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.maoshen.component.mybatis.route.DbSeg;
import com.maoshen.echo.domain.CheckRouteDb;

@Repository
@DbSeg(shardBy = "id")
public interface CheckRouteDbDao {
    public CheckRouteDb selectById(@Param("id")Long id);
    
    public void insert(CheckRouteDb checkRouteDb);
}
