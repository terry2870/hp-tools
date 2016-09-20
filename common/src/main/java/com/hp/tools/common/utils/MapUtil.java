package com.hp.tools.common.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;

/**
 * map的工具类
 * 
 * @author MALI
 */
public class MapUtil {
	/**
	 * Iterator 转换成map
	 * 
	 * @param values
	 * @param keyFunction
	 * @return
	 */
	public static final <K, V> Map<K, V> list2map(Iterable<V> values, Function<K, ? super V> keyFunction) {
		if (Iterables.isEmpty(values) || null == keyFunction) {
			return Maps.newHashMap();
		}

		Map<K, V> builder = new HashMap<K, V>();
		Iterator<V> iterator = values.iterator();
		while (iterator.hasNext()) {
			V value = iterator.next();
			builder.put(keyFunction.apply(value), value);
		}
		return builder;
	}

	/**
	 * Iterator 转换成map
	 * T-原value值
	 * K-新的key值
	 * V-新的value值
	 * @param values
	 * @param keyFunction
	 * @return
	 */
	public static final <T, K, V> Map<K, V> list2map(Iterable<T> values, FunctionExPlus<T, K, V> keyFunction) {
		if (Iterables.isEmpty(values) || null == keyFunction) {
			return Maps.newHashMap();
		}
		Map<K, V> builder = new HashMap<K, V>();
		Iterator<T> iterator = values.iterator();
		while (iterator.hasNext()) {
			T value = iterator.next();
			builder.put(keyFunction.applyKey(value), keyFunction.applyValue(value));
		}
		return builder;
	}

	public static interface Function<K, V> {
		K apply(V input);
	}

	public static interface FunctionExPlus<T, K, V> {
		K applyKey(T input);
		V applyValue(T input);
	}

}