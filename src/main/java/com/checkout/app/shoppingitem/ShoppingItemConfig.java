package com.checkout.app.shoppingitem;

import com.checkout.app.calculator.OfferCalculator;
import com.checkout.app.calculator.OfferCalculatorConfig;
import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import static com.checkout.app.shoppingitem.ShoppingItem.*;

@Configuration
@Import(OfferCalculatorConfig.class)
public class ShoppingItemConfig {
    @Autowired
    private OfferCalculator noOfferCalculator;
    @Autowired
    private OfferCalculator bogofCalculator;
    @Autowired
    private OfferCalculator threeForTwoCalculator;

    @Bean
    public ShoppingItemMetaDataProvider shoppingItemMetaDataProvider() {
        return new ShoppingItemMetaDataProvider(ImmutableMap.of(
                APPLE, new ShoppingItemMetaData(35, noOfferCalculator),
                BANANA, new ShoppingItemMetaData(20, noOfferCalculator),
                MELON, new ShoppingItemMetaData(50, bogofCalculator),
                LIME, new ShoppingItemMetaData(15, threeForTwoCalculator)));
    }
}
