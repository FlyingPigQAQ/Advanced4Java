package com.pigcanfly.advance.rxjava2;

import io.reactivex.Observable;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author tobbyquinn
 * @date 2019/09/10
 */
public class RxFrom {
    public static void main(String[] args) {
        fromArray();
        fromIterable();
        fromFuture();
    }

    public static void fromArray() {
        Observable.fromArray("张三", "李四").doOnSubscribe(s -> {
            System.out.println("FromArray Begin");
        }).subscribe(System.out::println,
                Throwable::printStackTrace,
                () -> System.out.println("OnComplete"));
    }

    public static void fromIterable() {
        ArrayList<Integer> objs = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            objs.add(i);
        }
        Observable.fromIterable(objs).doOnSubscribe(s -> {
            System.out.println("FromIterable Begin");
        }).subscribe(System.out::println,
                Throwable::printStackTrace,
                () -> System.out.println("OnComplete"));
    }

    public static void fromFuture() {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Future<?> future = executorService.submit(() -> {
            System.out.println("开始处理业务");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "处理完毕";
        });
        //处理超时
        Observable.fromFuture(future,4, TimeUnit.SECONDS).doOnSubscribe(s -> {
            System.out.println("FromFuture Begin");
        }).subscribe(str -> {
                    System.out.println(str);
                    Thread.sleep(5000);
                },
                Throwable::printStackTrace,
                () -> System.out.println("OnComplete"));
        System.out.println("测试异步");
        executorService.shutdownNow();
    }
}
