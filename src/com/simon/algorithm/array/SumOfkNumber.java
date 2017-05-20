package com.simon.algorithm.array;

import org.junit.Test;

/**
 * 给定数组array，长度为k，在其中任取n个数使之和为m，求这n个数的集合
 * Created by simon on 17-5-20.
 */
public class SumOfkNumber {

    //running result
    //success! numbers are [1 2 7 10 ] Sum is 20
    //success! numbers are [1 2 3 4 5 15 ] Sum is 30
    //success! numbers are [1 2 3 4 5 15 50 ] Sum is 80
    //success! numbers are [1 2 3 4 5 6 7 73 99 100 ] Sum is 300
    //success! numbers are [1 2 3 4 5 6 7 8 9 10 51 97 98 99 100 ] Sum is 500
    //success! numbers are [1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 70 97 98 99 100 ] Sum is 600
    @Test
    public void testSumOfKNum() {
        sumOfKNum(10,4,20);
        sumOfKNum(40,6,30);
        sumOfKNum(50,7,80);
        sumOfKNum(100,10,300);
        sumOfKNum(100,15,500);
        sumOfKNum(100,21,600);
    }

    void sumOfKNum(int k, int n, int m) {

        int[] array = new int[k];
        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1;
        }
        boolean[] selected = new boolean[k];


        boolean result = sumOfKNum(array, selected, n, m);

        if(result) {
            StringBuilder sb = new StringBuilder();
            int sum = 0;
            for (int i = 0; i < array.length; i++) {
                if(selected[i]) {
                    sb.append(array[i]).append(' ');
                    sum += array[i];
                }
            }
            System.out.println("success! numbers are [" + sb.toString() + "] Sum is " + sum);
        } else {
            System.out.println("failure!");
        }
    }

    boolean sumOfKNum(int[] array, boolean[] selected, int n, int m) {
        if(m == -1) return false;
        if(n == 1) {
            for (int i = 0; i < array.length; i++) {
                if(!selected[i] && m == array[i]) {
                    selected[i] = true;
                    return true;
                }
            }
            return false;
        } else {
            for (int i = 0; i < array.length; i++) {
                if(selected[i]) continue;
                selected[i] = true;
                if(sumOfKNum(array, selected, n - 1, m - array[i])) {
                    return true;
                } else {
                    selected[i] = false;
                }
            }
            return false;
        }
    }
}
