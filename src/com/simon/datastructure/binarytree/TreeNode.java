package com.simon.datastructure.binarytree;

/**
 * tree node
 * Created by simon on 17-5-22.
 */
public class TreeNode {

    public TreeNode() {
    }

    public TreeNode(String data) {
        this.data = data;
    }

    public String data = "";

    public TreeNode leftChild;

    public TreeNode rightChild;

    public int count = 1;//表示以该节点为根的二叉树的节点总数，默认为1

}
