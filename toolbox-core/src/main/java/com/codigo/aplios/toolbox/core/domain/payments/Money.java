package com.codigo.aplios.toolbox.core.domain.payments;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.Currency;
import java.util.Objects;

import com.codigo.aplios.toolbox.core.global.Constants.Logical;

public final class Money implements Comparable<Money>, Serializable {

	// PRIVATE SECTION //

	/**
	 * Determines if a deserialized file is compatible with this class.
	 *
	 * Maintainers must change this value if and only if the new version of this class is not compatible with old
	 * versions. See Sun docs for <a href=http://java.sun.com/products/jdk/1.1/docs/guide
	 * /serialization/spec/version.doc.html> details. </a>
	 *
	 * Not necessary to include in first version of the class, but included here as a reminder of its importance.
	 */
	private static final long serialVersionUID = 7526471155622776147L;

	/**
	 * Pole klasy reprezentuje domyślną instancję obiektu klasy, z której korzystamy w momencie tworzenia obiektu z
	 * domyślnymi parametrami. Dla tego scenariusza tworzenia obiektu wywoływany jest domyślny konstruktor.
	 */
	private static Currency DEFAULT_CURRENCY;

	// ------------------------------------------------------------------------------------------------------------------

	/**
	 * Wartość pieniądza. Nigdy nie jest null.
	 *
	 * @serial
	 */
	private BigDecimal amount;

	// ------------------------------------------------------------------------------------------------------------------

	/**
	 * Waluta pieniądza, taka jak US Dollars, Euros, Złoty. Nigdy nie jest null.
	 *
	 * @serial
	 */
	private final Currency currency;

	// ------------------------------------------------------------------------------------------------------------------

	/**
	 * The rounding mode to be used. See {@link BigDecimal}.
	 *
	 * @serial
	 */
	private final RoundingMode rounding;

	// ------------------------------------------------------------------------------------------------------------------

	/**
	 * The default rounding style to be used if no currency is passed to the constructor. See {@link BigDecimal}.
	 */
	private static RoundingMode DEFAULT_ROUNDING;

	// ------------------------------------------------------------------------------------------------------------------

	/** @serial */
	private int hashCode;

	// ------------------------------------------------------------------------------------------------------------------

	private static final int HASH_SEED = 23;

	// ------------------------------------------------------------------------------------------------------------------

	private static final int HASH_FACTOR = 37;

	// ------------------------------------------------------------------------------------------------------------------

	// PUBLIC SECTION //

	public static final class MismatchedCurrencyException extends RuntimeException {

		private static final long serialVersionUID = -8568697262819643759L;

		MismatchedCurrencyException(String aMessage) {

			super(aMessage);
		}
	}

	// ------------------------------------------------------------------------------------------------------------------

	/**
	 * Set default values for currency and rounding style.
	 *
	 * <em>Your application must call this method upon startup</em>. This method should usually be called only once
	 * (upon startup).
	 *
	 * <P>
	 * The recommended rounding style is {@link RoundingMode#HALF_EVEN}, also called <em>banker's rounding</em>; this
	 * rounding style introduces the least bias.
	 *
	 * <P>
	 * Setting these defaults allow you to use the more terse constructors of this class, which are much more
	 * convenient.
	 *
	 * <P>
	 * (In a servlet environment, each app has its own classloader. Calling this method in one app will never affect the
	 * operation of a second app running in the same servlet container. They are independent.)
	 */
	public static void init(Currency defaultCurrency, RoundingMode defaultRounding) {

		Money.DEFAULT_CURRENCY = defaultCurrency;
		Money.DEFAULT_ROUNDING = defaultRounding;
	}

	/**
	 * Full constructor.
	 *
	 * @param amount is required, can be positive or negative. The number of decimals in the amount cannot
	 *            <em>exceed</em> the maximum number of decimals for the given {@link Currency}. It's possible to create
	 *            a <tt>Money</tt> object in terms of 'thousands of dollars', for instance. Such an amount would have a
	 *            scale of -3.
	 * @param currency is required.
	 * @param roundingMode is required, must match a rounding style used by {@link BigDecimal}.
	 */
	public Money(BigDecimal amount, Currency currency, RoundingMode roundingMode) {

		this.amount = amount;
		this.currency = currency;
		this.rounding = roundingMode;
		this.validateState();
	}

