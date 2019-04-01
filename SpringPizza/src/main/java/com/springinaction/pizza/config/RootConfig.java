package com.springinaction.pizza.config;

import com.springinaction.pizza.service.CustomerService;
import com.springinaction.pizza.service.CustomerServiceImpl;
import com.springinaction.pizza.service.PricingEngine;
import com.springinaction.pizza.service.PricingEngineImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

/**
 * Created by gongzhaopeng on 14/03/2018.
 */
@Configuration
@ComponentScan(basePackages = {"com.springinaction.pizza"},
        excludeFilters = {
                @Filter(type = FilterType.REGEX, pattern = "com\\.springinaction\\.pizza\\.web\\..*")
        })
public class RootConfig {

    @Bean
    public PricingEngine pricingEngine() {
        return new PricingEngineImpl();
    }

    @Bean
    public CustomerService customerService() {
        return new CustomerServiceImpl();
    }
}
