package com.codigo.aplios.toolbox.core.gui.sheet;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

public class ExtendedPropertyDescriptor extends PropertyDescriptor {

	private Class<?>	tableCellRendererClass	= null;
	private String		category				= "";

	public ExtendedPropertyDescriptor(String propertyName, Class<?> beanClass) throws IntrospectionException {

		super(propertyName,
			beanClass);
	}

	public ExtendedPropertyDescriptor(String propertyName, Method getter, Method setter) throws IntrospectionException {

		super(propertyName,
			getter,
			setter);
	}

	public ExtendedPropertyDescriptor(String propertyName, Class<?> beanClass, String getterName, String setterName)
			throws IntrospectionException {

		super(propertyName,
			beanClass,
			getterName,
			setterName);
	}

	/**
	 * Sets this property category
	 *
	 * @param category
	 * @return this property for chaining calls.
	 */
	public ExtendedPropertyDescriptor setCategory(String category) {

		this.category = category;
		return this;
	}

	/**
	 * @return the category in which this property belongs
	 */
	public String getCategory() {

		return this.category;
	}

	/**
	 * Force this property to be readonly
	 *
	 * @return this property for chaining calls.
	 */
	public ExtendedPropertyDescriptor setReadOnly() {

		try {
			this.setWriteMethod(null);
		}
		catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return this;
	}

	/**
	 * You can associate a special tablecellrenderer with a particular Property. If set to null default renderer will be
	 * used.
	 *
	 * @param tableCellRendererClass
	 */
	public void setPropertyTableRendererClass(Class<?> tableCellRendererClass) {

		this.tableCellRendererClass = tableCellRendererClass;
	}

	/**
	 * @return null or a custom TableCellRenderer-Class for this property
	 */
	public Class<?> getPropertyTableRendererClass() {

		return this.tableCellRendererClass;
	}

	public static ExtendedPropertyDescriptor newPropertyDescriptor(String propertyName, Class<?> beanClass)
			throws IntrospectionException {

		// the same initialization phase as in the PropertyDescriptor
		Method readMethod = BeanUtils.getReadMethod(beanClass, propertyName);
		Method writeMethod = null;

		if (readMethod == null) throw new IntrospectionException("No getter for property "
																		+ propertyName
																		+ " in class "
																		+ beanClass.getName());

		writeMethod = BeanUtils.getWriteMethod(beanClass, propertyName, readMethod.getReturnType());

		return new ExtendedPropertyDescriptor(propertyName,
			readMethod,
			writeMethod);
	}
}

// public static final Comparator<PropertyDescriptor> BY_CATEGORY_COMPARATOR = (desc1, desc2) -> {
// };
// }

// if ((desc1 == null) && (desc2 == null))
// return 0;
// else if ((desc1 != null) && (desc2 == null))
// return 1;
// else if ((desc1 == null) && (desc2 != null))
// return -1;
// else if ((desc1 instanceof ExtendedPropertyDescriptor) && !(desc2 instanceof ExtendedPropertyDescriptor))
// return -1;
// else if (!(desc1 instanceof ExtendedPropertyDescriptor) && (desc2 instanceof ExtendedPropertyDescriptor))
// return 1;
// else if (!(desc1 instanceof ExtendedPropertyDescriptor) && !(desc2 instanceof ExtendedPropertyDescriptor))
// return String.CASE_INSENSITIVE_ORDER.compare(desc1.getDisplayName(), desc2.getDisplayName());
// else {
// int category = String.CASE_INSENSITIVE_ORDER.compare(
// ((ExtendedPropertyDescriptor) desc1).getCategory() == null
// ? ""
// : ((ExtendedPropertyDescriptor) desc1).getCategory(),
// ((ExtendedPropertyDescriptor) desc2).getCategory() == null
// ? ""
// : ((ExtendedPropertyDescriptor) desc2).getCategory());
//
// if (category == 0)
// return String.CASE_INSENSITIVE_ORDER.compare(desc1.getDisplayName(), desc2.getDisplayName());
// else
// return category;
// }
// };
