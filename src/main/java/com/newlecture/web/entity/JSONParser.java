package com.newlecture.web.entity;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class JSONParser {
	
	public static String toJSON(Object object) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, SecurityException {
	
	Class<?> clazz = object.getClass();	
		
	Field[] fields = clazz.getClass().getDeclaredFields();
	//Method method = clazz.getDeclaredMethod(name, parameterTypes);
	//Method[] methods = clazz.getDeclaredMethods();
	
	StringBuilder json = new StringBuilder();
	json.append("{");
	for(Field field : fields) {
		
		String name = field.getName();
		String getterName = "get" 
								+ Character.toUpperCase(name.charAt(0))
								+ name.substring(1);
		
		Method method = clazz.getDeclaredMethod(getterName, new Class[] {});
		
		String value = String.valueOf(method.invoke(object));
		
		
		json.append(String.format("\"%s\":\"\"", name,value));
	}
	json.append("}");
	
	return json.toString();
	}
}
