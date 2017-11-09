package com.codigo.aplios.toolbox.core.metric;

public class Property<T> implements IProperty<T> {

	public static <T> T create(Class<T> clazz) throws Exception {

		return clazz.newInstance();
	}

	// dodać klonowanie jako wzorzez protonyme np z wartośią domyslną
	private T value;

	@Override
	public void set(T value) {

		this.value = value;
	}

	@Override
	public T get() {

		return this.value;
	}

	public boolean isPrimitive() {

		final Class<?> clazz = this.value.getClass();
		return clazz.equals(Boolean.class) || clazz.equals(Integer.class) || clazz.equals(Character.class)
				|| clazz.equals(Byte.class) || clazz.equals(Short.class) || clazz.equals(Double.class)
				|| clazz.equals(Long.class) || clazz.equals(Float.class);
	}
}
