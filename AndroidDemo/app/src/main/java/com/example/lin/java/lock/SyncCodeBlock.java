package com.example.lin.java.lock;

public class SyncCodeBlock {

   public volatile int i =1;

    public void add() {
        System.out.println("add here...." + i);
        i++;
    }

    public static void main(String[] args) {
        new SyncCodeBlock().add();
    }

}