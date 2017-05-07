package com.checkout.app;

import com.checkout.app.calculator.OfferCalculator;
import com.checkout.app.shoppingitem.ShoppingItem;
import com.checkout.app.shoppingitem.ShoppingItemConfig;
import com.checkout.app.shoppingitem.ShoppingItemMetaData;
import com.checkout.app.shoppingitem.ShoppingItemMetaDataProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkNotNull;

@Slf4j
@Configuration
@Import(ShoppingItemConfig.class)
public class CheckoutImpl implements Checkout {
    private final ShoppingItemMetaDataProvider shoppingItemMetaDataProvider;

    @Autowired
    CheckoutImpl(ShoppingItemMetaDataProvider shoppingItemMetaDataProvider) {
        this.shoppingItemMetaDataProvider = checkNotNull(shoppingItemMetaDataProvider);
    }

    @Override
    public int calculateTotalPrice(ShoppingItem[] basket) {
        log.info("Calculating total price for {}", Arrays.toString(basket));

        // map of each item and number of occurrences
        Map<ShoppingItem, Long> groupedItems = Arrays.stream(basket).collect(
                Collectors.groupingBy(Function.identity(), Collectors.counting()));

        int totalPrice = groupedItems.entrySet().stream().map(entry -> {
            ShoppingItem item = entry.getKey();
            int quantity = entry.getValue().intValue();
            ShoppingItemMetaData shoppingItemMetaData = shoppingItemMetaDataProvider.getShoppingItemMetaData(item);
            int unitPrice = shoppingItemMetaData.getUnitPrice();
            OfferCalculator calculator = shoppingItemMetaData.getOfferCalculator();
            return calculator.calculate(unitPrice, quantity);
        }).mapToInt(Integer::intValue).sum();

        log.info("Sum of items is {}", totalPrice);

        return totalPrice;
    }
}
