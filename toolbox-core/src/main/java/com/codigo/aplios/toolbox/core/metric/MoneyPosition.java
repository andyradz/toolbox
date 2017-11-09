package com.codigo.aplios.toolbox.core.metric;

/**
 * * Klasa reprezentuje sekcje tysięcznej wartości jednostki monetarnej.**
 *
 * @author andrzej.radziszewski
 *
 */
public class MoneyPosition {

	// -------------------------------------------------------------------------------------------------------------
	public static MoneyPosition create(long money, MoneyInWords.MoneyGroup group) {

		return new MoneyPosition(money, group);
	}

	// -------------------------------------------------------------------------------------------------------------
	private final long money;

	// -------------------------------------------------------------------------------------------------------------
	private final MoneyInWords.MoneyGroup group;

	// -------------------------------------------------------------------------------------------------------------
	private MoneyPosition(long money, MoneyInWords.MoneyGroup group) {

		super();
		this.money = money;
		this.group = group;
	}

	// -------------------------------------------------------------------------------------------------------------
	public byte getOnes() {

		return (byte) (((this.money % 100) / 10) == 1 ? 0
				: (this.money % 10));
	}

	// -------------------------------------------------------------------------------------------------------------
	public byte getTeens() {

		return (byte) (((this.money % 100) / 10) != 1 ? 0
				: (this.money % 10));
	}

	// -------------------------------------------------------------------------------------------------------------
	public byte getTens() {

		return (byte) (((this.money % 100) / 10) != 1 ? ((this.money % 100) / 10)
				: ((this.money % 10) == 0) ? 1
						: 0);
	}

	// -------------------------------------------------------------------------------------------------------------
	public byte getHundreds() {

		return (byte) (((this.money % 1000) / 100));
	}

	// -------------------------------------------------------------------------------------------------------------
	@Override
	public String toString() {

		// return this.money + "-(J)" + MoneyInWords.MoneyNames.ONES.getName(this
		// .getOnes()) + "\n" + this.money + "-(D)" +
		// MoneyInWords.MoneyNames.TENS.getName(this.getTens()) + "\n" + this.money + "-(N)" +
		// MoneyInWords.MoneyNames.TEENS.getName(this.getTeens()) + "\n" + this.money + "-(S)" +
		// MoneyInWords.MoneyNames.HUNDRED.getName(this.getHundreds()) + "\n" + "rząd wielkości" + " " +
		// this.group;
		return "";
	}

	// -------------------------------------------------------------------------------------------------------------
}
