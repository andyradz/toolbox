package com.codigo.aplios.toolbox.core.gui.sheet;

import java.lang.reflect.Method;

public class BeanUtils {

	private BeanUtils() {

	}

	public static Method getReadMethod(Class<?> clazz, String propertyName) {

		Method readMethod = null;
		String base = BeanUtils.capitalize(propertyName);

		// Since there can be multiple setter methods but only one getter
		// method, find the getter method first so that you know what the
		// property type is. For booleans, there can be "is" and "get"
		// methods. If an "is" method exists, this is the official
		// reader method so look for this one first.

		try {
			readMethod = clazz.getMethod("is" + base, (Class<?>[]) null);
		} catch (Exception getterExc) {
			try {
				// no "is" method, so look for a "get" method.
				readMethod = clazz.getMethod("get" + base, (Class<?>[]) null);
			} catch (Exception e) {
				// fall thru, no is and no get, we will return null
			}
		}

		return readMethod;
	}

	public static Method getWriteMethod(Class<?> clazz, String propertyName, Class<?> propertyType) {

		Method writeMethod = null;
		String base = BeanUtils.capitalize(propertyName);

		Class<?> params[] = { propertyType };
		try {
			writeMethod = clazz.getMethod("set" + base, params);
		} catch (Exception e) {
			// no write method
		}

		return writeMethod;
	}

	private static String capitalize(String s) {

		if (s.length() == 0)
			return s;
		else {
			char chars[] = s.toCharArray();
			chars[0] = Character.toUpperCase(chars[0]);
			return String.valueOf(chars);
		}
	}

}
