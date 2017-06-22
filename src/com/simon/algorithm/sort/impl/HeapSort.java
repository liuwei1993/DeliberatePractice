package com.simon.algorithm.sort.impl;

import com.simon.algorithm.ArrayUtils;
import org.junit.Test;

import static com.simon.algorithm.ArrayUtils.*;

/**
 * Heap Sort impl
 * Created by simon on 17-6-21.
 */
public class HeapSort extends BaseSort {

    @Test
    @Override
    public void testSort() {
        super.testSort();
    }

    @Override
    public void sort(int[] data, int start, int end) {
        heapSort(data, start, end);
    }

    @Override
    public void sort(int[] data) {
        //使用现成的最大堆实现
        /*MaxHeap maxHeap = new MaxHeap(data);
        int length = data.length;
        for (int i = length - 1; i >= 0; i--) {
            data[i] = maxHeap.extractMax();
        }*/
        //使用临时创建的最大堆实现
        sort(data, 0, data.length - 1);
    }

    private void heapSort(int[] data, int start, int end) {
        int length = end - start + 1;
        int[] heapArr = new int[length + 1];
        System.arraycopy(data, start, heapArr, 1, length);
        heapify(heapArr, length);
        for (int i = end; i >= start; i--) {
            data[i] = extractMax(heapArr, length--);
        }
    }

    private void shiftUp(int[] heapArr, int k) {
        while (k > 1 && heapArr[k] > heapArr[k / 2]) {
            swap(heapArr, k, k / 2);
            k /= 2;
        }
    }

    private void shiftDown(int[] heapArr, int count, int k) {
        while (k <= count / 2) {
            int nextK = k * 2;
            if (nextK + 1 <= count && heapArr[nextK + 1] > heapArr[nextK])
                nextK += 1;
            if (heapArr[k] < heapArr[nextK]) {
                swap(heapArr, k, nextK);
                k = nextK;
            } else {
                break;
            }
        }
    }

    public int extractMax(int[] heapArr, int count) {
        int ret = heapArr[1];
        heapArr[1] = heapArr[count];
        heapArr[count] = ret;
        shiftDown(heapArr,count - 1,1);
        return ret;
    }

    private void heapify(int[] heapArr, int count) {
        for (int k = count / 2; k > 0; k--) {
            shiftDown(heapArr, count, k);
        }
    }

    @Test
    public void test() {
        int[] randomArray = ArrayUtils.createRandomArray(8);
        printArray(randomArray);
        sort(randomArray);
        printArray(randomArray);
        System.out.println(checkSort(randomArray));
    }

}
