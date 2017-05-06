package com.checkout.app.calculator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OfferCalculatorConfig {

    @Bean
    public OfferCalculator bogofCalculator() {
        return new BogofCalculator();
    }

    @Bean
    public OfferCalculator noOfferCalculator() {
        return new NoOfferCalculator();
    }

    @Bean
    public OfferCalculator threeForTwoCalculator() {
        return new ThreeForTwoCalculator();
    }
}
