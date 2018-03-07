package com.rxjava.one;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Description: 基础教程1
 * Author: lizhen
 * Create Date: 07/03/2018
 */
public class Basic1 {
    private final static String TAG = Basic1.class.getSimpleName();


    /**
     * Sample.observable observe基本用法
     */
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


    /**
     * map(),将observable发射的数据通过一个function函数转化为其他数据，并发送给observer。
     */
    public static void map(){
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                Log.d(TAG,"emit "+ 4);
                e.onNext(4);
                Log.d(TAG,"emit "+ 6);
                e.onNext(6);
                Log.d(TAG,"emit "+ 8);
                e.onNext(8);
                Log.d(TAG,"emit complete");
                e.onComplete();
            }
        });
        observable.map(new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer integer) throws Exception {
                return integer / 2;
            }
        }).subscribe(new Observer<Integer>() {

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer value) {
                Log.d(TAG,"onNext : " + value);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                Log.d(TAG,"Observer onComplete");
            }
        });
    }

    /**
     * flatmap() 将一个observable的发送事件转化为多个observables，并最后发送到同一个observer上，无序。
     */
    public static void flatmap(){
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                Log.d(TAG,"emit "+ 4);
                e.onNext(4);
                Log.d(TAG,"emit "+ 6);
                e.onNext(6);
                Log.d(TAG,"emit "+ 8);
                e.onNext(8);
                Log.d(TAG,"emit complete");
                e.onComplete();
            }
        });
        observable.flatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Exception {
                List<String> list = new ArrayList<>();
                for(int i=0;i<integer;i++){
                    list.add("who am I ? " + integer);
                }
                return Observable.fromIterable(list).delay(10, TimeUnit.MILLISECONDS);
            }
        }).subscribe(new Observer<String>() {

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String value) {
                Log.d(TAG,"onNext : " + value);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                Log.d(TAG,"Observer onComplete");
            }
        });
    }

    /**
     * concatmap（） 将一个observable的发送事件转化为多个observables，并最后发送到同一个observer上，无序。
     */
    public static void concatmap(){
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                Log.d(TAG,"emit "+ 4);
                e.onNext(4);
                Log.d(TAG,"emit "+ 6);
                e.onNext(6);
                Log.d(TAG,"emit "+ 8);
                e.onNext(8);
                Log.d(TAG,"emit complete");
                e.onComplete();
            }
        });
        observable.concatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Exception {
                List<String> list = new ArrayList<>();
                for(int i=0;i<integer;i++){
                    list.add("who am I ? " + integer);
                }
                return Observable.fromIterable(list).delay(10, TimeUnit.MILLISECONDS);
            }
        }).subscribe(new Observer<String>() {

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String value) {
                Log.d(TAG,"onNext : " + value);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                Log.d(TAG,"Observer onComplete");
            }
        });
    }

    public static void zip(){
        Observable<Integer> observable1 = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                Log.d(TAG,"emit1 "+ 4);
                e.onNext(4);
                Thread.sleep(1000);
                Log.d(TAG,"emit1 "+ 6);
                e.onNext(6);
                Thread.sleep(1000);
                Log.d(TAG,"emit1 "+ 8);
                e.onNext(8);
                Thread.sleep(1000);
                Log.d(TAG,"emit1 complete");
                e.onComplete();
            }
        }).subscribeOn(Schedulers.io());
        Observable<String> observable2 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                Log.d(TAG,"emit2 a");
                e.onNext("a");
                Thread.sleep(1000);
                Log.d(TAG,"emit2 b");
                e.onNext("b");
                Thread.sleep(1000);
                Log.d(TAG,"emit2 c");
                e.onNext("c");
                Thread.sleep(1000);
                Log.d(TAG,"emit2 d");
                e.onNext("d");
                Thread.sleep(1000);
                Log.d(TAG,"emit2 complete");
                e.onComplete();
            }
        }).subscribeOn(Schedulers.io());
        Observable.zip(observable1, observable2, new BiFunction<Integer, String, String>() {
            @Override
            public String apply(Integer integer, String s) throws Exception {
                return integer + "," + s;
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String value) {
                Log.d(TAG,"onNext : " + value);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                Log.d(TAG,"Observer onComplete");
            }
        });
    }
}
