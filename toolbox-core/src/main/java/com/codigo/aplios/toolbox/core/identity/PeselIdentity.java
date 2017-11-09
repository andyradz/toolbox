package com.codigo.aplios.toolbox.core.identity;

import java.util.Objects;

import com.codigo.aplios.toolbox.core.domain.evidence.gender.Gender;

/**
 * Klasa reprezentuje numer identyfikacyjny PESEL.
 *
 * @author Andrzej Radziszewski
 * @version beta
 */
@PeselCheck
public final class PeselIdentity {
	private static final String PESEL_REGEX = "[0-9]{11}";

	private static final byte PESEL_LENGTH = 11;

	private static enum Century {

		CENTURY_NONE((byte) -1),
		CENTURY_19ST((byte) 19),
		CENTURY_20ST((byte) 20),
		CENTURY_21ST((byte) 21),
		CENTURY_22ST((byte) 22),
		CENTURY_23ST((byte) 23);

		Century(byte century) {

			this.century = century;
		}

		private byte century;

		public byte getValue() {

			return this.century;
		}
	}

	private final String pesel;

	// -----------------------------------------------------------------------------------------------------------------

	/**
	 * Główny konstruktor obiektu klasy
	 *
	 * @param pesel Numer identyfikacyjny PESEL
	 * @throws NullPointerException
	 * @throws IllegalArgumentException
	 * @throws Exception
	 */
	public PeselIdentity(String pesel) throws NullPointerException, IllegalArgumentException, Exception {

		if (Objects.isNull(pesel))
			throw new NullPointerException();

		if (pesel.isEmpty())
			throw new IllegalArgumentException();

		if (PeselIdentity.PESEL_LENGTH != pesel.length())
			throw new IllegalArgumentException("Numer pesel ma nieprawidłową długość!");

		if (!pesel.matches(PeselIdentity.PESEL_REGEX))
			throw new Exception("Numer pesel może składać się wyłącznie z cyfr!");

		this.pesel = pesel;
	}

	// -----------------------------------------------------------------------------------------------------------------

	/**
	 * @return
	 */
	public String getBirthOfYear() {

		return this.pesel.substring(0, 2);
	}

	// -----------------------------------------------------------------------------------------------------------------

	/**
	 * @return
	 */
	public String getMonthOfBirth() {

		return this.pesel.substring(2, 4);
	}

	// -----------------------------------------------------------------------------------------------------------------

	/**
	 * @return
	 */
	public String getDayOfBirth() {

		return this.pesel.substring(4, 6);
	}

	// -----------------------------------------------------------------------------------------------------------------

	/**
	 * Metoda dostarcza informacjo o stuleciu, w którym dana osoba się urodziła.
	 *
	 * @return Wartość numeryczna z zakresu -1..23.
	 */
	public byte getCentury() {

		final byte centurySign = Byte.parseByte(this.pesel.substring(2, 3));
		Century century = null;
		switch (centurySign) {
		case 0:
			century = Century.CENTURY_20ST;
			break;
		case 2:
			century = Century.CENTURY_21ST;
			break;
		case 4:
			century = Century.CENTURY_22ST;
			break;
		case 6:
			century = Century.CENTURY_23ST;
			break;
		case 8:
			century = Century.CENTURY_19ST;
			break;
		default:
			century = Century.CENTURY_NONE;
		}

		return century.getValue();
	}

	// -----------------------------------------------------------------------------------------------------------------

	/**
	 * @return
	 */
	public Gender getGender() {

		final byte genderSign = Byte.parseByte(this.pesel.substring(9, 10));
		if (0 == (genderSign % 2))
			return Gender.FEMALE;
		else if (0 != (genderSign % 2))
			return Gender.MALE;
		else
			return Gender.UNKNOWN;
	}

	// -----------------------------------------------------------------------------------------------------------------

	/**
	 * Właściwość prezentuje numer pesel.
	 *
	 * @return Wartość znakowa <code><b>String</b></code>
	 */
	public String pesel() {

		return this.pesel;
	}

	// -----------------------------------------------------------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {

		return Objects.hashCode(this.pesel);
	}

	// -----------------------------------------------------------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 *
	 * @see {@link java.lang.Object#}
	 */
	@Override
	public String toString() {

		return Objects.toString(this.pesel);
	}

	// -----------------------------------------------------------------------------------------------------------------

	public static void main(String[] args) throws NullPointerException, IllegalArgumentException, Exception {

		PeselIdentity id = new PeselIdentity("44051401358");
		Gender gender = id.getGender();
		id = new PeselIdentity("49040501580");
		gender = id.getGender();

		id = new PeselIdentity("79062601652");
		gender = id.getGender();
		String date = id.getBirthOfYear() + id.getMonthOfBirth() + id.getDayOfBirth();
		System.out.println(date);
		System.out.println(gender);
	}
}

// ---------------------------------------------------------------------------------------------------------------------
