package com.maoshen.common.interceptor;


import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.maoshen.component.aop.interceptor.dao.DaoInterceptor;

@Aspect
@Component
@Order(0)
public class CommonDaoInterceptor extends DaoInterceptor {
	private static final Logger LOGGER = LoggerFactory.getLogger(CommonDaoInterceptor.class);

	public CommonDaoInterceptor() {
		LOGGER.info("{} {}_dao Interceptor is start", CommonDaoInterceptor.class.getName(), getServiceName());
	}

	@Override
	@Pointcut("execution(* com.maoshen.*.dao.*.*(..))")
	public void pointcut() {

	}

	@Override
	public String getServiceName() {
		return "commonSpringMvc";
	}
}
