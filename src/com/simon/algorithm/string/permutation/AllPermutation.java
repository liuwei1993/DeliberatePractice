package com.simon.algorithm.string.permutation;

import org.junit.Test;

import java.util.Arrays;

/**
 * 求全排列
 * Created by simon on 17-5-26.
 */
public class AllPermutation {

    @Test
    public void testCalcAllPermutation() {
        calcAllPermutation("123".toCharArray(),0);
    }


    static void calcAllPermutation(char[] chars, int pointer) {
        int length = chars.length;
        if(pointer == length - 1) {
            System.out.println(Arrays.toString(chars));
        } else {
            for (int i = pointer; i < length; i++) {
                swap(chars, pointer, i);
                calcAllPermutation(chars, pointer + 1);
                swap(chars, pointer, i);
            }
        }

    }

    static void swap(char[] chars, int from, int to) {
        if(from == to) return;
        char tmp = chars[to];
        chars[to] = chars[from];
        chars[from] = tmp;
    }


    /*
    * 打印出字符串chars中所有元素的非简单全排列
    * 类似进制计算，元素个数决定几进制，也可想象n个转轮组合在一起，也就是ofo单车的密码锁的结构
    * */
    static void calcAllPermutationNotSimple(char[] result, char[] chars, int pointer) {
        int count = chars.length;
        if(count == pointer) {
            System.out.println(Arrays.toString(result));
        } else {
            for (int i = 0; i < count; i++) {
                result[pointer] = chars[i];
                calcAllPermutationNotSimple(result, chars, pointer+1);
            }
        }
    }

    @Test
    public void testCalcAllPermutationNotSimple() {
        char[] chars = "abc".toCharArray();
        char[] result = new char[chars.length];
        calcAllPermutationNotSimple(result, chars, 0);
    }

    static void calcAllCombination(char[] chars) {
        int length = chars.length;
        int combinationCount = (int) Math.pow(length,2);
        for (int count = 0; count < combinationCount; count++) {
            for (int i = 0; i < chars.length; i++) {
                if((count & (1 << i)) != 0) {
                    System.out.print(chars[i]);
                }
            }
            System.out.println();
        }
    }

    @Test
    public void testCalcAllCombination() {
        calcAllCombination("abc".toCharArray());
    }


}