	// -----------------------------------------------------------------------------------------------------------------

	/**
	 * Constructor taking only the money amount.
	 *
	 * <P>
	 * The currency and rounding style both take default values.
	 *
	 * @param amount is required, can be positive or negative.
	 */
	public Money(BigDecimal amount) {

		this(amount, Money.DEFAULT_CURRENCY, Money.DEFAULT_ROUNDING);
	}

	// -----------------------------------------------------------------------------------------------------------------

	/**
	 * Constructor taking the money amount and currency.
	 *
	 * <P>
	 * The rounding style takes a default value.
	 *
	 * @param amount is required, can be positive or negative.
	 * @param currency is required.
	 */
	public Money(BigDecimal amount, Currency currency) {

		this(amount, currency, Money.DEFAULT_ROUNDING);
	}

	// ------------------------------------------------------------------------------------------------------------------

	/**
	 * Metoda zwraca wartość waluty.
	 *
	 * @return Wartość numeryczna.
	 */
	public BigDecimal getAmount() {

		return this.amount;
	}

	// ------------------------------------------------------------------------------------------------------------------

	/**
	 * Metoda zwraca symbol waluty.
	 *
	 * @return Wartość tekstowa.
	 */
	public Currency getCurrency() {

		return this.currency;
	}

	// ------------------------------------------------------------------------------------------------------------------

	/** Return the rounding style passed to the constructor, or the default rounding style. */
	public RoundingMode getRoundingMode() {

		return this.rounding;
	}

	// ------------------------------------------------------------------------------------------------------------------

	/**
	 * Return <tt>OK</tt> only if <tt>aThat</tt> <tt>Money</tt> has the same currency as this <tt>Money</tt>.
	 */
	public boolean isSameCurrencyAs(Money money) {

		boolean result = Logical.OK;
		if (Objects.nonNull(money))
			result = this.currency.equals(money.currency);

		return result;
	}

	// ------------------------------------------------------------------------------------------------------------------

	/** Return <tt>OK</tt> only if the amount is positive. */
	public boolean isPlus() {

		return this.amount.compareTo(BigDecimal.ZERO) > 0;
	}

	// ------------------------------------------------------------------------------------------------------------------

	/** Return <tt>OK</tt> only if the amount is negative. */
	public boolean isMinus() {

		return this.amount.compareTo(BigDecimal.ZERO) < 0;
	}

	// ------------------------------------------------------------------------------------------------------------------

	/** Return <tt>OK</tt> only if the amount is zero. */
	public boolean isZero() {

		return 0 == this.amount.compareTo(BigDecimal.ZERO);
	}

	// ------------------------------------------------------------------------------------------------------------------

	/**
	 * Add <tt>aThat</tt> <tt>Money</tt> to this <tt>Money</tt>. Currencies must match.
	 */
	public Money plus(Money money) {

		this.checkCurrenciesMatch(money);
		return new Money(this.amount.add(money.amount), this.currency, this.rounding);
	}

	// ------------------------------------------------------------------------------------------------------------------

	/**
	 * Subtract <tt>aThat</tt> <tt>Money</tt> from this <tt>Money</tt>. Currencies must match.
	 */
	public Money minus(Money money) {

		this.checkCurrenciesMatch(money);
		return new Money(this.amount.subtract(money.amount), this.currency, this.rounding);
	}

	// ------------------------------------------------------------------------------------------------------------------

	/**
	 * Sum a collection of <tt>Money</tt> objects. Currencies must match. You are encouraged to use database summary
	 * functions whenever possible, instead of this method.
	 *
	 * @param moneys collection of <tt>Money</tt> objects, all of the same currency. If the collection is empty, then a
	 *            zero value is returned.
	 * @param currencyIfEmpty is used only when <tt>aMoneys</tt> is empty; that way, this method can return a zero
	 *            amount in the desired currency.
	 */
	public static Money sum(Collection<Money> moneys, Currency currencyIfEmpty) {

		Money sum = new Money(BigDecimal.ZERO, currencyIfEmpty);
		for (Money money : moneys)
			sum = sum.plus(money);

		return sum;
	}

