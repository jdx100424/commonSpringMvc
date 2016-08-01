package com.maoshen.jdx.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maoshen.account.domain.Account;
import com.maoshen.jdx.service.JdxService;

@Service("jdxServiceImpl")
public class JdxServiceImpl implements JdxService {
	@Autowired
	private com.maoshen.account.dao.AccountDao accountDao;

	@Override
	public Account getAccount(String userName, String password) {
		Account account = accountDao.selectByUserNameAndPwd(userName, password);
		if(account == null || account.getId()==0){
			account = new Account();
		}
		return account;
	}

}
