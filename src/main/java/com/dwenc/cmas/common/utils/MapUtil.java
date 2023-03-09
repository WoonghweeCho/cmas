package com.dwenc.cmas.common.utils;

import java.util.HashMap;
import java.util.Map;

import docfbaro.common.Constants;

public class MapUtil {
	public static Map<String, Object> objectToMap(Object obj) {
		
		java.lang.reflect.Field[] fields = obj.getClass().getDeclaredFields();
		Map<String, Object> map = new HashMap<String, Object>();

		for (int i = 0; i < fields.length; i++) {

			// private 변수에 접근 막음
			fields[i].setAccessible(false);
			try {

				// 변수 명을 key로 value 저장.
				map.put(fields[i].getName(), fields[i].get(obj));
			} catch (IllegalArgumentException e) {
			} catch (IllegalAccessException e) {
			}
		}
		return map;
	}

	public static void main(String[] args) {
		Constants constants = new Constants();
		Map<String, Object> map = MapUtil.objectToMap(constants);
		System.out.println(map);
	}
}
