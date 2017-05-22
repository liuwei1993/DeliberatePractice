package com.simon.datastructure.stack;

/**
 * stack
 * Created by simon on 17-5-22.
 */
public class Stack<T> {


    private Node<T> head;

    private int size;

    public Stack() {
        size = 0;
    }

    public boolean push(T data) {
        Node<T> node = new Node<>();
        node.data = data;
        if(head == null) {
            head = node;
        } else {
            node.next = head;
            head = node;
        }
        size++;
        return true;
    }


    public T pop() {
        if(head == null) return null;
        Node<T> result = head;
        head = result.next;
        size--;
        return result.data;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    static class Node<E> {
        E data;
        Node<E> next;
    }

}
