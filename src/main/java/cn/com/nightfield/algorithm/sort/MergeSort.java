package cn.com.nightfield.algorithm.sort;

import java.util.*;

/**
 * @author: zhochi
 * @create: 2020/10/26
 * http://www.linuxcoming.com/blog/2019/09/19/java_merge_sort.html
 **/
public class MergeSort {
    public static void main(String[] args) {
        int[] array = new int[16];
        for (int i = 0; i < 16; i++) {
            array[i] = new Random().nextInt(100);
        }

        System.out.println(Arrays.toString(array));
        int[] result = new int[16];
        merge_sort_recursive(array, result, 0, 15);

        System.out.println(Arrays.toString(array));
    }

    private static void merge_sort_recursive(int[] array, int[] result, int start, int end) {
        if (start >= end) {
            return;
        }

        int length = end - start, mid = (length >> 1) + start;
        int startLeft = start, endLeft = mid, startRight = mid + 1, endRight = end;
        merge_sort_recursive(array, result, startLeft, endLeft);
        merge_sort_recursive(array, result, startRight, endRight);

        int index = start;
        while (startLeft <= endLeft && startRight <= endRight) {
            result[index++] = array[startLeft] > array[startRight] ? array[startRight++] : array[startLeft++];
        }
        while (startLeft <= endLeft) {
            result[index++] = array[startLeft++];
        }
        while (startRight <= endRight) {
            result[index++] = array[startRight++];
        }
        for (index = start; index <= end; index++) {
            array[index] = result[index];
        }
    }

}
