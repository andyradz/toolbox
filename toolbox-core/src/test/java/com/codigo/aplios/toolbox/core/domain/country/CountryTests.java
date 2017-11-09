package com.codigo.aplios.toolbox.core.domain.country;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Instant;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.codigo.aplios.toolbox.core.data.repository.GenericRepository;
import com.codigo.aplios.toolbox.core.domain.locale.Country;
import com.codigo.aplios.toolbox.core.domain.locale.StateProvince;
import com.codigo.aplios.toolbox.core.metrics.Order;

// @RunWith(OrderedRunner.class)
public class CountryTests {

	// -----------------------------------------------------------------------------------------------------------------

	GenericRepository<Country> countries = new GenericRepository<>(Country.class, "db");

	// -----------------------------------------------------------------------------------------------------------------

	@Before
	public void setUp() throws Exception {

		this.countries.removeAll();
		Country countryPL = new Country();
		countryPL.setName("Poland");
		countryPL.setTwoLetterIsoCode("PL");
		countryPL.setThreLetterIsoCode("POL");
		countryPL.setNumericIsoCode("616");
		countryPL.setPicture(CountryTests.readBytesFromFile("D:/databases/flagi/mini/pl.png"));
		countryPL.setSubjectToVal(true);
		countryPL.setPublished(false);
		countryPL.setDisplayOrder(100L);
		countryPL.setAllowsShipping(true);
		countryPL.setAllowsShipping(true);

		this.countries.save(countryPL);
		this.countries.count();
	}

	// -----------------------------------------------------------------------------------------------------------------

	@After
	public void tearDown() throws Exception {

	}

	// -----------------------------------------------------------------------------------------------------------------

