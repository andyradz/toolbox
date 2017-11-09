package com.codigo.aplios.toolbox.core.metrics;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import org.junit.AssumptionViolatedException;
import org.junit.rules.Stopwatch;
import org.junit.runner.Description;

public class JUnitStopWatch extends Stopwatch {

	// -----------------------------------------------------------------------------------------------------------------

	private static void logInfo(Description description, String status, long nanos) {

		String testName = description.getMethodName();
		System.out.println(String.format("Test date %s",
				LocalDateTime.now()
					.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)));
		System.out.println(String
			.format("Test %s %s, spent %d microseconds", testName, status, TimeUnit.NANOSECONDS.toMicros(nanos)));
	}

	// -----------------------------------------------------------------------------------------------------------------

	@Override
	protected void succeeded(long nanos, Description description) {

		JUnitStopWatch.logInfo(description, "succeeded", nanos);
	}

	// -----------------------------------------------------------------------------------------------------------------

	@Override
	protected void failed(long nanos, Throwable e, Description description) {

		JUnitStopWatch.logInfo(description, "failed", nanos);
	}

	// -----------------------------------------------------------------------------------------------------------------

	@Override
	protected void skipped(long nanos, AssumptionViolatedException e, Description description) {

		JUnitStopWatch.logInfo(description, "skipped", nanos);
	}

	// -----------------------------------------------------------------------------------------------------------------

	@Override
	protected void finished(long nanos, Description description) {

		JUnitStopWatch.logInfo(description, "finished", nanos);
	}

	// -----------------------------------------------------------------------------------------------------------------
}
