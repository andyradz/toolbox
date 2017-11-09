package com.codigo.aplios.toolbox.core.data.sorting;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

/**
 * Klasa realizuje mechanizm sortowania sposobem HeapSort.
 *
 * @author andrzej.radziszewski
 *
 * @param <T>
 */
public final class HeapSort<T> extends AbstractSorter<T> {

	public HeapSort(Comparator<T> comp) {

		super(comp);
	}

	@Override
	public void sort(List<T> data) {

	}

	@Override
	public void sort(Stream<T> data) {

	}

	@Override
	public void sort(T[] data) {

		// TODO Auto-generated method stub

	}
}