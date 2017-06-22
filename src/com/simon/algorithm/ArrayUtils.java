package com.simon.algorithm;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * ArrayUtils
 * Created by simon on 17-6-19.
 */
public class ArrayUtils {

    public static int[] createRandomArray(int length) {
        return createRandomArray(length, length);
    }

    public static int[] createRandomArray(int length, int max) {
        int[] data = new int[length];
        for (int i = 0; i < length; i++) {
            data[i] = (int) (Math.abs(Math.random()) * (max - 1) + 1);//防止出现0
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


    /**
     * 获取数值num在十六进制形式下第pos位的数值
     * @param num 目标数值
     * @return num在十六进制形式下第pos位的数值
     * */
    public static int intAtHexPos(int num, int pos) {
        int offset = pos * 4;
        return (num & (15 << offset)) >> offset;
    }

    /**
     * 计算num的十六进制形式下的位数
     * @param num 目标数值
     * @return num在十六进制形式下的位数
     * */
    public static int computeHexCount(int num) {
        // TODO int有32bit组成，最高位为符号位，但为了简化问题，这里我们假定num永远为正数，后期再做优化
        //32bit就意味着最高有8个二进制位
        for (int i = 7; i >= 0; i--) {
            if(intAtHexPos(num, i) != 0) {
                return i + 1;
            }
        }
        return 0;
    }

    @Test
    public void testComputeHexCount() {
        for (int i = 0; i < 100; i++) {
            //TODO 当count 为0或者8时会出现问题，后续解决
            int count = makeRandomNum(1,7);
            int max = (1 << count * 4) - 1;
            int min = (1 << (count - 1) * 4);
            int randomNum = makeRandomNum(min, max);
            assertEquals(count, computeHexCount(randomNum));
        }
    }


    public static boolean checkSort(int[] data) {
        boolean isSort = true;
        for (int j = 1; j < data.length; j++) {
            if(data[j - 1] > data[j]) {
                isSort = false;
                System.out.println("data[" + (j-1) + "] : " +  data[j - 1] );
                System.out.println("data[" + j + "] : " +  data[j] );
            }
        }
        return isSort;
    }


}
