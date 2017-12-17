package com.simon.algorithm.array;

import com.simon.algorithm.ArrayUtils;
import com.simon.algorithm.sort.ISort;
import com.simon.algorithm.sort.impl.QuickSort;

import static com.simon.algorithm.ArrayUtils.makeRandomNum;
import static com.simon.algorithm.ArrayUtils.swap;

/**
 * 求乱序数组中Top m个数的集合
 * Created by simon on 17-12-17.
 */
public class FindTopNumbers {

    static final int[] data = ArrayUtils.createRandomArray(20);


    //将前m个数排在前面
    public static void findTopM(int[] data, int start, int end, int m) {
        int targetIndex = -1;
        while (true) {
            if(targetIndex == -1) {
                targetIndex = partition2(data, start, end);
            }else if (targetIndex > m) {
                targetIndex = partition2(data, start, targetIndex);
            } else if (targetIndex < m) {
                targetIndex = partition2(data, targetIndex, end);
            } else {
                return;
            }
        }
    }

    public static void main(String[] args) {
        findTopM(data, 0, data.length - 1, 10);
        ArrayUtils.printArray(data);
        ArrayUtils.printArray(data, 0,9);
        ISort sort = new QuickSort();
        sort.sort(data);
        ArrayUtils.printArray(data);
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
            while (i<= end && data[i] < target) i++;
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



}
