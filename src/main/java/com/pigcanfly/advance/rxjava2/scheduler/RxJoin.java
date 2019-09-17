package com.pigcanfly.advance.rxjava2.scheduler;

import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

/**
 * TODO：混乱
 *
 * @author tobbyquinn
 * @date 2019/09/16
 */
public class RxJoin {
    public static void main(String[] args) throws InterruptedException {
        Observable<Long> o1 = Observable.just(1L,2L,3L).take(6);
        Observable<Long> o2 = Observable.just(4L,5L,6L).take(6);
        o1.join(o2,str1->{
            return Observable.just(str1).delay(500, TimeUnit.MILLISECONDS);
        },str2->{
            return Observable.just(str2).delay(1, TimeUnit.SECONDS);
        },(str3,str4)->{
            return str3+":"+str4;
        }).subscribe(System.out::println);
        Thread.sleep(120000);

    }
}
