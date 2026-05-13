package cn.com.nightfield.algorithm.sort;

import java.util.Arrays;

/**
 * @author: zhochi
 * @create: 2020/11/5
 **/
public class MinMax {
    public static void main(String[] args) {
        int[] array = {2,6,2,3,7,1,-2,6,8,3,5,0,2,-1};
        //print(array);
        //process(array, 0, 2);
        //print(array);

        int n = 150;
        int result = trailingZeroes(n);
        System.out.println(result);
        result = fun1(n);
        System.out.println(result);
    }

    public static void process(int[] array, int start, int step) {
        int arrayLength = array.length;
        if (step / 2 > arrayLength) {
            return;
        }

        int halfStep = step / 2, temp;
        for (int i = start; i + halfStep < arrayLength; i += step) {
            if (array[i] > array[i + halfStep]) {
                temp = array[i];
                array[i] = array[i + halfStep];
                array[i + halfStep] = temp;
            }
        }

        print(array);

        process(array, start, step * 2);
        process(array, start + halfStep, step * 2);
    }

    public static void recurToMin(int[] array, int left, int end) {
        if (left >= end) {
            return;
        }

        int gap = (end - left) / 2 + 1;
        for (int i = left; i < left + gap; i++) {
            if (i + gap <= end && array[i] > array[i + gap]) {
                int temp = array[i];
                array[i] = array[i + gap];
                array[i + gap] = temp;
            }
        }

        recurToMin(array, left, left + gap - 1);
    }

    public static void recurToMax(int[] array, int left, int end) {
        if (left >= end) {
            return;
        }

        int gap = (end - left) / 2 + 1;
        for (int i = left; i < left + gap; i++) {
            if (i + gap <= end && array[i] > array[i + gap]) {
                int temp = array[i];
                array[i] = array[i + gap];
                array[i + gap] = temp;
            }
        }


        recurToMax(array, left + gap - 1, end);
    }

    public static void print(int[] array) {
        System.out.println(Arrays.toString(array));
    }

    public static int trailingZeroes(int n) {
        if (n < 5) {
            return 0;
        }

        int result = 0;
        for (int i = 5; i <= n; i += 5) {
            if (i % 5 == 0) {
                // a 5 lead to a 0
                if (i % 10 != 0) {
                    result += getPowerOf5(i);
                }
                else {
                    // calculate the i's number of 0 at tail
                    int tmp = i;
                    while (tmp % 10 == 0 && tmp > 0) {
                        tmp = tmp / 10;
                        result++;
                    }
                    // after trimed the 0 at tail, if the number ends with 5, it will lead to another 0
                    if (tmp % 5 == 0) {
                        result += getPowerOf5(tmp);
                    }
                }
            }
        }

        return result;
    }

    // if i could be divided by power of 5, it will lead to more 0
    public static int getPowerOf5(int number) {
        int result = 0;
        while (number % 5 == 0 && number >0) {
            result++;
            number = number / 5;
        }
        return result;
    }

    static int fun1(int n)
    {
        int num = 0;
        int i,j;

        for (i = 5;i <= n;i += 5)
        {
            j = i;
            while (j % 5 == 0)
            {
                num++;
                j /= 5;
            }
        }

        return num;
    }
}
