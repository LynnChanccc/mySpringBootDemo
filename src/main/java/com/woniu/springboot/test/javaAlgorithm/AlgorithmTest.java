package com.woniu.springboot.test.javaAlgorithm;

import java.util.Arrays;

/**
 * @author cl
 * @Date 2020/3/13 16:32
 * java算法
 */
public class AlgorithmTest {
    public static void main(String[] args) {
        //二分查找
        //必须有序
        int[] array1 = {10, 24, 33, 44, 55, 64, 72, 88, 99};
        //返回的是数组 下标+1
        System.out.println(biSearch(array1, 44)); //返回4

        //冒泡排序
        int[] array2 = {2, 1, 3, 5, 3, 7, 9, 8, 11};
        System.out.println(Arrays.toString(bubbleSort(array2)));
    }

    /**
     * 二分查找（循环实现）
     * 又叫折半查找，要求待查找的序列有序。每次取中间位置的值与待查关键字比较，如果中间位置
     * 的值比待查关键字大，则在前半部分循环这个查找的过程，如果中间位置的值比待查关键字小，
     * 则在后半部分循环这个查找的过程。直到查找到了为止，否则序列中没有待查的关键字。
     *
     * @param array
     * @param a
     * @return
     */
    public static int biSearch(int[] array, int a) {
        int low = 0;//第一个下标
        int high = array.length - 1;//最后一个下标
        int mid;
        while (low <= high) {
            mid = (low + high) / 2;//中间的位置
            if (array[mid] == a) {
                return mid + 1;

            } else if (array[mid] < a) { //向右查找
                low = mid + 1;
            } else { //向左查找
                high = mid - 1;
            }
        }
        return -1;
    }

    /**
     * 冒泡排序
     * （1）比较前后相邻的二个数据，如果前面数据大于后面的数据，就将这二个数据交换。
     * （2）这样对数组的第 0 个数据到 N-1 个数据进行一次遍历后，最大的一个数据就“沉”到数组第N-1 个位置。
     * （3）N=N-1，如果 N 不为 0 就重复前面二步，否则排序完成
     *
     * @param array 排序的数组
     * @return
     */
    public static int[] bubbleSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 1; j < array.length - 1 - i; j++) {
                if (array[j - 1] > array[j]) {//前面的数大于后面的数
                    //交换位置
                    int temp;
                    temp = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = temp;
                }
            }
        }
        return array;
    }
}
