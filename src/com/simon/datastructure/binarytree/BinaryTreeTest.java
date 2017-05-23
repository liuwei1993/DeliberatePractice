package com.simon.datastructure.binarytree;

import com.simon.datastructure.queue.LinkedQueue;
import com.simon.datastructure.queue.Queue;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * binary tree test
 * Created by simon on 17-5-22.
 */
public class BinaryTreeTest {

    @Test
    public void testCreateTree() {
        Queue dataQueue = LinkedQueue.createQueue(new String[]{"a","b","#","d","#","#","c","#","e","#","#"});
        BinaryTree binaryTree = BinaryTree.createTreeByPreOrderDev(dataQueue);
        TreeNode rootNode = binaryTree.rootNode;
        assertEquals("a", rootNode.data);
        assertEquals("e", rootNode.rightChild.rightChild.data);
    }

    @Test
    public void testPreOrder() {
        Queue dataQueue = LinkedQueue.createQueue(new String[]{"a","b","#","d","#","#","c","#","e","#","#"});
        BinaryTree binaryTree = BinaryTree.createTreeByPreOrderDev(dataQueue);
        String result = binaryTree.preOrderErgodic();
        assertEquals("abdce", result);
    }

    @Test
    public void testMidOrder() {
        Queue dataQueue = LinkedQueue.createQueue(new String[]{"a","b","#","d","#","#","c","#","e","#","#"});
        BinaryTree binaryTree = BinaryTree.createTreeByPreOrderDev(dataQueue);
        String result = binaryTree.midOrderErgodic();
        assertEquals("bdace", result);
    }

    @Test
    public void testPostOrder() {
        Queue dataQueue = LinkedQueue.createQueue(new String[]{"a","b","#","d","#","#","c","#","e","#","#"});
        BinaryTree binaryTree = BinaryTree.createTreeByPreOrderDev(dataQueue);
        String result = binaryTree.postOrderErgodic();
        assertEquals("dbeca", result);
    }

    @Test
    public void testPreOrderUnergodic() {
        Queue dataQueue = LinkedQueue.createQueue(new String[]{"a","b","#","d","#","#","c","#","e","#","#"});
        BinaryTree binaryTree = BinaryTree.createTreeByPreOrderDev(dataQueue);
        String result = binaryTree.preOrderUnergodic();
        assertEquals("abdce", result);
    }

    @Test
    public void testMidOrderUnergodic() {
        Queue dataQueue = LinkedQueue.createQueue(new String[]{"a","b","#","d","#","#","c","#","e","#","#"});
        BinaryTree binaryTree = BinaryTree.createTreeByPreOrderDev(dataQueue);
        String result = binaryTree.midOrderUnergodic();
        assertEquals("bdace", result);
    }

    @Test
    public void testPostOrderUnergodic() {
        Queue dataQueue = LinkedQueue.createQueue(new String[]{"a","b","#","d","#","#","c","#","e","#","#"});
        BinaryTree binaryTree = BinaryTree.createTreeByPreOrderDev(dataQueue);
        String result = binaryTree.postOrderUnergodic();
        assertEquals("dbeca", result);
    }



}
