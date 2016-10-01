/**
 * 
 */
package com.hp.tools.common.utils;

import java.text.DecimalFormat;

import org.apache.commons.lang3.StringUtils;

/**
 * @author ping.huang 2016年7月30日
 */
public class StringUtil {

	/**
	 * 驼峰转数据库字段名
	 * 
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

	/**
	 * 保留指定位数的小数
	 * 
	 * @param d
	 * @param num
	 * @return
	 */
	public static String getNumber(double d, int num) {
		String str = "0.";
		for (int i = 0; i < num; i++) {
			str += "0";
		}
		DecimalFormat df = new DecimalFormat(str);
		return df.format(d);
	}
}
