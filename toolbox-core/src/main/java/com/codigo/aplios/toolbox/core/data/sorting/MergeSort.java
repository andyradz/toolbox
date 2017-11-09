package com.codigo.aplios.toolbox.core.data.sorting;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public final class MergeSort<T> extends AbstractSorter<T> {

	public MergeSort(Comparator<T> comp) {

		super(comp);
	}

	@Override
	public void sort(List<T> data) {

		// TODO Auto-generated method stub
		// Zastosować metodę dziel i zwyciężaj
	}

	@Override
	public void sort(Stream<T> data) {

	}

	@Override
	public void sort(T[] data) {

	}
}