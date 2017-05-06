package com.checkout.app.calculator;

public class ThreeForTwoCalculator implements OfferCalculator {

    @Override
    public int calculate(int unitPrice, int quantity) {
        int remainder = quantity % 3;
        int chargeableQuantity = (quantity - remainder) * 2 / 3 + remainder;
        return unitPrice * chargeableQuantity;
    }
}
