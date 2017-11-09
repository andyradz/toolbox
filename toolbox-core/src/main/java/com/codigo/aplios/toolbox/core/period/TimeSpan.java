package com.codigo.aplios.toolbox.core.period;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.OffsetDateTime;
import java.time.Year;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.IsoFields;
import java.time.temporal.TemporalAdjusters;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Locale.Category;
import java.util.Set;

public class TimeSpan {

	// custom clock -
	// http://www.concretepage.com/java/jdk-8/java-8-clock-example-using-dependency-injection-framework
	public static void main(String[] args) {

		Locale.setDefault(Category.DISPLAY, Locale.US);
		Locale.setDefault(Category.FORMAT, Locale.US);

		LocalDate king = LocalDate.now()
			.query(new NextMartinLutherKingDayQuery());

		// LocalDate datefirst = LocalDate.now();
		// LocalDate datelast = LocalDate.of(2016, Month.MAY, 3);
		//
		// long period = ChronoUnit.DAYS.between(datefirst, datelast);
		// period = ChronoUnit.MONTHS.between(datefirst, datelast);
		// period = ChronoUnit.NANOS.between(datelast, datefirst);

		LocalDateTime ldt1 = LocalDateTime.of(2010, 6, 12, 18, 32, 0);
		LocalDateTime ldt2 = LocalDateTime.of(2012, 6, 13, 22, 47, 0);
		long quantity = ChronoUnit.YEARS.between(ldt1, ldt2);
		quantity = ChronoUnit.MONTHS.between(ldt1, ldt2);
		quantity = ChronoUnit.WEEKS.between(ldt1, ldt2);
		quantity = ChronoUnit.DAYS.between(ldt1, ldt2);
		quantity = ChronoUnit.HOURS.between(ldt1, ldt2);
		quantity = ChronoUnit.MINUTES.between(ldt1, ldt2);
		quantity = ChronoUnit.SECONDS.between(ldt1, ldt2);
		quantity = LocalDate.now()
			.get(IsoFields.QUARTER_OF_YEAR);
		quantity = ChronoField.DAY_OF_MONTH.getFrom(LocalDate.now());
		quantity = ChronoField.DAY_OF_WEEK.getFrom(LocalDate.now());
		quantity = ChronoField.DAY_OF_YEAR.getFrom(LocalDate.now());
		quantity = Year.of(2016)
			.length();
		quantity = Month.of(2)
			.length(Year.of(2016)
				.isLeap());

		quantity = ChronoField.DAY_OF_MONTH.getFrom(LocalDate.now()
			.with(TemporalAdjusters.next(DayOfWeek.SUNDAY)));

		ZoneId losAngeles = ZoneId.of("America/Los_Angeles");
		ZoneId berlin = ZoneId.of("Europe/Berlin");
		// 2014-02-20 12:00
		LocalDateTime dateTime = LocalDateTime.now();
		// 2014-02-20 12:00, Europe/Berlin (+01:00)
		ZonedDateTime berlinDateTime = ZonedDateTime.of(dateTime, berlin);
		// 2014-02-20 03:00, America/Los_Angeles (-08:00)
		ZonedDateTime losAngelesDateTime = berlinDateTime.withZoneSameInstant(losAngeles);
		int offsetInSeconds = losAngelesDateTime.getOffset()
			.getTotalSeconds(); // -28800
		// a collection of all available zones
		Set<String> allZoneIds = ZoneId.getAvailableZoneIds();
		// using offsets
		LocalDateTime date = LocalDateTime.of(2013, Month.JULY, 20, 3, 30);
		ZoneOffset offset = ZoneOffset.of("+05:00");
		// 2013-07-20 03:30 +05:00
		OffsetDateTime plusFive = OffsetDateTime.of(date, offset);
		// 2013-07-19 20:30 -02:00
		OffsetDateTime minusTwo = plusFive.withOffsetSameInstant(ZoneOffset.ofHours(-2));

		// current time
		Instant now = Instant.now();
		// from unix timestamp, 2010-01-01 12:00:00
		Instant fromUnixTimestamp = Instant.ofEpochSecond(1262347200);
		// same time in millis
		Instant fromEpochMilli = Instant.ofEpochMilli(1262347200000l);
		// parsing from ISO 8601
		Instant fromIso8601 = Instant.parse("2010-01-01T12:00:00Z");
		// toString() returns ISO 8601 format, e.g. 2014-02-15T01:02:03Z
		String toIso8601 = now.toString();
		// as unix timestamp
		long toUnixTimestamp = now.getEpochSecond();
		// in millis
		long toEpochMillis = now.toEpochMilli();
		// plus/minus methods are available too
		Instant nowPlusTenSeconds = now.plusSeconds(10);
		Instant nowPlusOneWeek = now.plus(7, ChronoUnit.DAYS);
		Instant nowPlusTwoWeeks = now.plus(14, ChronoUnit.DAYS);

		System.out.println(DateTimeFormatter.ISO_DATE_TIME.format(ldt2));
		System.out.println(DateTimeFormatter.ISO_INSTANT.format(nowPlusTwoWeeks));
		System.out.println(DateTimeFormatter.ofPattern("yyyy.mm.dd hh:mm:ss")
			.format(ldt2));

		final GregorianCalendar gregorianCalendar = GregorianCalendar.from(ZonedDateTime.now());
		System.out.println(Instant.MIN);
		System.out.println(Instant.MAX);
		System.out.println(nowPlusOneWeek);
		System.out.println(nowPlusTwoWeeks);
		System.out.println((ChronoUnit.DAYS.between(now, nowPlusTwoWeeks)) / 7);
	}
}
