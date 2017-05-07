package com.checkout.app;

import com.checkout.app.calculator.BogofCalculator;
import com.checkout.app.calculator.NoOfferCalculator;
import com.checkout.app.calculator.ThreeForTwoCalculator;
import com.checkout.app.shoppingitem.ShoppingItem;
import com.checkout.app.shoppingitem.ShoppingItemMetaData;
import com.checkout.app.shoppingitem.ShoppingItemMetaDataProvider;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.checkout.app.shoppingitem.ShoppingItem.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CheckoutImplTest {

    private CheckoutImpl checkout;
    @Mock
    private ShoppingItemMetaDataProvider shoppingItemMetaDataProvider;

    @Before
    public void setUp() {
        checkout = new CheckoutImpl(shoppingItemMetaDataProvider);
    }

    @Test
    public void testSingleItem() {
        ShoppingItem[] shoppingItems = {APPLE};
        ShoppingItemMetaData metaData = new ShoppingItemMetaData(35, new NoOfferCalculator());
        when(shoppingItemMetaDataProvider.getShoppingItemMetaData(APPLE)).thenReturn(metaData);
        int total = checkout.calculateTotalPrice(shoppingItems);
        assertThat(total, is(35));
    }

    @Test
    public void testSingleItemMultipleTimes() {
        ShoppingItem[] shoppingItems = {APPLE, APPLE};
        ShoppingItemMetaData metaData = new ShoppingItemMetaData(35, new NoOfferCalculator());
        when(shoppingItemMetaDataProvider.getShoppingItemMetaData(APPLE)).thenReturn(metaData);
        int total = checkout.calculateTotalPrice(shoppingItems);
        assertThat(total, is(70));
    }

    @Test
    public void testMultipleItemsMultipleTimes() {
        ShoppingItem[] shoppingItems = {APPLE, APPLE, BANANA, LIME, MELON, APPLE, BANANA};
        ShoppingItemMetaData appleMetaData = new ShoppingItemMetaData(35, new NoOfferCalculator());
        ShoppingItemMetaData bananaMetaData = new ShoppingItemMetaData(20, new NoOfferCalculator());
        ShoppingItemMetaData melonMetaData = new ShoppingItemMetaData(50, new BogofCalculator());
        ShoppingItemMetaData limeMetaData = new ShoppingItemMetaData(15, new ThreeForTwoCalculator());
        when(shoppingItemMetaDataProvider.getShoppingItemMetaData(APPLE)).thenReturn(appleMetaData);
        when(shoppingItemMetaDataProvider.getShoppingItemMetaData(BANANA)).thenReturn(bananaMetaData);
        when(shoppingItemMetaDataProvider.getShoppingItemMetaData(MELON)).thenReturn(melonMetaData);
        when(shoppingItemMetaDataProvider.getShoppingItemMetaData(LIME)).thenReturn(limeMetaData);
        int total = checkout.calculateTotalPrice(shoppingItems);
        assertThat(total, is(210));
    }

    @Test
    public void testMultipleItemsThreeForTwoOffer() {
        ShoppingItem[] shoppingItems = {BANANA, BANANA, BANANA, BANANA, BANANA, BANANA};
        ShoppingItemMetaData bananaMetaData = new ShoppingItemMetaData(20, new ThreeForTwoCalculator());
        when(shoppingItemMetaDataProvider.getShoppingItemMetaData(BANANA)).thenReturn(bananaMetaData);
        int total = checkout.calculateTotalPrice(shoppingItems);
        assertThat(total, is(80));
    }

    @Test
    public void testMultipleItemsBogofOffer() {
        ShoppingItem[] shoppingItems = {APPLE, APPLE, APPLE, APPLE};
        ShoppingItemMetaData bananaMetaData = new ShoppingItemMetaData(40, new BogofCalculator());
        when(shoppingItemMetaDataProvider.getShoppingItemMetaData(APPLE)).thenReturn(bananaMetaData);
        int total = checkout.calculateTotalPrice(shoppingItems);
        assertThat(total, is(80));
    }

    @Test
    public void testEmptyBasket() {
        ShoppingItem[] basket = {};
        int total = checkout.calculateTotalPrice(basket);
        assertThat(total, is(0));
    }
}
