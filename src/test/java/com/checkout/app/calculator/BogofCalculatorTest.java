package com.checkout.app.calculator;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class BogofCalculatorTest {

    private BogofCalculator calculator;

    @Before
    public void setUp() {
        calculator = new BogofCalculator();
    }

    @Test
    public void test1Item() {
        int price = calculator.calculate(50, 1);
        assertThat(price, is(50));
    }

    @Test
    public void test2Items() {
        int price = calculator.calculate(50, 2);
        assertThat(price, is(50));
    }

    @Test
    public void testEvenNumberOfItems() {
        int price = calculator.calculate(50, 6);
        assertThat(price, is(150));
    }

    @Test
    public void testOddNumberOfItems() {
        int price = calculator.calculate(50, 7);
        assertThat(price, is(200));
    }
}