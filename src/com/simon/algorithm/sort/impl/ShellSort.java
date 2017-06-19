package com.simon.algorithm.sort.impl;

import org.junit.Test;

/**
 * ShellSort
 * Created by simon on 17-6-19.
 */
public class ShellSort extends BaseSort {

    InsertSort insertSort = new InsertSort();

    @Test
    @Override
    public void testSort() {
        super.testSort();
    }

    @Override
    public void sort(int[] data, int start, int end) {
        shellSort(data, start, end);
    }

    @Override
    public void sort(int[] data) {
        shellSort(data,0, data.length - 1);
    }

    void shellSort(int[] data, int start, int end) {
        int length = end - start + 1;
        for (int gap = length / 2; gap > 0 ; gap /= 2) {
            for (int i = 0; i < gap; i++) {
                //插入排序
                insertSort.insertSort(data, i + start, end, gap);
            }
        }
    }
}
