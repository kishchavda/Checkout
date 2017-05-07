package com.checkout.app;

import com.checkout.app.shoppingitem.ShoppingItem;

interface Checkout {
    /**
     * Calculates the total price of the basket of items in pence
     * @param basket array of items
     * @return total price in pence
     */
    int calculateTotalPrice(ShoppingItem[] basket);
}
