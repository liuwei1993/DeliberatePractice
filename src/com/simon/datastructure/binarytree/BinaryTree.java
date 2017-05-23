package com.simon.datastructure.binarytree;

import com.simon.datastructure.queue.Queue;
import com.simon.datastructure.stack.Stack;

/**
 * binary tree
 * Created by simon on 17-5-22.
 */
public class BinaryTree {

    public TreeNode rootNode;

    public static BinaryTree createTreeByPreOrderDev(Queue dataQueue) {
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.rootNode = createTreeByPreOrder(dataQueue);
        return binaryTree;
    }

    private static TreeNode createTreeByPreOrder(Queue dataQueue) {
        String data = dataQueue.exitQueue();
        if(data != null && !data.equals("#")) {
            TreeNode rootNode = new TreeNode();
            rootNode.data = data;
            rootNode.leftChild = createTreeByPreOrder(dataQueue);
            rootNode.rightChild = createTreeByPreOrder(dataQueue);
            return rootNode;
        } else {
            return null;
        }
    }

    public String preOrderErgodic() {
        StringBuilder sb = new StringBuilder();
        preOrderErgodic(rootNode, sb);
        return sb.toString();
    }

    private void preOrderErgodic(TreeNode rootNode,StringBuilder sb) {
        if(rootNode != null) {
            sb.append(rootNode.data);
            preOrderErgodic(rootNode.leftChild, sb);
            preOrderErgodic(rootNode.rightChild, sb);
        }
    }

    public String midOrderErgodic() {
        StringBuilder sb = new StringBuilder();
        midOrderErgodic(rootNode, sb);
        return sb.toString();
    }

    private void midOrderErgodic(TreeNode rootNode,StringBuilder sb) {
        if(rootNode != null) {
            midOrderErgodic(rootNode.leftChild, sb);
            sb.append(rootNode.data);
            midOrderErgodic(rootNode.rightChild, sb);
        }
    }

    public String postOrderErgodic() {
        StringBuilder sb = new StringBuilder();
        postOrderErgodic(rootNode, sb);
        return sb.toString();
    }

    private void postOrderErgodic(TreeNode rootNode, StringBuilder sb) {
        if(rootNode != null) {
            postOrderErgodic(rootNode.leftChild, sb);
            postOrderErgodic(rootNode.rightChild, sb);
            sb.append(rootNode.data);
        }
    }

    public String preOrderUnergodic() {
        StringBuilder sb = new StringBuilder();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pointer = rootNode;
        while (pointer != null || !stack.isEmpty()) {
            while (pointer != null) {
                sb.append(pointer.data);
                stack.push(pointer);
                pointer = pointer.leftChild;
            }
            pointer = stack.pop();
            pointer = pointer.rightChild;
        }
        return sb.toString();
    }

    public String midOrderUnergodic() {
        StringBuilder sb = new StringBuilder();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pointer = rootNode;
        while (pointer != null || !stack.isEmpty()) {
            while (pointer != null) {
                stack.push(pointer);
                pointer = pointer.leftChild;
            }
            pointer = stack.pop();
            sb.append(pointer.data);
            pointer = pointer.rightChild;
        }
        return sb.toString();
    }

    public String postOrderUnergodic() {
        StringBuilder sb = new StringBuilder();
        Stack<TreeNodeWrap> stack = new Stack<>();

        TreeNode pointer = rootNode;

        while (pointer != null || !stack.isEmpty()) {
            while (pointer != null) {
                stack.push(new TreeNodeWrap(pointer));
                pointer = pointer.leftChild;
            }
            if(!stack.peek().accessed) {
                TreeNodeWrap topNode = stack.peek();
                topNode.accessed = true;
                pointer = topNode.treeNode.rightChild;
            } else {
                while (stack.peek() != null && stack.peek().accessed) {
                    sb.append(stack.pop().treeNode.data);
                }
            }
        }
        return sb.toString();
    }

    static class TreeNodeWrap {
        TreeNodeWrap(TreeNode treeNode) {
            this.treeNode = treeNode;
        }

        TreeNode treeNode;
        boolean accessed = false;
    }

}
