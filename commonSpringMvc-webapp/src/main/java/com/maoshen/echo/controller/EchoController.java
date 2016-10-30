package com.maoshen.echo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.maoshen.base.BaseController;
import com.maoshen.echo.domain.Echo;
import com.maoshen.echo.service.EchoService;
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
@RequestMapping("/echo")
public class EchoController extends BaseController {
	private static final Logger LOGGER = Logger.getLogger(EchoController.class);

	@Autowired
	@Qualifier("echoServiceImpl")
	private EchoService echoService;

	/**
	 * 
	 * @param request
	 * @param model
	 * @param src
	 * @return
	 */
	@RequestMapping(value = "check", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public ResponseResult<Map<String,Object>> echo(HttpServletRequest request, Model model, String src){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			boolean result = echoService.checkEchoIsExist(1L);
			resultMap.put("echoHasResult",result);
		}catch(Exception e){
			LOGGER.error("echo check error:",e);
			resultMap.put("echoHasResult",e.getMessage());
		}
	
		return new ResponseResult<Map<String,Object>>(resultMap);
	}
	
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
	@RequestMapping(value = "/index", method = { RequestMethod.POST, RequestMethod.GET })
	public String index(HttpServletRequest request, Model model, String src) {
		LOGGER.info("进入首页");
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

        List<Echo> jdxListObject = new ArrayList<Echo>();
        jdxListObject.add(new Echo());
        jdxListObject.add(new Echo());
        jdxListObject.add(new Echo());
        jdxListObject.add(new Echo());

        Map<String, Echo> jdxMapObject = new HashMap<String, Echo>();
        jdxMapObject.put("1", new Echo());
        jdxMapObject.put("2", new Echo());
        jdxMapObject.put("3", new Echo());
        jdxMapObject.put("4", new Echo());

        model.addAttribute("jdxListObject", jdxListObject);
        model.addAttribute("jdxMapObject", jdxMapObject);		
		return "/echo/index";
	}
}
