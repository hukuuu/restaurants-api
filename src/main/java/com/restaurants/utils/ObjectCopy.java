package com.restaurants.utils;

import java.lang.reflect.Field;

public class ObjectCopy {

	public void copy(Object dest, Object orig) throws IllegalArgumentException, IllegalAccessException {
		Class<? extends Object> claz = dest.getClass();
		Field[] declaredFields = claz.getDeclaredFields();
		for (Field field : declaredFields) {
			field.setAccessible(true);
			Object value = field.get(orig);
			if(value != null) {
				field.set(dest, value);
			}
		}		
	}
}
