package com.ws.learn.hystrix;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func0;

/**
 * @author willis<songkai01>
 * @chapter
 * @section
 * @since 2019年06月28日 10:49
 */
public class ObservableTest {
    public static void main(String[] args) {
        testDefer();
    }
    public static void testDefer() {
        Observable<Integer> observable = Observable.<Integer>defer(new Func0<Observable<Integer>>() {
            @Override
            public Observable<Integer> call() {
                Observable<Integer> result = Observable.<Integer>create(new Observable.OnSubscribe<Integer>() {
                    @Override
                    public void call(Subscriber<? super Integer> subscriber) {
                        System.out.println("回调");
                        subscriber.onNext(11);
                    }
                });
                return result;
            }
        });

        observable.subscribe(arg -> {
            System.out.println("result=" + arg);
        });
    }
}