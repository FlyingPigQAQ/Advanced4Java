package com.pigcanfly.advance.rxjava2;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * 总结：
 * ObserverOn,只会影响下有操作，多次执行observerOn，每次都会生效(线程会一直切换)
 * SubscribeOn,同时影响上下游操作，多次执行SubscribeOn，只有第一次切换线程有效
 * 当SubscribeOn和ObserverOn同时存在的时候，SubscribeOn并不会覆盖ObserverOn切换的线程
 * 当线程已经创建好后，即使延迟切换线程，也不会生效
 * @author tobbyquinn
 * @date 2019/09/17
 */
public class DifferenceInSubscribeOnAndObserveOn {
    public static void main(String[] args) throws InterruptedException {
        ObserveOnAndSubscribeOn();
    }

    public static void onlyObserveOn() {
        Observable.create(emitter -> {
            for (int i = 0; i < 10; i++) {
                Thread.sleep(1000);
                emitter.onNext(i + " " + Thread.currentThread().getName()); // main Thread
            }
        }).observeOn(Schedulers.computation())
                .subscribe(str -> {                // RxComputationThreadPool
                    System.out.println("Observable: " + str);
                    System.out.println("Observer: " + Thread.currentThread().getName());
                });
    }

    public static void onlySubscribeOn() throws InterruptedException {
        Observable.create(emitter -> {
            for (int i = 0; i < 10; i++) {
                Thread.sleep(1000);
                emitter.onNext(i + " " + Thread.currentThread().getName()); // RxComputationThreadPool
            }
        }).subscribeOn(Schedulers.computation())
                .subscribe(str -> {                // RxComputationThreadPool
                    System.out.println("Observable: " + str);
                    System.out.println("Observer: " + Thread.currentThread().getName());
                });
        Thread.sleep(20_000);
    }

    public static void delaySubscribeOn() throws InterruptedException {
        Observable<Object> observable = Observable.create(emitter -> {
            for (int i = 0; i < 10; i++) {
                Thread.sleep(1000);
                emitter.onNext(i + " " + Thread.currentThread().getName()); // Thread-0
            }
        });
        new Thread(() -> {
            observable.subscribe(str -> {              //  Thread-0
                System.out.println("Observable: " + str);
                System.out.println("Observer: " + Thread.currentThread().getName());
            });
        }).start();

        System.out.println("Switch Thread"); //并不会更改上下文线程
        observable.subscribeOn(Schedulers.computation());
        Thread.sleep(20_000);
    }

    /**
     * 多个subscribeOn只有第一个生效
     * @throws InterruptedException
     */
    public static void multiSubscribeOn() throws InterruptedException {
        Observable.create(emitter -> {
            for (int i = 0; i < 10; i++) {
                Thread.sleep(1000);
                emitter.onNext(i + " " + Thread.currentThread().getName()); // io thread: RxCachedThreadScheduler
            }
        }).subscribeOn(Schedulers.io()).subscribeOn(Schedulers.computation())
                .subscribe(str -> {              // io thread: RxCachedThreadScheduler
                    System.out.println("Observable: " + str);
                    System.out.println("Observer: " + Thread.currentThread().getName());
                });

        Thread.sleep(20_000);
    }

    public static void multiObserveOn() throws InterruptedException {
        Observable.create(emitter -> {
            for (int i = 0; i < 10; i++) {
                Thread.sleep(1000);
                emitter.onNext(i + " " + Thread.currentThread().getName()); // main thread
            }
        }).observeOn(Schedulers.io())
                .map(item->{
                    System.out.println("Map: "+Thread.currentThread().getName()); //io thread :RxCachedThreadScheduler
                    return item;
                }).observeOn(Schedulers.computation())
                .subscribe(str -> {              // conputation thread: RxComputationThreadPool
                    System.out.println("Observable: " + str);
                    System.out.println("Observer: " + Thread.currentThread().getName());
                });
        Thread.sleep(20_000);
    }

    public static void ObserveOnAndSubscribeOn() throws InterruptedException {
        Observable.create(emitter -> {
            for (int i = 0; i < 10; i++) {
                Thread.sleep(1000);
                emitter.onNext(i + " " + Thread.currentThread().getName()); // RxSingleScheduler thread
            }
        }).observeOn(Schedulers.io()).subscribeOn(Schedulers.single())
                .map(item->{
                    System.out.println("Map: "+Thread.currentThread().getName()); //io thread :RxCachedThreadScheduler
                    return item;
                }).observeOn(Schedulers.computation())
                .subscribe(str -> {              // conputation thread: RxComputationThreadPool
                    System.out.println("Observable: " + str);
                    System.out.println("Observer: " + Thread.currentThread().getName());
                });
        Thread.sleep(20_000);
    }
}
