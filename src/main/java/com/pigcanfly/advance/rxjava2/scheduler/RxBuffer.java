package com.pigcanfly.advance.rxjava2.scheduler;

import io.reactivex.Observable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

/**
 * @author tobbyquinn
 * @date 2019/09/11
 */
public class RxBuffer {
    public static void main(String[] args) {
        ArrayList<String> ints = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            ints.add("z"+i);
        }
        Observable.fromIterable(ints)
                .takeLast(1,TimeUnit.SECONDS)
                .subscribe(longs->{
                    System.out.println(longs);
                    Thread.sleep(5000);
                },e->{
                    e.printStackTrace();
                },()->{
                    System.out.println("Complete");
                });

        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
