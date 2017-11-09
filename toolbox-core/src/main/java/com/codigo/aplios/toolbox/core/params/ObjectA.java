package com.codigo.aplios.toolbox.core.params;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

class ObjectA<T> {

	public static void main(String[] args) {

		Type type = ObjectB.class.getGenericSuperclass();
		System.out.println(type);

		ParameterizedType ptype = (ParameterizedType) type;
		System.out.println(ptype.getActualTypeArguments()[0]);
	}

	protected String name;

	public ObjectA() {

		// TODO Auto-generated constructor stub
	}
}

class ObjectB<T> extends ObjectA<Integer> {
	public ObjectB() {

		super();
	}

	public void regMe() {

	}
}