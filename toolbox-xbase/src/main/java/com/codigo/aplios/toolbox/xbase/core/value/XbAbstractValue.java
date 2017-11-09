package com.codigo.aplios.toolbox.xbase.core.value;

import com.codigo.aplios.toolbox.xbase.core.field.XbFieldInfo;

/**
*
* @author andrzej.radziszewski
*/
abstract class XbAbstractValue implements IXbFieldValue {

	private final XbFieldInfo fieldInfo;
	private final byte[] rawValue;

	protected XbAbstractValue(final XbFieldInfo fieldInfo) {

		this(fieldInfo, new byte[] {});
	}

	protected XbAbstractValue(final XbFieldInfo fieldInfo, final byte[] rawValue) {

		this.fieldInfo = fieldInfo;
		this.rawValue = rawValue;
	}

	@Override
	public byte[] getValue() {

		return this.rawValue;
	}
}