package com.maoshen.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

/**
 * http://www.myhack58.com/Article/html/3/7/2012/36142_6.htm
 * 
 * 
 * @since 2014年8月14日 上午10:03:26
 */
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {

    private static final Logger LOGGER = Logger.getLogger(XssHttpServletRequestWrapper.class);

    public XssHttpServletRequestWrapper(HttpServletRequest servletRequest) {
        super(servletRequest);
        LOGGER.info("XssHttpServletRequestWrapper init");
    }

    @Override
    public String[] getParameterValues(String parameter) {
        String[] values = super.getParameterValues(parameter);
        if (values == null) {
            return null;
        }
        int count = values.length;
        String[] encodedValues = new String[count];
        for (int i = 0; i < count; i++) {
            encodedValues[i] = cleanXSS(values[i]);
        }
        return encodedValues;
    }

    @Override
    public String getParameter(String parameter) {
        String value = super.getParameter(parameter);
        return cleanXSS(value);
    }

    @Override
    public String getHeader(String name) {
        String value = super.getHeader(name);
        return cleanXSS(value);
    }

    private static final String REGEX_LT           = "<";

    private static final String REGEX_LT_T         = "&lt;";

    private static final String REGEX_RT           = ">";

    private static final String REGEX_RT_T         = "&gt;";

    private static final String REGEX_40           = "\\(";

    private static final String REGEX_40_T         = "&#40;";

    private static final String REGEX_41           = "\\)";

    private static final String REGEX_41_T         = "&#41;";

    private static final String REGEX_39           = "'";

    private static final String REGEX_39_T         = "&#39;";

    private static final String REGEX_EVAL         = "eval\\((.*)\\)";

    private static final String REGEX_JAVASCRIPT   = "[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']";

    private static final String REGEX_JAVASCRIPT_T = "\"\"";

    private static final String REGEX_SCRIPT       = "script";

    private String cleanXSS(String value) {
        if (value == null)
            return null;
        LOGGER.debug("before:" + value);
        value = value.replaceAll(REGEX_LT, REGEX_LT_T).replaceAll(REGEX_RT, REGEX_RT_T);
        value = value.replaceAll(REGEX_40, REGEX_40_T).replaceAll(REGEX_41, REGEX_41_T);
        value = value.replaceAll(REGEX_39, REGEX_39_T);
        value = value.replaceAll(REGEX_EVAL, StringUtils.EMPTY);
        value = value.replaceAll(REGEX_JAVASCRIPT, REGEX_JAVASCRIPT_T);
        value = value.replaceAll(REGEX_SCRIPT, StringUtils.EMPTY);
        LOGGER.debug("after:" + value);
        return value;
    }

}