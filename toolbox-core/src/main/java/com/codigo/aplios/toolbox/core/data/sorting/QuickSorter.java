package com.codigo.aplios.toolbox.core.data.sorting;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.eclipse.jdt.annotation.NonNull;

/**
 * Klasa realizuje mechanizm sortowania zgodny a algorytmem sortowania QuickSort.
 *
 * @author andrzej.radziszewski
 * @category data
 * @version 1.0.0.0
 * @since 2017
 * @param <T> Generyczny typ danych
 */
public final class QuickSorter<T> extends AbstractSorter<T> {

    // -----------------------------------------------------------------------------------------------------------------
    /**
     * Podstawowy konstruktor obiektu klasy
     *
     * @param comparator Mechanizm porównymania obiektów
     */
    public QuickSorter(@NonNull Comparator<T> comparator) {

        super(comparator);
    }

    // -----------------------------------------------------------------------------------------------------------------
    /**
     * Podstawowy konstruktor obiektu klasy
     *
     * @param comparator  Mechanizm porównymania obiektów
     * @param sortingMode Kolejność sortowania elementów
     */
    public QuickSorter(@NonNull Comparator<T> comparator, @NonNull SortingMode sortingMode) {

        super(comparator, sortingMode);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.codigo.aplios.allmarks.system.sort.AbstractSort#sort(java.util.List)
     */
    @Override
    public void sort(List<T> data) {

        if (data.size() < 2)
            return;

        this.sort(data, 0, data.size() - 1);
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

        if (data.length < 2)
            return;

        this.sort(data, 0, data.length - 1);
    }

    // -----------------------------------------------------------------------------------------------------------------
    private void sort(T[] data, int lbound, int ubound) {

        int i = (lbound + ubound) / 2, j = lbound;

        final T pivot = data[i];
        data[i] = data[ubound];

        for (i = lbound; i < ubound; i++)
            if (!this.sortMode.apply(data[i], pivot)) {
                this.swapper.swap(data, i, j);
                j++;
            }

        data[ubound] = data[j];
        data[j] = pivot;

        if (lbound < (j - 1))
            this.sort(data, lbound, j - 1);

        if ((j + 1) < ubound)
            this.sort(data, j + 1, ubound);
    }

    // -----------------------------------------------------------------------------------------------------------------
    private void sort(List<T> data, int lbound, int ubound) {

        int i = (lbound + ubound) / 2, j = lbound;

        final T pivot = data.get(i);
        data.set(i, data.get(ubound));

        for (i = lbound; i < ubound; i++)
            if (!this.sortMode.apply(data.get(i), pivot)) {
                Collections.swap(data, i, j);
                j++;
            }

        data.set(ubound, data.get(j));
        data.set(j, pivot);

        if (lbound < (j - 1))
            this.sort(data, lbound, j - 1);

        if ((j + 1) < ubound)
            this.sort(data, j + 1, ubound);
    }

    // -----------------------------------------------------------------------------------------------------------------
}