	// ------------------------------------------------------------------------------------------------------------------

	/**
	 * Equals (insensitive to scale).
	 *
	 * <P>
	 * Return <tt>OK</tt> only if the amounts are equal. Currencies must match. This method is <em>not</em> synonymous
	 * with the <tt>equals</tt> method.
	 */
	public boolean eq(Money money) {

		this.checkCurrenciesMatch(money);
		return 0 == this.compareAmount(money);
	}

	// ------------------------------------------------------------------------------------------------------------------

	/**
	 * Procedura wykonuje sprawdzenie logiczne czy przekazana wartość pieniężna jest większa od wartości pieniężnej
	 * bieżącego obiektu.
	 *
	 * @param money Obiekt Wartość pienieżna
	 * @return
	 */
	public boolean gt(Money money) {

		this.checkCurrenciesMatch(money);
		return this.compareAmount(money) > 0;
	}

	// ------------------------------------------------------------------------------------------------------------------

	/**
	 * Greater than or equal to.
	 *
	 * <P>
	 * Return <tt>OK</tt> only if 'this' amount is greater than or equal to 'that' amount. Currencies must match.
	 */
	public boolean gteq(Money money) {

		this.checkCurrenciesMatch(money);
		return this.compareAmount(money) >= 0;
	}

	/**
	 * Less than.
	 *
	 * <P>
	 * Return <tt>OK</tt> only if 'this' amount is less than 'that' amount. Currencies must match.
	 */
	public boolean lt(Money aThat) {

		this.checkCurrenciesMatch(aThat);
		return this.compareAmount(aThat) < 0;
	}

	/**
	 * Less than or equal to.
	 *
	 * <P>
	 * Return <tt>OK</tt> only if 'this' amount is less than or equal to 'that' amount. Currencies must match.
	 */
	public boolean lteq(Money money) {

		this.checkCurrenciesMatch(money);
		return this.compareAmount(money) <= 0;
	}

	/**
	 * Multiply this <tt>Money</tt> by an integral factor.
	 *
	 * The scale of the returned <tt>Money</tt> is equal to the scale of 'this' <tt>Money</tt>.
	 */
	public Money times(int factor) {

		final BigDecimal newFactor = new BigDecimal(factor);
		final BigDecimal newAmount = this.amount.multiply(newFactor);
		return new Money(newAmount, this.currency, this.rounding);
	}

	/**
	 * Multiply this <tt>Money</tt> by an non-integral factor (having a decimal point).
	 *
	 * <P>
	 * The scale of the returned <tt>Money</tt> is equal to the scale of 'this' <tt>Money</tt>.
	 */
	public Money times(double factor) {

		BigDecimal newAmount = this.amount.multiply(this.asBigDecimal(factor));
		newAmount = newAmount.setScale(this.getNumDecimalsForCurrency(), this.rounding);

		return new Money(newAmount, this.currency, this.rounding);
	}

	/**
	 * Divide this <tt>Money</tt> by an integral divisor.
	 *
	 * <P>
	 * The scale of the returned <tt>Money</tt> is equal to the scale of 'this' <tt>Money</tt>.
	 */
	public Money div(int aDivisor) {

		BigDecimal divisor = new BigDecimal(aDivisor);
		BigDecimal newAmount = this.amount.divide(divisor, this.rounding);
		return new Money(newAmount, this.currency, this.rounding);
	}

	/**
	 * Divide this <tt>Money</tt> by an non-integral divisor.
	 *
	 * <P>
	 * The scale of the returned <tt>Money</tt> is equal to the scale of 'this' <tt>Money</tt>.
	 */
	public Money div(double aDivisor) {

		BigDecimal newAmount = this.amount.divide(this.asBigDecimal(aDivisor), this.rounding);
		return new Money(newAmount, this.currency, this.rounding);
	}

	/** Return the absolute value of the amount. */
	public Money abs() {

		return this.isPlus() ? this
				: this.times(-1);
	}

	/** Return the amount x (-1). */
	public Money negate() {

		return this.times(-1);
	}

