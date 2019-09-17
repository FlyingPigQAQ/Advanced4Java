package com.pigcanfly.advance.rxjava2.backpressure;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.processors.PublishProcessor;
import io.reactivex.schedulers.Schedulers;



/**
 * @author tobbyquinn
 * @date 2019/09/17
 */
public class RxBackPressure {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("MainThread:"+Thread.currentThread().getId());
        Observable.create(emitter -> {
            System.out.println("SubThread:"+Thread.currentThread().getId());
            for (int i = 0;i<1000000 ; i++) {
                emitter.onNext(i);
            }
        })
                .observeOn(Schedulers.io())
                .subscribe(ints->{
                    System.out.println("SubscribeThread:"+Thread.currentThread().getId());
                    System.out.println(ints);
                    Thread.sleep(1_000);
                },e->{
                    e.printStackTrace();
                });
//        PublishProcessor<Integer> source = PublishProcessor.create();
//
//        source
//                .observeOn(Schedulers.single())
//                .subscribe(v -> System.out.println(v), Throwable::printStackTrace);
//
//        for (int i = 0; i < 1_000_000; i++) {
//            source.onNext(i);
//        }

        Thread.sleep(100_000);
    }
}
