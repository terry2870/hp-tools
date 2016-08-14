package com.hp.tools.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 时间转换帮助类
 *
 */
public class DateUtil {

	static Logger log = LoggerFactory.getLogger(DateUtil.class);

	// 基础(通用)的FORMAT类型,
	public static final String NORMAL_FORMAT = "yyyy-MM-dd";

	// 完整格式的时间
	public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	// 默认格式的时间
	public static final String DEFAULT_DATE_TIME_FORMAT = DATE_TIME_FORMAT;

	/**
	 * 判断当前时间是不是在22点到第二天8点之间
	 * 
	 * @return
	 */
	public static boolean isNotDisturbTime() {
		String time = DateUtil.getToday("HH:mm:ss");
		return time.compareTo("22:00:00") > 0 || time.compareTo("08:00:00") < 0;
		// return time.compareTo("18:00:00") > 0 || time.compareTo("08:00:00") <
		// 0;
	}

	public static void main(String[] args) {
		System.out.println(isNotDisturbTime());
		System.out.println("19:36:00".compareTo("18:00:00"));
		System.out.println("19:36:00".compareTo("08:00:00"));
	}

	/**
	 * 获取当前时间
	 * 
	 * @return
	 */
	public static Date getCurrentDate() {
		return new Date();
	}

	/**
	 * 获取当前时间, 并且将当前时间转换称INT类型
	 *
	 * @return 当前时间的INT类型
	 */
	public static int getCurrentTimeSeconds() {
		long longTime = getCurrentDate().getTime();
		return (int) (longTime / 1000);
	}

	/**
	 * 将通过getTime获得的long型的时间，转换成format类型的时间，如"2015-08-09"
	 *
	 * @param time
	 *            int型的时间戳
	 * @param format
	 *            需要转化的类型 “yyyy-MM-dd”
	 * @return String "2015-08-09"
	 */
	public static String int2DateStr(int time, String... format) {
		if (time == 0) {
			return "";
		}
		Date date = new Date((long) time * 1000);
		return dateToString(date, format);
	}

	/**
	 * 获取字符串格式的时间秒数
	 * 
	 * @param time
	 * @return
	 */
	public static int string2Int(String time, String... format) {
		if (StringUtils.isEmpty(time)) {
			return 0;
		}
		Date d = string2Date(time, format);
		return (int) (d.getTime() / 1000);
	}

	/**
	 * 根据字符串和格式获取date对象
	 */
	public static Date string2Date(String time, String... format) {
		if (StringUtils.isEmpty(time)) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(ArrayUtils.isEmpty(format) ? DEFAULT_DATE_TIME_FORMAT : format[0]);
		Date date = null;
		try {
			date = sdf.parse(time);
		} catch (ParseException e) {
			log.error("string2Date error. with time={}, format={}", time, format);
		}
		return date;

	}

	/**
	 * 按照格式，取得当前时间
	 * 
	 * @param str
	 * @return
	 */
	public static String getToday(String str) {
		return dateToString(getCurrentDate(), str);
	}

	/**
	 * ͳDate型日期转换为String型
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String dateToString(Date date, String... format) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(ArrayUtils.isEmpty(format) ? DEFAULT_DATE_TIME_FORMAT : format[0]);
		return sdf.format(date);
	}
}
