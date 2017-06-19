package com.simon.algorithm.sort.impl;

import com.simon.algorithm.sort.ISort;
import org.junit.Test;

import static com.simon.algorithm.sort.SortUtils.createAlmostOrderArray;
import static com.simon.algorithm.sort.SortUtils.createRandomArray;

/**
 * BaseSort with test case
 * Created by simon on 17-6-19.
 */
public abstract class BaseSort implements ISort {

    @Test
    public void testSort() {
        testRandomArraySort();
        testAlmostOrderArraySort();
    }

    public void testRandomArraySort() {
        long mainTime = 0L;
        int count = 3;
        int arrayLength = 100000;
        boolean isSuccess = true;
        for (int i = 0; i < count; i++) {
            int[] array = createRandomArray(arrayLength);
//            printArray(array);
            long time = System.currentTimeMillis();
            sort(array);
//            printArray(array);
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
        int count = 3;
        int arrayLength = 100000;
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

}
