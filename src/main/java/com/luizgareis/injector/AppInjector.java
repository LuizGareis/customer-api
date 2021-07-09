package com.luizgareis.injector;

import com.google.inject.AbstractModule;
import com.luizgareis.services.AddressService;
import com.luizgareis.services.CustomerService;

public class AppInjector extends AbstractModule {

    @Override
    protected void configure() {
        bind(AddressService.class);
        bind(CustomerService.class);
    }
}
