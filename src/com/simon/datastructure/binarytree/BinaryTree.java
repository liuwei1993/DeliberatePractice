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
        if (data != null && !data.equals("#")) {
            TreeNode rootNode = new TreeNode();
            rootNode.data = data;
            rootNode.leftChild = createTreeByPreOrder(dataQueue);
            rootNode.rightChild = createTreeByPreOrder(dataQueue);
            return rootNode;
        } else {
            return null;
        }
    }

    public static BinaryTree createTreeByPreInOrder(String pre, String in) {
        StringWrap preWrap = new StringWrap(pre, 0, pre.length());
        StringWrap inWrap = new StringWrap(in, 0, in.length());
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.rootNode = createTreeByPreInOrder(preWrap, inWrap);
        return binaryTree;
    }

    private static TreeNode createTreeByPreInOrder(StringWrap pre, StringWrap in) {
        //1 step get first element of pre, and split tree
        if (pre.length == 0) return null;
        String rootData = pre.firstChar();
        int indexOfRootInOrder = in.indexOf(rootData);
        if(indexOfRootInOrder == -1) {
            throw new IllegalArgumentException("please check pre and in content");
        }
        TreeNode rootNode = new TreeNode();
        rootNode.data = rootData;

        //2 step create left tree
        StringWrap subPreLeft = new StringWrap(pre, 1, indexOfRootInOrder);
        StringWrap subInLeft = new StringWrap(in, 0, indexOfRootInOrder);
        rootNode.leftChild = createTreeByPreInOrder(subPreLeft, subInLeft);

        //3 step create right tree
        StringWrap subPreRight = new StringWrap(pre, indexOfRootInOrder + 1, pre.length - indexOfRootInOrder - 1);
        StringWrap subInRight = new StringWrap(in, indexOfRootInOrder + 1, in.length - indexOfRootInOrder - 1);
        rootNode.rightChild = createTreeByPreInOrder(subPreRight, subInRight);
        return rootNode;
    }

    public static BinaryTree createTreeByInPostOrder(String in, String post) {
        StringWrap preWrap = new StringWrap(in, 0, in.length());
        StringWrap inWrap = new StringWrap(post, 0, post.length());
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.rootNode = createTreeByInPostOrder(preWrap, inWrap);
        return binaryTree;
    }

    private static TreeNode createTreeByInPostOrder(StringWrap in, StringWrap post) {
        //1 step
        if(in.length == 0) return null;
        String rootData = post.endChar();
        int indexOfInOrder = in.indexOf(rootData);
        if(indexOfInOrder == -1) {
            throw new IllegalArgumentException("please check in and post");
        }
        TreeNode rootNode = new TreeNode();
        rootNode.data = rootData;

        //2 step
        StringWrap subInLeft = new StringWrap(in, 0, indexOfInOrder);
        StringWrap subPostLeft = new StringWrap(post, 0, indexOfInOrder);
        rootNode.leftChild = createTreeByInPostOrder(subInLeft, subPostLeft);

        //3 step
        StringWrap subInRight = new StringWrap(in, indexOfInOrder + 1, in.length - indexOfInOrder - 1);
        StringWrap subPostRight = new StringWrap(post, indexOfInOrder, in.length - indexOfInOrder - 1);
        rootNode.rightChild = createTreeByInPostOrder(subInRight, subPostRight);

        return rootNode;
    }

    static class StringWrap {
        final String string;
        int start;
        int length;

        public StringWrap(String string, int start, int length) {
            this.string = string;
            this.start = start;
            this.length = length;
            checkBounds(string, start, length);
        }

        private void checkBounds(String string, int start, int length) {
            if(length == 0) return;
            int strLength = string.length();
            if (start > strLength - 1 || start + length > strLength) {
                throw new IllegalArgumentException("out of bounds start " + start + " length " + length + " str length " + strLength);
            }
        }

        public StringWrap(StringWrap stringWrap, int start, int length) {
            this.string = stringWrap.string;
            this.start = stringWrap.start + start;
            this.length = length;
            checkBounds(string, this.start, length);
        }

        String charAt(int index) {
            return String.valueOf(string.charAt(index + start));
        }

        int indexOf(String s) {
            if (s != null) {
                for (int i = 0; i < length; i++) {
                    String charAt = charAt(i);
                    if (s.equals(charAt)) {
                        return i;
                    }
                }
            }
            return -1;
        }

        String firstChar() {
            return charAt(0);
        }

        String endChar() {
            return charAt(length - 1);
        }

    }

    public String preOrderErgodic() {
        StringBuilder sb = new StringBuilder();
        preOrderErgodic(rootNode, sb);
        return sb.toString();
    }

    private void preOrderErgodic(TreeNode rootNode, StringBuilder sb) {
        if (rootNode != null) {
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

    private void midOrderErgodic(TreeNode rootNode, StringBuilder sb) {
        if (rootNode != null) {
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
        if (rootNode != null) {
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
            if (!stack.peek().accessed) {
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
