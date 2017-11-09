package com.codigo.aplios.toolbox.core.data.sorting;

import com.codigo.aplios.toolbox.core.data.sorting.ISortable.SortingMode;
import com.codigo.aplios.toolbox.core.metrics.JUnitStopWatch;
import com.codigo.aplios.toolbox.core.metrics.RepeatRule;
import java.util.stream.Stream;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class QuickSorterTests {

    // -----------------------------------------------------------------------------------------------------------------
    private final static int REPEAT_TIMES = 1;

    // -----------------------------------------------------------------------------------------------------------------
    @Rule
    public JUnitStopWatch stopwatch = new JUnitStopWatch();

    // -----------------------------------------------------------------------------------------------------------------
    @Rule
    public RepeatRule repeatRule = new RepeatRule();

    // -----------------------------------------------------------------------------------------------------------------
    private Integer[] data;

    // -----------------------------------------------------------------------------------------------------------------
    private SortingContext<Integer> sortable;

    // -----------------------------------------------------------------------------------------------------------------
    @BeforeEach
    public void fillArray() {

        this.data =
                new Integer[]{
                    3,
                    231,
                    332,
                    10,
                    -2,
                    -912,
                    -0,
                    +0,
                    11,
                    23,
                    23,
                    -12,
                    -1,
                    -6,
                    -5,
                    221,
                    10,
                    12,
                    209,
                    20,
                    0,
                    3,
                    2,
                    23,
                    12,
                    23,
                    34,
                    45,
                    3,
                    42,
                    1,
                    7,
                    2,
                    12,
                    9,
                    4,
                    8,
                    1,
                    22,
                    50,
                    34,
                    1,
                    2,
                    3,
                    4};
    }

    // -----------------------------------------------------------------------------------------------------------------
    @AfterEach
    public void printEmptyLine() {

        System.out.println();
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Test
    @RepeatedTest(value = QuickSorterTests.REPEAT_TIMES)
    public void shouldSortNumbersAscending() {

        // arrange
        this.sortable = SortingContext.create(new BubbleSorter<>(Integer::compare));

        // act
        this.sortable.getSorter()
                .sort(this.data);

        // assert
        Stream.of(this.data)
                .forEach(e -> System.out.printf("|%4d", e));
        Assert.assertTrue(this.data[0] == -912);
        Assert.assertTrue(this.data[this.data.length - 1] == 332);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Test
    @RepeatedTest(value = QuickSorterTests.REPEAT_TIMES)
    public void shouldSortNumberDescending() {

        // arrange
        this.sortable = SortingContext.create(new BubbleSorter<>(Integer::compare, SortingMode.Descending));

        // act
        this.sortable.getSorter()
                .sort(this.data);

        // assert
        Stream.of(this.data)
                .forEach(e -> System.out.printf("|%4d", e));
        Assert.assertTrue(this.data[0] == 332);
        Assert.assertTrue(this.data[this.data.length - 1] == -912);
    }

    // -----------------------------------------------------------------------------------------------------------------
}
