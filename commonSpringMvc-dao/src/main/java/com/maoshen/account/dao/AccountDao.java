package com.maoshen.account.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.maoshen.account.domain.Account;

@Repository
public interface AccountDao {
    public Account selectByUserNameAndPwd(@Param("userName")String userName, @Param("password")String password);
}
