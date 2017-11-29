package sort;

import org.apache.commons.lang3.StringUtils;

import java.util.Random;

/**
 * 直接插入排序(Straight Insertion Sort)的基本思想是：
 * 把n个待排序的元素看成为一个有序表和一个无序表。
 * 开始时有序表中只包含1个元素，
 * 无序表中包含有n-1个元素，
 * 排序过程中每次从无序表中取出第一个元素，
 * 将它插入到有序表中的适当位置，使之成为新的有序表，重复n-1次可完成排序过程。
 * <p>
 * 插入排序是稳定的算法
 * 插入排序的最差时间分析是O(N2)
 * 插入排序的时间复杂度是O(N2)
 * 插入排序的空间复杂度是O(1)
 */
public class InsertSort {

    /**
     * 插入排序
     *
     * @param a 需要排序的数组
     */
    private static void insertSort(Comparable a[]) {
        int length = a.length;
        int sortIndex = 0;  // 最大排好序的索引

        while (sortIndex < length - 1) {
            int i;
            for (i = sortIndex + 1; i < length; i++) {
                if (a[i].compareTo(a[i - 1])<0) {  // 找到不成顺序开始的位置
                    break;
                }
            }
            for (int j = i; j > 0; j--) {
                if (a[j - 1].compareTo(a[j])>0) {
                    SortUtil.swap(a, j - 1, j);
                } else {
                    break;
                }
            }
            sortIndex = i;
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
        insertSort(a);
        long end = System.currentTimeMillis();
        System.out.println("结束时间：" + end + "毫秒");
        System.out.println("耗时：" + (end - start) + "毫秒");
        System.out.println(StringUtils.join(a, ','));
        System.out.println(SortUtil.isMonotoneInc(a));
    }

}
