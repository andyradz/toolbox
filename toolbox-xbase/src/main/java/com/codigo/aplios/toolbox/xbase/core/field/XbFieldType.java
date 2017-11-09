package com.codigo.aplios.toolbox.xbase.core.field;

import java.util.stream.Stream;

/**
 * Klasa przentuje znaczniki odpowiedający odpowiednim typom pól dostępnych dla zbioru DBase.
 *
 * @author andrzej.radziszewski
 * @version 1.0.0.0
 * @since 2017
 * @category enumeration
 */
public enum XbFieldType {

	/**
	 * Default unknown type
	 */
	UNKNOWN((byte) 0),
	/*
	 * Character data, padded with whitespaces.
	 */
	CHARACTER('C', 1, 254, 0, true),
	/**
	 * Character data, not padded
	 */
	VARCHAR('V', 1, 254, 0, false),
	/**
	 * Binary data
	 */
	VARBINARY('Q', 1, 254, 0, false),
	/**
	 * Date
	 */
	DATE('D', 8, 8, 8, true),
	/**
	 * Numeric data
	 */
	FLOATING_POINT('F', 1, 20, 0, true),
	/**
	 * Double value
	 */
	DOUBLE('O', 8, 8, 0, false),
	/**
	 * To store boolean values.
	 */
	LOGICAL('L', 1, 1, 1, true),
	/**
	 * Memo (data is stored in dbt file)
	 */
	MEMO('M'),
	/**
	 * Binary (data is stored in dbt file)
	 */
	BINARY('B'),
	/**
	 * Blob (VFP 9) (data is stored in fpt file)
	 */
	BLOB('W'),
	/**
	 * OLE Objects (data is stored in dbt file)
	 */
	GENERAL_OLE('G'),
	/**
	 * Picture (FoxPro, data is sotred in dbt file)
	 */
	PICTURE('P'),
	/**
	 * Numeric data
	 */
	NUMERIC('N', 1, 32, 0, true),
	/**
	 * Numeric long (FoxPro)
	 */
	LONG('I', 4, 4, 4, false),
	/**
	 * Autoincrement (same as long, dbase 7)
	 */
	AUTOINCREMENT('+', 4, 4, 4, false),
	/**
	 * Currency type (FoxPro)
	 */
	CURRENCY('Y', 8, 8, 8, false),
	/**
	 * Timestamp type (FoxPro)
	 */
	TIMESTAMP('T', 8, 8, 8, false),
	/**
	 * Timestamp type (dbase level 7)
	 */
	TIMESTAMP_DBASE7('@', 8, 8, 8, false),
	/**
	 * Flags
	 */
	NULL_FLAGS('0');

	private XbFieldType(final byte code) {

		this((char) code);
	}

	private XbFieldType(final char code) {

		this.fieldCode = code;
	}

	private XbFieldType(final byte code, final int minSize, final int maxSize, final int defaultSize, final boolean wroteSupported) {

		this((char) code, minSize, maxSize, defaultSize, wroteSupported);
	}

	private XbFieldType(final char code, final int minSize, final int maxSize, final int defaultSize, final boolean wroteSupported) {

		this(code);
		this.fieldMinSize = minSize;
		this.fieldMaxSize = maxSize;
		this.fieldDefaultSize = defaultSize;
		this.fieldWroteSupported = wroteSupported;
	}

	public XbFieldType contains(final byte code) {

		return this.contains((char) code);
	}

	/**
	 *
	 * @param code
	 * @return
	 */
	public XbFieldType contains(final char code) {

		return Stream.of(XbFieldType.values())
				.filter(item -> item.getFieldCode() == code)
				.findAny()
				.orElse(XbFieldType.UNKNOWN);
	}

	/**
	 * Właściwość określa jednoznakowy kod typu danych
	 *
	 * @return Pojedyńczy znak typu <code>Character</code>
	 */
	public char getFieldCode() {

		return this.fieldCode;
	}

	/**
	 * Właściwość określa nazwę typu danych
	 *
	 * @return Sekwencja znaków typu <code>String</code>
	 */
	public String getFieldName() {

		return this.fieldName;
	}

	/**
	 * Właściowość określa ilość miejsc po przecinku dla wartości zmiennoprzecinkowej
	 *
	 * @return Wartość numeryczna całkowita
	 */
	public int getFieldDecimalPlaces() {

		return this.fieldDecimalPlaces;
	}

	/**
	 * Właściwość określa domyślną długość pola
	 *
	 * @return Wartość numeryczna całkowita
	 */
	public int getFieldDefaultSize() {

		return this.fieldDefaultSize;
	}

	/**
	 * Właściwość określa minimalną długość pola
	 *
	 * @return Wartość numeryczna całkowita
	 */
	public int getFieldMinSize() {

		return this.fieldMinSize;
	}

	/**
	 * Własciwość określa czy jest mozliwośc aktualizacji pola
	 *
	 * @return Wartość logiczna
	 */
	public boolean getFieldWroteSupported() {

		return this.fieldWroteSupported;
	}

	/**
	 * Właściwość określa maksymalną długość pola
	 *
	 * @return Wartość numeryczna całkowita
	 */
	public int getFieldMaxSize() {

		return this.fieldMaxSize;
	}

	/**
	 * Kod typu danych
	 */
	private char fieldCode;

	/**
	 * Nazwa typu danych
	 */
	private String fieldName;

	/**
	 * Ilość miejsc po przecinku typu danych
	 */
	private int fieldDecimalPlaces;

	/**
	 * Domyślny rozmiar w bajtach typu danych
	 */
	private int fieldDefaultSize;

	/**
	 * Minimalny rozmiar w bajtach typu danych
	 */
	private int fieldMinSize;

	/**
	 * Maksymalny rozmiar w bajtach typu danych
	 */
	private int fieldMaxSize;

	/**
	 * Wsparcie typu danych do zapisywania
	 */
	private boolean fieldWroteSupported;

}
