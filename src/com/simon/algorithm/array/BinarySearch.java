package com.simon.algorithm.array;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * 二分查找
 * Created by simon on 17-5-27.
 */
public class BinarySearch {

    @Test
    public void testBinarySearch() {
        assertEquals(-1,binarySearch("abcdefijk".toCharArray(), 0,8,'z'));
    }


    public static int binarySearch(char[] chars, int start, int end, char target) {
//        int index = (start + end) / 2;
        int index = start + (end - start) / 2;
        char charAtIndex = chars[index];
        if(charAtIndex == target){
            return index;
        } else if(start == end){
            return -1;
        } else if(charAtIndex > target) {
            return binarySearch(chars, start, index - 1, target);
        } else {
            return binarySearch(chars, index + 1, end,target);
        }
    }

    //TODO floor


    //TODO ceil


}
