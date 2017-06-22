package com.simon.datastructure.heap;

import static java.lang.Math.pow;
import static jdk.nashorn.internal.objects.NativeMath.min;

/**
 *
 * Created by simon on 17-6-22.
 */
public class IndexMaxHeap implements Heap{


    private int[] data;

    private int[] indexes;

    private int[] reverse;

    private static int MAX_COUNT = 1024;

    private int count = 0;

    public IndexMaxHeap() {
        this.data = new int[MAX_COUNT + 1];
        this.indexes = new int[MAX_COUNT + 1];
        this.reverse = new int[MAX_COUNT + 1];

    }

    public int getCount() {
        return count;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public void insertData(int e) {
        int idx = ++count;
        data[idx] = e;
        indexes[count] = idx;
        reverse[idx] = count;
        shiftUp(count);
    }

    public void change(int idx, int e) {
        assert idx >= 0 && idx < count;
        idx++;
        int i = reverse[idx];
        data[i] = e;
        shiftUp(i);
        shiftDown(i);
    }

    public int extractMax() {
        int ret = data[indexes[1]];
        swap(1, count--);
        shiftDown(1);
        return ret;
    }

    private void shiftUp(int k) {
        while (k > 1 && data[indexes[k]] > data[indexes[k / 2]]) {
            swap(k, k / 2);
            k /= 2;
        }
    }

    private void shiftDown(int k) {
        while (k <= count / 2) {
            int nextK = k * 2;
            if (nextK + 1 <= count && data[indexes[nextK + 1]] > data[indexes[nextK]])
                nextK += 1;
            if (data[indexes[k]] < data[indexes[nextK]]) {
                swap(k, nextK);
                k = nextK;
            } else {
                break;
            }
        }
    }

    private void heapify() {
        for (int k = count/2; k > 0; k--) {
            shiftDown(k);
        }
    }



    private void swap(int i1, int i2) {
        int tmp = indexes[i1];
        indexes[i1] = indexes[i2];
        indexes[i2] = tmp;
        reverse[indexes[i1]] = i1;
        reverse[indexes[i2]] = i2;
    }



    public void print() {
        // 我们的testPrint只能打印100个元素以内的堆的树状信息
        if (count >= 100) {
            System.out.println("This print function can only work for less than 100 int");
            return;
        }

        System.out.println("The max heap size is: " + MAX_COUNT);
        System.out.println("Data in the max heap: ");
        for (int i = 1; i <= count; i++) {
            // 我们的testPrint要求堆中的所有整数在[0, 100)的范围内
            assert (data[indexes[i]] >= 0 && data[indexes[i]] < 100);
            System.out.print(data[indexes[i]] + ", ");
        }
        System.out.println();

        int n = count;
        int max_level = 0;
        int number_per_level = 1;
        while (n > 0) {
            max_level += 1;
            n -= number_per_level;
            number_per_level *= 2;
        }

        int max_level_number = (int) pow(2, max_level - 1);
        int cur_tree_max_level_number = max_level_number;
        int index = 1;
        for (int level = 0; level < max_level; level++) {
            StringBuilder line1 = createString(max_level_number * 3 - 1, ' ');

            int cur_level_number = (int) min(count - (int) (pow(2, level)) + 1, (int) pow(2, level));
            boolean isLeft = true;
            for (int index_cur_level = 0; index_cur_level < cur_level_number; index++, index_cur_level++) {
                putNumberInLine(data[indexes[index]], line1, index_cur_level, cur_tree_max_level_number * 3 - 1, isLeft);
                isLeft = !isLeft;
            }
            System.out.println(line1);

            if (level == max_level - 1)
                break;

            StringBuilder line2 = createString(max_level_number * 3 - 1, ' ');
            for (int index_cur_level = 0; index_cur_level < cur_level_number; index_cur_level++)
                putBranchInLine(line2, index_cur_level, cur_tree_max_level_number * 3 - 1);
            System.out.println(line2);

            cur_tree_max_level_number /= 2;
        }
    }

    private void putBranchInLine(StringBuilder line, int index_cur_level, int cur_tree_width) {
        int sub_tree_width = (cur_tree_width - 1) / 2;
        int sub_sub_tree_width = (sub_tree_width - 1) / 2;
        int offset_left = index_cur_level * (cur_tree_width + 1) + sub_sub_tree_width;
        assert (offset_left + 1 < line.length());
        int offset_right = index_cur_level * (cur_tree_width + 1) + sub_tree_width + 1 + sub_sub_tree_width;
        assert (offset_right < line.length());

        line.setCharAt(offset_left + 1, '/');
        line.setCharAt(offset_right, '\\');
    }

    private void putNumberInLine(int num, StringBuilder line, int index_cur_level, int cur_tree_width, boolean isLeft) {
        int sub_tree_width = (cur_tree_width - 1) / 2;
        int offset = index_cur_level * (cur_tree_width + 1) + sub_tree_width;
        assert (offset + 1 < line.length());
        if (num >= 10) {
            line.setCharAt(offset, (char) ('0' + num / 10));
            line.setCharAt(offset + 1, (char) ('0' + num / 10));
        } else {
            if (isLeft)
                line.setCharAt(offset, (char) ('0' + num));
            else
                line.setCharAt(offset + 1, (char) ('0' + num));
        }
    }

    private StringBuilder createString(int n, char c) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(c);
        }
        return sb;
    }


}
