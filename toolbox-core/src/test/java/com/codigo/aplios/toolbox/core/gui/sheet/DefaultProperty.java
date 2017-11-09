package com.codigo.aplios.toolbox.core.gui.sheet;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * DefaultProperty. <br>
 *
 */
public class DefaultProperty extends AbstractProperty {

	private static final long serialVersionUID = -1985210935837921803L;

	private String name;
	private String displayName;
	private String shortDescription;
	private Class<?> type;
	private boolean editable = true;
	private String category;
	private Property parent;
	private List<Property> subProperties = new ArrayList<>();

	@Override
	public String getName() {

		return this.name;
	}

	public void setName(String name) {

		this.name = name;
	}

	@Override
	public String getDisplayName() {

		return this.displayName;
	}

	public void setDisplayName(String displayName) {

		this.displayName = displayName;
	}

	@Override
	public String getShortDescription() {

		return this.shortDescription;
	}

	public void setShortDescription(String shortDescription) {

		this.shortDescription = shortDescription;
	}

	@Override
	public Class<?> getType() {

		return this.type;
	}

	public void setType(Class<?> type) {

		this.type = type;
	}

	@Override
	public boolean isEditable() {

		return this.editable;
	}

	public void setEditable(boolean editable) {

		this.editable = editable;
	}

	@Override
	public String getCategory() {

		return this.category;
	}

	public void setCategory(String category) {

		this.category = category;
	}

	/**
	 * Reads the value of this Property from the given object. It uses reflection and looks for a method starting with
	 * "is" or "get" followed by the capitalized Property name.
	 */
	@Override
	public void readFromObject(Object object) {

		try {
			Method method = BeanUtils.getReadMethod(object.getClass(), this.getName());
			if (method != null) {
				Object value = method.invoke(object);
				this.initializeValue(value); // avoid updating parent or firing
				// property change
				if (value != null)
					for (Property subProperty : this.subProperties)
						subProperty.readFromObject(value);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Writes the value of the Property to the given object. It uses reflection and looks for a method starting with
	 * "set" followed by the capitalized Property name and with one parameter with the same type as the Property.
	 */
	@Override
	public void writeToObject(Object object) {

		try {
			Method method = BeanUtils.getWriteMethod(object.getClass(), this.getName(), this.getType());
			if (method != null)
				method.invoke(object, new Object[] { this.getValue() });
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.l2fprod.common.propertysheet.Property#setValue(java.lang.Object)
	 */
	@Override
	public void setValue(Object value) {

		super.setValue(value);
		if (this.parent != null) {
			Object parentValue = this.parent.getValue();
			if (parentValue != null) {
				this.writeToObject(parentValue);
				this.parent.setValue(parentValue);
			}
		}
		if (value != null)
			for (Property subProperty : this.subProperties)
				subProperty.readFromObject(value);
	}

	@Override
	public int hashCode() {

		return 28 + ((this.name != null) ? this.name.hashCode()
				: 3)
				+ ((this.displayName != null) ? this.displayName.hashCode()
						: 94)
				+ ((this.shortDescription != null) ? this.shortDescription.hashCode()
						: 394)
				+ ((this.category != null) ? this.category.hashCode()
						: 34)
				+ ((this.type != null) ? this.type.hashCode()
						: 39)
				+ Boolean.valueOf(this.editable)
					.hashCode();
	}

	/**
	 * Compares two DefaultProperty objects. Two DefaultProperty objects are equal if they are the same object or if
	 * their name, display name, short description, category, type and editable property are the same. Note the property
	 * value is not considered in the implementation.
	 */
	@Override
	public boolean equals(Object other) {

		if ((other == null) || (this.getClass() != other.getClass()))
			return false;

		if (other == this)
			return true;

		DefaultProperty dp = (DefaultProperty) other;

		return this.compare(this.name, dp.name) && this.compare(this.displayName, dp.displayName)
				&& this.compare(this.shortDescription, dp.shortDescription) && this.compare(this.category, dp.category)
				&& this.compare(this.type, dp.type) && (this.editable == dp.editable);
	}

	private boolean compare(Object o1, Object o2) {

		return (o1 != null) ? o1.equals(o2)
				: o2 == null;
	}

	@Override
	public String toString() {

		return "name=" + this.getName() + ", displayName=" + this.getDisplayName() + ", type=" + this.getType()
				+ ", category=" + this.getCategory() + ", editable=" + this.isEditable() + ", value=" + this.getValue();
	}

	@Override
	public Property getParentProperty() {

		return this.parent;
	}

	public void setParentProperty(Property parent) {

		this.parent = parent;
	}

	@Override
	public Property[] getSubProperties() {

		return this.subProperties.toArray(new Property[this.subProperties.size()]);
	}

	public void clearSubProperties() {

		for (Property subProp : this.subProperties)
			if (subProp instanceof DefaultProperty)
				((DefaultProperty) subProp).setParentProperty(null);
		this.subProperties.clear();
	}

	public void addSubProperties(Collection<Property> subProperties) {

		this.subProperties.addAll(subProperties);
		for (Property subProp : this.subProperties)
			if (subProp instanceof DefaultProperty)
				((DefaultProperty) subProp).setParentProperty(this);
	}

	public void addSubProperties(Property[] subProperties) {

		this.addSubProperties(Arrays.asList(subProperties));
	}

	public void addSubProperty(Property subProperty) {

		this.subProperties.add(subProperty);
		if (subProperty instanceof DefaultProperty)
			((DefaultProperty) subProperty).setParentProperty(this);
	}
}
