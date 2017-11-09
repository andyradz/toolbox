import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.ExecutionException;

// https://www.shoper.pl/help/artykul/waga-gabarytowa/

public class TestDb {
	public static void main(String[] args) throws InterruptedException, ExecutionException, IOException {

		// Class<Product> cls = Product.class;
		// Metamodel model = em.getMetamodel();
		// EntityType<Product> entity = model.entity(cls);
		// CriteriaQuery<Product> c = cb.createQuery(cls);
		// Root<Product> account = c.from(entity);
		// Path<Integer> balance = account.<Integer>get("balance");
		// c.where(cb.and
		// (c.greaterThan(balance, 100),
		// c.lessThan(balance), 200)));

		// final GenericRepository<Product> repository = new GenericRepository<>(Product.class, "db");
		// Product entity1 = new Product();
		// entity1.setName("Pojemnik na brudy");
		// entity1.setCreatedDate(Date.from(Instant.now()));
		// entity1.setPrice(100.12);
		//
		// Product entity2 = new Product();
		// entity2.setName("Pojemnik na dane");
		// entity2.setCreatedDate(Date.from(Instant.now()));
		// entity2.setPrice(100.14);
		//
		// Product entity3 = new Product();
		// // entity3.setName("Pojemnik na dane");
		// entity3.setCreatedDate(Date.from(Instant.now()));
		// entity3.setPrice(1001.14);
		//
		// Product entity4 = new Product();
		// entity4.setName("Rower górski");
		// entity4.setCreatedDate(Date.from(Instant.now()));
		// entity4.setPrice(2001.14);
		//
		// Product entity5 = new Product();
		// entity5.setCreatedDate(Date.from(Instant.now()));
		// entity5.setName("Rower górski");
		// entity5.setPrice(6001.14);
		//
		// Product entity6 = new Product();
		// entity6.setCreatedDate(Date.from(Instant.now()));
		// entity6.setName("Samochów górski");
		// entity6.setPrice(11001.14);
		//
		// Product entity7 = new Product();
		// entity7.setCreatedDate(Date.from(Instant.now()));
		// entity7.setName("Pojemnik ukropu");
		// entity7.setPrice(1.14);
		//
		// // repository.persist(entity1,entity2,entity3,entity4,entity5);
		// repository.get(4).ifPresent(out::println);
		// // System.exit(0);
		//
		// GenericRepository<CategoryAttribute> repoCategory = new GenericRepository<>(CategoryAttribute.class, "db");
		// CategoryAttribute ca1 = new CategoryAttribute();
		// ca1.setName("Main");
		// ca1.setSearchable(true);
		// ca1.setValue("1");
		// CategoryAttribute ca2 = new CategoryAttribute();
		// ca2.setName("Product");
		// ca2.setSearchable(true);
		// ca2.setValue("2");
		//
		// repoCategory.persist(ca1, ca2);
		//
		// System.out.println("Primary key: " + entity1.getId());
		// System.out.println("Primary key: " + entity2.getId());
		// System.out.println(repository.count());
		// System.out.println(repository.countAsync());
		//
		// System.out.println(repoCategory.find(1));
		// System.out.println(repoCategory.find(3));
		//
		// GenericRepository<ZipCode> zipCodes = new GenericRepository<>(ZipCode.class, "db");
		//
		// ZipCode zipCode1 = new ZipCode();
		// zipCode1.setZipCity("Warszwa");
		// zipCode1.setZipCode("03-126");
		// zipCode1.setZipState("Tarchomin");
		// zipCode1.setZipLatitude(1.0D);
		// zipCode1.setZipLongitude(1.0D);
		// zipCode1.setZipState("V");
		// zipCodes.persist(zipCode1);
		//
		// zipCode1 = new ZipCode();
		// zipCode1.setZipCity("Brzoza");
		// zipCode1.setZipCode("86-061");
		// zipCode1.setZipState("Brzoza");
		// zipCode1.setZipLatitude(2.0D);
		// zipCode1.setZipLongitude(2.0D);
		// zipCode1.setZipState("D");
		// zipCodes.persist(zipCode1);
		//
		// System.out.println(zipCodes.find(1).get(0).getZipCode());
		// System.out.println(zipCodes.find(2).get(0).getZipCode());
		// System.out.println(zipCodes.countAsync());
		//
		// Currency currencyUSD = new Currency();
		// currencyUSD.setName("US Dollar");
		// currencyUSD.setCurrencyCode("USD");
		// currencyUSD.setRate(1.2D);
		// currencyUSD.setDisplayLocale("en-US");
		// currencyUSD.setCustomFormatting("");
		// currencyUSD.setPublished(true);
		// currencyUSD.setDisplayOrder(1);
		// currencyUSD.setCreatedOnUtc(Date.from(ZonedDateTime.now(ZoneOffset.UTC).toInstant()));
		// currencyUSD.setUpdatedOnUtc(Date.from(ZonedDateTime.now(ZoneOffset.UTC).toInstant()));
		// currencyUSD.setSequence("2323-213-123-123");
		//
		// // RUR 3
		// Currency currencyRUR = new Currency();
		// currencyRUR.setName("Russian Rouble");
		// currencyRUR.setCurrencyCode("RUB");
		// currencyRUR.setRate(34.5D);
		// currencyRUR.setDisplayLocale("ru-RU");
		// currencyRUR.setCustomFormatting("");
		// currencyRUR.setPublished(true);
		// currencyRUR.setDisplayOrder(3);
		// currencyRUR.setCreatedOnUtc(Date.from(ZonedDateTime.now(ZoneOffset.UTC).toInstant()));
		// currencyRUR.setUpdatedOnUtc(Date.from(ZonedDateTime.now(ZoneOffset.UTC).toInstant()));
		//
		// List<Currency> curriences = new ArrayList<>();
		// curriences.add(currencyUSD);
		//
		// GenericRepository<Country> countries = new GenericRepository<>(Country.class, "db");
		//
		// byte[] picturePl = Files.readAllBytes(Paths.get("D:/databases/flagi/pl.png"));
		//
		// Country country = new Country();
		// country.setPicture(picturePl);
		// CreditCard cd = new CreditCard();
		// cd.setCcNumber("Andrzej");
		// countries.persist(country);
		//
		// curriences.add(currencyRUR);
		// country = new Country();
		// country.setPicture(picturePl);
		// countries.persist(country);
		//
		// System.out.println(countries.countAsync());
		// country = countries.find(2).stream().findFirst().get();
		//
		// final GenericRepository<CalendarData> cals = new GenericRepository<>(CalendarData.class, "db");
		// //cals.removeAll();
		// out.println(cals.count());
		// System.exit(0);
		// Calendar cal = Calendar.getInstance(Locale.getDefault());
		// CalendarData calDta = new CalendarData();
		// calDta.setDate(Date.from(Instant.now()));
		// for (int idx = 0; idx < 21000; idx++) {
		// cal.add(Calendar.DATE, 1);
		// calDta.setDate(cal.getTime());
		// calDta.setYear(cal.get(Calendar.YEAR));
		// calDta.setDayNumInYear(cal.get(Calendar.DAY_OF_YEAR));
		// calDta.setDayNumInMonth(cal.get(Calendar.DAY_OF_MONTH));
		// calDta.setDayNumInWeek(cal.get(Calendar.DAY_OF_WEEK));
		// calDta.setMonthName(new SimpleDateFormat("MMMM").format(cal.getTime()));
		// calDta.setMonthNumInYear(cal.get(Calendar.MONTH) + 1);
		// calDta.setQuaterNum((cal.get(Calendar.MONTH) / 3) + 1);
		// cals.persist(calDta);
		// }
		//
		// out.println(cals.count());

	}

	public static long dayOfQtr(Instant date) {

		LocalDate ld = LocalDate.from(date);
		LocalDate firstDayOfQtr = LocalDate.of(ld.getYear(), ((TestDb.qtr(ld) - 1) * 3) + 1, 1);
		return ChronoUnit.DAYS.between(firstDayOfQtr, date) + 1;
	}

	public static int qtr(LocalDate date) {

		return ((date.getMonthValue() - 1) / 3) + 1;
	}
}
