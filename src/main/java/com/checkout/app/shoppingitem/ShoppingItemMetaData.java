package com.checkout.app.shoppingitem;

import com.checkout.app.calculator.OfferCalculator;
import lombok.Value;

@Value
public class ShoppingItemMetaData {
    private final int unitPrice;
    private final OfferCalculator offerCalculator;
}
