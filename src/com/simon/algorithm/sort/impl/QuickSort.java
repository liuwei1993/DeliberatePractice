package com.simon.algorithm.sort.impl;

import org.junit.Test;

import static com.simon.algorithm.sort.SortUtils.*;

/**
 * QuickSort
 * Created by simon on 17-6-19.
 */
public class QuickSort extends BaseSort {

    @Test
    @Override
    public void testSort() {
        super.testSort();
    }

    @Override
    public void sort(int[] data, int start, int end) {
        quickSort(data, start, end);
    }

    @Override
    public void sort(int[] data) {
        quickSort(data, 0, data.length - 1);
    }

    private static void quickSort(int[] data, int start, int end) {
        if(start >= end) return;
        int targetIndex = partition(data, start, end);
        quickSort(data, start, targetIndex - 1);
        quickSort(data, targetIndex + 1, end);
    }

    private static int partition(int[] data, int start, int end) {
        int target = data[start];
        int targetIndex = start;
        for(int pointer = start + 1; pointer <= end; pointer++) {
            if(data[pointer] < target) {
                targetIndex++;
                swap(data, targetIndex, pointer);
            }
        }
        swap(data, targetIndex, start);
        return targetIndex;
    }

    private static int partition(char[] data, int start, int end) {
        int target = data[start];
        int targetIndex = start;
        for(int pointer = start + 1; pointer <= end; pointer++) {
            if(data[pointer] < target) {
                targetIndex++;
                swap(data, targetIndex, pointer);
            }
        }
        swap(data, targetIndex, start);
        return targetIndex;
    }

    public static void quickSort(char[] data, int start, int end) {
        if(start >= end) return;
        int targetIndex = partition(data, start, end);
        quickSort(data, start, targetIndex - 1);
        quickSort(data, targetIndex + 1, end);
    }

    public static void quickSort(char[] data) {
        quickSort(data, 0, data.length - 1);
    }


}
