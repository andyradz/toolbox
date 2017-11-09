package com.codigo.aplios.toolbox.core.helpers;

import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Objects;
import java.util.function.DoubleFunction;
import java.util.function.IntFunction;
import java.util.stream.Stream;

/**
 * @author andrzej.radziszewski
 * @version 1.0.0.1
 * @since 2017
 * @category Helpers
 */
public class ArrayHelper {

	public static void main(String[] args) {

		Integer[][] mularr = { { 1 }, { 1, 2, 3, 4, 5 }, {}, { 0, 0, 0 }, { 12 } };
		Integer[] mularr1 = {};
		Long count = ArrayHelper.hbound(mularr, 1);
		count = ArrayHelper.hbound(mularr, 2);

		Integer[] array = { 1, 5, 1212, 121, 1_000, 110, 12, 1 };

		double avg = ArrayHelper.avg(array);

		IntFunction<String> i = (x) -> Integer.toString(x);
		DoubleFunction<String> ee = (item) -> Double.toHexString(item);

		Integer[] list = ArrayHelper.duplicate(array)
			.toArray(size -> new Integer[size]);
		// final Double maxDouble = ArrayHelper.max(array, Double::compare);
		// ArrayHelper.min(array, Double::compare);
		// ArrayHelper.toByteArray(maxDouble);
		//
		// array = ArrayHelper.resize(array, 1_000);
		//
		// ArrayHelper.values();
		//
		// ArrayHelper.lbound(array);
		// ArrayHelper.hbound(array);
		// array = ArrayHelper.intersect(Double[].class, array, array1);
	}

	/**
	 * Metoda wyznacza średnią wartości z elementów przekaznaych jako tablica elementów numerycznych.
	 *
	 * @param <T> Generyczny typ danych odpowiadający wartości numerycznej
	 * @param array Tablica elementów wartości numerycznych
	 * @return Średnia wartość wyznaczona z elentów tablicy
	 */
	public static final <T extends Number> double avg(T[] array) {

		if (Objects.isNull(array))
			throw new NullPointerException("Tablica elementów nie ma swojej instancji w pamięci!");

		return Arrays.stream(array)
			.map(Number::doubleValue)
			.mapToDouble(Double::doubleValue)
			.average()
			.getAsDouble();
	}

	/**
	 * Metoda wyznacza duplikaty wartości z tablicy elementów.
	 *
	 * @param <T> Generyczny typ danych
	 * @param array Tablica elementów
	 * @return Strumień danych z duplikatami wartości.
	 */
	public static final <T> Stream<T> duplicate(T[] array) {

		if (Objects.isNull(array))
			throw new NullPointerException();

		return Arrays.stream(array)
			.filter(n -> Arrays.stream(array)
				.filter(x -> x == n)
				.count() > 1)
			.distinct();
	}

	public static <E> long hbound(E[][] array, int dimension) throws NullPointerException {

		if (Objects.isNull(array))
			throw new NullPointerException("Brak instancji tablicy!");

		final long count = Arrays.stream(array[dimension])
			.count();
		if (count <= 0)
			throw new ArrayIndexOutOfBoundsException("Tablica elemntów nie posiada ostatniego indeksu!");
		return count - 1;
	}

	public static <E> long hbound(E[] array) throws NullPointerException {

		if (Objects.isNull(array))
			throw new NullPointerException("Brak instancji tablicy!");

		return array.length - 1;
	}

	// -----------------------------------------------------------------------------------------------------------------
	public static <E> E[] intersect(Class<E[]> clazz, E[] arrayLeft, E[] arrayRight) {

		if (arrayLeft == arrayRight)
			return arrayLeft;

		return Arrays.stream(arrayLeft)
			.filter(left -> Arrays.stream(arrayRight)
				.anyMatch(right -> right.equals(left)))
			.toArray(e1 -> clazz.cast(Array.newInstance(clazz.getComponentType(),
					arrayLeft.length >= arrayRight.length ? arrayLeft.length
							: arrayRight.length)));
	}

	// -----------------------------------------------------------------------------------------------------------------
	public static final <T> Iterator<T> join(T[] array1, T[] array2) {

		if (Objects.isNull(array1) || Objects.isNull(array1))
			throw new NullPointerException();

		return Stream.concat(Arrays.stream(array1), Arrays.stream(array2))
			.iterator();
	}

	// -----------------------------------------------------------------------------------------------------------------
	public static <E> long lbound(E[] array) {

		if (Objects.isNull(array))
			throw new NullPointerException("Brak instancji tablicy!");

		return 0L;
	}

	/**
	 * Metoda wyznacza maksymalną wartość z przekazanej tablicy. Maksymalna wartość wyznaczana jest na podstawie
	 * przekazanego komparatora.
	 *
	 * @param array Tablica elementów
	 * @return Maksymalna wartość
	 * @throws NullPointerException Brak instancji tablicy, Brak instancji komparatora
	 */
	public static <E extends Comparable<E>> E max(E[] array, Comparator<E> comparator) throws NullPointerException {

		if (Objects.isNull(array))
			throw new NullPointerException("Brak instancji tablicy!");

		if (Objects.isNull(comparator))
			throw new NullPointerException("Brak instancji komparatora!");

		return Arrays.stream(array)
			.max(comparator)
			.get();
	}

	// -----------------------------------------------------------------------------------------------------------------
	/**
	 * Metoda wyznacza minimalną wartość z przekazanej tablicy. Maksymalna wartość wyznaczana jest na podstawie
	 * przekazanego komparatora.
	 *
	 * @param array Tablica elementów
	 * @return Minimalna wartość
	 * @throws NullPointerException Brak instancji tablicy, Brak instancji komparatora
	 */
	public static <E extends Comparable<E>> E min(E[] array, Comparator<E> comparator) {

		if (Objects.isNull(array))
			throw new NullPointerException("Brak instancji tablicy!");

		if (Objects.isNull(comparator))
			throw new NullPointerException("Brak instancji komparatora!");

		return Arrays.stream(array)
			.min(comparator)
			.get();
	}

	// -----------------------------------------------------------------------------------------------------------------
	public static <E> E[] resize(E[] array, int size)
			throws NullPointerException, IllegalArgumentException, OutOfMemoryError {

		if (Objects.isNull(array))
			throw new NullPointerException("Brak instancji tablicy!");

		if (0 > size)
			throw new IllegalArgumentException("Rozmiar tablicay nie może być wartością ujemną!");

		if (0 == size)
			return array;

		return Arrays.copyOf(array, size);
	}

	// -----------------------------------------------------------------------------------------------------------------
	public static final <T> Iterator<T> sort(T[] array, Comparator<T> comparatotr) {

		return Arrays.stream(array)
			.sorted(comparatotr)
			.iterator();
	}

	// -----------------------------------------------------------------------------------------------------------------
	/**
	 * Metoda tworzy instancję tablicę bajtów na podstawie przekazanej wartości Double. Rozmiar tablicy ustalany jest na
	 * podstawie ilość bajtów typu Double.
	 *
	 * @param value Wartość rzeczywista
	 * @return Tablica bajtów
	 */
	public static byte[] toByteArray(double value) {

		final byte[] bytes = new byte[Double.SIZE];
		ByteBuffer.wrap(bytes)
			.putDouble(value);

		return bytes;
	}

	// -----------------------------------------------------------------------------------------------------------------
	public static int[] values() {

		final int[] vals = { 12, 12, 12, 1, 21, 2, 1, 2 };
		return vals;
	}

	// -----------------------------------------------------------------------------------------------------------------
}
