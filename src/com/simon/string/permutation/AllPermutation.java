package com.simon.string.permutation;

import org.junit.Test;

import java.util.Arrays;

/**
 * 求全排列
 * Created by simon on 17-5-26.
 */
public class AllPermutation {

    @Test
    public void testCalcAllPermutation() {
        calcAllPermutation("123456".toCharArray(),0,5);
    }


    static void calcAllPermutation(char[] chars, int start, int end) {
        if(start == end) {
            System.out.println(Arrays.toString(chars));
        } else {
            for (int i = start; i <= end; i++) {
                swap(chars, start, i);
                calcAllPermutation(chars, start + 1, end);
                swap(chars, start, i);
            }
        }

    }

    static void swap(char[] chars, int from, int to) {
        char tmp = chars[to];
        chars[to] = chars[from];
        chars[from] = tmp;
    }


    static void calcAllPermutationNotSimple(char[] result, char[] chars, int count, int pointer) {
        if(count == pointer) {
            System.out.println(Arrays.toString(result));
        } else {
            for (int i = 0; i < count; i++) {
                result[pointer] = chars[i];
                calcAllPermutationNotSimple(result, chars, count, pointer+1);
            }
        }
    }

    //TODO 未完成
    @Test
    public void testCalcAllPermutationNotSimple() {
        char[] chars = "abcd".toCharArray();
        for (int count = 0; count < chars.length; count++) {
            char[] result = new char[count];
            calcAllPermutationNotSimple(result, chars, count, 0);
        }

    }

}
