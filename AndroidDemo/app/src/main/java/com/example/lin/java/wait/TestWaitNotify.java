package com.example.lin.java.wait;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * Created by lin on 17/9/11.
 */

public class TestWaitNotify {

    public static void main(String[] args) {
        System.out.println("How to use wait and notify method in Java");
        System.out.println("Solving Producer Consumer Problem");

        new TestWaitNotify().test();

    }

    private void test() {
        Queue<Integer> buffer = new LinkedList<>();
        int maxSize = 10;

        Thread producer = new Producer(buffer, maxSize, "PRODUCER");
        Thread consumer = new Consumer(buffer, maxSize, "CONSUMER");

        producer.start();
        consumer.start();
    }


    /**
     * Producer Thread will keep producing values for Consumer
     * It will use wait() method when queue is full
     * and use notify method to send notification to Consumer Thread
     */
    class Producer extends Thread {

        private Queue<Integer> queue;
        private int maxSize;

        public Producer(Queue<Integer> queue, int maxSize, String name) {
            super(name);
            this.queue = queue;
            this.maxSize = maxSize;
        }

        @Override
        public void run() {

            while (true) {
                synchronized (queue) {
                    while (queue.size() == maxSize) {
                        try {
                            System.out.println("Queue is full, "
                                    + "Producer thread waiting for "
                                    + "consumer to take something from queue"
                            );
                            queue.wait();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    Random random = new Random();
                    int i = random.nextInt(100);
                    System.out.println("Producing value: " + i);
                    queue.add(i);
                    queue.notifyAll();
                }
            }

        }

    }


    class Consumer extends Thread {

        private Queue<Integer> mQueue;

        private int maxSize;

        public Consumer(Queue<Integer> queue, int maxSize, String name) {
            super(name);
            this.mQueue = queue;
            this.maxSize = maxSize;
        }

        @Override
        public void run() {
            while (true) {
                synchronized (mQueue) {
                    while (mQueue.isEmpty()) {
                        System.out.println("Queue is empty,"
                                + "Consumer Thread is waiting"
                                + "for producer thread to put something in queue"
                        );

                        try {
                            mQueue.wait();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    System.out.println("Consuming value: " + mQueue.remove());

                    mQueue.notifyAll();
                }
            }
        }

    }

}
