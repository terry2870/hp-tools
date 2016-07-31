package com.my.tools.common.enums;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public enum YesOrNoEnum {

	
	YES(1, "Y"),
	NO(0, "N");
	
	public int getValue() {
		return value;
	}

	public String getText() {
		return text;
	}

	private int value;
	private String text;

	private YesOrNoEnum(int value, String text) {
		this.value = value;
		this.text = text;
	}
	
	
	/**
	 * 返回json格式的数据
	 * @param value
	 * @return
	 */
	public static JSONArray toJSON(int value) {
		JSONArray arr = new JSONArray();
		JSONObject json = null;
		for (YesOrNoEnum e : values()) {
			json = new JSONObject();
			json.put("value", e.getValue());
			json.put("text", e.getText());
			arr.add(json);
		}
		return arr;
	}
	
	/**
	 * 根据value值，获取枚举对象
	 * @param value
	 * @return
	 */
	public static YesOrNoEnum getEnumByValue(int value) {
		for (YesOrNoEnum e : values()) {
			if (value == e.getValue()) {
				return e;
			}
		}
		return null;
	}
	
}
