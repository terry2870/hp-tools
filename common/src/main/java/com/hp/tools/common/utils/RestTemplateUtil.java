/**
 * 
 */
package com.hp.tools.common.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.hp.tools.common.beans.Response;

/**
 * @author ping.huang 2016年10月10日
 */
public class RestTemplateUtil {

	private static Logger log = LoggerFactory.getLogger(RestTemplateUtil.class);

	/**
	 * 使用json格式调用post请求
	 * 
	 * @param restTemplate
	 * @param url
	 * @param request
	 * @param responseClass
	 * @return
	 */
	public static Response<?> postJSON(RestTemplate restTemplate, String url, Object request) {
		log.info("call postJSON with url={}, request={}", url, request);
		HttpHeaders headers = new HttpHeaders();
		MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
		headers.setContentType(type);
		headers.add("Accept", MediaType.APPLICATION_JSON.toString());
		HttpEntity<String> entity = new HttpEntity<String>(JSON.toJSONString(request), headers);
		Response<?> response = restTemplate.postForObject(url, entity, Response.class);
		return response;
	}
	
	/**
	 * 使用form格式调用post的请求
	 * @param restTemplate
	 * @param url
	 * @param request
	 * @param responseClass
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Response<?> postForm(RestTemplate restTemplate, String url, Object request) {
		log.info("call postForm with url={}, request={}", url, request);
		List<String> paramList = new ArrayList<>();
		if (request != null) {
			if (request instanceof Map) {
				for (Entry<String, Object> entry : ((Map<String, Object>) request).entrySet()) {
					paramList.add(entry.getKey() + "=" + entry.getValue().toString());
				}
			} else {
				try {
					Field[] declaredFields = request.getClass().getDeclaredFields();
					for (Field field : declaredFields) {
						field.setAccessible(true);
						// 过滤内容为空的
						if (field.get(request) == null) {
							continue;
						}
						paramList.add(field.getName() + "=" + field.get(request).toString());
					}
				} catch (IllegalAccessException e) {
					log.error("", e);
				}
			}
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		HttpEntity<String> entity = new HttpEntity<String>(StringUtils.join(paramList, "&"), headers);
		Response<?> response = restTemplate.postForObject(url, entity, Response.class);
		return response;
	}
	
	/**
	 * 发送get请求
	 * @param restTemplate
	 * @param url
	 * @param responseClass
	 * @return
	 */
	public static Response<?> get(RestTemplate restTemplate, String url) {
		log.info("call get with url={}", url);
		Response<?> response = restTemplate.getForObject(url, Response.class);
		return response;
	}
	
}
