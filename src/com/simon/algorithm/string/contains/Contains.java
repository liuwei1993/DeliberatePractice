package com.simon.algorithm.string.contains;

import org.junit.Test;

/**
 * 给定两个分别由字母组成的字符串A和字符串B，字符串B的长度比字符串A短。请问，如何最快地判断字符串B中所有字母是否都在字符串A里？
 * Created by simon on 17-5-17.
 */
public class Contains {


    private char[] a = "abcdefg".toCharArray();

    private char[] b = "cdef".toCharArray();

    @Test
    public void methodOne() {

        boolean[] bollArray = new boolean[26];


        for (char c : a) {
            int index = c - 'a';
            bollArray[index] = true;
        }

        for (char c : b) {
            int index = c - 'a';
            if(!bollArray[index]) {
                System.out.println("not contains");
                return;
            }
        }
        System.out.println("contains");
    }

    @Test
    public void methodTwo() {
        int hash = 0;

        for (char c : a) {
            hash |= 1 << (c - 'a');
        }

        for (char c : b) {
            if((hash & 1 << c - 'a') == 0) {
                System.out.println("not contains");
                return;
            }
        }
        System.out.println("contains");

    }

    //用空间换时间


}
