package com.codigo.aplios.toolbox.core.data.sorting;

public final class SortingContext<T> {

    // -----------------------------------------------------------------------------------------------------------------
    public static <T> SortingContext<T> create(ISortable<T> sorter) {

        return new SortingContext<>(sorter);
    }

    private ISortable<T> sorter;

    private SortingContext(ISortable<T> sorter) {

        this.sorter = sorter;
    }

    public ISortable<T> getSorter() {

        return this.sorter;
    }

    public void setSorter(ISortable<T> sorter) {

        this.sorter = sorter;
    }

}
