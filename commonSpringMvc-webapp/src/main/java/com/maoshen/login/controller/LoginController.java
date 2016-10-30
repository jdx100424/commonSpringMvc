package com.maoshen.login.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		List<String> jdxList = new ArrayList<String>();
        jdxList.add("1");
        jdxList.add("2");
        jdxList.add("3");
        jdxList.add("4");

        Map<String, Object> jdxMap = new HashMap<String, Object>();
        jdxMap.put("1", "111");
        jdxMap.put("2", "222");
        jdxMap.put("3", "333");
        jdxMap.put("4", "444");

        model.addAttribute("jdx", "jiangdaxian");
        model.addAttribute("jdxList", jdxList);
        model.addAttribute("jdxMap", jdxMap);

        List<Account> jdxListObject = new ArrayList<Account>();
        jdxListObject.add(new Account());
        jdxListObject.add(new Account());
        jdxListObject.add(new Account());
        jdxListObject.add(new Account());

        Map<String, Account> jdxMapObject = new HashMap<String, Account>();
        jdxMapObject.put("1", new Account());
        jdxMapObject.put("2", new Account());
        jdxMapObject.put("3", new Account());
        jdxMapObject.put("4", new Account());

        model.addAttribute("jdxListObject", jdxListObject);
        model.addAttribute("jdxMapObject", jdxMapObject);		
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
