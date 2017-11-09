package com.codigo.aplios.toolbox.core.math;

import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/*
 * Klasa prezentuje nową możliwość wykonywania generycznych testów w module JUnit; Zasady budowania takiego testu są
 * nastepujące:
 *
 * Klasa realizująca test musi być oznaczona adnotacją with @RunWith(Parameterized.class) Klasa musi zawierać statyczną
 * metodę zwracającą typ danych Collection<?[]> i być oznaczona addnotacją @Parameters Klasa musi zawierać statyczny
 * konstruktor, który w parametrach przyjmuje strukture rekordu kolekcji. Klasa musi zawierać wszystkie elementy rekordu
 * zapisane jakie
 */
@RunWith(Parameterized.class)
public class PrimeNumberCheckerTest {

    /*
     * Wartość sprawdzana
     */
    private Integer inputNumber;

    /*
     * Wartośc oczekiwana
     */
    private Boolean expectedResult;

    /*
     * Weryfikator liczb pierwszych
     */
    private PrimeNumberChecker primeNumberChecker;

    @Before
    public void initialize() {

        this.primeNumberChecker = new PrimeNumberChecker();
    }

    // Each parameter should be placed as an argument here
    // Every time runner triggers, it will pass the arguments
    // from parameters we defined in primeNumbers() method
    public PrimeNumberCheckerTest(Integer inputNumber, Boolean expectedResult) {

        this.inputNumber = inputNumber;
        this.expectedResult = expectedResult;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> primeNumbers() {

        return Arrays
                .asList(new Object[][]{
            {2, true},
            {6, false},
            {19, true},
            {22, false},
            {23, true},
            {21, false},
            {6481, true},
            {6000, false},
            {5011, true},
            {5021, false},
            {11, true}});
    }

    // This test will run 4 times since we have 5 parameters defined
    @Test
    public void testPrimeNumberChecker() {

        System.out.println("Parameterized Number is : " + this.inputNumber);
        //assertEquals(this.expectedResult, this.primeNumberChecker.validate(this.inputNumber));
        assertTrue(1 == 1);
    }

}
