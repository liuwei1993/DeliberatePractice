package com.simon.algorithm.sort.impl;

import com.simon.algorithm.sort.ISort;
import org.junit.Test;

import static com.simon.algorithm.ArrayUtils.createAlmostOrderArray;
import static com.simon.algorithm.ArrayUtils.createRandomArray;

/**
 * BaseSort with test case
 * Created by simon on 17-6-19.
 */
public abstract class BaseSort implements ISort {

    @Test
    public void testSort() {
        testRandomArraySort();
        testAlmostOrderArraySort();
        testSmallRangeArraySort();
    }

    private int length = 1000000;

    public void testRandomArraySort() {
        long mainTime = 0L;
        int count = 1;
        int arrayLength = length;
        boolean isSuccess = true;
        for (int i = 0; i < count; i++) {
            int[] array = createRandomArray(arrayLength);
            long time = System.currentTimeMillis();
            sort(array);
            long useTime = System.currentTimeMillis() - time;
            mainTime += useTime;
            for (int j = 1; j < array.length; j++) {
                if(array[j - 1] > array[j]) {
                    isSuccess = false;
                }
            }
        }
        System.out.println(getClass().getSimpleName() + " Random " + arrayLength
                + " length array " + (isSuccess ? " SUCCESS " : " FAILURE ")
                + " Take time average : " + mainTime / count + " ms");
    }

    public void testAlmostOrderArraySort() {
        long mainTime = 0L;
        int count = 1;
        int arrayLength = length;
        boolean isSuccess = true;
        for (int i = 0; i < count; i++) {
            int[] array = createAlmostOrderArray(arrayLength,arrayLength / 500);
            long time = System.currentTimeMillis();
            sort(array);
            long useTime = System.currentTimeMillis() - time;
            mainTime += useTime;
            for (int j = 1; j < array.length; j++) {
                if(array[j - 1] > array[j]) {
                    isSuccess = false;
                }
            }
        }
        System.out.println(getClass().getSimpleName() + " AlmostOrder " + arrayLength
                + " length array " + (isSuccess ? " SUCCESS " : " FAILURE ")
                + " Take time average : " + mainTime / count + " ms");
    }

    public void testSmallRangeArraySort() {
        long mainTime = 0L;
        int count = 1;
        int arrayLength = length;
        int range = 10;
        boolean isSuccess = true;
        for (int i = 0; i < count; i++) {
            int[] array = createRandomArray(arrayLength, range);
            long time = System.currentTimeMillis();
            sort(array,0,array.length - 1);
            long useTime = System.currentTimeMillis() - time;
            mainTime += useTime;
            for (int j = 1; j < array.length; j++) {
                if(array[j - 1] > array[j]) {
                    isSuccess = false;
                }
            }
        }
        System.out.println(getClass().getSimpleName() + " SmallRangeArray " + arrayLength
                + " length array " + " range: " + range
                + (isSuccess ? " SUCCESS " : " FAILURE ")
                + " Take time average : " + mainTime / count + " ms");
    }

}
