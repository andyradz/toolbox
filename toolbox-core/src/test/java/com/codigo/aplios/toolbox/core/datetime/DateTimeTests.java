package com.codigo.aplios.toolbox.core.datetime;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class DateTimeTests {
	public static void main(String[] args) {

		Date in = new Date();
		LocalDateTime ldt = LocalDateTime.ofInstant(in.toInstant(), ZoneId.systemDefault());
		Date out = Date.from(ldt.atZone(ZoneId.systemDefault())
			.toInstant());
		System.out.println(out);

		ZonedDateTime nowUTC = ZonedDateTime.ofInstant(out.toInstant(), ZoneId.of("Europe/Moscow"));
		// ZonedDateTime nowUTC = ldt.atZone(ZoneId.of("UTC"));
		System.out.println(nowUTC.toLocalDateTime());
	}
}
