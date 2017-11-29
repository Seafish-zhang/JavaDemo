package sort;

import org.apache.commons.lang3.StringUtils;

import java.util.Random;

import static sort.SortUtil.swap;

/**
 * 选择排序（Selection sort）是一种简单直观的排序算法。
 * 它的工作原理是每一次从待排序的数据元素中选出最小（或最大）的一个元素，
 * 存放在序列的起始位置，直到全部待排序的数据元素排完。
 * <p>
 * 选择排序是不稳定的算法 （比如序列[5， 5， 3]第一次就将第一个[5]与[3]交换，导致第一个5挪动到第二个5后面）。
 * 选择排序的最差时间分析是O(N2)
 * 选择排序的时间复杂度是O(N2)
 * 选择排序的空间复杂度是O(1)
 */
public class SelectionSort {

    /**
     * 选择排序
     *
     * @param a 需要排序的数组
     */
    private static void selectSort(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            int min = i;
            for (int j = i + 1; j < a.length; j++) {
                if (a[j].compareTo(a[min]) < 0) {
                    min = j;
                }
            }

            if (min != i) {
                swap(a, i, min);
            }
        }
    }

    public static void main(String[] args) {
        int length = 1000;
        Integer[] a = new Integer[length];
        for (int i = 0; i < length; i++) {
            a[i] = new Random().nextInt(10000);
        }
        System.out.println(StringUtils.join(a, ','));
        long start = System.currentTimeMillis();
        System.out.println("开始时间：" + start + "毫秒");
        selectSort(a);
        long end = System.currentTimeMillis();
        System.out.println("结束时间：" + end + "毫秒");
        System.out.println("耗时：" + (end - start) + "毫秒");
        System.out.println(StringUtils.join(a, ','));
        System.out.println(SortUtil.isMonotoneInc(a));
    }

}
