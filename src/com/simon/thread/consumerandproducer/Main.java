package com.simon.thread.consumerandproducer;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Stack stack = new Stack();
        P p1 = new P("p1", stack);
        P p2 = new P("p2",stack);
        P p3 = new P("p3",stack);
        C c1 = new C("c1",stack);
        C c2 = new C("c2",stack);
        while (true) {
            Thread.sleep(100000);
        }
    }
}
