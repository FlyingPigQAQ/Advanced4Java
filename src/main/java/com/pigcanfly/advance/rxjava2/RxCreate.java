package com.pigcanfly.advance.rxjava2;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;

/**
 * @author tobbyquinn
 * @date 2019/09/09
 */
public class RxCreate {
    public static void main(String[] args) {
        Observable.create((ObservableOnSubscribe<Integer>) emitter -> {
            try {
                if (!emitter.isDisposed()) {
                    for (int i = 0; i < 10; i++) {
                        emitter.onNext(i);
                    }
                    emitter.onComplete();
                }
            } catch (Exception e) {
                emitter.onError(e);
            }
        }).subscribe(e -> System.out.println("Next:" + e), t -> t.printStackTrace(), () -> System.out.println("Sequence Complete"));
    }
}
