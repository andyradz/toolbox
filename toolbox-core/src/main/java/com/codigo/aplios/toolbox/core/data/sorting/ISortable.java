package com.codigo.aplios.toolbox.core.data.sorting;

import java.util.List;
import java.util.stream.Stream;

/**
 * Interfejs definuje zestaw metod sortujących wybrane typy kolekcji danych.
 * Sortowanie elementów w kolekcji będzie odbywało się przez przestawianie elementów na określonych pozycjach kolekcji
 * bez potrzeby tworzenia kolekcji pomocniczych.
 *
 * @author andrzej.radziszewski
 * @version 0.0.0.1
 * @category data
 * @since 2017
 * @param <T> Generyczny typ danych
 */
public interface ISortable<T> {

    /**
     * Rodzaje algorytmów sortujących
     */
    static enum SortingType {

        /**
         * Znacznik sortowania bądelkowego
         */
        Bubble,
        /**
         * Znacznik sortowania przez wstawianie
         */
        Insert,
        /**
         * Znacznik sortowania mieszanego
         */
        Coctail,
        Heap,
        Quick

    }

    /**
     * Tryb sortowania danych rosnący lub malejący
     */
    static enum SortingMode {

        /**
         * Tryb sortowanie - malejący
         */
        Ascending,
        /**
         * Tryb sortowania - rosnący
         */
        Descending;

    }

    /**
     * Metoda realizuje sortowanie elementów kolekcji zgodnie z zastosowanym algorytmem sortowania
     *
     * @param data Kolekcja elementów w postaci tablicy
     */
    void sort(final T[] data);

    /**
     * Wersja metody przeciążonej {@link #sort(Object[]) }
     *
     * @param data Kolekcja elementów w postaci listy
     */
    void sort(final List<T> data);

    /**
     * Wersja metody przeciążonej {@link #sort(Object[]) }
     *
     * @param data Kolekcja elementów w postaci strumienia
     */
    void sort(final Stream<T> data);

}
