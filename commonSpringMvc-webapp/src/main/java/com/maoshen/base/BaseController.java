/**   
 * @Description:(用一句话描述该类做什么)
 * @author Daxian.jiang
 * @Email  Daxian.jiang@vipshop.com
 * @Date 2015年9月17日 上午10:58:30
 * @Version V1.0   
 */
package com.maoshen.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.maoshen.component.json.JsonpUtil;
import com.maoshen.errorcode.ErrorCode;
import com.maoshen.response.ResponseResult;

public abstract class BaseController {
	private static final Logger LOGGER = Logger.getLogger(BaseController.class);

	@ExceptionHandler
	@ResponseBody
	protected void handleException(HttpServletRequest request, 
			HttpServletResponse response, Throwable thr){
		ResponseResult<Object> result = new ResponseResult<Object>();
		result.setCode(ErrorCode.SERVICE_EXCEPTION.getCode());
		result.setMessage(ErrorCode.SERVICE_EXCEPTION.getMsg());
		
		LOGGER.error("baseBontroller_base common error " + thr.getMessage(), thr);
		response.setHeader("Content-type", "application/javascript;charset=UTF-8");
		try {
			response.getWriter().write(JsonpUtil.restJsonp(request.getParameter("callback"), result));
		} catch (Exception e) {
			LOGGER.error("baseBontroller_base response error " + thr.getMessage(), thr);
		}
	}
}