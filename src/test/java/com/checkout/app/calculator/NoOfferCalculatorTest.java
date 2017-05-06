package com.checkout.app.calculator;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class NoOfferCalculatorTest {

    private NoOfferCalculator calculator;

    @Before
    public void setUp() {
        calculator = new NoOfferCalculator();
    }

    @Test
    public void testManyItems() {
        int price = calculator.calculate(20, 10);
        assertThat(price, is(200));
    }
}
