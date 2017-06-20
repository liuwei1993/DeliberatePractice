package com.simon.algorithm.sort.impl;

import org.junit.Test;

import java.util.ArrayList;

import static com.simon.algorithm.sort.SortUtils.makeRandomNum;
import static org.junit.Assert.assertEquals;

/**
 * RadixSort
 * Created by simon on 17-6-20.
 */
public class RadixSort extends BaseSort {


    @Test
    @Override
    public void testSort() {
        super.testSort();
    }

    @Override
    public void sort(int[] data, int min, int max) {
        radixSort(data, min, max);
    }

    @Override
    public void sort(int[] data) {
        radixSort(data, 0, data.length - 1);
    }

    static void radixSort(int[] data, int intLen) {
        final int BUCKETS_COUNT = 16;
        ArrayList<Integer>[] buckets = new ArrayList[BUCKETS_COUNT];

        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<>();
        }

        for (int pos = 0; pos < intLen; pos++) {
            for (Integer num : data) {
                buckets[intAtHexPos(num, pos)].add(num);
            }

            int idx = 0;
            for (ArrayList<Integer> bucket : buckets) {
                for (Integer num : bucket) {
                    data[idx++] = num;
                }
                bucket.clear();
            }
        }
    }


    static void radixSort(int[] data,int min, int max) {
        final int BUCKETS_COUNT = 16;
        ArrayList<Integer>[] buckets = new ArrayList[BUCKETS_COUNT];

        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<>();
        }
        int length = max - min + 1;
        int intLen = computeHexCount(length);
        for (int pos = 0; pos < intLen; pos++) {
            for (Integer num : data) {
                buckets[intAtHexPos(num - min, pos)].add(num);
            }

            int idx = 0;
            for (ArrayList<Integer> bucket : buckets) {
                for (Integer num : bucket) {
                    data[idx++] = num;
                }
                bucket.clear();
            }
        }
    }


    static int intAtHexPos(int num, int pos) {
        int offset = pos * 4;
        return (num & (15 << offset)) >> offset;
    }

    static int computeHexCount(int num) {
        // TODO int有32bit组成，最高位为符号位，但为了简化问题，这里我们假定num永远为正数，后期再做优化
        //32bit就意味着最高有8个二进制位
        for (int i = 7; i >= 0; i--) {
            if(intAtHexPos(num, i) != 0) {
                return i + 1;
            }
        }
        return 0;
    }

    @Test
    public void testComputeHexCount() {
        for (int i = 0; i < 100; i++) {
            //TODO 当count 为0或者8时会出现问题，后续解决
            int count = makeRandomNum(1,7);
            int max = (1 << count * 4) - 1;
            int min = (1 << (count - 1) * 4);
            int randomNum = makeRandomNum(min, max);
            assertEquals(count, computeHexCount(randomNum));
        }
    }

}
