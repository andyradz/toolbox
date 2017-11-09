package com.codigo.aplios.toolbox.core.domain.currency;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.codigo.aplios.toolbox.core.data.repository.GenericRepository;
import com.codigo.aplios.toolbox.core.data.repository.MemoryRepository;
import com.codigo.aplios.toolbox.core.domain.locale.Country;
import com.codigo.aplios.toolbox.core.domain.locale.Currency;
import com.codigo.aplios.toolbox.core.metrics.Order;

public class CurrencyTests {

	static enum LangNames {
		PL(new Locale("pl_PL")),
		EN(new Locale("en_EN"));

		private Locale locale;

		private LangNames(Locale locale) {

			this.locale = locale;
			// ładujemy odpowiedni dla locale plik z zasobów z nazwami dla adanego kraju
		}

		public String getOnes(int index) {

			return "";
		}

		public String getTeens(int index) {

			return "";
		}

		public String getTens(int index) {

			return "";
		}

		public String getHunds(int index) {

			return "";
		}

		public String getSignnum(int signum) {

			return "";
		}

		public String getName(int group) {

			return "milion";
		}
	}

	// Dodac mock lub mem repository
	final MemoryRepository<Currency> repository = new MemoryRepository<>();
	Currency currencyUSD, currencyRUR, currencyEUR, currencyCHF, currencyPLN, currencyAED, currencyAFN;
	BigDecimal posval, negval, bigval;

	@Before
	@Order(order = 1)
	public void setUp() {

		LangNames lang = LangNames.PL;

		this.negval = BigDecimal.valueOf(-1.512D);
		this.posval = BigDecimal.valueOf(1.512D);
		this.repository.removeAll();

		// USD 1
		this.currencyUSD = new Currency();
		this.currencyUSD.setName("US Dollar");
		this.currencyUSD.setCurrencyCode("USD");
		this.currencyUSD.setRate(1.2D);
		this.currencyUSD.setDisplayLocale("en-US");
		this.currencyUSD.setCustomFormatting("");
		this.currencyUSD.setPublished(true);
		this.currencyUSD.setDisplayOrder(1);
		this.currencyUSD.setCreatedOnUtc(Date.from(ZonedDateTime.now(ZoneOffset.UTC)
			.toInstant()));
		this.currencyUSD.setUpdatedOnUtc(Date.from(ZonedDateTime.now(ZoneOffset.UTC)
			.toInstant()));
		this.currencyUSD.setSequence("2323-213-123-123");

		// EUR 2
		this.currencyEUR = new Currency();
		this.currencyEUR.setName("Euro");
		this.currencyEUR.setCurrencyCode("EUR");
		this.currencyEUR.setRate(1D);
		this.currencyEUR.setDisplayLocale("de-DE");
		this.currencyEUR.setCustomFormatting("0.00");
		this.currencyEUR.setPublished(true);
		this.currencyEUR.setDisplayOrder(2);
		this.currencyEUR.setCreatedOnUtc(Date.from(ZonedDateTime.now(ZoneOffset.UTC)
			.toInstant()));
		this.currencyEUR.setUpdatedOnUtc(Date.from(ZonedDateTime.now(ZoneOffset.UTC)
			.toInstant()));

		// RUR 3
		this.currencyRUR = new Currency();
		this.currencyRUR.setName("Russian Rouble");
		this.currencyRUR.setCurrencyCode("RUB");
		this.currencyRUR.setRate(34.5D);
		this.currencyRUR.setDisplayLocale("ru-RU");
		this.currencyRUR.setCustomFormatting("");
		this.currencyRUR.setPublished(true);
		this.currencyRUR.setDisplayOrder(3);
		this.currencyRUR.setCreatedOnUtc(Date.from(ZonedDateTime.now(ZoneOffset.UTC)
			.toInstant()));
		this.currencyRUR.setUpdatedOnUtc(Date.from(ZonedDateTime.now(ZoneOffset.UTC)
			.toInstant()));

		// CHF 4
		this.currencyCHF = new Currency();
		this.currencyCHF.setName("Swiss franc");
		this.currencyCHF.setCurrencyCode("CHF");
		this.currencyCHF.setRate(4.5D);
		this.currencyCHF.setDisplayLocale("fr-CH");
		this.currencyCHF.setCustomFormatting("");
		this.currencyCHF.setPublished(true);
		this.currencyCHF.setDisplayOrder(4);
		this.currencyCHF.setCreatedOnUtc(Date.from(ZonedDateTime.now(ZoneOffset.UTC)
			.toInstant()));
		this.currencyCHF.setUpdatedOnUtc(Date.from(ZonedDateTime.now(ZoneOffset.UTC)
			.toInstant()));

		// PLN 5
		this.currencyPLN = new Currency();
		this.currencyPLN.setName("Polish złoty");
		this.currencyPLN.setCurrencyCode("PLN");
		this.currencyPLN.setRate(4.5D);
		this.currencyPLN.setDisplayLocale("pl-PL");
		this.currencyPLN.setCustomFormatting("");
		this.currencyPLN.setPublished(true);
		this.currencyPLN.setDisplayOrder(5);
		this.currencyPLN.setCreatedOnUtc(Date.from(ZonedDateTime.now(ZoneOffset.UTC)
			.toInstant()));
		this.currencyPLN.setUpdatedOnUtc(Date.from(ZonedDateTime.now(ZoneOffset.UTC)
			.toInstant()));

		// AED 6
		this.currencyAED = new Currency();
		this.currencyAED.setName("Emirates dirham");
		this.currencyAED.setCurrencyCode("AED");
		this.currencyAED.setRate(4.5D);
		this.currencyAED.setDisplayLocale("ar_AE");
		this.currencyAED.setCustomFormatting("");
		this.currencyAED.setPublished(true);
		this.currencyAED.setDisplayOrder(6);
		this.currencyAED.setCreatedOnUtc(Date.from(ZonedDateTime.now(ZoneOffset.UTC)
			.toInstant()));
		this.currencyAED.setUpdatedOnUtc(Date.from(ZonedDateTime.now(ZoneOffset.UTC)
			.toInstant()));

		// AFN 7
		this.currencyAFN = new Currency();
		this.currencyAFN.setName("Emirates dirham");
		this.currencyAFN.setCurrencyCode("AFN");
		this.currencyAFN.setRate(4.5D);
		this.currencyAFN.setDisplayLocale("Afghan afghani");
		this.currencyAFN.setCustomFormatting("");
		this.currencyAFN.setPublished(true);
		this.currencyAFN.setDisplayOrder(7);
		this.currencyAFN.setCreatedOnUtc(Date.from(ZonedDateTime.now(ZoneOffset.UTC)
			.toInstant()));
		this.currencyAFN.setUpdatedOnUtc(Date.from(ZonedDateTime.now(ZoneOffset.UTC)
			.toInstant()));
		this.repository.persist(this.currencyEUR,
				this.currencyUSD,
				this.currencyRUR,
				this.currencyCHF,
				this.currencyPLN,
				this.currencyAED,
				this.currencyAFN);

		this.repository.count();
	}

