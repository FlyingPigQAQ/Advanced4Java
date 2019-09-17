package com.pigcanfly.advance.rxjava2.scheduler;

import io.reactivex.Observable;
import io.reactivex.observables.ConnectableObservable;

import java.time.LocalTime;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author tobbyquinn
 * @date 2019/09/16
 */
public class RxRePlay {
    public static void main(String[] args) throws InterruptedException {
        LocalTime now = LocalTime.now();
        System.out.println("开始时间"+now);
        Observable<Long> obs = Observable.interval(1, TimeUnit.SECONDS).take(6);
        ConnectableObservable<Long> conn = obs.replay(1);
        conn.connect();
        conn.subscribe(longs->{
            System.out.println("Sub1:"+longs+LocalTime.now());
        });
        conn.delaySubscription(5,TimeUnit.SECONDS).subscribe(longs->{
            System.out.println("Sub2:"+longs+LocalTime.now());
        });


        Thread.sleep(120000);
    }
}
