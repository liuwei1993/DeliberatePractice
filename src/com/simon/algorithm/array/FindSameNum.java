package com.simon.algorithm.array;


import static com.simon.algorithm.ArrayUtils.makeRandomNum;
import static com.simon.algorithm.ArrayUtils.swap;

/**
 * 一个数组中有一组重复数字且数量大于数组长度1/2，求该重复数字是多少以及其个数
 * Created by simon on 17-12-17.
 */
public class FindSameNum {

    //最多的数字是8 共12个
    static final int[] data = {1,8,2,8,9,8,4,8,5,8,8,6,8,4,8,3,8,5,8,8,7,8};


    //在三路快速排序基础上改
    private static int[] quickSort3Ways(int[] data, int start, int end) {
        int swapIdx = makeRandomNum(start + 1, end);
        swap(data, start, swapIdx);

        int target = data[start];
        int lt = start;//data[start+1...lt] < target
        int gt = end + 1;//data[gt...end] > target
        int i = start + 1;//data[lt + 1...i) == v
        while (i < gt) {
            if(data[i] > target) {
                swap(data, i, --gt);
            } else if(data[i] < target) {
                swap(data, i++, ++lt);
            } else {
                i++;
            }
        }
        swap(data, start, lt);
        return new int[]{lt, gt - 1};
    }


    public static void findSameNumber(int[] data, int start, int end) {
        int p = -1;
        int q = -1;
        int[] ints;
        while (true) {
            if (p == -1) {
                ints = quickSort3Ways(data, start, end);
                if(ints[0] < (start + end) / 2) {
                    p = ints[1];
                    q = end;
                } else {
                    p = start;
                    q = ints[0];
                }
            } else if(q - p < (end - start) / 2) {
                System.out.println("cannot find it");
                return;
            }else {
                ints = quickSort3Ways(data, p, q);
                if(ints[0] < (start + end) / 2) {
                    p = ints[1];
                } else {
                    q = ints[0];
                }
            }
            if(ints[1] - ints[0] > (end - start) / 2) {
                System.out.println("find it is " + data[ints[0]] + " and they are " + (ints[1] - ints[0] + 1));
                return;
            }
        }
    }

    public static void main(String[] args) {
        findSameNumber(data,0, data.length - 1);
    }

}
