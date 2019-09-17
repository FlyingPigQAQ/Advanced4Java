package com.pigcanfly.advance.rxjava2.scheduler;

import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

/**
 * @author tobbyquinn
 * @date 2019/09/16
 */
public class RxInterval {
    public static void main(String[] args) throws InterruptedException {
        Observable.interval(6, TimeUnit.SECONDS).take(6).subscribe(System.out::println);
        Thread.sleep(120000);
    }
}
