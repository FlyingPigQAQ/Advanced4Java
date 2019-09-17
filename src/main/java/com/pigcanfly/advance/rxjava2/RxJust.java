package com.pigcanfly.advance.rxjava2;

import io.reactivex.Observable;

/**
 * @author tobbyquinn
 * @date 2019/09/09
 */
public class RxJust {
    public static void main(String[] args) {
        Observable.just("H").subscribe(System.out::println, Throwable::printStackTrace, () -> System.out.println("Complete"));
       //支持参数1-10个，且参数不能为空
        Observable.just("H", "L", "L", "O", "W","W","O","R","L","D").subscribe(System.out::println, Throwable::printStackTrace, () -> System.out.println("Complete"));
        //如果参数为null,则不会执行onError,直接校验抛出异常
        Observable.just(null).subscribe( str->{
                System.out.println(str);
        }, e->{
            System.out.println("Error:"+e.getMessage());
        }, () -> System.out.println("Complete"));


    }
}
