package com.simon.string.exercises;

import com.simon.algorithm.array.BinarySearch;
import com.simon.algorithm.sort.Sort;
import com.simon.datastructure.stack.Stack;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * 字符串类习题
 * Created by simon on 17-5-27.
 */
public class Exercises {

    //习题1.第一个只出现一次的字符
    //在一个字符串中找到第一个只出现一次的字符。如输入abaccdeff，则输出b。

    //解法1 假设字符串是26个英文字母组成，不区分大小写 时间复杂度 O(n)

    @Test
    public void testPrintFirstOnceLetter() {
        System.out.println(printFirstOnceLetter("abcbddca".toCharArray()));
    }

    char printFirstOnceLetter(char[] chars) {
        int charExistedHash = 0;//根据字母的出现做的hash
        int charMultipleExistedHash = 0;//对字母多次出现做的hash
        for (char c : chars) {
            int hash = 1 << c - 'a';
            if ((charExistedHash & hash) != 0) {
                charMultipleExistedHash |= hash;
            }
            charExistedHash |= hash;
        }
        int charOnceExistedHash = charExistedHash & (~charMultipleExistedHash);
        for (char c : chars) {
            int hash = 1 << c - 'a';
            if ((charOnceExistedHash & hash) != 0) {
                return c;
            }
        }
        return ' ';//未找到只出现一次的字母，返回空格
    }

    //解法2 假设是任意字符 时间复杂度 O(nlog2n)

    @Test
    public void testPrintFirstOnceChar() {
        assertEquals('d', printFirstOnceChar("aagbbfccdefg".toCharArray()));
    }

    char printFirstOnceChar(char[] chars) {
        int length = chars.length;
        char[] copyOfChars = Arrays.copyOf(chars, length);
        Sort.quickSort(copyOfChars);
        char[] onceChars = new char[length];
        int onceCharsCount = 0;
        for (int i = 0; i < length; i++) {
            if ((i == 0 || copyOfChars[i - 1] != copyOfChars[i]) && (i == length - 1 || copyOfChars[i + 1] != copyOfChars[i])) {
                onceChars[onceCharsCount] = copyOfChars[i];
                onceCharsCount++;
            }
        }
        for (char c : chars) {
            if (BinarySearch.binarySearch(onceChars, 0, onceCharsCount, c) != -1) {
                return c;
            }
        }
        return ' ';
    }




    //习题2 对称子字符串的最大长度
    //输入一个字符串，输出该字符串中对称的子字符串的最大长度。比如输入字符串“google”，
    // 由于该字符串里最长的对称子字符串是“goog”，因此输出4。
    @Test
    public void testFindLongestPalindrome() {
        assertEquals(4,findLongestPalindrome("google"));
    }

    int findLongestPalindrome(String srcStr) {
        int id = 0;
        int mx = 0;
        int maxIndex = 0;
        StringBuilder sb = new StringBuilder("#");
        for (int i = 0; i < srcStr.length(); i++) {
            sb.append(srcStr.charAt(i)).append("#");
        }
        String targetString = sb.toString();
        int[] p = new int[targetString.length()];
        for (int i = 0; i < targetString.length(); i++) {
            p[i] = mx > i ? Math.min(p[2 * id - i], mx - i) : 1;
            while(i + p[i] < targetString.length() && i - p[i] > 0 && targetString.charAt(i + p[i]) == targetString.charAt(i - p[i])) {
                p[i]++;
            }
            if(p[i] + i > mx) {
                mx = p[i] + i;
                id = i;
            }
            if(p[maxIndex] < p[i]) {
                maxIndex = i;
            }
        }
        return p[maxIndex];
    }


    //3、编程判断俩个链表是否相交
    //给出俩个单向链表的头指针，比如h1，h2，判断这俩个链表是否相交。为了简化问题，我们假设俩个链表均不带环。
    //解法1 假设不带环
    @Test
    public void testLinkedListIntersect() {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        Node node8 = new Node(8);
        Node node9 = new Node(9);
        Node node10 = new Node(10);
        Node node11 = new Node(11);

        node1.next = node2;
        node2.next = node5;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;
        node7.next = node8;
        node8.next = node9;
        node9.next = node10;
        node10.next = node11;

        assertEquals(true, isLinkedListIntersect(node1,node3));

        assertEquals(false, isLinkedListWithCircle(node1));
        node11.next = node6;
        assertEquals(true, isLinkedListWithCircle(node1));

        assertEquals(node5, isLinkedListIntersectWithCircle(node1,node3));

    }

    //假设不带环
    boolean isLinkedListIntersect(Node head1, Node head2) {
        Node pointer1 = head1;
        while (pointer1 != null && pointer1.next != null) {
            pointer1 = pointer1.next;
        }

        Node pointer2 = head2;
        while (pointer2 != null && pointer2.next != null) {
            pointer2 = pointer2.next;
        }
        System.out.println(String.valueOf(pointer1));
        System.out.println(String.valueOf(pointer2));
        return pointer1 == pointer2;
    }

    //判断是否带环
    boolean isLinkedListWithCircle(Node head) {
        if(head == null) return false;
        Node fast = head;
        Node slow = head;
        while (fast != null && slow != null) {
            fast = fast.next;
            if(fast != null) {
                fast = fast.next;
            }
            slow = slow.next;
            if(fast == slow) return true;
        }
        return false;
    }

    //如果带环
     Node isLinkedListIntersectWithCircle(Node head1, Node head2) {
        Node endNode = null;
        Node fast = head1;
        Node slow = head1;
        while (fast != null && slow != null) {
            fast = fast.next;
            if(fast != null) {
                fast = fast.next;
            }
            slow = slow.next;
            if(fast == slow) {
                endNode = fast;
                break;
            }
        }
        if(endNode == null) throw new IllegalStateException("head1 链表不带环");
        Node pointer = head2;
        while (pointer != null) {
            if(pointer == endNode) {
                return getFirstNodeOnIntersectWithCircle(head1, head2, endNode);
            }
            pointer = pointer.next;
        }
        return null;
    }

    //获取第一个相交的节点
    Node getFirstNodeOnIntersectWithCircle(Node head1, Node head2, Node endNode) {
        Node pointer1 = head1;
        Stack<Node> stack1 = new Stack<>();
        while (pointer1 != null && pointer1.next != null && pointer1 != endNode) {
            stack1.push(pointer1);
            pointer1 = pointer1.next;
        }
        Node pointer2 = head2;
        Stack<Node> stack2 = new Stack<>();
        while (pointer2 != null && pointer2.next != null && pointer2 != endNode) {
            stack2.push(pointer2);
            pointer2 = pointer2.next;
        }
        Node result = endNode;
        while(true) {
            Node pop1 = stack1.pop();
            Node pop2 = stack2.pop();
            if(pop1 == pop2) {
                result = pop1;
            } else {
                break;
            }
        }
        return result;
    }


    static class Node {

        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "{data=" + data + '}';
        }
    }



}
