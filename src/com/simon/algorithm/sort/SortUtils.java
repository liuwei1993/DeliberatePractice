package com.simon.algorithm.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * SortUtils
 * Created by simon on 17-6-19.
 */
public class SortUtils {

    public static int[] createRandomArray(int length) {
        return createRandomArray(length, length);
    }

    public static int[] createRandomArray(int length, int max) {
        int[] data = new int[length];
        for (int i = 0; i < length; i++) {
            data[i] = (int) (Math.abs(Math.random()) * max);
        }
        return data;
    }

    public static int makeRandomNum(int left, int right) {
        int length = right - left + 1;
        return (int) (Math.abs(Math.random()) * length) + left;
    }

    public static int[] createAlmostOrderArray(int length, int swapTime) {
        int[] data = new int[length];
        for (int i = 0; i < length; i++) {
            data[i] = i;
        }
        for (int i = 0; i < swapTime; i++) {
            int index1 = (int) (Math.abs(Math.random()) * length);
            int index2 = (int) (Math.abs(Math.random()) * length);
            swap(data, index1, index2);
        }
        return data;
    }

    @Test
    public void testCreateAlmostOrderArray() {
        int[] almostOrderArray = createAlmostOrderArray(10, 2);
        printArray(almostOrderArray);
    }

    public static void printArray(int[] array) {
        if (array != null) {
            if (array.length <= 30) {
                System.out.println(Arrays.toString(array));
            } else {
                System.out.print("[");
                for (int i = 0; i < 30; i++) {
                    System.out.print(array[i] + " ");
                }
                System.out.println("]");
            }
        }
    }

    public static void swap(int[] data, int i, int j) {
        int temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    public static void swap(char[] data, int i, int j) {
        char temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

}
