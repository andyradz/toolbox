package com.codigo.aplios.toolbox.core.metric;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.InvalidPropertiesFormatException;

public class MoneyInWords {

	// -----------------------------------------------------------------------------------------------------------------

	private StringBuilder moneyChain;

	// -----------------------------------------------------------------------------------------------------------------
	// TODO: zrobić test ze ze zwroconej wartosci pomnozycj przez odpowienio
	// dziesiatki lub setki i
	// sumować i porównać czy wyjdzie wartośc bazowa
	public static void main(String[] args) throws InvalidPropertiesFormatException, FileNotFoundException, IOException {

		BigDecimal f = BigDecimal.valueOf(257_510_124_612_011.232_998);
		MoneyInWords minw = new MoneyInWords(f);
		minw.transform();
	}

	// -----------------------------------------------------------------------------------------------------------------

	private final BigDecimal money;

	// -----------------------------------------------------------------------------------------------------------------

	public MoneyInWords(BigDecimal money) {

		super();
		this.money = money;
		this.moneyChain = new StringBuilder();

	}

	// -----------------------------------------------------------------------------------------------------------------

	/**
	 * Metoda zwraca informacje o tym czy liczba jest dodatnia czy ujemna
	 *
	 * @return
	 */
	private String getMoneySign() {

		return this.money.signum() == -1 ? "minus"
				: "plus";
	}

	// -----------------------------------------------------------------------------------------------------------------

	private BigDecimal getMoneyFractional() {

		return this.money.divide(BigDecimal.ONE);
	}

	// -----------------------------------------------------------------------------------------------------------------

	private void transform() {

		BigDecimal copy = BigDecimal.ZERO.add(this.money);
		final long remaind = 0;
		// MoneyGroup mg = MoneyGroup.THOUSAND;
		// while (0 != (remaind = copy.remainder(BigDecimal.valueOf(1E3)).longValue())) {
		// System.out.println(MoneyPosition.create(remaind, mg).toString());
		// mg = mg.next();
		// copy = copy.divide(BigDecimal.valueOf(1E3));

		// }
	}

	static enum MoneyGroup {
		THOUSAND(new BigDecimal("10E3")),
		MILION(new BigDecimal("10E6")),
		MILIARD(new BigDecimal("10E9")),
		BILION(new BigDecimal("10E12")),
		BILIARD(new BigDecimal("10E16")),
		TRYLION(new BigDecimal("10E19")),
		TRYLIARD(new BigDecimal("10E21")),
		KWADRYLION(new BigDecimal("10E24")),
		KWADRYLIAD(new BigDecimal("10E27")),
		KWINTYLION(new BigDecimal("10E30")),
		KWINTYLIARD(new BigDecimal("10E33")),
		SEKSTYLION(new BigDecimal("10E36")),
		SEKSTYLIARD(new BigDecimal("10E39")),
		SEPTYLION(new BigDecimal("10E42")),
		SEPTYLIARD(new BigDecimal("10E45")),
		OKTYLION(new BigDecimal("10E48")),
		OKTYLIARD(new BigDecimal("10E51")),
		NONYLION(new BigDecimal("10E54")),
		NONYLIARD(new BigDecimal("10E57")),
		DECYLION(new BigDecimal("10E60")),
		DECYLIARD(new BigDecimal("10E61")),
		UNDECYLION(new BigDecimal("10E66")),
		UNDECYLIARD(new BigDecimal("10E69")),
		DUODECYLION(new BigDecimal("10E72")),
		DUODECYLIARD(new BigDecimal("10E75")),
		TRYCYLION(new BigDecimal("10E180")),
		TRYCYLIARD(new BigDecimal("10E183")),
		KWADRAGILION(new BigDecimal("10E240")),
		CZWORKWADRAGILIARD(new BigDecimal("10E243")),
		CZWOROKTOGILION(new BigDecimal("10E243")),
		OKTOGILION(new BigDecimal("10E480")),
		OKTOGILIARD(new BigDecimal("10E483")),
		CENTYLION(new BigDecimal("10E600")),
		CENTYLIARD(new BigDecimal("10E603"));

		private final BigDecimal money;

		private static MoneyGroup[] array = MoneyGroup.values();

		private MoneyGroup(BigDecimal value) {

			this.money = value;

		}

		public MoneyGroup next() {

			return MoneyGroup.array[(this.ordinal() + 1) % MoneyGroup.array.length];
		}

	}

	// -----------------------------------------------------------------------------------------------------------------

}
