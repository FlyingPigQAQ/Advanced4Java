package com.pigcanfly.advance.rxjava1;

import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * @author tobbyquinn
 * @date 2019/09/17
 */
public class TestBackPressure {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("MainThread:" + Thread.currentThread().getId());
        Observable.create(emitter -> {
            System.out.println("SubThread:" + Thread.currentThread().getId());
            for (int i = 0; i < 1000; i++) {
                emitter.onNext(i);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .subscribe(ints -> {
                    System.out.println("SubscribeThread:" + Thread.currentThread().getId());
                    System.out.println(ints);
                }, e -> {
                    e.printStackTrace();
                });
        Thread.sleep(1000000);
    }
}
