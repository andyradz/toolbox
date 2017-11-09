package com.codigo.aplios.toolbox.core.helpers;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Klasa zawiera metody pomocnicze do obsługi dowolnego typu wyliczeniowego. Realizuje najczęściej potrzebne operacje
 * wykonywane na dowolnym typie wyliczeniowym.
 *
 * @author andrzej.radziszewski
 * @version 1.0.0.0
 * @since 2017
 * @category helper
 */
public final class EnumHelper {

	/**
	 * Metoda pobiera wszystkie elementy typu wyliczeniowego
	 *
	 * @param type Klasa typu wyliczeniowego
	 * @return Kolekcja elementów klasy typu wyliczeniowego
	 */
	public static List<Enum<?>> getItems(final Class<? extends Enum<?>> type) {

		return Stream.of(type.getEnumConstants())
				.collect(Collectors.toList());
	}

	/**
	 * Metoda pobiera nazwy elemntów typu wyliczeniowego.
	 *
	 * @param type Klasa typu wyliczeniowego
	 * @return Kolekcja nazw elementów klasy typu wyliczeniowego
	 */
	public static List<String> getNames(final Class<? extends Enum<?>> type) {

		return Stream.of(type.getEnumConstants())
				.map(item -> item.name())
				.collect(Collectors.toList());
	}

	/**
	 * Metoda wyznacza sumę z wartości indeksów poszczególnych elementów typu wyliczeniowego.
	 *
	 * @param type Klasa typu wyliczeniowego
	 * @return Wartość numeryczna, wartośc -1 gdy brak elemntów
	 */
	public static long getOrdinalSum(final Class<? extends Enum<?>> type) {

		return Stream.of(type.getEnumConstants())
				.map(item -> item.ordinal())
				.collect(Collectors.summingLong(item -> item));
	}

	/**
	 * Metoda wyznacza średnią z wartości indeksów poszczególnych elementów typu wyliczeniowego.
	 *
	 * @param type Klasa typu wyliczeniowego
	 * @return Wartość numeryczna, wartośc -1 gdy brak elemntów
	 */
	public static double getOrdinalAvg(final Class<? extends Enum<?>> type) {

		return Stream.of(type.getEnumConstants())
				.map(item -> item.ordinal())
				.collect(Collectors.averagingDouble(item -> item));
	}

	/**
	 * Metoda wyznacza iloczyn z wartości indeksów poszczególnych elementów typu wyliczeniowego.
	 *
	 * @param type Klasa typu wyliczeniowego
	 * @return Wartość numeryczna, wartośc -1 gdy brak elemntów
	 */
	public static long getOrdinalMul(final Class<? extends Enum<?>> type) {

		return Stream.of(type.getEnumConstants())
				.map(item -> item.ordinal())
				.reduce((sum, p) -> sum == 0L ? 1 : p * sum)
				.orElse(-1);
	}

	/**
	 * Metoda pobiera index pierrwszego elementu znajdujący się w klasie typu wyliczeniowego.
	 *
	 * @param type Klasa typu wyliczeniowego
	 * @return Wartość numeryczna, wartość -1 gdy brak elemntów
	 */
	public static int getFirstOrdinal(final Class<? extends Enum<?>> type) {

		return Stream.of(type.getEnumConstants())
				.map(item -> item.ordinal())
				.reduce((a, b) -> a)
				.orElse(-1);
	}

	/**
	 * Metoda pobiera index ostatniego elementu znajdujący się w klasie typu wyliczeniowego.
	 *
	 * @param type Klasa typu wyliczeniowego
	 * @return Wartość numeryczna, wartość -1 gdy brak elemntów
	 */
	public static int getLastOrdinal(final Class<? extends Enum<?>> type) {

		return Stream.of(type.getEnumConstants())
				.map(item -> item.ordinal())
				.reduce((a, b) -> b)
				.orElse(-1);
	}

	/**
	 * Metoda pobiera nazwę pierwszego elementu znajdujący się w klasie typu wyliczeniowego.
	 *
	 * @param type Klasa typu wyliczeniowego
	 * @return Ciąg znaków, pusty ciąg znaków gdy brak elemntów
	 */
	public static String getFirstName(final Class<? extends Enum<?>> type) {

		return Stream.of(type.getEnumConstants())
				.map(item -> item.name())
				.reduce((a, b) -> a)
				.orElse("");
	}

	/**
	 * Metoda pobiera nazwę ostatniego elementu znajdujący się w klasie typu wyliczeniowego.
	 *
	 * @param type Klasa typu wyliczeniowego
	 * @return Ciąg znaków, pusty ciąg znaków gdy brak elemntów
	 */
	public static String getLastName(final Class<? extends Enum<?>> type) {

		return Stream.of(type.getEnumConstants())
				.map(item -> item.name())
				.reduce((a, b) -> b)
				.orElse("");
	}

	/**
	 * Metoda sprawdza czy dany element typu wyliczeniowego posiada definicę stałej o określonej nazwie.
	 *
	 * @param type Klasa typu wyliczeniowego
	 * @param name Nazwa elementu wyliczeniowego szukanego w klasie typu wyliczeniowego
	 * @return Wartość true typ wyliczeniowy zawiera element o takiej nazwie, wartość false brak elementu o takiej nazwie w
	 *         typie wyliczeniowy. Metoda nie jest wrażliwa na wielkośc znaków.
	 */
	public static boolean isExists(final Class<? extends Enum<?>> type, final String name) {

		return Stream.of(type.getEnumConstants())
				.map(item -> item.name())
				.filter(item -> item.equalsIgnoreCase(name))
				.findAny()
				.isPresent();
	}

	/**
	 * Metoda zwarca ilość elementów typu wyliczeniowego
	 *
	 * @param type Klasa typu wyliczeniowego
	 * @return Wartość numeryczna, całkowita z zakresu liczb dodatnich
	 */
	public static long getLength(final Class<? extends Enum<?>> type) {

		return Stream.of(type.getEnumConstants())
				.count();
	}

	/**
	 * Metoda pobiera wzkazany element typu wyliczeniowego na podstawie wskazanej nazwie. Metoda nie jest wrażliwa na
	 * wielkośc znaków.
	 *
	 * @param type Klasa typu wyliczeniowego
	 * @param name Nazwa elementu wyliczeniowego szukanego w klasie typu wyliczeniowego
	 * @return Ciag znaków, pusty ciąg znaków gdy element nieznaleziony
	 */
	public static String getName(final Class<? extends Enum<?>> type, final String name) {

		return Stream.of(type.getEnumConstants())
				.map(item -> item.name())
				.filter(item -> item.equalsIgnoreCase(name))
				.findAny()
				.orElse("");
	}
}
