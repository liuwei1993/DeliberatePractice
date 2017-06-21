package com.simon.algorithm.sort.impl;

import org.junit.Test;

import static com.simon.algorithm.sort.SortUtils.createRandomArray;
import static com.simon.algorithm.sort.SortUtils.printArray;

/**
 * InsertSort
 * Created by simon on 17-6-19.
 */
public class InsertSort extends BaseSort{


    @Test
    public void testSort() {
        super.testSort();
    }

    @Override
    public void sort(int[] data, int start, int end) {
        insertSort(data, start, end);
    }

    @Override
    public void sort(int[] data) {
        insertSort(data);
    }

    public static void insertSort(int[] data) {
        // 寻找元素data[i]合适的插入位置

        // 写法1
        // for( int j = i ; j > 0 ; j -- )
        //     if( data[j] < arr[j-1] )
        //         swap( data, j , j-1 );
        //     else
        //         break;

        // 将写法1变得更简洁
        // for( int j = i; j > 0 && data[j] < data[j-1]; j--)
        //     swap(data, j, j-1);

        // 写法2 对写法1和写法2进行性能优化
        int length = data.length;
        for (int i = 1; i < length; i++) {
            int tmp = data[i];
            int j = i;
            for (; j > 0 && data[j - 1] > tmp ; j--)
                data[j] = data[j-1];
            data[j] = tmp;
        }

        // 写法3 使用System.arraycopy方法提升性能 TODO 经过测试该方法并没能提升性能，后续查找原因
        /*int length = data.length;
        for (int i = 1; i < length; i++) {
            int tmp = data[i];
            int j = i;
            while (j > 0 && data[j-1] > tmp) j--;
            System.arraycopy(data, j, data, j + 1, i - j);
            data[j] = tmp;
        }*/
    }

    public static void insertSort(int data[], int start, int end) {
        for (int i = start + 1; i <= end; i++) {
            int tmp = data[i];
            int j = i;
            for (; j > start && data[j - 1] > tmp ; j--)
                data[j] = data[j-1];
            data[j] = tmp;
        }
    }

    void insertSort(int[] data, int start, int end, int gap) {
        for (int i = start + gap; i <= end; i+=gap) {
            int tmp = data[i];
            int j = i;
            for (; j >= start + gap && data[j - gap] > tmp; j-=gap) {
                data[j] = data[j-gap];
            }
            data[j] = tmp;
        }
    }

    @Test
    public void testInsertSortWithGap() {
        int[] data = createRandomArray(10);
//        printArray(data);
        long time = System.currentTimeMillis();
        insertSort(data,1,data.length - 1, 2);
        long takeTime = System.currentTimeMillis() - time;
        System.out.println(takeTime);
//        printArray(data);
    }

    @Test
    public void testArraySort() {
        int[] data = createRandomArray(7);
        printArray(data);
        sort(data);
        printArray(data);
    }



    @Test
    public void testArrayCopy() {
        int length = 10;
        int[] data = createRandomArray(length);
        int[] dataCopy = new int[length];
        long time = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            System.arraycopy(data,0,dataCopy,0,length);
        }
        long takeTime = System.currentTimeMillis() - time;
        System.out.println("system.arraycopy takses " + takeTime);
        dataCopy  = new int[length];
        time = System.currentTimeMillis();
        for (int j = 0; j < 10000000; j++) {
            for (int i = 0; i < length; i++) {
                dataCopy[i] = data[i];
            }
        }
        takeTime = System.currentTimeMillis() - time;
        System.out.println("for takses " + takeTime);

    }

}
