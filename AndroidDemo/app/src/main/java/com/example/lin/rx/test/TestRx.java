package com.example.lin.rx.test;


import java.io.File;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by lin on 18/1/3.
 */

public class TestRx {

    public static void main(String[] args) {
        new TestRx().test();
    }

    public void test() {

        File[] files = new File[10];
        Observable.from(files)
                .flatMap(new Func1<File, Observable<File>>() {
                    @Override
                    public Observable<File> call(File file) {
                        return Observable.from(file.listFiles());
                    }
                });
    }

}
