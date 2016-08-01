package com.maoshen.login.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.maoshen.account.domain.Account;
import com.maoshen.base.BaseController;
import com.maoshen.jdx.service.JdxService;
import com.maoshen.response.ResponseResult;

/**
 * 
 * @Description:
 * @author Daxian.jiang
 * @Email Daxian.jiang@vipshop.com
 * @Date 2015年7月14日 上午11:28:11
 * @Version V1.0
 */
@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {
	private static final Logger LOGGER = Logger.getLogger(LoginController.class);

	@Autowired
	@Qualifier("jdxServiceImpl")
	private JdxService jdxService;

	/**
	 * 
	 * @Description: 登录首页入口
	 * @author Daxian.jiang
	 * @Email Daxian.jiang@vipshop.com
	 * @Date 2015年9月24日 上午9:37:35
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "", method = { RequestMethod.POST, RequestMethod.GET })
	public String index(HttpServletRequest request, Model model, String src) {
		LOGGER.info("进入登录页首页");
		return "/login/index";
	}

	/**
	 * 
	 * @Description:验证用户名和密码是否有效
	 * @author Daxian.jiang
	 * @Email Daxian.jiang@vipshop.com
	 * @Date 2015年7月14日 上午11:51:56
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/showInfo", method = { RequestMethod.POST, RequestMethod.GET })
	public ResponseResult<Account> submit(HttpServletRequest request, HttpServletResponse response,
			@RequestParam String userName, @RequestParam String password)
			throws Exception {
		LOGGER.info("登录提交");
		Account account = jdxService.getAccount(userName, password);
		return new ResponseResult<Account>(account);
	}
}
