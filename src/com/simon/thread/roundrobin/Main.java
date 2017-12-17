package com.simon.thread.roundrobin;

/**
 * 实现线程轮循
 * Created by simon on 17-12-17.
 */
public class Main {

    private final static int[] data = {1,2,3,4,5,6,7,8,9,10};
    private static int state = 0;
    private static int position = 0;

    static class Thread1 extends Thread {

        @Override
        public void run() {
            while (true) {
                if (position >= data.length) return;
                synchronized (data) {
                    if (state % 3 == 0) {
                        System.out.print("Thread1 print ");
                        System.out.println(data[position]);
                        state++;
                        position++;
                        data.notifyAll();
                    } else {
                        try {
                            data.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    static class Thread2 extends Thread {

        @Override
        public void run() {
            while (true) {
                if(position >= data.length) return;
                synchronized (data) {
                    if(state%3 == 1) {
                        System.out.print("Thread2 print ");
                        System.out.println(data[position]);
                        state ++;
                        state %= 3;
                        position++;
                        data.notifyAll();
                    } else {
                        try {
                            data.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    static class Thread3 extends Thread {

        @Override
        public void run() {
            while (true) {
                if (position >= data.length) return;
                synchronized (data) {
                    if (state % 3 == 2) {
                        System.out.print("Thread3 print ");
                        System.out.println(data[position]);
                        state++;
                        state %= 3;
                        position++;
                        data.notifyAll();
                    } else {
                        try {
                            data.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        new Thread1().start();
        new Thread2().start();
        new Thread3().start();new Thread1().start();
        new Thread2().start();
        new Thread3().start();
    }


}
