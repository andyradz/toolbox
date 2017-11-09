package com.codigo.aplios.toolbox.xbase.core.version;

import java.util.EnumSet;

import com.codigo.aplios.toolbox.xbase.core.field.XbFieldType;

// TODO: dodać włąsciwości limitów dla tabeli
public enum XbFileVersion1 {

	DBASE_3((byte) 0x03, XbTableFormats.XBASE3),
	DBASE_4((byte) 0x03, XbTableFormats.XBASE4),
	DBASE_5((byte) 0x03, XbTableFormats.XBASE5),
	CLIPPER_5((byte) 0x03, XbTableFormats.CLIPPER_5),
	FOXPRO_26((byte) 0x03, XbTableFormats.CLIPPER_5);

	public enum XbTableFormats {
		XBASE2 {
			@Override
			public EnumSet<XbFieldType> fieldTypes() {

				return EnumSet.of(XbFieldType.CHARACTER, XbFieldType.NUMERIC);
			}
		},
		XBASE3 {
			@Override
			public EnumSet<XbFieldType> fieldTypes() {

				return EnumSet.of(XbFieldType.CHARACTER, XbFieldType.NUMERIC);
			}
		},
		XBASE4 {
			@Override
			public EnumSet<XbFieldType> fieldTypes() {

				return EnumSet.of(XbFieldType.CHARACTER, XbFieldType.NUMERIC);
			}
		},
		XBASE5 {
			@Override
			public EnumSet<XbFieldType> fieldTypes() {

				return EnumSet.of(XbFieldType.CHARACTER, XbFieldType.NUMERIC);
			}
		},
		CLIPPER_5 {
			@Override
			public EnumSet<XbFieldType> fieldTypes() {

				return EnumSet.of(XbFieldType.CHARACTER, XbFieldType.NUMERIC);
			}
		},
		FOXPRO_5 {
			@Override
			public EnumSet<XbFieldType> fieldTypes() {

				return EnumSet.of(XbFieldType.CHARACTER, XbFieldType.NUMERIC);
			}
		};

		public abstract EnumSet<XbFieldType> fieldTypes();
	}

	XbFileVersion1(byte byteVersion, XbFileVersion1.XbTableFormats format) {

		this.byteVersion = byteVersion;
		this.types = format;
	}

	private final byte byteVersion;
	private final XbFileVersion1.XbTableFormats types;
}
