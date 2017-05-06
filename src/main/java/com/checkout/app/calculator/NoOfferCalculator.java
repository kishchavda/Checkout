package com.checkout.app.calculator;

public class NoOfferCalculator implements OfferCalculator {
    @Override
    public int calculate(int unitPrice, int quantity) {
        return unitPrice * quantity;
    }
}
