package com.codigo.aplios.toolbox.core.data.sorting;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Klasa realizuje mechanizm sortowania zgodny a algorytmem sortowania przez wstrząsanie.
 *
 * @author andrzej.radziszewski
 * @category data
 * @version 1.0.0.0
 * @since 2017
 *
 * @param <T> Generyczny typ danych
 */
public final class CoctailSorter<T> extends AbstractSorter<T> {

	// -----------------------------------------------------------------------------------------------------------------

	/**
	 * Podstawowy konstruktor obiektu klasy
	 *
	 * @param comparator Mechanizm porównymania obiektów
	 */
	public CoctailSorter(Comparator<T> comparator) {

		super(comparator);
	}

	// -----------------------------------------------------------------------------------------------------------------

	/**
	 * Podstawowy konstruktor obiektu klasy
	 *
	 * @param comparator Mechanizm porównymania obiektów
	 * @param sortingMode Kolejność sortowania elementów
	 */
	public CoctailSorter(Comparator<T> comparator, SortingMode sortingMode) {

		super(comparator, sortingMode);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.codigo.aplios.allmarks.system.sort.AbstractSort#sort(java.util.List)
	 */
	@Override
	public void sort(List<T> data) {

		boolean swapped = true;

		int i = 0;
		int j = data.size() - 1;
		while ((i < j) && swapped) {
			swapped = false;
			for (int k = i; k < j; k++)
				if (this.sortMode.apply(data.get(k), data.get(k + 1))) {
					T temp = data.get(k);
					Collections.swap(data, k, k + 1);
					data.set(k + 1, temp);
					swapped = true;
				}
			j--;
			if (swapped) {
				swapped = false;
				for (int k = j; k > i; k--)
					if (!this.sortMode.apply(data.get(k), data.get(k - 1))) {
						T temp = data.get(k);
						Collections.swap(data, k, k - 1);
						data.set(k - 1, temp);
						swapped = true;
					}
			}
			i++;
		}
	}

	// -----------------------------------------------------------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 *
	 * @see com.codigo.aplios.allmarks.system.sort.ISortable#sort(java.util.stream.Stream)
	 */
	@Override
	public void sort(Stream<T> data) {

		this.sort(data.collect(Collectors.toList()));
	}

	// -----------------------------------------------------------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 *
	 * @see com.codigo.aplios.allmarks.system.sort.ISortable#sort(java.lang.Object[])
	 */
	@Override
	public void sort(T[] data) {

		boolean swapped = true;

		int i = 0;
		int j = data.length - 1;
		while ((i < j) && swapped) {
			swapped = false;
			for (int k = i; k < j; k++)
				if (this.sortMode.apply(data[k], data[k + 1])) {
					T temp = data[k];
					data[k] = data[k + 1];
					data[k + 1] = temp;
					swapped = true;
				}
			j--;
			if (swapped) {
				swapped = false;
				for (int k = j; k > i; k--)
					if (!this.sortMode.apply(data[k], data[k - 1])) {
						T temp = data[k];
						data[k] = data[k - 1];
						data[k - 1] = temp;
						swapped = true;
					}
			}
			i++;
		}
	}

	// -----------------------------------------------------------------------------------------------------------------
}
