package com.java.my;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Class bookService = null;
		try {
			bookService = Class.forName("com.java.my.Test");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //  User.class 도 가능
	     
	    System.out.println("====Field List====");
	     
	    for(Field field : bookService.getFields()){
	        System.out.println(field.getName());
	    }
	     
	    System.out.println("\n====Constructor List====");
	     
	    for(Constructor constructor : bookService.getConstructors()){
	         
	        System.out.print("개수  " + constructor.getParameterCount() + " = ");
	         
	        for(Class parameter : constructor.getParameterTypes()){
	            System.out.print(parameter.getName() + " / ");
	        }
	         
	        System.out.println();
	    }
	     
	    System.out.println("\n====Method List====");
	     
	    for(Method method : bookService.getMethods()){
	        System.out.println(method.getName());
	    }

	}

}
