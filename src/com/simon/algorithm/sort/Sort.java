package com.simon.algorithm.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * quick sort
 * @author simon liu
 * 2017.5.20
 */
public class Sort {

    //时间复杂度O(n^2) 稳定
    @Test
    public void testSelectSort() {
        int[] data = {4,9,10,66,13,2,100};
        selectSort(data, 0, data.length - 1);
        System.out.println("after sort : " + Arrays.toString(data));
    }

    //时间复杂度O(Nlog2N) 不稳定
    @Test
    public void testQuickSort() {
        int[] data = { 1,4,3,2 };
        System.out.println("before sort \n" + Arrays.toString(data));
        quickSort(data, 0, data.length - 1);
        System.out.println("after sort \n" + Arrays.toString(data));
    }
    
	void swap(int[] data, int i, int j) {
		int temp = data[i];
		data[i] = data[j];
		data[j] = temp;
	}

    void selectSort(int[] data, int start, int end) {
        if(end != start) {
            int maxIndex = start;
            int max = data[start];
            for (int i = start + 1; i <= end; i++) {
                if(data[i] >= max) {
                    maxIndex = i;
                    max = data[i];
                }
            }
            swap(data, maxIndex,end);
            selectSort(data, start, end - 1);
        }
    }

    void quickSort(int[] data, int start, int end) {
		if (start < end) {
			int base = data[start];
			int i = start;
			int j = end + 1;
			while (true) {
				while (i < end && data[++i] <= base);
				while (j > start && data[--j] >= base);
				if (i < j) {
					swap(data, i, j);
				} else {
					break;
				}
			}
			swap(data, start, j);
			quickSort(data, start, j - 1);
			quickSort(data, j + 1, end);
		}
	}

}