	@Test
	public void countIsThreeItemsInRepo() {

		final long count = this.repository.count();
		Assert.assertTrue(count == 7);
	}

	@Test
	public void countAsyncIsThreeItemsInRepo() throws InterruptedException, ExecutionException {

		final long count = this.repository.countAsync();
		Assert.assertTrue(count == 7);
	}

	@Test
	public void getCurrencyItems() {

		this.repository.get()
			.forEach(item -> System.out.println(item));
	}

	@Test
	public void testIsNegativeValue() {

		Assert.assertTrue(this.negval.signum() == -1);
	}

	@Test
	public void testIsPositiveValue() {

		Assert.assertTrue(this.posval.signum() == 1);
	}

	@Test
	public void testNegativeFractionValue() {

		final BigDecimal fractionalPart = this.negval.remainder(BigDecimal.ONE);
		Assert.assertTrue(fractionalPart.doubleValue() == -.512);
	}

	@Test
	public void testPositiveFractionValue() {

		final BigDecimal fractionalPart = this.posval.remainder(BigDecimal.ONE);
		Assert.assertTrue(fractionalPart.doubleValue() == .512);
	}

	@Test
	public void testDivideBy1000() {

		this.bigval = BigDecimal.valueOf(1_033_121_511_200_615D);
		int val = 0;
		long valgrp = 0;
		while (0 != (val = this.bigval.remainder(BigDecimal.valueOf(1E3))
			.intValue())) {
			valgrp++;
			System.out.println(val);
			this.bigval = this.bigval.divide(BigDecimal.valueOf(1E3));
		}
		System.out.println("Grupa wartości " + valgrp);

	}

