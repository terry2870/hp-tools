/**
 * 
 */
package com.my.tools.common.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * @author ping.huang
 * 2016年7月30日
 */
public class StringUtil {

	/**
	 * 驼峰转数据库字段名
	 * @param fieldName
	 * @return
	 */
	public static String toDBColumn(String fieldName) {
		if (StringUtils.isEmpty(fieldName)) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		String[] arr = fieldName.split("");
		String reg = "[A-Z]";
		for (String s : arr) {
			if (s.matches(reg)) {
				sb.append("_").append(s.toLowerCase());
			} else {
				sb.append(s);
			}
		}
		return sb.toString();
	}
}
