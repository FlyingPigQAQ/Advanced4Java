package com.pigcanfly.advance.rxjava2;

import io.reactivex.Observable;

import java.util.Arrays;
import java.util.List;

/**
 * isEmpty()用于判断发射端是否未发射过数据，true表示未发射过，false表示发射过
 * @author tobbyquinn
 * @date 2019/09/16
 */
public class RxIsEmpty {
    public static void main(String[] args) {

        List<int[]> ints = Arrays.asList(new int[]{1, 2, 3, 4, 5});
        List<int[]> ints1 = Arrays.asList();
        List<int[]> ints2 = null;
//        Observable.fromIterable(ints).isEmpty()
//                .subscribe(System.out::println);
        Observable.fromIterable(ints1).isEmpty()
                .subscribe(System.out::println);
        //Throw Exception
//        Observable.fromIterable(ints2).isEmpty()
//                .subscribe(System.out::println);
    }
}
