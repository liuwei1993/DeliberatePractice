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

    @Test
    public void testDeleteMaxMin() {
        BinarySearchTree bst = new BinarySearchTree();
        int[] arr = {9,3,6,1,9,5,7,3};
        for (int i : arr) {
            bst.insert(String.valueOf(i));
        }
        System.out.println("BST中总共有 "+bst.getCount() + " 个数据 ");
        bst.deleteMin();
        bst.deleteMin();
        bst.deleteMax();
        bst.deleteMax();
    }

    @Test
    public void testDelete() {
        BinarySearchTree bst = new BinarySearchTree();
        int[] arr = {9,3,6,1,9,5,7,3};
        for (int i : arr) {
            bst.insert(String.valueOf(i));
        }
        System.out.println("此时BST中总共有 "+bst.getCount() + " 个数据");
        bst.delete("7");
        bst.deleteMax();
        bst.deleteMax();
        bst.deleteMin();
        bst.deleteMin();
        System.out.println("此时BST中总共有 "+bst.getCount() + " 个数据");
        bst.deleteMin();
        System.out.println("此时BST中总共有 "+bst.getCount() + " 个数据");
        //已删除完毕
        bst.deleteMin();
        bst.deleteMax();

        //结果：
        //此时BST中总共有 6 个数据
        //delete Max 9
        //delete Max 6
        //delete Min 1
        //delete Min 3
        //此时BST中总共有 1 个数据
        //delete Min 5
        //此时BST中总共有 0 个数据
    }

    @Test
    public void testRank() {
        BinarySearchTree bst = new BinarySearchTree();
        int[] arr = {9,3,6,1,5,7};
        for (int i : arr) {
            bst.insert(String.valueOf(i));
        }
        System.out.println("此时BST中总共有 "+bst.getCount() + " 个数据");
        int key = 5;
        System.out.println(key + " 在有序序列中的位置" + bst.rank(String.valueOf(key)));
    }


}
