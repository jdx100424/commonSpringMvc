package com.maoshen.echo.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.maoshen.echo.domain.Echo;

@Repository
public interface EchoDao {
    public Echo selectById(@Param("id")Long id);
    
    public void insert(Echo echo);
}
