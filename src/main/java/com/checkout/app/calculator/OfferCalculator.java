package com.checkout.app.calculator;

public interface OfferCalculator {
    /**
     * Calculates the total price given the quantity and unit price
     * @param unitPrice price of item in pence
     * @param quantity number of items
     * @return total price
     */
    int calculate(int unitPrice, int quantity);
}
