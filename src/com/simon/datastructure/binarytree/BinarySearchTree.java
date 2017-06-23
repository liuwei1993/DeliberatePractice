package com.simon.datastructure.binarytree;

import com.sun.istack.internal.NotNull;

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
    }

    public String search(String key) {
        TreeNode node = search(root, key);
        return node == null ? null : node.data;
    }

    private TreeNode search(TreeNode root, String key) {
        if (root == null) return null;
        int compare = key.compareTo(root.data);
        if (compare == 0) {
            return root;
        } else if (compare < 0) {
            //搜索左子树
            return search(root.leftChild, key);
        } else {
            //搜索右子树
            return search(root.rightChild, key);
        }
    }


    private TreeNode insert(TreeNode rootNode, String s) {
        if (rootNode == null) {
            count++;
            return new TreeNode(s);
        }
        int compare = s.compareTo(rootNode.data);
        if (compare < 0) {
            //s小于根点数据则将数据插入左子树：
            rootNode.leftChild = insert(rootNode.leftChild, s);
            rootNode.count++;//rootNode节点下增加了一个节点，所以它的count加一
        } else if (compare > 0) {
            //s大于根点数据则将数据插入右子树：
            rootNode.rightChild = insert(rootNode.rightChild, s);
            rootNode.count++;//rootNode节点下增加了一个节点，所以它的count加一
        } else {
            //根点数据等于s则将数据进行覆盖：
            rootNode.data = s;
        }
        return rootNode;
    }


    //删除最大
    public void deleteMax() {
        root = deleteMaxNode(root);
    }

    /**
     * @return 删除节点后该树的根节点
     **/
    private TreeNode deleteMaxNode(TreeNode root) {
        if (root == null) return null;
        if (root.rightChild == null) {
            count--;
            System.out.println("delete Max " + root.data);
            return root.leftChild;
        } else {
            root.rightChild = deleteMaxNode(root.rightChild);
            return root;
        }
    }

    //删除最小

    public void deleteMin() {
        root = deleteMinNode(root);
    }

    private TreeNode deleteMinNode(TreeNode root) {
        if (root == null) return null;
        if (root.leftChild == null) {
            count--;
            System.out.println("delete Min " + root.data);
            return root.rightChild;
        } else {
            root.leftChild = deleteMinNode(root.leftChild);
            return root;
        }
    }

    //删除指定节点 和后继进行交换 然后删除指定节点
    //后继，可以用于替代当前节点的节点。

    public void delete(String key) {
        root = delete(root, key);
    }

    private TreeNode delete(TreeNode root, String key) {
        if (root == null) return null;
        int compareTo = key.compareTo(root.data);
        if (compareTo < 0) {
            root.leftChild = delete(root.leftChild, key);
        } else if (compareTo > 0) {
            root.rightChild = delete(root.rightChild, key);
        } else {
            //此时要删除root节点
            //查找该节点的后继
            TreeNode successor = searchAndRemoveSuccessor(root);
            if (successor == null) {
                //如果后继为空 则右子树为空，将左子树的根覆盖到target节点即可
                count--;
                return root.leftChild;
            } else {
                //将successor中的数据复制到targetNode中
                root.data = successor.data;
            }
        }
        return root;
    }

    private TreeNode searchAndRemoveSuccessor(@NotNull TreeNode target) {
        if (target.rightChild == null) {
            return null;
        } else {
            TreeNode rightChild = target.rightChild;
            TreeNode node = searchMinNode(rightChild);
            deleteMinNode(rightChild);
            return node;
        }
    }

    private TreeNode searchMinNode(@NotNull TreeNode root) {
        if (root.leftChild == null) {
            return root;
        } else {
            return searchMinNode(root.leftChild);
        }
    }

    public int rank(String key) {
        return rank(root, key, 0,true);
    }

    //rank
    // 需要的参数：key值
    // 已知比key大的节点的数量,如果是根节点则传0, (上层传入的该参数加上当前节点右子树的count + 1) 或者 已知比key大的节点的数量,如果是根节点则传0, (上层传入的该参数加上当前节点左子树的count + 1)
    // 传入的knownCount是比下个节点大的还是小的,小为true,大为false,当knownCount为0时，该参数无意义
    // 如果当前节点等于key 传入的count即为比key大或者小的数量，据此便能推算出key所在的排序位置然后返回
    private int rank(TreeNode root, String key, int knownCount, boolean less) {
        if(root == null) return -1;
        int compare = key.compareTo(root.data);
        if(compare < 0) {
            //访问左子树 并计算已知的比左子树根节点大的个数count
            TreeNode rightChild = root.rightChild;
            int greaterCount = 1 + (rightChild == null ? 0 : rightChild.count);
            if(!less) {
                //上层传入已知更大的数量，则累加
                greaterCount += knownCount;
            }
            return rank(root.leftChild, key, greaterCount, false);
        } else if(compare > 0) {
            //访问右子树 并计算已知的比右子树根节点小的个数count
            TreeNode leftChild = root.leftChild;
            int lessCount = 1 + (leftChild == null ? 0 : leftChild.count);
            if(less) {
                //上层传入已知更小的数量，则累加
                lessCount += knownCount;
            }
            return rank(root.rightChild, key, lessCount, true);
        } else {
            //当前节点数据等于key
            return less ? knownCount : count - knownCount - 1;
        }
    }


    //select

    //平衡二叉树——红黑树

    //平衡二叉树——AVL

    // Treap 二叉搜索树和堆的结合

    // trie 统计词频

}
