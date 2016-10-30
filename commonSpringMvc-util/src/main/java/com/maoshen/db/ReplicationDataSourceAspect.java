package com.maoshen.db;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

public class ReplicationDataSourceAspect {
    private static final Logger LOGGER = Logger.getLogger(ReplicationDataSourceAspect.class);

    public void before(JoinPoint point) {
        Object targetObject = point.getTarget();
        String method = point.getSignature().getName();

        Class<?>[] clazz = targetObject.getClass().getInterfaces();
        Class<?>[] parameterTypes = ((MethodSignature) point.getSignature()).getMethod()
                .getParameterTypes();

        try {
            Method m = clazz[0].getMethod(method, parameterTypes);
            if (null != m && m.isAnnotationPresent(DataSource.class)) {
                DataSource source = m.getAnnotation(DataSource.class);
                ReplicationDataSourceHolder.putDataSource(source.value());
                LOGGER.info("==============" + source.value() + "=============");
            }
        } catch (NoSuchMethodException e) {
            LOGGER.error(e.getMessage(), e);
        } catch (SecurityException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
