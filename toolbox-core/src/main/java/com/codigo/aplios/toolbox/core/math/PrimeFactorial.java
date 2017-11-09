package com.codigo.aplios.toolbox.core.math;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*
 * Znajdziemy rozkład liczby n = 36: Pierwiastek z 36 wynosi 6, zatem dzielniki będą kolejno przyjmowały wartości od 2
 * do 6: 36 : 2 = 18 - dzieli się, czynnik 2 18 : 2 = 9 - dzieli się, czynniki 2 2 9 : 2 = - nie dzieli się, bierzemy
 * kolejny dzielnik 3 9 : 3 = 3 - dzieli się, czynniki 2 2 3 3 : 3 = 1 - dzieli się, czynniki 2 2 3 3 - koniec, gdyż n
 * zredukowało się do 1 36 = 2 × 2 × 3 × 3
 */

/*
 * Zbiór liczb naturalnych większych od 1 dzieli się na dwa rodzaje liczb: Liczby pierwsze, które nie są iloczynami
 * żadnych mniejszych liczb z tego zbioru: 2, 3, 5, 7, 11, 13, 17, 19... Liczby złożone, które są iloczynami liczb
 * mniejszych: 4, 6, 8, 9, 10, 12, 14, 15, 16, 18... Rozkład na czynniki pierwsze (ang. prime factorization) polega na
 * znalezieniu dla danej liczby naturalnej n, n > 1, wszystkich liczb pierwszych, których iloczyn daje n. Zadanie to
 * można rozwiązać na kilka różnych sposobów, jednakże nie istnieje żaden prosty wzór, który by pozwolił łatwo wyznaczać
 * wszystkie czynniki pierwsze rozkładu - należy ich szukać. My podejdziemy do problemu w sposób następujący:
 */

public class PrimeFactorial {

	// -----------------------------------------------------------------------------------------------------------------

	/**
	 * Główna procedura programu. Realizuje test rozkładu liczby na czynniki pierwsze
	 *
	 * @param args
	 */
	public static void main(String[] args) {

		for (long idx = 0; idx <= 1_000; idx++) {
			Iterator<Long> iter = PrimeFactorial.dissect(idx);

			System.out.print(idx + " = [");
			while (iter.hasNext())
				System.out.print(iter.next() + (iter.hasNext() ? " * "
						: "]"));

			System.out.println(" ");
		}
	}

	// -----------------------------------------------------------------------------------------------------------------

	/**
	 * Metoda realizuje rozkład liczby naturalnej na czynniki pierwsze. Zastosowano matematyczny algorytm rozkładu na
	 * czynniki pierwsze.
	 *
	 * @param number Wartość numeryczna liczby naturalnej.
	 */
	public static Iterator<Long> dissect(long number) {

		if (number < 0)
			throw new IllegalArgumentException(
					"\nNiepoprawna wartość liczby naturalnej." + "\nDozwolone wartości to 0..N");

		final List<Long> factors = new ArrayList<>();
		long orginalValue = number;

		double value = Math.floor(Math.sqrt(number));
		double verifyValue = 1;

		// pętla przechodzi przez kolejne wartości do osiągnięcia wartości liczby naturalnej
		for (long idx = 2; idx <= value; idx++) {

			// powtarza kroki
			// 1. dzielenie liczby przez kolejne wartości do momentu gdy modulo równa jest 0
			// 2. dodaje do kolekcji wartość liczby z dzielenia
			// ---------------------------------------------------------------------------------------------------------
			while (0 == (number % idx)) {
				verifyValue *= idx;
				number /= idx;
				factors.add(idx);
			}
			// gdy wartośc liczby osiąga wartość 1 to kończymy działanie metody
			// ---------------------------------------------------------------------------------------------------------
			if (1 == number)
				return factors.iterator();
		}

		factors.add(number);
		verifyValue *= number;
		assert (orginalValue == verifyValue);
		return factors.iterator();
	}

	// -----------------------------------------------------------------------------------------------------------------
}
