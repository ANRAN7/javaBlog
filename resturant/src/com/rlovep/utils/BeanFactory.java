package com.rlovep.utils;

import java.util.ResourceBundle;

public class BeanFactory {
	private static ResourceBundle bundle=null;
	static{
		bundle=ResourceBundle.getBundle("instance");
	}
	public static <T> T getInstance(String key,Class<T>clazz) {
		try {
			String name=bundle.getString(key);
			return (T)Class.forName(name).newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
