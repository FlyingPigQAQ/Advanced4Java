package com.pigcanfly.advance.rxjava2.scheduler;

import io.reactivex.Observable;
import io.reactivex.schedulers.TestScheduler;

import java.util.concurrent.TimeUnit;

/**
 * @author tobbyquinn
 * @date 2019/09/11
 */
public class RxTestScheduler {
    public static void main(String[] args) {
        testAdvanceTo();

    }
    public static void testAdvanceTo(){
        TestScheduler scheduler = new TestScheduler();
        scheduler.createWorker().schedule(()->{
            System.out.println("Run Immediate");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        scheduler.createWorker().schedule(()->{
            System.out.println("延迟20秒运行");
        },20,TimeUnit.SECONDS);
        scheduler.createWorker().schedule(()->{
            System.out.println("延迟40秒运行");
        },40,TimeUnit.SECONDS);

        scheduler.advanceTimeTo(1,TimeUnit.SECONDS);
        System.out.println("Virtual Time:"+scheduler.now(TimeUnit.SECONDS));

        scheduler.advanceTimeTo(100,TimeUnit.SECONDS);
        System.out.println("Virtual Time:"+scheduler.now(TimeUnit.SECONDS));

        scheduler.advanceTimeTo(40,TimeUnit.SECONDS);
        System.out.println("Virtual Time:"+scheduler.now(TimeUnit.SECONDS));


    }
}
