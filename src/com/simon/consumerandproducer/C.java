package com.simon.consumerandproducer;

/**
 *
 * Created by simon on 17-5-16.
 */
public class C implements Runnable{

    private final Stack stack;
    private String name;

    public C(String name, Stack stack) {
        this.stack = stack;
        this.name = name;
        start();
    }


    public void work() throws InterruptedException {
        synchronized (stack) {
            while (stack.isEmpty()) {
                System.out.println("thread " + name + " wait!");
                stack.wait();
            }
            String pop = stack.pop();
            Thread.sleep(100);
            System.out.println("thread " + name + " pop " + pop + " and size is " + stack.size());
            stack.notifyAll();
        }
    }


    @Override
    public void run() {
        while(true) {
            try {
                work();
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }

    public void start() {
        Thread thread = new Thread(this);
        thread.setName(name);
        thread.start();
    }
}
