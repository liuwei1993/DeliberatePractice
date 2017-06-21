package com.simon.algorithm.sort;

import com.simon.algorithm.sort.impl.*;
import org.junit.Test;

/**
 *
 * Created by simon on 17-6-19.
 */
public class SortTest {

    BaseSort[] sorts = {/*new BubbleSort(), new SelectSort(), new InsertSort(), */new ShellSort(), new MergeSort(), new QuickSort(), new BucketSort(), new RadixSort(), new CountingRadixSort(), new HeapSort()};

    @Test
    public void testAllSorts() {
        for (BaseSort sort : sorts) {
            sort.testRandomArraySort();
        }
        System.out.println();
        for (BaseSort sort : sorts) {
            sort.testAlmostOrderArraySort();
        }
        System.out.println();
        for (BaseSort sort : sorts) {
            sort.testSmallRangeArraySort();
        }
    }


}
