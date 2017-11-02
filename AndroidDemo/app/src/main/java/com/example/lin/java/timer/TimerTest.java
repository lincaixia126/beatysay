package com.example.lin.java.timer;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by lin on 17/10/30.
 */

public class TimerTest {

    private int i;

    private Timer timer = new Timer();
    public static void main(String[] args) {
        new TimerTest().test();
    }

    public void test() {

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("饿" + i + Thread.currentThread());
                i++;
                if (i > 20) {
                    timer.cancel();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            System.out.println("test...");
                        }
                    }, 0, 300);
                }
            }
        }, 0, 30);

    }


}
