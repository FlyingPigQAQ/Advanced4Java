package com.pigcanfly.advance.rxjava2.scheduler;

import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

/**
 * @author tobbyquinn
 * @date 2019/09/16
 */
public class RxSkipWhile {
    public static void main(String[] args) throws InterruptedException {
        Observable.just(1,2,3,4,2,2,1)
                .skipWhile(ints->{
                    return ints<=2;
                })
                .subscribe(System.out::println);
        Thread.sleep(5000);
    }
}
