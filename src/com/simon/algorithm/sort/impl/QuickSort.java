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


    @Test
    public void testSmallRangeArraySort() {
        long mainTime = 0L;
        int count = 3;
        int arrayLength = 1000000;
        int range = 10;
        boolean isSuccess = true;
        for (int i = 0; i < count; i++) {
            int[] array = createRandomArray(arrayLength, range);
            long time = System.currentTimeMillis();
            quickSort3Ways(array,0,array.length - 1);
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


    @Override
    public void sort(int[] data, int start, int end) {
        quickSort3Ways(data, start, end);
    }

    @Override
    public void sort(int[] data) {
        quickSort3Ways(data, 0, data.length - 1);
    }



    private static void quickSort(int[] data, int start, int end) {
        if(start >= end) return;
        int targetIndex = partition2(data, start, end);
        quickSort(data, start, targetIndex - 1);
        quickSort(data, targetIndex + 1, end);
    }

    //第一种写法，如果对于接近有序的数组，会使得递归树结构不平衡，甚至可能使得时间复杂度变为O(n^2)
    //而对于数量庞大，重复数据较多的数组中，也因为分治的两个区间为n<target 和 n<=target ，也会使得树结构不平衡。
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

    private static int partition2(int[] data, int start, int end) {
        //优化1 取随机位置的数值作为target 用于避免在接近有序的数组中，递归树结构的不稳定
        int swapIdx = makeRandomNum(start + 1, end);
        swap(data, start, swapIdx);
        //优化2
        int target = data[start];
        int i = start + 1;
        int j = end;
        while (true) {
            while (i<=end && data[i] < target) i++;
            while (j>= start + 1 && data[j] > target) j--;
            if(i >= j) {
                break;
            }
            swap(data, i, j);
            i++;
            j--;
        }
        swap(data, start, j);
        return j;
    }

    //三路快速排序
    private static void quickSort3Ways(int[] data, int start, int end) {
        if(end - start <= 15) {
            InsertSort.insertSort(data, start, end);
            return;
        }
        int swapIdx = makeRandomNum(start + 1, end);
        swap(data, start, swapIdx);

        int target = data[start];
        int lt = start;//data[start+1...lt] < target
        int gt = end + 1;//data[gt...end] > target
        int i = start + 1;//data[lt + 1...i) == v

        while (i < gt) {
            if(data[i] > target) {
                swap(data, i, --gt);
            } else if(data[i] < target) {
                swap(data, i++, ++lt);
            } else {
                i++;
            }
        }
        swap(data, start, lt);
        quickSort3Ways(data, start, lt - 1);
        quickSort3Ways(data, gt, end);
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
