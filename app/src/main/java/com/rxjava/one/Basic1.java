package com.rxjava.one;

import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Description: 基础教程1
 * Author: lizhen
 * Create Date: 07/03/2018
 */
public class Basic1 {
    private final static String TAG = Basic1.class.getSimpleName();

    public static void sample(){
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
            }
        });

        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG,"observer onSubscribe");
            }

            @Override
            public void onNext(Integer value) {
                Log.d(TAG,"observer onNext : " + value);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                Log.d(TAG,"observer onComplete");
            }
        };
        observable.subscribe(observer);
    }
}
