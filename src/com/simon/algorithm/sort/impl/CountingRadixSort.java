package com.simon.algorithm.sort.impl;

import org.junit.Test;

import java.util.Arrays;

import static com.simon.algorithm.ArrayUtils.computeHexCount;
import static com.simon.algorithm.ArrayUtils.createRandomArray;
import static com.simon.algorithm.ArrayUtils.intAtHexPos;

/**
 * CountingRadixSort
 * Created by simon on 17-6-20.
 */
public class CountingRadixSort extends BaseSort {

    @Test
    @Override
    public void testSort() {
        super.testSort();
    }

    @Override
    public void sort(int[] data, int min, int max) {
        countingRadixSort(data, min, max);
    }

    @Override
    public void sort(int[] data) {
        countingRadixSort(data, 0, data.length - 1);
    }

    static void countingRadixSort(int[] data, int stringLen) {
        final int BUCKETS_COUNT = 16;
        int[] buffer = new int[data.length];
        int[] in = data;
        int[] out = buffer;

        for (int pos = 0; pos < stringLen; pos++) {
            int[] count = new int[BUCKETS_COUNT + 1];
            for (int i = 0; i < data.length; i++) {
                count[intAtHexPos(in[i],pos) + 1]++;
            }

            for (int j = 1; j <= BUCKETS_COUNT; j++) {
                count[j] += count[j - 1];
            }

            for (int k = 0; k < data.length; k++) {
                int c = intAtHexPos(in[k],pos);
                int idx = count[c];
                out[idx] = in[k];
                //out在idx = count[c]的位置已经被放入了元素in[k]，如果下次循环又出现一个c，新数据应该放在idx + 1的位置，因此把count[c]加一
                count[c]++;
            }

            //swap in and out
            int[] tmp = in;
            in = out;
            out = tmp;
        }

        if (stringLen % 2 == 1) {
            System.arraycopy(in, 0, out, 0, in.length);
        }

    }

    @Test
    public void testCountingRadixSort() {
        int[] data = {345,235,33,789,67,311,987,222};
        countingRadixSort(data, 3);
        System.out.println(Arrays.toString(data));
    }


    static void countingRadixSort(int[] data, int min, int max) {
        final int BUCKETS_COUNT = 16;
        int[] buffer = new int[data.length];
        int[] in = data;
        int[] out = buffer;
        int range = max - min + 1;
        int intLen = computeHexCount(range);
        for (int pos = 0; pos < intLen; pos++) {
            int[] count = new int[BUCKETS_COUNT + 1];
            for (int i = 0; i < data.length; i++) {
                count[intAtHexPos(in[i] - min, pos) + 1]++;
            }

            for (int j = 1; j <= BUCKETS_COUNT; j++) {
                count[j] += count[j - 1];
            }

            for (int k = 0; k < data.length; k++) {
                int c = intAtHexPos(in[k], pos);
                int idx = count[c];
                out[idx] = in[k];
                //out在idx = count[c]的位置已经被放入了元素in[k]，如果下次循环又出现一个c，新数据应该放在idx + 1的位置，因此把count[c]加一
                count[c]++;
            }

            //swap in and out
            int[] tmp = in;
            in = out;
            out = tmp;
        }

        if (intLen % 2 == 1) {
            System.arraycopy(in, 0, out, 0, in.length);
        }

    }

    @Test
    public void testCountingRadixSort2() {
        int[] data = createRandomArray(1000000);
        countingRadixSort(data, 0,data.length - 1);
        System.out.println(Arrays.toString(data));
    }

    @Test
    public void test() {
        int a = 2;
        System.out.println(++a);
    }


}
