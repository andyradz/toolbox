package com.codigo.aplios.toolbox.core.sort;

// import com.codigo.aplios.toolbox.core.data.sorting.CocktailSorter;
// import com.codigo.aplios.toolbox.core.data.sorting.SortingContext;
// import com.codigo.aplios.toolbox.core.domain.evidence.comparator.PersonByAgeComparator;
// import com.codigo.aplios.toolbox.core.metrics.JUnitStopWatch;
// import com.codigo.aplios.toolbox.core.metrics.RepeatRule;
//
// @FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
// public class BubbleSortTests {
//
// private final static int repeatTimes = 1;
//
// @Rule
// public JUnitStopWatch stopwatch = new JUnitStopWatch();
//
// @Rule
// public RepeatRule repeatRule = new com.codigo.aplios.toolbox.core.metrics.RepeatRule();
//
// private static List<Person> data;
//
// // -----------------------------------------------------------------------------------------------------------------
//
// @Before
// public void initialize() {
//
// data = new ArrayList<>();
// data.add(new Person("Andrzej", "Radziszewski", (byte) 44));
// data.add(new Person("Izabela", "Radziszewska", (byte) 33));
// data.add(new Person("Aleksandra", "Radziszewska", (byte) 2));
// data.add(new Person("Małgorzata", "Sudoł", (byte) 45));
// data.add(new Person("Tomasz", "Jachimek", (byte) 21));
// data.add(new Person("Damian", "Gliwiński", (byte) 25));
// data.add(new Person("Arkadiusz", "Król", (byte) 30));
// data.add(new Person("Artur", "Kotkowski", (byte) 34));
// data.add(new Person("Waldemar", "Rolski", (byte) 70));
// data.add(new Person("Jan", "Majchert", (byte) 44));
// data.add(new Person("Tomasz", "Kopiński", (byte) 11));
// data.add(new Person("Józef", "Połaciński", (byte) 34));
// data.add(new Person("Zygmunt", "Poleczko", (byte) 32));
// data.add(new Person("Stanisława", "Kurcewiczówna", (byte) 100));
// data.add(new Person("Robert", "Górski", (byte) 43));
// data.add(new Person("Jacek", "Placek", (byte) 32));
// data.add(new Person("Ania", "Momar", (byte) 22));
// data.add(new Person("Arkadiusz", "Markowski", (byte) 65));
// data.add(new Person("Jan", "Karczewski", (byte) 35));
// data.add(new Person("Wiktor", "Packa", (byte) 67));
// data.add(new Person("Tamara", "Jacenko", (byte) 54));
// data.add(new Person("Damian", "Waligóra", (byte) 11));
// data.add(new Person("Konrad", "Waligóra", (byte) 11));
// data.add(new Person("Mateusz", "Morawiecki", (byte) 41));
// data.add(new Person("", "", (byte) 0));
// data.add(new Person("Wincentyna", "Koluszko", (byte) 0));
// data.add(new Person("Feliks", "Dzierżyński", (byte) 78));
// data.add(new Person("Marek", "Sudoł", (byte) 78));
// data.add(new Person("Henryk", "Legat ", (byte) 58));
// data.add(new Person(" Adam", "Leguszko ", (byte) 48));
// data.add(new Person(" Marcin ", " Wielicki ", (byte) 23));
// data.add(new Person("Janusz", " Rozpara", (byte) 15));
// data.add(new Person("Bogdan", "Kot", (byte) 115));
//
// System.out.println();
// }
//
// // -----------------------------------------------------------------------------------------------------------------
//
// @Test
// @Repeat(times = repeatTimes)
// public void testBubbleSortByPersonName() {
//
// System.out.println("Sortowanie po imieniu osoby..............................................................");
//
// // SortingContext<Person> ctx = SortingContext.create(new BubbleSorter<>(new PersonByNameComparator()));
// // ctx.sort(data);
//
// data.stream().forEach(System.out::println);
// }
//
// // -----------------------------------------------------------------------------------------------------------------
//
// @Test
// @Repeat(times = repeatTimes)
// public void testBubbleSortByPersonSurname() {
//
// System.out.println("Sortowanie po nazwisku osoby.............................................................");
// //
// // SortingContext<Person> ctx0 = SortingContext.create(new BubbleSorter<>(new PersonBySurnameComparator()));
// // ctx0.sort(data);
//
// data.stream().forEach(System.out::println);
// }
//
// // -----------------------------------------------------------------------------------------------------------------
//
// @Test
// @Repeat(times = repeatTimes)
// public void testBubbleSortByPersonAge() {
//
// System.out.println("Sortowanie po wieku osoby................................................................");
//
// final Class<Person> pesronCls = Person.class;
// final Person personOrginPos = pesronCls.cast(data.toArray()[2]);
// // SortingContext<Person> ctx0 = SortingContext.create(new BubbleSorter<>(new PersonByAgeComparator()));
// // ctx0.getSorter().sort(data);
//
// data.stream().forEach(System.out::println);
//
// final Person personSortPos = pesronCls.cast(data.toArray()[0]);
// assertThat(personSortPos, equalTo(personOrginPos));
// }
//
// // -----------------------------------------------------------------------------------------------------------------
//
// @Test
// @Repeat(times = repeatTimes)
// public void testBubbleSortMinNumber() {
//
// System.out.println("Sortowanie liczb.........................................................................");
// Integer[] data = { 0, 9, 1, 2, 3, 4, 5, 6, 7, 8 };
// //
// // SortingContext<Integer> ctx0 = SortingContext.create(new BubbleSorter<>(Integer::compare));
// // ctx0.getSorter().sort(data);
// assertEquals(data[0], Integer.valueOf(0));
// Stream.of(data).forEach(System.out::println);
// }
//
// // -----------------------------------------------------------------------------------------------------------------
//
// @Test
// @Repeat(times = repeatTimes)
// public void testBubbleSortMaxNumber() {
//
// System.out.println("Sortowanie liczb.........................................................................");
// Integer[] data = { 0, 9, 1, 2, 3, 4, 5, 6, 7, 8 };
//
// // SortingContext<Integer> ctx0 = SortingContext.create(new BubbleSorter<>(Integer::compare));
// // ctx0.getSorter().sort(data);
// assertEquals(data[data.length - 1], Integer.valueOf(9));
// Stream.of(data).forEach(e -> System.out.print(e));
// }
//
// // -----------------------------------------------------------------------------------------------------------------
//
// @Test
// @Repeat(times = repeatTimes)
// public void testCoctailSortMaxNumber() {
//
// System.out.println("Sortowanie liczb.........................................................................");
// Integer[] data = { 0, 9, 1, 2, 3, 4, 5, 6, 7, 8, 12, 33, 11, -1 };
//
// SortingContext<Integer> ctx0 = SortingContext.create(new CocktailSorter<>(Integer::compare));
// ctx0.getSorter().sort(data);
// Stream.of(data).forEach(e -> System.out.print(e));
// }
//
// // -----------------------------------------------------------------------------------------------------------------
//
// @Test
// @Repeat(times = repeatTimes)
// public void testCoctailSortByPersonAge() {
//
// System.out.println("Sortowanie po wieku osoby................................................................");
//
// SortingContext<Person> ctx0 = SortingContext.create(new CocktailSorter<>(new PersonByAgeComparator()));
// ctx0.getSorter().sort(data);
//
// data.stream().forEach(System.out::println);
// }
//
// // -----------------------------------------------------------------------------------------------------------------
//
// }
