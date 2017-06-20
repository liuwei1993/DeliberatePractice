package com.simon.algorithm.sort.impl;

import org.junit.Test;

/**
 * BucketSort
 * Created by simon on 17-6-19.
 */
public class BucketSort extends BaseSort {

    @Test
    @Override
    public void testSort() {
        super.testSort();
    }

    @Override
    public void sort(int[] data, int min, int max) {
        bucketSort(data, min, max);
    }

    /**
     * default data in [0, data.length - 1]
     * */
    @Override
    public void sort(int[] data) {
        bucketSort(data,0, data.length - 1);
    }

    public static void bucketSort(int[] data, int min, int max) {
        int[] buckets = new int[max - min + 1];
        for (int num : data) {
            buckets[num - min]++;
        }
        int i = 0;
        for (int j = 0; j < buckets.length; j++) {
            int bucket = buckets[j];
            if(bucket != 0) {
                for (int k = 0; k < bucket; k++) {
                    data[i++] = j + min;
                }
            }
        }
    }
}
