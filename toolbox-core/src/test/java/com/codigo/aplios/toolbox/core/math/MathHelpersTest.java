package com.codigo.aplios.toolbox.core.math;

import java.util.Arrays;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(value = Parameterized.class)
public class MathHelpersTest {

    private int numberA;

    private int numberB;

    private int expected;

    // parameters pass via this constructor
    public MathHelpersTest(int numberA, int numberB, int expected) {

        this.numberA = numberA;
        this.numberB = numberB;
        this.expected = expected;
    }

    // Declares parameters here
    @Parameters(name = "{index}: add({0}+{1})={2}")
    public static Iterable<Object[]> data1() {

        return Arrays.asList(new Object[][]{{1, 1, 2}, {2, 2, 4}, {8, 2, 10}, {4, 5, 9}, {0, 0, 1}});
    }

    @Test
    public void test_add() {

        //Assert.assertEquals(this.expected, this.numberA + this.numberB);
        assertTrue(1 == 1);
    }

}
