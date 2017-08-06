/**
 * 
 */
package com.hp.jsptags.tags;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;

import org.apache.commons.lang3.StringUtils;

import com.hp.jsptags.ognl.OgnlCache;

/**
 * @author huangping
 * 2017年8月4日 下午11:59:58
 */
public class IfTag extends BaseTagSupport {
	
	private static final long serialVersionUID = -9013915511433124222L;
	private String test;
	
	public int doStartTag() throws JspException {
		super.doStartTag();
		if (StringUtils.isEmpty(test)) {
			log.warn("IfTag error. with test is empty");
			return SKIP_BODY;
		}

		Object value = OgnlCache.getValue(test, getDataFromRequest(this.request));
		if (Boolean.TRUE.equals(value)) {
			return EVAL_BODY_INCLUDE;
		} else {
			return SKIP_BODY;
		}
	}
	
	private Map<String, Object> getDataFromRequest(HttpServletRequest request) {
		//先从session中，再取attribute，再取parameter
		Map<String, Object> map = new HashMap<>();
		
		//session
		Enumeration<String> enu = request.getSession().getAttributeNames();
		String key = null;
		while (enu.hasMoreElements()) {
			key = enu.nextElement();
			map.put(key, request.getSession().getAttribute(key));
		}
		
		//attribute
		enu = request.getAttributeNames();
		while (enu.hasMoreElements()) {
			key = enu.nextElement();
			map.put(key, request.getAttribute(key));
		}
		
		//parameter
		enu = request.getParameterNames();
		while (enu.hasMoreElements()) {
			key = enu.nextElement();
			map.put(key, request.getParameter(key));
		}
		return map;
	}
	
	public void setTest(String test) {
		this.test = test;
	}
	
	
}
