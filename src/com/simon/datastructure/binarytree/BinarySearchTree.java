package com.simon.datastructure.binarytree;

/**
 * BinarySearchTree
 * Created by simon on 17-6-22.
 */
public class BinarySearchTree {

    private TreeNode root;

    private int count = 0;

    public int getCount() {
        return count;
    }

    public boolean isEmpty() {
        return root == null;
    }


    public void insert(String s) {
        root = insert(root, s);
        count++;
    }

    public String search(String key) {
        TreeNode node = search(root, key);
        return node == null ? null : node.data;
    }

    private TreeNode search(TreeNode root, String key) {
        if(root == null) return null;
        int compare = key.compareTo(root.data);
        if(compare == 0) {
            return root;
        } else if(compare < 0) {
            //搜索左子树
            return search(root.leftChild, key);
        } else {
            //搜索右子树
            return search(root.rightChild, key);
        }
    }


    private TreeNode insert(TreeNode rootNode, String s) {
        if(rootNode == null) {
            return new TreeNode(s);
        }
        int compare = s.compareTo(rootNode.data);
        if(compare < 0) {
            //s小于根点数据则将数据插入左子树：
            rootNode.leftChild = insert(rootNode.leftChild, s);
        } else if(compare > 0) {
            //s大于根点数据则将数据插入右子树：
            rootNode.rightChild = insert(rootNode.rightChild, s);
        } else {
            //根点数据等于s则将数据进行覆盖：
            rootNode.data = s;
        }
        return rootNode;
    }


}
