package cn.com.nightfield.algorithm.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * @author: zhochi
 * @create: 2021/4/23
 **/
public class HeapSort {
    public static void main(String[] args) {
        int[] array = new int[8];
        for (int i = 0; i < 8; i++) {
            array[i] = new Random().nextInt(1000);
        }

        System.out.println(System.currentTimeMillis());
        print(array);

        sort(array);

        System.out.println(System.currentTimeMillis());
        print(array);
    }

    private static int[] sort(int[] array) {
        int length = array.length;
        for (int i = (length >> 1); i >= 0; i--) {
            heapify(array, i, length);
        }
        print(array);

        for (int i = length - 1; i > 0; i--) {
            swap(array, 0, i);
            length--;
            heapify(array, 0, length);
            System.out.print(i);
            print(array);
        }

        return array;
    }

    private static void heapify(int[] array, int i, int length) {
        int left = (i << 1) + 1, right = (i << 1) + 2, minIndex = i;
        if (left < length && array[left] < array[minIndex]) {
            minIndex = left;
        }
        if (right < length && array[right] < array[minIndex]) {
            minIndex = right;
        }
        if (minIndex != i) {
            swap(array, minIndex, i);
            heapify(array, minIndex, length);
        }
    }

    private static void swap(int[] array, int i, int j) {
        array[i] ^= array[j];
        array[j] ^= array[i];
        array[i] ^= array[j];
    }

    static void print(int[] array) {
        System.out.println(Arrays.toString(array));
    }
}
