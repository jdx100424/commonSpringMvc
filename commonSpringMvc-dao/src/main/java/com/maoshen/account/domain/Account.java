package com.maoshen.account.domain;

import com.maoshen.base.entity.BaseEntity;

public class Account extends BaseEntity {
    private String password;

    private String userName;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public Account() {

    }
}
