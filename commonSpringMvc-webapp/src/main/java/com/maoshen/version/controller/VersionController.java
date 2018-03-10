package com.maoshen.version.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.maoshen.component.base.dto.ResponseResultDto;
import com.maoshen.component.controller.BaseController;
import com.maoshen.component.rest.UserRestContext;
import com.maoshen.echo.service.impl.EchoServiceImpl;

/**
 * 
 * @Description:
 * @author Daxian.jiang
 * @Email Daxian.jiang@vipshop.com
 * @Date 2015年7月14日 上午11:28:11
 * @Version V1.0
 */
@Controller
@RequestMapping("/version")
public class VersionController extends BaseController {
	private static final Logger LOGGER = LoggerFactory.getLogger(VersionController.class);    

	@Autowired
	@Qualifier("echoServiceImpl")
	private EchoServiceImpl echoServiceImpl;
	
	@Autowired
	private com.maoshen.version.dubbo.VersionDubbo versionDubbo;

	/**
	 * 
	 * @param request
	 * @param model
	 * @param src
	 * @return
	 */
	@RequestMapping(value = "get", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public ResponseResultDto<Map<String, Object>> check(HttpServletRequest request, Model model, String src) {
		UserRestContext userRestContext = UserRestContext.get();
		LOGGER.info(request.getParameter("accessToken"));
		LOGGER.info(userRestContext.getAccessToken());
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("versionResult", versionDubbo.getVersion());
		return new ResponseResultDto<Map<String, Object>>(resultMap);
	}

	@Override
	public String getServiceName() {
		return "VersionController";
	}
}
