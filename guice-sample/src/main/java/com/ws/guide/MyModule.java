package com.ws.guide;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

/**
 * @author willis<songkai01>
 * @chapter
 * @section
 * @since 2020年04月23日 20:39
 */
public class MyModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Human.class).to(Man.class).in(Singleton.class);
    }
}