	/**
	 * Returns {@link #getAmount()}.getPlainString() + space + {@link #getCurrency()}.getSymbol().
	 *
	 * <P>
	 * The return value uses the runtime's <em>default locale</em>, and will not always be suitable for display to an
	 * end user.
	 */
	@Override
	public String toString() {

		return this.amount.toPlainString() + " " + this.currency.getSymbol();
	}

	/**
	 * Like {@link BigDecimal#equals(java.lang.Object)}, this <tt>equals</tt> method is also sensitive to scale.
	 *
	 * For example, <tt>10</tt> is <em>not</em> equal to <tt>10.00</tt> The {@link #eq(Money)} method, on the other
	 * hand, is <em>not</em> sensitive to scale.
	 */
	@Override
	public boolean equals(Object aThat) {

		if (this == aThat)
			return Logical.OK;
		if (!(aThat instanceof Money))
			return Logical.NO;
		Money that = (Money) aThat;
		// the object fields are never null :
		boolean result = (this.amount.equals(that.amount));
		result = result && (this.currency.equals(that.currency));
		result = result && (this.rounding == that.rounding);
		return result;
	}

	@Override
	public int hashCode() {

		if (0 == this.hashCode) {
			this.hashCode = Money.HASH_SEED;
			this.hashCode = (Money.HASH_FACTOR * this.hashCode) + this.amount.hashCode();
			this.hashCode = (Money.HASH_FACTOR * this.hashCode) + this.currency.hashCode();
			this.hashCode = (Money.HASH_FACTOR * this.hashCode) + this.rounding.hashCode();
		}
		return this.hashCode;
	}

	@Override
	public int compareTo(Money aThat) {

		final int EQUAL = 0;

		if (this == aThat)
			return EQUAL;

		// the object fields are never null
		int comparison = this.amount.compareTo(aThat.amount);
		if (comparison != EQUAL)
			return comparison;

		comparison = this.currency.getCurrencyCode()
			.compareTo(aThat.currency.getCurrencyCode());
		if (comparison != EQUAL)
			return comparison;

		comparison = this.rounding.compareTo(aThat.rounding);
		if (comparison != EQUAL)
			return comparison;

		return EQUAL;
	}

	/**
	 * Always treat de-serialization as a full-blown constructor, by validating the final state of the de-serialized
	 * object.
	 */
	private void readObject(ObjectInputStream inputStream) throws ClassNotFoundException, IOException {

		// always perform the default de-serialization first
		inputStream.defaultReadObject();
		// defensive copy for mutable date field
		// BigDecimal is not technically immutable, since its non-final
		this.amount = new BigDecimal(this.amount.toPlainString());
		// ensure that object state has not been corrupted or tampered with maliciously
		this.validateState();
	}

	// ------------------------------------------------------------------------------------------------------------------

	private void writeObject(ObjectOutputStream outputStream) throws IOException {

		// perform the default serialization for all non-transient, non-static fields
		outputStream.defaultWriteObject();
	}

	// ------------------------------------------------------------------------------------------------------------------

	private void validateState() {

		if (Objects.isNull(this.amount))
			throw new IllegalArgumentException("Amount cannot be null");

		if (Objects.isNull(this.currency))
			throw new IllegalArgumentException("Currency cannot be null");

		if (this.amount.scale() > this.getNumDecimalsForCurrency())
			throw new IllegalArgumentException("Number of decimals is " + this.amount.scale()
					+ ", but currency only takes " + this.getNumDecimalsForCurrency() + " decimals.");
	}

	// -----------------------------------------------------------------------------------------------------------------

	private int getNumDecimalsForCurrency() {

		return this.currency.getDefaultFractionDigits();
	}

	// -----------------------------------------------------------------------------------------------------------------

	private void checkCurrenciesMatch(Money money) {

		if (!this.currency.equals(money.getCurrency()))
			throw new MismatchedCurrencyException(
					money.getCurrency() + " doesn't match the expected currency : " + this.currency);
	}

	// -----------------------------------------------------------------------------------------------------------------

	private int compareAmount(Money money) {

		return this.amount.compareTo(money.amount);
	}

	// -----------------------------------------------------------------------------------------------------------------

	private BigDecimal asBigDecimal(double decimal) {

		return BigDecimal.valueOf(decimal);
	}

	// -----------------------------------------------------------------------------------------------------------------
}
