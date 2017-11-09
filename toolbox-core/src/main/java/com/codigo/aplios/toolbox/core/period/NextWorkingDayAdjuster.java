package com.codigo.aplios.toolbox.core.period;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;

/**
 * Klasa realizuje wyliczenie kolejnego dnia roboczego
 *
 * @author Andrzej Radziszewski
 * @version 1.0.01
 * @since 1.0.01 </br>
 *        Przykład użycia: <br>
 *        <code>
 *  TemporalAdjuster nextWorkingDay = new NextWorkingDayAdjuster();
    LocalDate now = LocalDate.now();
    LocalDate nextDay = now.with(nextWorkingDay);
 *  </code> <br>
 *        <strong> Klasa realizuej rozszerzenie interfejsu TemporalAdjuster </strong>
 */
public final class NextWorkingDayAdjuster implements TemporalAdjuster {

	/*
	 * (non-Javadoc)
	 *
	 * @see java.time.temporal.TemporalAdjuster#adjustInto(java.time.temporal.Temporal)
	 */
	@Override
	public Temporal adjustInto(Temporal temporal) {
		// DONE Auto-generated method stub

		final byte currentDayNo = (byte) temporal.get(ChronoField.DAY_OF_WEEK);
		final DayOfWeek currentDay = DayOfWeek.of(currentDayNo);
		byte countDaysToAdd = 1;

		if (DayOfWeek.FRIDAY.equals(currentDay))
			countDaysToAdd = 3;
		else if (DayOfWeek.SATURDAY.equals(currentDay))
			countDaysToAdd = 2;

		return temporal.plus(countDaysToAdd, ChronoUnit.DAYS);
	}

	public static void main(String[] args) {

		TemporalAdjuster nextWorkingDay = new NextWorkingDayAdjuster();

		LocalDate now = LocalDate.now();
		LocalDate nextDay = now.with(nextWorkingDay);
		System.out.println("now            = " + now);
		System.out.println("nextWorkingDay = " + nextDay);

		LocalDate friday = LocalDate.of(2016, Month.MARCH, 11);
		nextDay = friday.with(nextWorkingDay);
		System.out.println("friday         = " + friday);
		System.out.println("nextWorkingDay = " + nextDay);

		LocalDate saturday = LocalDate.of(2016, Month.MARCH, 12);
		nextDay = saturday.with(nextWorkingDay);
		System.out.println("saturday       = " + saturday);
		System.out.println("nextWorkingDay = " + nextDay);
	}

}
