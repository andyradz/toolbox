package com.codigo.aplios.toolbox.core.period;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.MonthDay;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalQueries;
import java.time.temporal.TemporalQuery;

public class HurricaneSeasonQuery implements TemporalQuery<Boolean> {

	/*
	 * (non-Javadoc)
	 *
	 * @see java.time.temporal.TemporalQuery#queryFrom(java.time.temporal. TemporalAccessor)
	 */
	@Override
	public Boolean queryFrom(TemporalAccessor temporal) {

		LocalDate date = LocalDate.from(temporal);

		MonthDay juneFirst = MonthDay.of(Month.JUNE.getValue(), 1);
		MonthDay novemberThirty = MonthDay.of(Month.NOVEMBER.getValue(), 30);

		if (date.isAfter(juneFirst.atYear(date.getYear())) && date.isBefore(novemberThirty.atYear(date.getYear())))
			return true;
		else
			return false;
	}

	public static void main(String[] args) {

		LocalDate date = LocalDate.of(2014, Month.JUNE, 30);
		final Boolean isHurricaneSeason = date.query(new HurricaneSeasonQuery());

		TemporalQuery<ZoneId> query = TemporalQueries.zone();

		LocalDateTime date1 = LocalDateTime.of(2014, Month.DECEMBER, 02, 0, 0);
		ZonedDateTime zonedDate = ZonedDateTime.of(date1, ZoneId.of("Pacific/Chatham"));

		ZoneId zoneId = zonedDate.query(query);

		System.out.println(zoneId); // "Pacific/Chatham"

	}
}
