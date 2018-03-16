package com.maoshen.echo.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.maoshen.component.base.dto.ResponseResultDto;
import com.maoshen.component.controller.BaseController;
import com.maoshen.component.disconf.KafkaDisconf;
import com.maoshen.component.disconf.MysqlDisconf;
import com.maoshen.component.disconf.SentryDisconf;
import com.maoshen.component.elasticsearch.BaseElasticSearchClientHandle;
import com.maoshen.component.kafka.BaseProducer;
import com.maoshen.component.kafka.dto.MessageDto;
import com.maoshen.component.kafka.dto.MessageVo;
import com.maoshen.component.rest.UserRestContext;
import com.maoshen.component.sentry.SentryProvider;
import com.maoshen.echo.disconf.JdxDisconf;
import com.maoshen.echo.domain.CheckRouteDb;
import com.maoshen.echo.domain.Echo;
import com.maoshen.echo.service.dto.RouteDto;
import com.maoshen.echo.service.impl.CheckRouteDbServiceImpl;
import com.maoshen.echo.service.impl.EchoServiceImpl;
import com.maoshen.echo.service.impl.RouteServiceImpl;
import com.maoshen.echo.vo.EchoVO;

import io.sentry.Sentry;
import io.sentry.event.Event;
import io.sentry.event.EventBuilder;

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
	private static final Logger LOGGER = LoggerFactory.getLogger(EchoController.class);

	@Autowired
	@Qualifier("routeServiceImpl")
	private RouteServiceImpl routeServiceImpl;
	@Autowired
	@Qualifier("echoServiceImpl")
	private EchoServiceImpl echoServiceImpl;
	@Autowired
	@Qualifier("checkRouteDbServiceImpl")
	private CheckRouteDbServiceImpl checkRouteDbServiceImpl;
	
	@Autowired
	@Qualifier("baseProducer")
	private BaseProducer baseProducer;
	
	@Autowired
	private KafkaDisconf kafkaDisconf;
	
	@Autowired
	private MysqlDisconf mysqlDisconf;
	
	@Autowired
	private SentryDisconf sentryDisconf;
	
	@Autowired
	private JdxDisconf jdxDisconf;
	
	@RequestMapping(value = "jdxDisconfTest", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public ResponseResultDto<Map<String, Object>> jdxDisconfTest(HttpServletRequest request, Model model, String src) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			jdxDisconf.getJdxName();
			resultMap.put("result", jdxDisconf.getJdxName());
			Map<String,Object> logLevelMap = new HashMap<String,Object>();
			logLevelMap.put("debug", LOGGER.isDebugEnabled());
			logLevelMap.put("info", LOGGER.isInfoEnabled());
			logLevelMap.put("warn", LOGGER.isWarnEnabled());
			logLevelMap.put("error", LOGGER.isErrorEnabled());
			resultMap.put("logLevel",logLevelMap);
		} catch (Exception e) {
			LOGGER.error("echo insert error:", e);
			resultMap.put("result", e.getMessage());
		}
		return new ResponseResultDto<Map<String, Object>>(resultMap);
	}
	
	@RequestMapping(value = "insertSameEchoId", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public ResponseResultDto<Map<String, Object>> insertSameEchoId(HttpServletRequest request, Model model, String src) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			Echo echo = new Echo();
			echo.setId(1L);
			echo.setName("dsfsdfdsfsdddddf");
			echoServiceImpl.insertAboutId(echo);
			resultMap.put("echoHasResultInsert", true);
		} catch (Exception e) {
			LOGGER.error("echo insert error:", e);
			resultMap.put("echoHasResultInsert", e.getMessage());
		}
		return new ResponseResultDto<Map<String, Object>>(resultMap);
	}
	@RequestMapping(value = "insertSameEchoName", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public ResponseResultDto<Map<String, Object>> insertSameEchoName(HttpServletRequest request, Model model, String src) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			Echo echo = new Echo();
			echo.setName("echo1");
			echoServiceImpl.insert(echo);
			resultMap.put("echoHasResultInsert", true);
		} catch (Exception e) {
			LOGGER.error("echo insert error:", e);
			resultMap.put("echoHasResultInsert", e.getMessage());
		}
		return new ResponseResultDto<Map<String, Object>>(resultMap);
	}
	
	@RequestMapping(value = "sentryTest", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public ResponseResultDto<Map<String,Object>> sentryTest(HttpServletRequest request, Model model, String src) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Sentry.init(sentryDisconf.getDsn());
		try {
			SentryProvider.sendLog(getServiceName(),"jdx test warn"  , Event.Level.WARNING, LOGGER);
			resultMap.put("status" , "ok" );
		} catch (Exception e) {
			LOGGER.error("routeSelectAll error:", e);
			resultMap.put("status", e.getMessage());
		}
		return new ResponseResultDto<Map<String,Object>>(resultMap);
	}
	
	@RequestMapping(value = "routeSelectAll", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public ResponseResultDto<List<RouteDto>> routeSelectAll(HttpServletRequest request, Model model, String src) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<RouteDto> list = null;
		try {
			list = routeServiceImpl.selectAll();
		} catch (Exception e) {
			LOGGER.error("routeSelectAll error:", e);
			resultMap.put("routeSelectAllError", e.getMessage());
		}
		return new ResponseResultDto<List<RouteDto>>(list);
	}
	
	@RequestMapping(value = "routeInsert", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public ResponseResultDto<Map<String, Object>> routeInsert(HttpServletRequest request, Model model, String src) {
		LOGGER.info(request.getParameter("id"));
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			long id = Long.parseLong(request.getParameter("id"));
			RouteDto routeDto = new RouteDto();
			routeDto.setId(id);
			routeServiceImpl.insert(routeDto);
		} catch (Exception e) {
			LOGGER.error("routeInsert error:", e);
			resultMap.put("routeInsertError", e.getMessage());
		}
		return new ResponseResultDto<Map<String, Object>>(resultMap);
	}

	@RequestMapping(value = "kafkaTest", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public ResponseResultDto<Map<String, Object>> kafkaTest(HttpServletRequest request, Model model, String src) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try{
			Map<String,Object> sendMap = new HashMap<String,Object>();
			sendMap.put("jdx", UUID.randomUUID().toString());
			MessageDto dto = new MessageDto(sendMap);
			baseProducer.send(MessageVo.JDX_MESSAGE.getTopicName(), dto);
			resultMap.put("kakfaResult", true);
		} catch (Exception e) {
			LOGGER.error("kakfaService error:", e);
			resultMap.put("kakfaResult", e.getMessage());
		}
		return new ResponseResultDto<Map<String, Object>>(resultMap);
	}
	
	@RequestMapping(value = "checkRouteDb", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public ResponseResultDto<Map<String, Object>> checkRouteDb(HttpServletRequest request, Model model, String src) {
		LOGGER.info(request.getParameter("timeId"));
		LOGGER.info(request.getParameter("selectOrInsert"));
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			long timeId = Long.parseLong(request.getParameter("timeId"));
			CheckRouteDb checkRouteDb = new CheckRouteDb();
			checkRouteDb.setId(timeId);
			checkRouteDb.setName(UUID.randomUUID().toString());
			if("0".equals(request.getParameter("selectOrInsert"))){
				boolean resultSelectOne = checkRouteDbServiceImpl.selectById(timeId);
				resultMap.put("result", resultSelectOne);
			}else{
				checkRouteDbServiceImpl.insert(checkRouteDb);
			}
			echoServiceImpl.checkEchoIsExist(1L);
			Echo echo = new Echo();
			echo.setName(UUID.randomUUID().toString());
			//echoServiceImpl.insert(echo);
		} catch (Exception e) {
			LOGGER.error("echo select error:", e);
			resultMap.put("echoHasResultSelect", e.getMessage());
		}
		return new ResponseResultDto<Map<String, Object>>(resultMap);
	}
	
	@RequestMapping(value = "checkHystrix", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public ResponseResultDto<Map<String, Object>> checkHystrix(HttpServletRequest request, Model model, String src) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try{
			boolean resultSelectDubbo = echoServiceImpl.checkDubbo(2L);
			Map<String, Object> dataResult = new HashMap<String, Object>();
			dataResult.put("3", resultSelectDubbo);
			resultMap.put("checkHystrix", dataResult);
		}catch(Exception e){
			resultMap.put("error", "error");
		}
		return new ResponseResultDto<Map<String, Object>>(resultMap);
	}
	
	@RequestMapping(value = "checkZipkin", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public ResponseResultDto<Map<String, Object>> checkZipkin(HttpServletRequest request, Model model, String src) throws Exception {
		Thread.sleep(2000);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		boolean resultSelectOne = echoServiceImpl.checkEchoIsExist(1L);
		boolean resultSelectDubbo = echoServiceImpl.checkDubbo(2L);
		boolean resultSelectTwo = echoServiceImpl.checkEchoIsExist(2L);
		Map<String, Object> dataResult = new HashMap<String, Object>();
		dataResult.put("1", resultSelectOne);
		dataResult.put("2", resultSelectTwo);
		dataResult.put("3", resultSelectDubbo);
		resultMap.put("echoHasResultSelect", dataResult);
		return new ResponseResultDto<Map<String, Object>>(resultMap);
	}
	/**
	 * 
	 * @param request
	 * @param model
	 * @param src
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "check", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public ResponseResultDto<Map<String, Object>> check(HttpServletRequest request, Model model, String src) throws Exception {
		//Thread.sleep(2000);
		
		UserRestContext userRestContext = UserRestContext.get();
		LOGGER.info(request.getParameter("accessToken"));
		LOGGER.info(userRestContext.getAccessToken());
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("controllerLoggerDebug" , LOGGER.isDebugEnabled());
		resultMap.put("controllerLoggerInfo" , LOGGER.isInfoEnabled());
		resultMap.put("controllerLoggerWarn" , LOGGER.isWarnEnabled());
		resultMap.put("controllerLoggerError" , LOGGER.isErrorEnabled());
		
		try {
			boolean resultSelectOne = echoServiceImpl.checkEchoIsExist(1L);
			boolean resultSelectTwo = echoServiceImpl.checkEchoIsExist(2L);
			boolean resultSelectDubbo = echoServiceImpl.checkDubbo(2L);
			Map<String, Object> dataResult = new HashMap<String, Object>();
			dataResult.put("1", resultSelectOne);
			dataResult.put("2", resultSelectTwo);
			dataResult.put("3", resultSelectDubbo);
			resultMap.put("echoHasResultSelect", dataResult);
			
			Echo e = new Echo();
			e.setId(1);
			e.setName("jdx");
			String str = JSONObject.toJSONString(e);
			List<String> list = new ArrayList<String>();
			list.add(str);
			BaseElasticSearchClientHandle.insert(list, "index1" , "type" );
			SearchHits hit = BaseElasticSearchClientHandle.select( "index1" , "type", "jdx" , "name" );
			
			resultMap.put("hitBool", hit.totalHits());
			String resultStr = "" ;
			if(hit.totalHits()>0){
				for(SearchHit h:hit){
					resultStr = resultStr + JSONObject.toJSONString(h.getSource());
				}
			}
			resultMap.put("hitResultStr", resultStr);
		} catch (Exception e) {
			LOGGER.error("echo select error:", e);
			resultMap.put("echoHasResultSelect", e.getMessage());
		}

		try {
			Echo echo = new Echo();
			echo.setName(UUID.randomUUID().toString());
			echoServiceImpl.insert(echo);
			resultMap.put("echoHasResultInsert", true);
		} catch (Exception e) {
			LOGGER.error("echo insert error:", e);
			resultMap.put("echoHasResultInsert", e.getMessage());
		}
		
		try{
			boolean result = echoServiceImpl.checkRedis();
			resultMap.put("redisHasResult", result);
		} catch (Exception e) {
			LOGGER.error("redisService error:", e);
			resultMap.put("redisHasResult", e.getMessage());
		}
		
		try{
			Map<String,Object> sendMap = new HashMap<String,Object>();
			sendMap.put("jdx", UUID.randomUUID().toString());
			MessageDto dto = new MessageDto(sendMap);
			baseProducer.send(MessageVo.ECHO_MESSAGE.getTopicName(), dto);
			resultMap.put("kakfaResult", true);
		} catch (Exception e) {
			LOGGER.error("kakfaService error:", e);
			resultMap.put("kakfaResult", e.getMessage());
		}

		
		try{
			Map<String,Object> sendMap = new HashMap<String,Object>();
			sendMap.put("kafkaIp", kafkaDisconf.getKafkaIp());
			sendMap.put("kafkaPort", kafkaDisconf.getKafkaPort());
			sendMap.put("MysqlMasterUsername",mysqlDisconf.getMysqlMasterUsername());
			sendMap.put("MysqlMasterPort",mysqlDisconf.getMysqlMasterPort());
			resultMap.put("disconfResult", sendMap);
		} catch (Exception e) {
			LOGGER.error("disconfResult error:", e);
			resultMap.put("disconfResult", e.getMessage());
		}

		return new ResponseResultDto<Map<String, Object>>(resultMap);
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
	public String index(HttpServletRequest request, Model model) {
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
	
	/**
	 * 测试抛异常是否在公共异常接收
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/testException", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public String testException(HttpServletRequest request, Model model) throws Exception{
		throw new Exception("testException");
	}
	
	/**
	 * 测试HTTP传JSON个格式的
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/testReceiveJson", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public ResponseResultDto<EchoVO> testReceiveJson(HttpServletRequest request, Model model,
			@RequestBody EchoVO echoVO) throws Exception {
		LOGGER.info("testReceiveJson result value:" + JSONObject.toJSONString(echoVO));
		echoVO.setId(echoVO.getId() + 5);
		echoVO.setName(echoVO.getName() + "echo");
		return new ResponseResultDto<EchoVO>(echoVO);
	}

	@Override
	public String getServiceName() {
		return "EchoController";
	}
}
