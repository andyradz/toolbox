package com.codigo.aplios.toolbox.core.gui.sheet;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;

public abstract class AbstractProperty implements Property {

	/**
	 *
	 */
	private static final long serialVersionUID = 130167621092787765L;

	private Object value;

	// PropertyChangeListeners are not serialized.
	private transient PropertyChangeSupport listeners = new PropertyChangeSupport(this);

	@Override
	public Object getValue() {

		return this.value;
	}

	@Override
	public Object clone() {

		AbstractProperty clone = null;
		try {
			clone = (AbstractProperty) super.clone();
			return clone;
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void setValue(Object value) {

		Object oldValue = this.value;
		this.value = value;
		if ((value != oldValue) && ((value == null) || !value.equals(oldValue)))
			this.firePropertyChange(oldValue, this.getValue());
	}

	protected void initializeValue(Object value) {

		this.value = value;
	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {

		this.listeners.addPropertyChangeListener(listener);
		Property[] subProperties = this.getSubProperties();
		if (subProperties != null)
			for (Property subPropertie : subProperties)
				subPropertie.addPropertyChangeListener(listener);
	}

	@Override
	public void removePropertyChangeListener(PropertyChangeListener listener) {

		this.listeners.removePropertyChangeListener(listener);
		Property[] subProperties = this.getSubProperties();
		if (subProperties != null)
			for (Property subPropertie : subProperties)
				subPropertie.removePropertyChangeListener(listener);
	}

	protected void firePropertyChange(Object oldValue, Object newValue) {

		this.listeners.firePropertyChange("value", oldValue, newValue);
	}

	private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {

		in.defaultReadObject();
		this.listeners = new PropertyChangeSupport(this);
	}

	@Override
	public Property getParentProperty() {

		return null;
	}

	@Override
	public Property[] getSubProperties() {

		return null;
	}
}
