package com.codigo.aplios.toolbox.core.period;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalQuery;

public class NextMartinLutherKingDayQuery implements TemporalQuery<LocalDate> {

	@Override
	public LocalDate queryFrom(TemporalAccessor temporal) {

		LocalDate date = LocalDate.from(temporal);
		LocalDate currentYearMLKDay = this.getMartinLutherKingDayForDateInYear(date.getYear());

		Period periodToCurrentYearMLKDay = Period.between(date, currentYearMLKDay);

		if (periodToCurrentYearMLKDay.isNegative() || periodToCurrentYearMLKDay.isZero())
			return this.getMartinLutherKingDayForDateInYear(date.getYear() + 1);
		else
			return currentYearMLKDay;
	}

	private LocalDate getMartinLutherKingDayForDateInYear(int year) {

		return LocalDate.of(year, Month.JANUARY, 1)
			.with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY))
			.with(TemporalAdjusters.next(DayOfWeek.MONDAY))
			.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
	}
}
