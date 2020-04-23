package com.ws.guide;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * @author willis<songkai01>
 * @chapter
 * @section
 * @since 2020年04月23日 20:44
 */
public class Main {
    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new MyModule());
        Human hu = injector.getInstance(Human.class);
        hu.go2Wc();
    }
}