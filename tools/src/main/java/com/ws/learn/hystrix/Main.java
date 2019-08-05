package com.ws.learn.hystrix;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Func0;

/**
 * @author willis<songkai01>
 * @chapter
 * @section
 * @since 2019年06月20日 21:04
 */
public class Main {
    public static void main(String[] args) {
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                System.out.println("回调观察者方法");
                subscriber.onNext("111");
            }

        });

        Observer<String> observer = new Observer<String>() {
            @Override
            public void onCompleted() {
                System.out.println("完成");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                System.out.println("接收事件：" + s);
            }

        };
        observable.subscribe(observer);
        observable.subscribe(arg -> {
            System.out.println("arg =>" + arg);
        });

        Observable.defer(new Func0<Observable<Integer>>() {
            @Override
            public Observable<Integer> call() {
                Observable<Integer> afterCache = null;
                return afterCache;
            }
        });

    }
}