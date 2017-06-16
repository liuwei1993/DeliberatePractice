package com.simon.algorithm.string.rotatestring;

import org.junit.Test;

/**
 * 翻转字符串
 * 题目：1
 * 单词翻转。输入一个英文句子，翻转句子中单词的顺序，但单词内字符的顺序不变，句子中单词以空格符隔开。
 * 为简单起见，标点符号和普通字母一样处理。例如，输入“I am a student.”，则输出“student. a am I”。
 *
 * 题目：2
 * 给定一个字符串，要求把字符串前面的若干个字符移动到字符串的尾部，
 * 如把字符串“abcdef”前面的2个字符'a'和'b'移动到字符串的尾部，使得原字符串变成字符串“cdefab”。
 * 请写一个函数完成此功能，要求对长度为n的字符串操作的时间复杂度为 O(n)，空间复杂度为 O(1)。
 *
 * Created by simon on 17-5-17.
 */
public class RotateString {

    static void reverseString(char[] chars, int from, int to) {
        char tmp;
        while (from < to) {
            tmp = chars[from];
            chars[from] = chars[to];
            chars[to] = tmp;
            from++;
            to--;
        }
    }

    @Test
    public void topicOne() {

        String content = "I am a student.";

        char[] chars = content.toCharArray();

        reverseString(chars,0,chars.length - 1);

        System.out.println(String.valueOf(chars));

        int head = 0;

        for (int i = 0; i < chars.length; i++) {

            if(chars[i] == ' ') {
                reverseString(chars, head, i - 1);
                head = i + 1;
            }

        }

        System.out.println(String.valueOf(chars));

    }

    @Test
    public void topicTwo() {

        String content = "abcdefgh";

        char[] chars = content.toCharArray();

        int m = 3;//假设移动3个字符，可修改

        reverseString(chars, 0, m - 1);
        reverseString(chars, m, chars.length - 1);
        reverseString(chars, 0, chars.length - 1);

        System.out.println(String.valueOf(chars));
        //原理：将一个字符串分成X和Y两个部分，在每部分字符串上定义反转操作，
        // 如X^T，即把X的所有字符反转（如，X="abc"，那么X^T="cba"），那么就得到下面的结论：(X^TY^T)^T=YX
    }

}
