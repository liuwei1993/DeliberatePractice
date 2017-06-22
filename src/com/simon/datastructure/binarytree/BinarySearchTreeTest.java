package com.simon.datastructure.binarytree;

import com.simon.algorithm.ArrayUtils;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * BinarySearchTreeTest
 * Created by simon on 17-6-22.
 */
public class BinarySearchTreeTest {

    @Test
    public void testInsert() {
        int[] arr = ArrayUtils.createRandomArray(100);
        BinarySearchTree BST = new BinarySearchTree();
        for (int i : arr) {
            BST.insert(String.valueOf(i));
        }
        for (int s : arr) {
            assertEquals(String.valueOf(s),BST.search(String.valueOf(s)));
        }
    }

}
