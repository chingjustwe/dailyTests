package cn.com.nightfield.algorithm.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * @author: zhochi
 * @create: 2020/10/26
 **/
public class QuickSort {
    public static void main(String[] args) {
        int[] array = new int[16];
        for (int i = 0; i < 16; i++) {
            array[i] = new Random().nextInt(10000);
        }

        System.out.println(System.currentTimeMillis());
        print(array);

        sort(array, 0, array.length - 1);

        System.out.println(System.currentTimeMillis());
        print(array);
    }

    private static void sort(int[] array, int start, int end) {
        if (start >= end) {
            return;
        }
        int pivot = array[start], left = start, right = end;

        while (left < right) {
            while (array[right] >= pivot && left < right) {
                right--;
            }
            while (array[left] <= pivot && left < right) {
                left++;
            }
            if (left < right) {
                array[left] ^= array[right];
                array[right] ^= array[left];
                array[left] ^= array[right];
                /*int temp = array[left];
                array[left] = array[right];
                array[right] = temp;*/
            }
        }

        if (left != start) {
            array[left] ^= array[start];
            array[start] ^= array[left];
            array[left] ^= array[start];
        }

        /*int temp = array[left];
        array[left] = array[start];
        array[start] = temp;*/

        //print(array);

        sort(array, start, left - 1);
        sort(array, left + 1, end);
    }

    static void print(int[] array) {
        System.out.println(Arrays.toString(array));
    }
}
