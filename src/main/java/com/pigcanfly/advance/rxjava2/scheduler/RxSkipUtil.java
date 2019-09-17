package com.pigcanfly.advance.rxjava2.scheduler;

import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

/**
 * @author tobbyquinn
 * @date 2019/09/16
 */
public class RxSkipUtil {
    public static void main(String[] args) throws InterruptedException {
        Observable.intervalRange(1, 9, 0, 1, TimeUnit.MILLISECONDS)
                .skipUntil(Observable.timer(4, TimeUnit.MILLISECONDS))
                .subscribe(System.out::println);
        Thread.sleep(5000);
    }
}
