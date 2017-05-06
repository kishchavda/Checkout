package com.checkout.app.shoppingitem;

import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

public class ShoppingItemMetaDataProvider {

    private final Map<ShoppingItem, ShoppingItemMetaData> config;

    ShoppingItemMetaDataProvider(Map<ShoppingItem, ShoppingItemMetaData> config) {
        this.config = config;
    }

    public ShoppingItemMetaData getShoppingItemMetaData(ShoppingItem item) {
        ShoppingItemMetaData shoppingItemMetaData = config.get(item);
        checkNotNull(shoppingItemMetaData);
        return shoppingItemMetaData;
    }
}
