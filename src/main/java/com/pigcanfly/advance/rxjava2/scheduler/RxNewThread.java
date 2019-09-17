package com.pigcanfly.advance.rxjava2.scheduler;


import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

import java.util.ArrayList;

/**
 * @author tobbyquinn
 * @date 2019/09/10
 */
public class RxNewThread {
    public static void main(String[] args) throws InterruptedException {
       testSingle();
    }
    public static  void testThread() throws InterruptedException {
        ArrayList<Integer> ori = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ori.add(i);
        }
        System.out.println("ThreadId:"+Thread.currentThread().getId());
        Observable.fromIterable(ori)
                .observeOn(Schedulers.computation())
                .subscribeOn(Schedulers.computation())
                .subscribe(ints->{
                    System.out.println(ints);
                    System.out.println(Thread.currentThread().getId());
                    Thread.sleep(1000);
                });
        Thread.sleep(10000);
    }
    public static void testSingle() throws InterruptedException {
        Observable.just(1,2).subscribeOn(Schedulers.io())
                .map(mapper->{
                    System.out.println("Observale主线程:"+Thread.currentThread().getId());
                    return mapper;
                })
                .observeOn(Schedulers.io())
                .subscribe(res->{
                    System.out.println(res);
                    System.out.println("子线程: "+Thread.currentThread().getId());
                    Thread.sleep(10000);
                });
        System.out.println("主线程："+Thread.currentThread().getId());
        Thread.sleep(100000);
    }
}
