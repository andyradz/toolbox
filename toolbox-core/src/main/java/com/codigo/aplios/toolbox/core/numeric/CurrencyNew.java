package com.codigo.aplios.toolbox.core.numeric;

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;

public class CurrencyNew {

	public static void main(String[] args) {

		int exponent = StrictMath.getExponent(123E23);
		double dbl = StrictMath.exp(123.98);
		System.out.println(exponent);
		System.out.println(dbl);
		String[][] values =
				{
					{ "", "jeden", "dwa", "trzy", "cztery", "pięć", "sześć", "siedem", "osiem", "dziewieć" },
					{
						"",
						"jedenaście",
						"dwanaście",
						"trzynaście",
						"czternaście",
						"piętnaście",
						"szesnaście",
						"siedemnaście",
						"osiemnaście",
						"dziewietnaście" },
					{
						"",
						"dziesięć",
						"dwadzieścia",
						"trzydzieści",
						"czterdzieści",
						"pięćdziesiąt",
						"sześćdziesiąt",
						"siedemdziesiąt",
						"osiemdzieiesiąt",
						"dziewiędziesiąt" },
					{
						"",
						"sto",
						"dwieście",
						"trzysta",
						"czterysta",
						"pięćset",
						"sześćset",
						"siedemset",
						"osiemset",
						"dziewięset" }

				};

		String[] numberSigns = { "", "plus", "minus" };
		long ammount = 0L;
		long level = 0;
		long prefix = 1000;

		System.out.println(numberSigns[CurrencyNew.detectNumberSign((short) (ammount))]);

		for (long index = 0; index < 1000005; index++) {
			System.out.print(ammount % 1000);
			if ((ammount / prefix) == 1) {
				level++;
				prefix *= 1000;
			}
			System.out.print("-");
			if (level == 1)
				System.out.print("tysięcy ");
			if (level == 2)
				System.out.print("milionów ");
			System.out.print(values[3][CurrencyNew.getHundreds((short) (ammount % 1E3))]);
			System.out.print(" ");
			System.out.print(values[2][CurrencyNew.getTens((short) (ammount % 1E3))]);
			System.out.print(" ");
			System.out.print(values[1][CurrencyNew.getTeensCount((short) (ammount % 1E3))]);
			System.out.print(values[0][CurrencyNew.getOnesCount((short) (ammount % 1E3))]);
			ammount++;
			System.out.println();
		}

		double amount = 10000232132999.55;
		NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.getDefault());
		formatter.setMaximumFractionDigits(2);
		formatter.setMinimumIntegerDigits(2);
		formatter.setRoundingMode(RoundingMode.UP);
		formatter.setGroupingUsed(true);

		String moneyString = formatter.format(amount);
		System.out.println(moneyString);
	}

	public static final byte getOnesCount(short number) {

		try {
			if (CurrencyNew.validNumber(number))
				return (((1 != ((number / 10) % 10))) && (0 != (number % 10))) ? (byte) (number % 10)
						: 0;
		} catch (IllegalArgumentException ex) {
			System.out.println(ex.getMessage());
		}
		return 0;
	}

	/**
	 * Procedura dokonuje wyliczenia ilości nastek w liczbie
	 *
	 * @param number wartość liczbowa
	 * @return ilość nastek w liczbie
	 */
	public static final byte getTeensCount(short number) {

		try {
			if (CurrencyNew.validNumber(number))
				return ((0 != (number % 10)) && (1 == ((number / 10) % 10))) ? (byte) (number % 10)
						: 0;
		} catch (IllegalArgumentException ex) {
			// TODO zaloguj bład dod dziennika
			System.out.println(ex.getMessage());
		}
		return 0;
	}

	/**
	 * Procedura dokonuje wyliczenia ilości dzisiątek w liczbie
	 *
	 * @param number wartość liczby
	 * @return ilość nastek w liczbie, wartość liczbowa dodatnia gdy występują jednostki, wartość 0 gdy brak jednostek,
	 *         wartość -1 gdy liczba nieprawidłowa
	 */
	public static final byte getTens(short number) {

		try {
			byte teensCount = CurrencyNew.getTeensCount(number);
			if ((teensCount >= 0) && CurrencyNew.validNumber(number))
				return (0 == teensCount) ? (byte) ((number % 1E2) / 1E1)
						: 0;
		} catch (IllegalArgumentException ex) {
			// TODO zaloguj bład dod dziennika
			System.out.println(ex.getMessage());
		}
		return 0;
	}

	public static final byte getHundreds(short number) {

		try {
			if (CurrencyNew.validNumber(number))
				return (byte) ((number % 1E3) / 1E2);
		} catch (IllegalArgumentException ex) {
			System.out.println(ex.getMessage());
		}
		return 0;
	}

	/**
	 * Procedura waliduje liczbę czy znajduje się w zakresie <0:999>
	 *
	 * @param number wartość liczby
	 * @return wartość true gry liczba z zakresu, wartość false gdy liczba poza zakresem
	 * @throws IllegalArgumentException wyjątek gdy liczba jest poza zakresem
	 */
	private static final boolean validNumber(short number) throws IllegalArgumentException {

		if ((number < 0) || (number > 999))
			throw new IllegalArgumentException(String
				.format("Liczba %d posiada nieprawidłową wartość.\nWartość musi być z zakresu od 0 do 999.", number));
		return true;
	}

	private static final byte detectNumberSign(short number) {

		byte numberSign = (byte) Math.signum(number);
		if (numberSign == -1)
			numberSign = 2;
		return numberSign;
	}
}
