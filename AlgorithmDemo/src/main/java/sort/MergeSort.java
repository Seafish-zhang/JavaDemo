package sort;

import org.apache.commons.lang3.StringUtils;

import java.util.Random;

/**
 * 归并排序（MERGE-SORT）是建立在归并操作上的一种有效的排序算法,
 * 该算法是采用分治法（Divide and Conquer）的一个非常典型的应用。
 * 将已有序的子序列合并，得到完全有序的序列；
 * 即先使每个子序列有序，再使子序列段间有序。
 * 若将两个有序表合并成一个有序表，称为二路归并。
 * <p>
 * 归并排序是稳定的算法
 * 归并排序的最差时间分析是O(N*logN)
 * 归并排序的时间复杂度是O(N*logN)
 * 归并排序的空间复杂度是O(N)
 */
public class MergeSort {

    private static void mergeSort(Comparable[] a, int start, int end) {
        if (start >= end) {
            return;
        }
        int mid = (end + start) / 2;
        mergeSort(a, start, mid);
        mergeSort(a, mid + 1, end);
        merge(a, start, mid + 1, end);
    }

    /**
     * 二路归并
     *
     * @param a      需合并数组
     * @param first  第一个有序子序列开始位置
     * @param second 第二个有序子序列开始位置
     * @param end    第二个有序子序列结束位置
     */
    private static void merge(Comparable[] a, int first, int second, int end) {
        Comparable[] tmp = new Comparable[end - first + 1];
        int i = first, j = second, k = 0;
        while (i < second && j <= end) {  // 两个子序列 均没遍历结束
            if (a[i].compareTo(a[j]) < 0) {
                tmp[k++] = a[i++];
            } else {
                tmp[k++] = a[j++];
            }
        }
        while (i < second) {  // 只有第一个子序列
            tmp[k++] = a[i++];
        }
        while (j <= end) { // 只有第二个子序列
            tmp[k++] = a[j++];
        }
        System.arraycopy(tmp, 0, a, first, tmp.length);
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
        mergeSort(a, 0, a.length - 1);
        long end = System.currentTimeMillis();
        System.out.println("结束时间：" + end + "毫秒");
        System.out.println("耗时：" + (end - start) + "毫秒");
        System.out.println(StringUtils.join(a, ','));
        System.out.println(SortUtil.isMonotoneInc(a));
    }
}