	/**
	 * Procedura dodaje do repozytorium kraj Polska i sprawdza jej wybrane właściwości
	 *
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	@Test(expected = IOException.class)
	@Order(order = 1)
	public void testInsertPolishCountry() throws IOException, InterruptedException, ExecutionException {

		// Iterator<Country> i1 = this.countries.get1();
		// i1.forEachRemaining(System.out::println);
		//
		// Country countryPL = this.countries.get(1).get();
		// assertTrue(null != countryPL);
		// assertTrue(1 == countries.countAsync());
		// assertTrue(countryPL.getTwoLetterIsoCode().equals("PL"));
	}

	// -----------------------------------------------------------------------------------------------------------------

	/**
	 * Procedura dodaje prowincje do kaju Polska i sprawdza wybrane właściwości
	 *
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	@Test(expected = IOException.class)
	@Order(order = 2)
	public void testInsertPolishProvince() throws IOException, InterruptedException, ExecutionException {

		Country countryPL = this.countries.get(1)
			.get();
		StateProvince stateProvince1 = new StateProvince();
		stateProvince1.setCountry(countryPL);
		stateProvince1.setAbbreviation("PL-DS");
		stateProvince1.setName("województwo dolnośląskie");
		stateProvince1.setDisplayOrder(1);
		stateProvince1.setPublishedDate(Date.from(Instant.now()));

		StateProvince stateProvince2 = new StateProvince();
		stateProvince2.setCountry(countryPL);
		stateProvince2.setAbbreviation("PL-KP");
		stateProvince2.setName("województwo kujawsko-pomorskie");
		stateProvince2.setDisplayOrder(2);
		stateProvince2.setPublishedDate(Date.from(Instant.now()));

		StateProvince stateProvince3 = new StateProvince();
		stateProvince3.setCountry(countryPL);
		stateProvince3.setAbbreviation("PL-LB");
		stateProvince3.setName("województwo lubuskie");
		stateProvince3.setDisplayOrder(3);
		stateProvince3.setPublishedDate(Date.from(Instant.now()));

		StateProvince stateProvince4 = new StateProvince();
		stateProvince4.setCountry(countryPL);
		stateProvince4.setAbbreviation("PL-LD");
		stateProvince4.setName("województwo łódzkie");
		stateProvince4.setDisplayOrder(4);
		stateProvince4.setPublishedDate(Date.from(Instant.now()));

		StateProvince stateProvince5 = new StateProvince();
		stateProvince5.setCountry(countryPL);
		stateProvince5.setAbbreviation("PL-MA");
		stateProvince5.setName("województwo małopolskie");
		stateProvince5.setDisplayOrder(5);
		stateProvince5.setPublishedDate(Date.from(Instant.now()));

		StateProvince stateProvince6 = new StateProvince();
		stateProvince6.setCountry(countryPL);
		stateProvince6.setAbbreviation("PL-MZ");
		stateProvince6.setName("województwo mazowieckie");
		stateProvince6.setDisplayOrder(6);
		stateProvince6.setPublishedDate(Date.from(Instant.now()));

		StateProvince stateProvince7 = new StateProvince();
		stateProvince7.setCountry(countryPL);
		stateProvince7.setAbbreviation("PL-OP");
		stateProvince7.setName("województwo opolskie");
		stateProvince7.setDisplayOrder(7);
		stateProvince7.setPublishedDate(Date.from(Instant.now()));

		StateProvince stateProvince8 = new StateProvince();
		stateProvince8.setCountry(countryPL);
		stateProvince8.setAbbreviation("PL-PK");
		stateProvince8.setName("województwo podkarpackie");
		stateProvince8.setDisplayOrder(8);
		stateProvince8.setPublishedDate(Date.from(Instant.now()));

		StateProvince stateProvince9 = new StateProvince();
		stateProvince9.setCountry(countryPL);
		stateProvince9.setAbbreviation("PL-PD");
		stateProvince9.setName("województwo podlaskie");
		stateProvince9.setDisplayOrder(9);
		stateProvince9.setPublishedDate(Date.from(Instant.now()));

		StateProvince stateProvince10 = new StateProvince();
		stateProvince10.setCountry(countryPL);
		stateProvince10.setAbbreviation("PL-PM");
		stateProvince10.setName("województwo pomorskie");
		stateProvince10.setDisplayOrder(10);
		stateProvince10.setPublishedDate(Date.from(Instant.now()));

		StateProvince stateProvince11 = new StateProvince();
		stateProvince11.setCountry(countryPL);
		stateProvince11.setAbbreviation("PL-SL");
		stateProvince11.setName("województwo śląskie");
		stateProvince11.setDisplayOrder(11);
		stateProvince11.setPublishedDate(Date.from(Instant.now()));

		StateProvince stateProvince12 = new StateProvince();
		stateProvince12.setCountry(countryPL);
		stateProvince12.setAbbreviation("PL-SK");
		stateProvince12.setName("województwo świętokrzyskie");
		stateProvince12.setDisplayOrder(12);
		stateProvince12.setPublishedDate(Date.from(Instant.now()));

		StateProvince stateProvince13 = new StateProvince();
		stateProvince13.setCountry(countryPL);
		stateProvince13.setAbbreviation("PL-WN");
		stateProvince13.setName("województwo warmińsko-mazurskie");
		stateProvince13.setDisplayOrder(13);
		stateProvince13.setPublishedDate(Date.from(Instant.now()));

		StateProvince stateProvince14 = new StateProvince();
		stateProvince14.setCountry(countryPL);
		stateProvince14.setAbbreviation("PL-WN");
		stateProvince14.setName("województwo wielkopolskie");
		stateProvince14.setDisplayOrder(14);
		stateProvince14.setPublishedDate(Date.from(Instant.now()));

		StateProvince stateProvince15 = new StateProvince();
		stateProvince15.setCountry(countryPL);
		stateProvince15.setAbbreviation("PL-WN");
		stateProvince15.setName("województwo zachodniopomorskie");
		stateProvince15.setDisplayOrder(15);
		stateProvince15.setPublishedDate(Date.from(Instant.now()));

		countryPL.setStateProvince(Stream
			.of(stateProvince1,
					stateProvince2,
					stateProvince3,
					stateProvince4,
					stateProvince5,
					stateProvince6,
					stateProvince7,
					stateProvince8,
					stateProvince9,
					stateProvince10,
					stateProvince11,
					stateProvince12,
					stateProvince13,
					stateProvince14,
					stateProvince15)
			.collect(Collectors.toSet()));
		this.countries.merge(countryPL);

		Assert.assertTrue(15 == countryPL.getStateProvince()
			.size());
	}

	// -----------------------------------------------------------------------------------------------------------------

	/**
	 * Procedura ładuje flagę kraju z danej lokalizacji
	 *
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	private static byte[] readBytesFromFile(String filePath) throws IOException {

		final File inputFile = new File(filePath);
		final FileInputStream inputStream = new FileInputStream(inputFile);

		byte[] fileBytes = new byte[(int) inputFile.length()];
		inputStream.read(fileBytes);
		inputStream.close();

		return fileBytes;
	}

	// -----------------------------------------------------------------------------------------------------------------
}
