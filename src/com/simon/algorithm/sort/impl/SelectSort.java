package com.simon.algorithm.sort.impl;

import org.junit.Test;

import static com.simon.algorithm.sort.SortUtils.swap;

/**
 * SelectSort
 * Created by simon on 17-6-19.
 */
public class SelectSort extends BaseSort {

    @Test
    @Override
    public void testSort() {
        super.testSort();
    }

    @Override
    public void sort(int[] data, int start, int end) {
        selectSort(data, start, end);
    }

    @Override
    public void sort(int[] data) {
        selectSort(data, 0, data.length - 1);
    }


    /*选择排序*/
    void selectSort(int[] data, int start, int end) {
        assert start <= end;
        for (int i = start; i < end; i++) {
            moveMinElementToHead(data, i, end);
        }
    }

    void moveMinElementToHead(int[] data, int start, int end) {
        if(end != start) {
            int minIndex = start;
            int min = data[start];
            for (int i = start + 1; i <= end; i++) {
                if(data[i] < min) {
                    minIndex = i;
                    min = data[i];
                }
            }
            swap(data, minIndex,start);
        }
    }
}