	@Ignore
	public void testCountry() throws IOException, InterruptedException, ExecutionException {

		GenericRepository<Country> countries = new GenericRepository<>(Country.class, "db");
		countries.removeAll();

		Country countryAF = new Country();
		countryAF.setName("Afghanistan");
		countryAF.setTwoLetterIsoCode("AF");
		countryAF.setThreLetterIsoCode("AFG");
		countryAF.setNumericIsoCode("004");
		countryAF.setPicture(CurrencyTests.readBytesFromFile("D:/databases/flagi/mini/af.png"));
		countryAF.setSubjectToVal(true);
		countryAF.setPublished(false);
		countryAF.setDisplayOrder(100L);
		countryAF.setAllowsShipping(true);
		countryAF.setAllowsShipping(true);

		Country countryAL = new Country();
		countryAL.setName("Albania");
		countryAL.setTwoLetterIsoCode("AL");
		countryAL.setThreLetterIsoCode("ALB");
		countryAL.setNumericIsoCode("008");
		countryAL.setPicture(CurrencyTests.readBytesFromFile("D:/databases/flagi/mini/al.png"));
		countryAL.setSubjectToVal(true);
		countryAL.setPublished(false);
		countryAL.setDisplayOrder(100L);
		countryAL.setAllowsShipping(true);
		countryAL.setAllowsShipping(true);

		Country countryDZ = new Country();
		countryDZ.setName("Algeria");
		countryDZ.setTwoLetterIsoCode("DZ");
		countryDZ.setThreLetterIsoCode("DZA");
		countryDZ.setNumericIsoCode("012");
		countryDZ.setPicture(CurrencyTests.readBytesFromFile("D:/databases/flagi/mini/dz.png"));
		countryDZ.setSubjectToVal(true);
		countryDZ.setPublished(false);
		countryDZ.setDisplayOrder(100L);
		countryDZ.setAllowsShipping(true);
		countryDZ.setAllowsShipping(true);

		Country countryAD = new Country();
		countryAD.setName("Andorra");
		countryAD.setTwoLetterIsoCode("AD");
		countryAD.setThreLetterIsoCode("AND");
		countryAD.setNumericIsoCode("020");
		countryAD.setPicture(CurrencyTests.readBytesFromFile("D:/databases/flagi/mini/ad.png"));
		countryAD.setSubjectToVal(true);
		countryAD.setPublished(false);
		countryAD.setDisplayOrder(100L);
		countryAD.setAllowsShipping(true);
		countryAD.setAllowsShipping(true);

		Country countryAO = new Country();
		countryAO.setName("Angola");
		countryAO.setTwoLetterIsoCode("AO");
		countryAO.setThreLetterIsoCode("AGO");
		countryAO.setNumericIsoCode("024");
		countryAO.setPicture(CurrencyTests.readBytesFromFile("D:/databases/flagi/mini/ao.png"));
		countryAO.setSubjectToVal(true);
		countryAO.setPublished(false);
		countryAO.setDisplayOrder(100L);
		countryAO.setAllowsShipping(true);
		countryAO.setAllowsShipping(true);

		Country countryAI = new Country();
		countryAI.setName("Anguilla");
		countryAI.setTwoLetterIsoCode("AI");
		countryAI.setThreLetterIsoCode("AIA");
		countryAI.setNumericIsoCode("660");
		// countryAI.setPicture(readBytesFromFile("D:/databases/flagi/mini/ai.png"));
		countryAI.setSubjectToVal(true);
		countryAI.setPublished(false);
		countryAI.setDisplayOrder(100L);
		countryAI.setAllowsShipping(true);
		countryAI.setAllowsShipping(true);

		Country countryAQ = new Country();
		countryAQ.setName("Antarctica");
		countryAQ.setTwoLetterIsoCode("AQ");
		countryAQ.setThreLetterIsoCode("ATA");
		countryAQ.setNumericIsoCode("010");
		// countryAQ.setPicture(readBytesFromFile("D:/databases/flagi/mini/aq.png"));
		countryAQ.setSubjectToVal(true);
		countryAQ.setPublished(false);
		countryAQ.setDisplayOrder(100L);
		countryAQ.setAllowsShipping(true);
		countryAQ.setAllowsShipping(true);

		Country countryAG = new Country();
		countryAG.setName("Antigua and Barbuda");
		countryAG.setTwoLetterIsoCode("AG");
		countryAG.setThreLetterIsoCode("ATG");
		countryAG.setNumericIsoCode("028");
		countryAG.setPicture(CurrencyTests.readBytesFromFile("D:/databases/flagi/mini/ag.png"));
		countryAG.setSubjectToVal(true);
		countryAG.setPublished(false);
		countryAG.setDisplayOrder(100L);
		countryAG.setAllowsShipping(true);
		countryAG.setAllowsShipping(true);

		Country countrySA = new Country();
		countrySA.setName("Saudi Arabia");
		countrySA.setTwoLetterIsoCode("SA");
		countrySA.setThreLetterIsoCode("SAU");
		countrySA.setNumericIsoCode("682");
		countrySA.setPicture(CurrencyTests.readBytesFromFile("D:/databases/flagi/mini/sa.png"));
		countrySA.setSubjectToVal(true);
		countrySA.setPublished(false);
		countrySA.setDisplayOrder(100L);
		countrySA.setAllowsShipping(true);
		countrySA.setAllowsShipping(true);

		Country countryAR = new Country();
		countryAR.setName("Argentina");
		countryAR.setTwoLetterIsoCode("AR");
		countryAR.setThreLetterIsoCode("ARG");
		countryAR.setNumericIsoCode("026");
		countryAR.setPicture(CurrencyTests.readBytesFromFile("D:/databases/flagi/mini/ar.png"));
		countryAR.setSubjectToVal(true);
		countryAR.setPublished(false);
		countryAR.setDisplayOrder(100L);
		countryAR.setAllowsShipping(true);
		countryAR.setAllowsShipping(true);

		Country countryAM = new Country();
		countryAM.setName("Armenia");
		countryAM.setTwoLetterIsoCode("AM");
		countryAM.setThreLetterIsoCode("ARM");
		countryAM.setNumericIsoCode("051");
		countryAM.setPicture(CurrencyTests.readBytesFromFile("D:/databases/flagi/mini/am.png"));
		countryAM.setSubjectToVal(true);
		countryAM.setPublished(false);
		countryAM.setDisplayOrder(100L);
		countryAM.setAllowsShipping(true);
		countryAM.setAllowsShipping(true);

		Country countryAW = new Country();
		countryAW.setName("Aruba");
		countryAW.setTwoLetterIsoCode("AW");
		countryAW.setThreLetterIsoCode("ABW");
		countryAW.setNumericIsoCode("533");
		// countryAW.setPicture(readBytesFromFile("D:/databases/flagi/mini/aw.png"));
		countryAW.setSubjectToVal(true);
		countryAW.setPublished(false);
		countryAW.setDisplayOrder(100L);
		countryAW.setAllowsShipping(true);
		countryAW.setAllowsShipping(true);

		Country countryAU = new Country();
		countryAU.setName("Australia");
		countryAU.setTwoLetterIsoCode("AU");
		countryAU.setThreLetterIsoCode("AUS");
		countryAU.setNumericIsoCode("036");
		countryAU.setPicture(CurrencyTests.readBytesFromFile("D:/databases/flagi/mini/au.png"));
		countryAU.setSubjectToVal(true);
		countryAU.setPublished(false);
		countryAU.setDisplayOrder(100L);
		countryAU.setAllowsShipping(true);
		countryAU.setAllowsShipping(true);

		Country countryAT = new Country();
		countryAT.setName("Austria");
		countryAT.setTwoLetterIsoCode("AT");
		countryAT.setThreLetterIsoCode("AUT");
		countryAT.setNumericIsoCode("040");
		countryAT.setPicture(CurrencyTests.readBytesFromFile("D:/databases/flagi/mini/at.png"));
		countryAT.setSubjectToVal(true);
		countryAT.setPublished(false);
		countryAT.setDisplayOrder(100L);
		countryAT.setAllowsShipping(true);
		countryAT.setAllowsShipping(true);

		Country countryAZ = new Country();
		countryAZ.setName("Azerbaijan");
		countryAZ.setTwoLetterIsoCode("AZ");
		countryAZ.setThreLetterIsoCode("AZE");
		countryAZ.setNumericIsoCode("031");
		countryAZ.setPicture(CurrencyTests.readBytesFromFile("D:/databases/flagi/mini/az.png"));
		countryAZ.setSubjectToVal(true);
		countryAZ.setPublished(false);
		countryAZ.setDisplayOrder(100L);
		countryAZ.setAllowsShipping(true);
		countryAZ.setAllowsShipping(true);

		Country countryBS = new Country();
		countryBS.setName("Bahamas");
		countryBS.setTwoLetterIsoCode("BS");
		countryBS.setThreLetterIsoCode("BHS");
		countryBS.setNumericIsoCode("044");
		countryBS.setPicture(CurrencyTests.readBytesFromFile("D:/databases/flagi/mini/bs.png"));
		countryBS.setSubjectToVal(true);
		countryBS.setPublished(false);
		countryBS.setDisplayOrder(100L);
		countryBS.setAllowsShipping(true);
		countryBS.setAllowsShipping(true);

		countries.persist(countryAF,
				countryAL,
				countryDZ,
				countryAD,
				countryAO,
				countryAI,
				countryAG,
				countrySA,
				countryAR,
				countryAM,
				countryAW,
				countryAT,
				countryAZ,
				countryBS);
		Assert.assertTrue(14 == countries.find(2L)
			.get(0)
			.getStateProvince()
			.size());
	}

	private static byte[] readBytesFromFile(String filePath) throws IOException {

		File inputFile = new File(filePath);
		FileInputStream inputStream = new FileInputStream(inputFile);

		byte[] fileBytes = new byte[(int) inputFile.length()];
		inputStream.read(fileBytes);
		inputStream.close();

		return fileBytes;
	}
}
