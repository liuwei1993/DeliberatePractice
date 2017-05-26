package com.simon.string.palindrome;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * 最长回文串查找
 * Created by simon on 17-5-23.
 */
public class LongestPalindrome {

    @Test
    public void testFindLongestPalindrome() {
        assertEquals("31213",findLongestPalindrome("4777312139856287"));
    }

    String findLongestPalindrome(String srcStr) {

        int id = 0;
        int mx = 0;
        int maxIndex = 0;
        StringBuilder sb = new StringBuilder("#");
        for (int i = 0; i < srcStr.length(); i++) {
            sb.append(srcStr.charAt(i)).append("#");
        }
        String targetString = sb.toString();
        System.out.println(targetString);
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
        System.out.println(Arrays.toString(p));
        String substring = targetString.substring(maxIndex - p[maxIndex] + 1, maxIndex + p[maxIndex]);
        return substring.replace("#","");
    }

}
