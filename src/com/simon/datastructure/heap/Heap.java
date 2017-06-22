package com.simon.datastructure.heap;

/**
 * Heap interface
 * Created by simon on 17-6-22.
 */
public interface Heap {

    int getCount();

    boolean isEmpty();

    void insertData(int e);

    int extractMax();

}
