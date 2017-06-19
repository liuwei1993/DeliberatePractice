package com.simon.algorithm.sort.impl;

import org.junit.Test;

import static com.simon.algorithm.sort.SortUtils.createRandomArray;
import static com.simon.algorithm.sort.SortUtils.printArray;

/**
 * InsertSort
 * Created by simon on 17-6-19.
 */
public class InsertSort extends BaseSort{


    @Test
    public void testSort() {
        super.testSort();
    }

    @Override
    public void sort(int[] data, int start, int end) {
        insertSort(data, start, end);
    }

    @Override
    public void sort(int[] data) {
        insertSort(data);
    }

    public static void insertSort(int[] data) {
        insertSort(data,0, data.length - 1);
    }

    public static void insertSort(int data[], int start, int end) {
        for (int i = start + 1; i <= end; i++) {
            int tmp = data[i];
            for (int j = start; j < i; j++) {
                if(data[j] > tmp) {
                    System.arraycopy(data, j, data, j + 1, i - j);
                    data[j] = tmp;
                    break;
                }
            }
        }
    }

    void insertSort(int[] data, int start, int end, int gap) {
        for (int i = start + gap; i <= end; i+=gap) {
            int tmp = data[i];
            for (int j = 0; j < i; j+=gap) {
                if(data[j] > tmp) {
                    for (int k = i; k >= j + gap; k-=gap) {
                        data[k] = data[k-gap];
                    }
                    data[j] = tmp;
                    break;
                }
            }
        }
    }

    @Test
    public void testInsertSortWithGap() {
        int[] data = createRandomArray(8);
        printArray(data);
        insertSort(data,1,data.length - 1, 2);
        printArray(data);
    }

}
