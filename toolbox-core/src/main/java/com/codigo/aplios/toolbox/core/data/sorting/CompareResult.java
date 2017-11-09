package com.codigo.aplios.toolbox.core.data.sorting;

/**
 * Typ wyliczeniowy reprezentuje wynik operacji porówania wyrażony w postaci znaczników. Poszczególne znacczniki
 * reprezentują możliwe wyniki operacji porównania.
 *
 * @author andrzej.radziszewski
 * @serial 2017
 */
public enum CompareResult {

	/**
	 * Znacznik reprezentuje wynik mniejszy niż wyrażony jako <code>x < y</code>
	 */
	LESSTH((byte) -1),

	/**
	 * Znacznik reprezentuje wynik równości wyrażony jako <code>x == y</code>
	 */
	EQUALS((byte) 0),

	/**
	 * Znacznik reprezentuje wynik większy niż wyrażony jako <code>x > y</code>
	 */
	MORETH((byte) 1);

	// -----------------------------------------------------------------------------------------------------------------

	/**
	 * Atrybut określa kod wyniku operacji porówania.
	 */
	private final byte compareCode;

	// -----------------------------------------------------------------------------------------------------------------

	/**
	 * Podstawowy kontruktor obiektu klasy.
	 *
	 * @param compareCode Kod identyfikujący wynik porówania.
	 */
	CompareResult(byte compareCode) {

		this.compareCode = compareCode;
	}

	// -----------------------------------------------------------------------------------------------------------------

	/**
	 * Właściwość wskazuje wartość wyniku operacji porównania
	 *
	 * @return Wartość numeryczna może zwracać wartości (-1, 0, 1)
	 */
	public byte val() {

		return this.compareCode;
	}

	// -----------------------------------------------------------------------------------------------------------------
}
