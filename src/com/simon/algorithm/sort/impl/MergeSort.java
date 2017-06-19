package com.simon.algorithm.sort.impl;

import org.junit.Test;

/**
 * MergeSort
 * Created by simon on 17-6-19.
 */
public class MergeSort extends BaseSort {

    @Test
    @Override
    public void testSort() {
        super.testSort();
    }

    @Override
    public void sort(int[] data, int start, int end) {
        if(start >= end) return;
        int middle = (start + end) / 2;
        sort(data, start, middle);
        sort(data, middle + 1, end);
        mergeArray(data, start, middle, end);
        /*int length = end - start + 1;

        for (int i = start; i <= end; i+=16) {
            InsertSort.insertSort(data, i, Math.min(i + 15, end));
        }

        for (int sortSize = 1; sortSize <= length; sortSize*=2) {
            for (int p = start; p < end; p += 2*sortSize) {
                mergeArray(data, p, p + sortSize -1, Math.min(p + 2*sortSize - 1, end));
            }
        }*/
    }

    @Override
    public void sort(int[] data) {
        sort(data, 0, data.length - 1);
    }

    private void mergeArray(int[] data, int start, int middle, int end) {
        int length = end - start + 1;
        int[] copyArray = new int[length];
        System.arraycopy(data, start, copyArray, 0, length);
        int p1 = 0;
        int p2 = middle - start + 1;
        for (int i = 0; i < length; i++) {
            if(p1 > middle - start) {
                data[start + i] = copyArray[p2];
                p2++;
            } else if(p2 > end - start) {
                data[start + i] = copyArray[p1];
                p1++;
            } else if(copyArray[p1] > copyArray[p2]) {
                data[start + i] = copyArray[p2];
                p2++;
            } else {
                data[start + i] = copyArray[p1];
                p1++;
            }
        }
    }


}
