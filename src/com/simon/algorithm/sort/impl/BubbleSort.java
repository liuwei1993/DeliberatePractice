package com.simon.algorithm.sort.impl;

import org.junit.Test;

import static com.simon.algorithm.ArrayUtils.swap;

/**
 * BubbleSort
 * Created by simon on 17-6-19.
 */
public class BubbleSort extends BaseSort {


    @Test
    @Override
    public void testSort() {
        super.testSort();
    }

    @Override
    public void sort(int[] data, int start, int end) {
        bubbleSort(data, start, end);
    }

    @Override
    public void sort(int[] data) {
        bubbleSort(data, 0, data.length - 1);
    }

    void bubbleSort(int[] data, int start, int end) {
        assert start <= end;
        for (int i = end; i > start; i--) {
            for (int j = start; j < i; j++) {
                if (data[j] > data[j + 1]) {
                    swap(data, j, j + 1);
                }
            }
        }
    }
}
