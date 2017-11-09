package com.codigo.aplios.toolbox.core.math;

/**
 * Klasa realizuej sprawdzenie czy dana liczba jest liczbą pierwszą
 *
 * @author andrzej.radziszewski
 */
public final class PrimeNumberChecker {

	/**
	 * @param primeNumber Wartość numeryczna do weryfikacji czy jest liczbą pierwszą
	 * @return Wartość logiczna <b>TRUE</b> gdy liczba jest liczbą pierwszą, <b>FALSE</b> - gdy liczba nie jest liczbą
	 *         pierwszą
	 */
	public Boolean validate(final Integer primeNumber) {

		for (int i = 2; i < (primeNumber / 2); i++)
			if ((primeNumber % i) == 0)
				return false;

		return true;
	}
}
