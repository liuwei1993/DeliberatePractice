package com.simon.algorithm.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * quick sort
 * @author simon liu
 * 2017.5.20
 */
public class Sort {

    //选择排序 时间复杂度O(n^2) 稳定
    @Test
    public void testSelectSort() {
        int[] data = {4,9,10,66,13,2,100};
        selectSort(data, 0, data.length - 1);
        System.out.println("after sort : " + Arrays.toString(data));
    }

    //冒泡排序 时间复杂度O(n^2) 稳定
    @Test
    public void testBubbleSort() {
        int[] data = {4,9,10,66,13,2,100};
        bubbleSort(data, 0, data.length - 1);
        System.out.println("after sort : " + Arrays.toString(data));
    }

    //插入排序 时间复杂度O(n^2) 稳定
    @Test
    public void testInsertSort() {
        int[] data = {4,9,10,66,13,2,100};
        insertSort(data);
        System.out.println("after sort : " + Arrays.toString(data));
    }

    //带有gap的插入排序，供希尔排序使用
    @Test
    public void testInsertSortWithGap() {
        int[] data = {4,9,19,66,13,2,100};
        insertSort(data,0,data.length - 1, 2);
        System.out.println("after testInsertSortWithGap : " + Arrays.toString(data));
    }

    @Test
    public void testShellSort() {
        int[] data = {4,9,19,66,13,2,100};
        shellSort(data);
        System.out.println("after testInsertSortWithGap : " + Arrays.toString(data));
    }

    //时间复杂度O(Nlog2N) 不稳定
    @Test
    public void testQuickSort() {
        int[] data = { 1,4,3,2 };
        System.out.println("before sort \n" + Arrays.toString(data));
        quickSort(data, 0, data.length - 1);
        System.out.println("after sort \n" + Arrays.toString(data));
    }

    static void swap(int[] data, int i, int j) {
		int temp = data[i];
		data[i] = data[j];
		data[j] = temp;
	}

    static void swap(char[] data, int i, int j) {
        char temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }
    /*选择排序*/
    void selectSort(int[] data, int start, int end) {
        assert start <= end;
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

    void insertSort(int[] data) {
        insertSort(data,0, data.length - 1);
    }

    void insertSort(int data[], int start, int end) {
        insertSort(data, start, end, 1);
    }

    void insertSort(int[] data, int start, int end, int gap) {
        for (int i = start + gap; i <= end; i+=gap) {
            int tmp = data[i];
            for (int j = 0; j < i; j+=gap) {
                if(data[j] > tmp) {
                    for (int k = i; k > j; k-=gap) {
                        data[k] = data[k-gap];
                    }
                    data[j] = tmp;
                    break;
                }
            }
        }
    }

    void bubbleSort(int[] data, int start, int end) {
        assert start <= end;
        if(start != end) {
            for (int i = start; i < end; i++) {
                if(data[i] > data[i+1]) {
                    swap(data, i, i+1);
                }
            }
            bubbleSort(data, start, end - 1);
        }
    }

    void shellSort(int[] data) {

        for (int gap = data.length / 2; gap > 0 ; gap /= 2) {
            int gapArrayLength = data.length / gap;
            for (int i = 0; i < gap; i++) {
                //插入排序
                insertSort(data, i, i + gapArrayLength - 1, gap);
            }
        }
    }

    int[] mergeArray(int[] data1, int start1, int end1, int[] data2, int start2, int end2) {
        assert start1 <= end1;
        assert start2 <= end2;
        if(data2 == null) return data1;
        int length1 = end1 - start1 + 1;
        int length2 = end2 - start2 + 1;
        int resultLength = length1 + length2;
        int[] result = new int[resultLength];
        int p1 = start1;
        int p2 = start2;
        int p;
        int[] unfinishedArray = null;
        int unfinishedArrayEnd = 0;
        int unfinishedPointer = 0;
        for (p = 0; p < resultLength; p++) {
            if(data1[p1] < data2[p2]) {
                result[p] = data1[p1];
                if(p1 == end1) {
                    unfinishedArray = data2;
                    unfinishedArrayEnd = end2;
                    unfinishedPointer = p2;
                    break;
                }
                p1++;
            } else {
                result[p] = data2[p2];
                if(p2 == end2) {
                    unfinishedArray = data1;
                    unfinishedArrayEnd = end1;
                    unfinishedPointer = p1;
                    break;
                }
                p2++;
            }
        }
        if(unfinishedArray != null) {
            System.arraycopy(unfinishedArray, unfinishedPointer, result, p+1, unfinishedArrayEnd - unfinishedPointer + 1);
        }
        return result;
    }

    @Test
    public void testMergeArray(){
        int[] data1 = {1, 3, 9, 10, 12, 12, 12};
        int[] data2 = {2, 4, 5, 11, 11, 11};
        int[] result = mergeArray(data1,0,data1.length - 1, data2, 0, data2.length -1);
        System.out.println(Arrays.toString(result));
    }

    //归并排序真特么复杂
    /*void mergeSort(int[] data) {
        int[][] temp;
        int numOfArray = data.length;
        for (int oldArrayLength = 1; oldArrayLength < data.length ; oldArrayLength *= 2) {
            int newArrayLength = oldArrayLength * 2;
            if(numOfArray % 2 != 0) numOfArray ++;
            numOfArray /= 2;
            temp = new int[numOfArray][newArrayLength];
            for (int i = 0; i < numOfArray; i++) {
                temp[i] = mergeArray(data,)
            }
        }
    }*/

    public static void quickSort(char[] data) {
        quickSort(data,0,data.length - 1);
    }

    public static void quickSort(int[] data, int start, int end) {
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

    public static void quickSort(char[] data, int start, int end) {
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