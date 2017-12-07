package com.simon.algorithm.linkedlist;


import org.junit.Test;

import java.util.Stack;

/**
 * Created by simon on 17-12-7.
 */
public class NumberAdd {

    public static class Node {
        Node next;
        int data;
    }

    public static Node createList(int ... args) {
        Node head = new Node();
        head.data = args[0];
        Node p = head;
        for (int i = 1; i < args.length; i++) {
            Node newNode = new Node();
            newNode.data = args[i];
            p.next = newNode;
            p = newNode;
        }
        return head;
    }

    public static void printList(Node numList) {
        Node p = numList;
        while (p != null) {
            System.out.print(p.data);
            p = p.next;
        }
        System.out.println();
    }


    public static Node numberAdd(Node numHead1, Node numHead2) {
        if(numHead1 == null) return numHead2;
        if(numHead2 == null) return numHead1;
        Stack<Node> stack1 = new Stack<>();
        Stack<Node> stack2 = new Stack<>();
        Node head1 = numHead1;
        while (head1 != null) {
            stack1.push(head1);
            head1 = head1.next;
        }
        Node head2 = numHead2;
        while (head2 != null) {
            stack2.push(head2);
            head2 = head2.next;
        }
        Stack<Node> longerStack = stack1.size() > stack2.size() ? stack1 : stack2;
        Stack<Node> shorterStack = stack1.size() > stack2.size() ? stack2 : stack1;
        Node result = null;
        int num1;
        int num2;
        int newNum;
        int newData;
        int jinwei = 0;
        Node newNode;
        while (!longerStack.empty()) {
            num1 = longerStack.pop().data;
            num2 = shorterStack.isEmpty() ? 0 : shorterStack.pop().data;
            newNum = num1 + num2;
            newData = newNum % 10;
            newNode = new Node();
            newNode.data = newData + jinwei;
            jinwei = newNum / 10;
            if(result == null) {
                result = newNode;
            } else {
                newNode.next = result;
                result = newNode;
            }
        }
        return result;
    }

    @Test
    public void testAddNumber() {
        Node list1 = createList(1,2,5);
        Node list2 = createList(7,9,4,5,6);
        printList(numberAdd(list1, list2));
    }


}
