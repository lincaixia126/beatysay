package com.example.lin.java.lock;

import java.util.HashMap;
import java.util.UUID;

/**
 * Created by lin on 17/10/19.
 */

public class HashMapMultiThreadTest {

    final HashMap<String, String> map = new HashMap<>(2);
    public void test() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().toString() + "");
                for (int i = 0; i < 10000; i++) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            System.out.println(Thread.currentThread().toString() + "");
                            map.put(UUID.randomUUID().toString(),"");
                        }
                    }, "test" + i).start();
                }
            }
        }, "test");
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new HashMapMultiThreadTest().test();
    }

}
