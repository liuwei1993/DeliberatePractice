package com.simon.thread.consumerandproducer;

import java.util.Random;

/**
 * Created by simon on 17-5-16.
 */
public class P implements Runnable {

    private final Stack stack;

    private String name;

    public P(String name, Stack stack) {
        this.stack = stack;
        this.name = name;
        start();
    }

    public String create() {
        Random random = new Random(System.currentTimeMillis());
        int anInt = random.nextInt(100);
        try {
            Thread.sleep(300 + 7 * anInt);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return String.valueOf(anInt);
    }

    public void work() throws InterruptedException {
        synchronized (stack) {
            while (stack.isFull()) {
                System.out.println("thread " + name + " wait!");
                stack.wait();
            }
            String s = create();
            stack.push(s);
            System.out.println("thread " + name + " push " + s + " and size is " + stack.size());
            stack.notifyAll();
        }
    }


    @Override
    public void run() {
        while (true) {
            try {
                work();
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }

    }

    public void start() {
        new Thread(this).start();
    }


}
