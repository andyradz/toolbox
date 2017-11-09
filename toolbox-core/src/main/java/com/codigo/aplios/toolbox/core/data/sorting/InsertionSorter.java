package com.codigo.aplios.toolbox.core.data.sorting;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Klasa implementuje mechanizm sortowania. Sortowanie wykonywanie jest na podstawie algorytmu przez wstawianie.
 *
 * Algorytm sortowania przez wstawianie jest uważany za bardzo zły algorytm sortujący. Można go stosować tylko dla
 * niewielkiej liczby elementów w sortowanym zbiorze (do około 5000). Przy większych zbiorach czas sortowania może być
 * zbyt długi.
 *
 * @author andrzej.radziszewski
 * @category data
 * @version 1.0.0.0
 * @since 2017
 *
 * @param <T> Generyczny typ danych
 */
public final class InsertionSorter<T> extends AbstractSorter<T> {

    // -----------------------------------------------------------------------------------------------------------------
    /**
     * Podstawowy konstruktor obiektu klasy
     *
     * @param comparator Mechanizm porównywania obiektów
     */
    public InsertionSorter(Comparator<T> comparator) {

        super(comparator);
    }

    // -----------------------------------------------------------------------------------------------------------------
    /**
     * Podstawowy konstruktor obiektu klasy
     *
     * @param comparator  Mechanizm porównywania obiektów
     * @param sortingMode Kolejność sortowania elementów
     */
    public InsertionSorter(Comparator<T> comparator, SortingMode sortingMode) {

        super(comparator, sortingMode);
    }

    // -----------------------------------------------------------------------------------------------------------------

    /*
     * (non-Javadoc)
     *
     * @see com.codigo.aplios.explorer.data.sorting.ISortable#sort(java.util.List)
     */
    @Override
    public void sort(List<T> data) {

        for (int i = AbstractSorter.ONE; i < data.size(); i++) {
            T element = data.get(i);
            int j = i;
            while ((j > 0) && (this.sortMode.apply(data.get(j - AbstractSorter.ONE), element))) {
                Collections.swap(data, j, j - AbstractSorter.ONE);
                j--;
            }
            data.set(j, element);
        }
    }

    // -----------------------------------------------------------------------------------------------------------------

    /*
     * (non-Javadoc)
     *
     * @see com.codigo.aplios.explorer.data.sorting.ISortable#sort(java.util.stream.Stream)
     */
    @Override
    public void sort(Stream<T> data) {

        this.sort(data.collect(Collectors.toList()));
    }

    // -----------------------------------------------------------------------------------------------------------------

    /*
     * (non-Javadoc)
     *
     * @see com.codigo.aplios.explorer.data.sorting.ISortable#sort(java.lang.Object[])
     */
    @Override
    public void sort(T[] data) {

        for (int i = AbstractSorter.ONE; i < data.length; i++) {
            final T element = data[i];
            int j = i;
            while ((j > 0) && (this.sortMode.apply(data[j - AbstractSorter.ONE], element))) {
                data[j] = data[j - AbstractSorter.ONE];
                j--;
            }
            data[j] = element;
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
}
