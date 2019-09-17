package com.pigcanfly.advance.rxjava2;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.observables.ConnectableObservable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

import java.util.concurrent.TimeUnit;

/**
 * @author tobbyquinn
 * @date 2019/09/07
 */
public class HelloWorld {

    public static void main(String[] args) throws InterruptedException {
        Observable.create(emitter -> {
            for (int i = 0; i < 100000000; i++) {
                emitter.onNext(i);
            }
        }).subscribe(System.out::println);
    }

    public void twoStartup() throws InterruptedException {
        //冷启动
        Observable<String> coldObservable = Observable.just("冷启动");
        Thread.sleep(5000);
        Consumer<String> coldObserver = System.out::println;
//        coldObservable.subscribe(coldObserver);
        //
        PublishSubject<String> pb = PublishSubject.create();
        coldObservable.subscribe(pb);
        Consumer<String> pber = System.out::println;
        pb.subscribe(pber);
        //热启动
        ConnectableObservable<String> hotObservable = Observable.just("热启动").delay(5, TimeUnit.SECONDS).publish();
        //启动
        hotObservable.connect();
        Thread.sleep(1000);
        Consumer<String> hotObserver = System.out::println;
        hotObservable.subscribe(hotObserver);
        //阻塞主线程20s
        Thread.sleep(20000);
    }
}
