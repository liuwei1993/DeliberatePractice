package com.simon.thread.consumerandproducer;

/**
 * set to save string
 * Created by simon on 17-5-16.
 */
public class Stack {

    public static final int MAX_SIZE = 10;

    private String[] set;

    private int size;

    public Stack() {
        set = new String[MAX_SIZE];
        size = 0;
    }


    public boolean isFull() {
        return size == MAX_SIZE;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    /**
     * @param s a string to push in
     * */
    public boolean push(String s) {
        if(isFull()) {
            return false;
        } else {
            set[size] = s;
            size ++;
            return true;
        }
    }

    public String pop() {
        if(size != 0) {
            return set[--size];
        } else {
            return null;
        }
    }


}
