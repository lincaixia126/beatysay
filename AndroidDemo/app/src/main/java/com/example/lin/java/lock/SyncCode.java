package com.example.lin.java.lock;

/**
 * Created by lin on 17/10/18.
 */

public class SyncCode {

    private int age = 1;

    public synchronized void add() {
        age ++;
    }

    public void minus() {
        synchronized (this) {
            age--;
        }
    }

    public void test() {
        add();
        minus();
    }

    public static void main(String[] args) {
        new SyncCode().test();
    }

}
