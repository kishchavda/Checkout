package com.checkout.app.calculator;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ThreeForTwoCalculatorTest {

    private ThreeForTwoCalculator calculator;

    @Before
    public void setUp() {
        calculator = new ThreeForTwoCalculator();
    }

    @Test
    public void test3Items() {
        int price = calculator.calculate(10, 3);
        assertThat(price, is(20));
    }

    @Test
    public void testMultipleOf3Items() {
        int price = calculator.calculate(25, 12);
        assertThat(price, is(200));
    }

    @Test
    public void testRemainderOf2Items() {
        int quantity = 14;
        int price = calculator.calculate(30, quantity);
        assertThat(price, is(300));
    }

    @Test
    public void testRemainderOf1Items() {
        int quantity = 13;
        int price = calculator.calculate(30, quantity);
        assertThat(price, is(270));
    }
}