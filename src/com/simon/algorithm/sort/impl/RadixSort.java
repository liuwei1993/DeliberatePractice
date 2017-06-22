package com.simon.algorithm.sort.impl;

import org.junit.Test;

import java.util.ArrayList;

import static com.simon.algorithm.ArrayUtils.computeHexCount;
import static com.simon.algorithm.ArrayUtils.intAtHexPos;

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



}
