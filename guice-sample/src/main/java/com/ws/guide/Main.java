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
        Animal animal = injector.getInstance(Animal.class);
        animal.say();
        hu.go2Wc();



        //-------------------
        System.out.println("----------------");

        Injector injector2 = Guice.createInjector(new MyModule2());
        Human human = injector2.getInstance(Human.class);
        Animal animal1 = injector2.getInstance(Animal.class);

        animal1.say();
        human.go2Wc();
    }
}