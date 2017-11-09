package com.codigo.aplios.toolbox.core.period;

import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalQuery;

public class WyznaczenieWielkanocy {
	// https://pl.wikipedia.org/wiki/Wielkanoc
	// Metoda gaussa
	// Metoda Jeana Meeusa
	// Doa wzorzec strategia
	public static void main(String[] args) {

		final LocalDate now = LocalDate.of(2016, 1, 1);

		// święto wielkiejnocy
		System.out.println("Wielkanoc:" + DateTimeFormatter.ISO_DATE.format(CalendarEvents.EASTERDAY.queryFrom(now)));

		// uroczystość wniebowstąpienia pańskiego
		System.out.println("Wniebowstąpienia Pańskieg:"
				+ DateTimeFormatter.ISO_DATE.format(CalendarEvents.ASCENSIONDAY.queryFrom(now)));

		// święto boże ciało (60 dni po wielkanocy)
		System.out
			.println("Boże Ciało:" + DateTimeFormatter.ISO_DATE.format(CalendarEvents.CORPUSCHRISTI.queryFrom(now)));

		// uroczystość miłosierdzia bożego tydzień po wielkanocy
		System.out.println(
				"Miłosierdzie Boże:" + DateTimeFormatter.ISO_DATE.format(CalendarEvents.CHARITABLENESS.queryFrom(now)));

		// uroczystość zielone świątki (pięcdzisiatnica)
		System.out
			.println("Zielone światki:" + DateTimeFormatter.ISO_DATE.format(CalendarEvents.WHITSUNTIDE.queryFrom(now)));

	}
}

interface IEasterDayStrategy {
	LocalDate getDay(Year easterYear);
}

enum CalendarEvents implements TemporalQuery<LocalDate> {
	/**
	 * Święto Wielkanocy
	 */
	EASTERDAY {
		@Override
		public LocalDate queryFrom(TemporalAccessor temporal) {

			// ...begin method
			return EasterDayContext.GAUSS_METHOD.queryFrom(temporal);
		}
	},
	/**
	 * Uroczystość Miłosierdzia Bożego
	 */
	CHARITABLENESS {
		@Override
		public LocalDate queryFrom(TemporalAccessor temporal) {

			// ...begin method
			return EasterDayContext.GAUSS_METHOD.queryFrom(temporal)
				.plusWeeks(1);
		}
	},
	/**
	 * Wniebowstapienie Pańskie
	 */
	ASCENSIONDAY {
		@Override
		public LocalDate queryFrom(TemporalAccessor temporal) {

			// ...begin method
			// return EasterDayContext.GAUSS_METHOD.queryFrom(temporal).plusDays(40);
			return EasterDayContext.GAUSS_METHOD.queryFrom(temporal)
				.plusWeeks(6);
		}
	},
	/**
	 * Zielone Świątki
	 */
	WHITSUNTIDE {
		@Override
		public LocalDate queryFrom(TemporalAccessor temporal) {

			// ...begin method
			return EasterDayContext.GAUSS_METHOD.queryFrom(temporal)
				.plusDays(49);
		}
	},
	/**
	 * Boże Ciało
	 */
	CORPUSCHRISTI {
		@Override
		public LocalDate queryFrom(TemporalAccessor temporal) {

			// ...begin method
			return EasterDayContext.GAUSS_METHOD.queryFrom(temporal)
				.plusDays(60);
		}
	},
	/**
	 * Święto Wszystkich Świętych
	 */
	ALLHALLOWMAS {
		@Override
		public LocalDate queryFrom(TemporalAccessor temporal) {

			// TODO Auto-generated method stub
			return null;
		}
	};

	@Override
	public abstract LocalDate queryFrom(TemporalAccessor temporal);
}

enum EasterDayContext implements IEasterDayStrategy, TemporalQuery<LocalDate> {

	/**
	 * Klasa realizuje algorytm wyznaczania dnia wielkanocy metodą opublikowaną przez C.F. Gaussa.
	 *
	 * @author Andrzej Radziszewski
	 *
	 */
	GAUSS_METHOD {
		@Override
		public LocalDate getDay(Year easterYear) {

			final int year = Year.of(easterYear.getValue())
				.getValue();
			int A = ((year % 19));
			int B = year % 4;
			int C = year % 7;

			int D = ((19 * A) + 24) % 30;
			int E = ((2 * B) + (4 * C) + (6 * D) + 5) % 7;

			return LocalDate.of(year, 3, 22)
				.plus((D + E), ChronoUnit.DAYS);
		}

		@Override
		public LocalDate queryFrom(TemporalAccessor temporal) {

			final LocalDate date = LocalDate.from(temporal);
			return EasterDayContext.GAUSS_METHOD.getDay(Year.of(date.getYear()));
		}
	},
	/**
	 * Klasa realizuje algorytm wyznaczania dnia wielkanocy metodą opublikowaną przez Jeana Meeusa.
	 *
	 * @author Andrzej Radziszewski
	 *
	 */
	MEEUSA_METHOD {
		@Override
		public LocalDate getDay(Year easterYear) {

			// TODO Auto-generated method stub
			return LocalDate.now();
		}

		@Override
		public LocalDate queryFrom(TemporalAccessor temporal) {

			final LocalDate date = LocalDate.from(temporal);
			return EasterDayContext.MEEUSA_METHOD.getDay(Year.of(date.getYear()));
		}
	};

	@Override
	public abstract LocalDate getDay(Year easterYear);

	@Override
	public abstract LocalDate queryFrom(TemporalAccessor temporal);
}
