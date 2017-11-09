package com.codigo.aplios.toolbox.core.domain.evidence.gender;

import java.util.EnumSet;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

interface IGender {

	/**
	 * Metoda prezentuje symbol przypisany do płci osoby.
	 *
	 * @return Wartość teksowa.
	 */
	String getSymbol();

	// -----------------------------------------------------------------------------------------------------------------

	/**
	 * Metoda przezentuje nazwę przypisaną do płci człowieka
	 *
	 * @return Wartość tekstowa.
	 */
	String getName();

	// -----------------------------------------------------------------------------------------------------------------
}

/**
 * @author Andrzej Radziszewski
 *
 */
public enum Gender implements IGender {

	/**
	 * Płeć męska
	 */
	MALE {

		/*
		 * (non-Javadoc)
		 *
		 * @see com.codigo.aplios.contos.system.domain.Gender#getSymbol()
		 */
		@Override
		public String getSymbol() {

			return Gender.bundleStrings.getString("man.symbol");
		}

		/*
		 * (non-Javadoc)
		 *
		 * @see com.codigo.aplios.contos.system.domain.Gender#getName()
		 */
		@Override
		public String getName() {

			return Gender.bundleStrings.getString("man.name");
		}
	},

	// -----------------------------------------------------------------------------------------------------------------

	/**
	 * Płeć żeńska
	 */
	FEMALE {
		/*
		 * (non-Javadoc)
		 *
		 * @see com.codigo.aplios.contos.system.domain.Gender#getSymbol()
		 */
		@Override
		public String getSymbol() {

			/* RETURN */
			return Gender.bundleStrings.getString("woman.symbol");
		}

		/*
		 * (non-Javadoc)
		 *
		 * @see com.codigo.aplios.contos.system.domain.Gender#getName()
		 */
		@Override
		public String getName() {

			return Gender.bundleStrings.getString("woman.name");
		}
	},

	// -----------------------------------------------------------------------------------------------------------------

	/**
	 * Płeć nieokreślona
	 */
	UNKNOWN { // Płeć nieokreślona

		/*
		 * (non-Javadoc)
		 *
		 * @see com.codigo.aplios.contos.system.domain.Gender#getSymbol()
		 */
		@Override
		public String getSymbol() {

			return Gender.bundleStrings.getString("unknown.symbol");
		}

		/*
		 * (non-Javadoc)
		 *
		 * @see com.codigo.aplios.contos.system.domain.Gender#getName()
		 */
		@Override
		public String getName() {

			return Gender.bundleStrings.getString("unknown.name");
		}
	};

	// -----------------------------------------------------------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 *
	 * @see com.codigo.aplios.contos.system.domain.IGender#getSymbol()
	 */
	@Override
	public abstract String getSymbol();

	// -----------------------------------------------------------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 *
	 * @see com.codigo.aplios.contos.system.domain.IGender#getName()
	 */
	@Override
	public abstract String getName();

	// -----------------------------------------------------------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {

		/* RETURN */
		return String.format("%s(%s)", this.getName(), this.getSymbol());
	}

	// -----------------------------------------------------------------------------------------------------------------

	final static ResourceBundle bundleStrings;

	// -----------------------------------------------------------------------------------------------------------------

	/**
	 * Statyczny inicjalizator zasobów struktury Enum.
	 */
	static {
		try {
			final Locale locale = new Locale(Locale.getDefault()
				.getLanguage(),
					Locale.getDefault()
						.getCountry());
			bundleStrings = ResourceBundle.getBundle("com.codigo.aplios.allmarks.system.domain.strings.gender", locale);
		} catch (NullPointerException | MissingResourceException ex) {
			throw ex;
		}
	}

	// -----------------------------------------------------------------------------------------------------------------

	public static void main(String[] args) {

		try {
			EnumSet<Gender> genders = EnumSet.allOf(Gender.class);
			for (Gender gender : genders)
				System.out.println(gender);
		} catch (Exception ex) {
			// System.out.println(ex.getMessage());
		}
	}
}
