package com.simon.algorithm.sort;

import com.simon.algorithm.sort.impl.*;
import org.junit.Test;

/**
 *
 * Created by simon on 17-6-19.
 */
public class SortTest {

    BaseSort[] sorts = {new BubbleSort(), new SelectSort(), new InsertSort(), new ShellSort(), new MergeSort(), new QuickSort()};

    @Test
    public void testAllSorts() {
        for (BaseSort sort : sorts) {
            sort.testRandomArraySort();
        }
        for (BaseSort sort : sorts) {
            sort.testAlmostOrderArraySort();
        }
    }


}
