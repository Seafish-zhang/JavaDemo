package sort;

import org.apache.commons.lang3.StringUtils;

import java.util.Random;

/**
 * 冒泡排序(Bubble Sort)
 * <p>
 * 它是一种较简单的排序算法。它会遍历若干次要排序的数列，
 * 每次遍历时，它都会从前往后依次的比较相邻两个数的大小；
 * 如果前者比后者大，则交换它们的位置。
 * 这样，一次遍历之后，最大的元素就在数列的末尾！
 * 采用相同的方法再次遍历时，第二大的元素就被排列在最大元素之前。
 * 重复此操作，直到整个数列都有序为止
 * <p>
 * 冒泡排序是稳定的算法
 * 冒泡排序的最差时间分析是O(N2)
 * 冒泡排序的时间复杂度是O(N2)
 * 冒泡排序的空间复杂度是O(1)
 * <p>
 * <p>
 * <p>
 * 注：排序算法稳定性
 * 假定在待排序的记录序列中，存在多个具有相同的关键字的记录，
 * 若经过排序，这些记录的相对次序保持不变，即在原序列中，ri=rj，
 * 且ri在rj之前，而在排序后的序列中，ri仍在rj之前，
 * 则称这种排序算法是稳定的；否则称为不稳定的。
 */
public class BubbleSort {

    /**
     * 冒泡排序
     *
     * @param a 需要排序的数组
     */
    private static void bubbleSort(Comparable[] a) {
        int length = a.length;
        boolean flag; // 标记是否发生过交换

        for (int i = length - 1; i > 0; i--) {
            flag = false; // 重制标志位
            for (int j = 0; j < i; j++) {
                if (a[j].compareTo(a[j + 1]) > 0) {
                    SortUtil.swap(a, j, j + 1);
                    flag = true;  // 设置标志位
                }
            }

            if (!flag) {  // 本轮没发生过交换 即都是有序的
                break; // 退出循环
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
        bubbleSort(a);
        long end = System.currentTimeMillis();
        System.out.println("结束时间：" + end + "毫秒");
        System.out.println("耗时：" + (end - start) + "毫秒");
        System.out.println(StringUtils.join(a, ','));
        System.out.println(SortUtil.isMonotoneInc(a));
    }

}
