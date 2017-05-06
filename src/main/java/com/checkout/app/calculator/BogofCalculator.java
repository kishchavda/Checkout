package com.checkout.app.calculator;

public class BogofCalculator implements OfferCalculator {

    @Override
    public int calculate(int unitPrice, int quantity) {
        if (quantity % 2 == 0) {
            return quantity / 2 * unitPrice;
        } else {
            return ((quantity + 1) / 2) * unitPrice;
        }
    }
}
