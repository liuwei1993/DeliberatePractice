package com.simon.datastructure.binarytree;

import com.simon.algorithm.sort.SortUtils;
import org.junit.Test;

import java.util.Arrays;

/**
 * BinarySearchTreeTest
 * Created by simon on 17-6-22.
 */
public class BinarySearchTreeTest {

    @Test
    public void testInsert() {
        int[] arr = SortUtils.createRandomArray(10);
        System.out.println(Arrays.toString(arr));
        BinarySearchTree BST = new BinarySearchTree();
        for (int i : arr) {
            BST.insert(String.valueOf(i));
        }
        System.out.println(BST.search(arr[6] + ""));
    }


}
