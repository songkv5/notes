package com.ws.guide;

import com.google.inject.AbstractModule;

/**
 * @Author willis
 * @Time 2020-04-23 23:33
 * @Description MyModule2
 */
public class MyModule2 extends AbstractModule {
    @Override
    protected void configure() {
        bind(Human.class).to(Man.class);
        bind(Animal.class).to(Cat.class);
    }
}
