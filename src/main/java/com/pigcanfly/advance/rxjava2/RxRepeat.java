package com.pigcanfly.advance.rxjava2;

import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author tobbyquinn
 * @date 2019/09/10
 */
public class RxRepeat {
    public static void main(String[] args) throws InterruptedException {
//        repeat();
//        repeatWhen();
        repeatUtil();
    }

    public static void repeat() {
        Observable.just("Hello").repeat(3)
                .doOnSubscribe(d -> System.out.println("Repeat Begin"))
                .subscribe(System.out::println);
    }

    public static void repeatWhen() throws InterruptedException {
        Observable.just("Hello")
                //十秒后再次发射
                .repeatWhen(objectObservable -> {
                    return Observable.timer(10, TimeUnit.SECONDS);
                })
                .doOnSubscribe(d -> System.out.println("RepeatWhen Begin"))
                .subscribe(System.out::println);
        Thread.sleep(12000);
    }

    public static void repeatUtil() throws InterruptedException {
        final AtomicInteger atomicInteger = new AtomicInteger();
        System.out.println(atomicInteger.get());
        Observable.just("Hello")
                //十秒后再次发射
                .repeatUntil(() -> {
                    return atomicInteger.get() > 5;
                })
                .doOnSubscribe(d -> {
                    System.out.println("RepeatUtil Begin");

                })
                .subscribe(str->{
                    System.out.println(str);
                    atomicInteger.incrementAndGet();
                });
        Thread.sleep(12000);
    }
}
