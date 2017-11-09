package com.codigo.aplios.toolbox.xbase.core.field;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.nio.charset.Charset;

interface IXbField {

	String getName();

	int getLenght();

	int getDecimalPlaces();

	XbFieldType getFieldType();

}

class XbField implements IXbField {

	protected static IXbField createField(DataInput data, Charset charset) {

		return new XbField();
	}

	protected void write(DataOutput out, Charset charset) throws IOException {

		// Field Name
		out.write(this.fieldName.getBytes(charset));
		/*
		 * 0-10
		 */
		out.write(new byte[11 - this.fieldName.length()]);

		// data type
		out.writeByte(this.type.getFieldCode());
		/*
		 * 11
		 */
		out.writeInt(0x00);
		/*
		 * 12-15
		 */
		out.writeByte(this.length);
		/*
		 * 16
		 */
		out.writeByte(this.decimalCount);
		/*
		 * 17
		 */
		out.writeShort((short) 0x00);
		/*
		 * 18-19
		 */
		out.writeByte((byte) 0x00);
		/*
		 * 20
		 */
		out.writeShort((short) 0x00);
		/*
		 * 21-22
		 */
		out.writeByte((byte) 0x00);
		/*
		 * 23
		 */
		out.write(new byte[7]);
		/*
		 * 24-30
		 */
		out.writeByte((byte) 0x00);
		/*
		 * 31
		 */
	}

	protected static IXbField createField(byte[] data, Charset charset) {

		return new XbField();
	}
	// <editor-fold defaultstate="collapsed" desc=" Constructor(s) ">

	public XbField() {

		super();
	}

	public XbField(String fieldName, XbFieldType fieldType) {

		this(fieldName, fieldType, 0);
	}

	public XbField(String fieldName, XbFieldType fieldType, int fieldSize) {

		this(fieldName, fieldType, fieldSize, 0);
	}

	public XbField(String fieldName, XbFieldType fieldType, int fieldSize, int fieldDecimalPlaces) {

		// TODO: inialize
	}

	public XbField(XbField origin) {

		this.fieldName = origin.fieldName;
		// TODO:dodac
	}

	// </editor-fold>
	// -----------------------------------------------------------------------------------------------------------------
	@Override
	public String getName() {

		return this.fieldName;
	}

	@Override
	public int getLenght() {

		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getDecimalPlaces() {

		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public XbFieldType getFieldType() {

		// TODO Auto-generated method stub
		return null;
	}

	// <editor-fold defaultstate="collapsed" desc=" Member(s) ">
	private static final Charset charset = Charset.forName("ASCII");

	private XbFieldType type;

	/*
	 * 11
	 */
	private int reserv1;

	/*
	 * 12-15
	 */
	private int length;

	/*
	 * 16
	 */
	private byte decimalCount;

	/*
	 * 17
	 */
	private short reserv2;

	/*
	 * 18-19
	 */
	private byte workAreaId;

	/*
	 * 20
	 */
	private short reserv3;

	/*
	 * 21-22
	 */
	private byte setFieldsFlag;

	/*
	 * 23
	 */
	private byte[] reserv4 = new byte[7];

	/*
	 * 24-30
	 */
	private byte indexFieldFlag;

	/*
	 * 31
	 */
	private String fieldName;
	// </editor-fold>

}
