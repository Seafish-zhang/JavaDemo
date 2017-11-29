package sort;

import org.apache.commons.lang3.StringUtils;

import java.util.Random;

import static sort.SortUtil.swap;

/**
 * 快速排序(Quick Sort)使用分治法策略。
 * 它的基本思想是：选择一个基准数，通过一趟排序将要排序的数据分割成独立的两部分；其中一部分的所有数据都比另外一部分的所有数据都要小。然后，再按此方法对这两部分数据分别进行快速排序，整个排序过程可以递归进行，以此达到整个数据变成有序序列。
 * <p>
 * 快速排序流程：
 * (1) 从数列中挑出一个基准值。
 * (2) 将所有比基准值小的摆放在基准前面，所有比基准值大的摆在基准的后面(相同的数可以到任一边)；在这个分区退出之后，该基准就处于数列的中间位置。
 * (3) 递归地把"基准值前面的子数列"和"基准值后面的子数列"进行排序。
 * <p>
 * 快速排序是不稳定的算法
 * 快速排序的最差时间分析是O(N2)
 * 快速排序的时间复杂度是O(n*log2n)
 * 快速排序的空间复杂度是O(logN~N)
 */
public class QuickSort {


    /**
     * 快速排序
     *
     * @param a     需要排序数组
     * @param left  数组左边界
     * @param right 数组右边界
     */
    private static void quickSort(Comparable[] a, int left, int right) {
        if (left < right) {  // 有未排序好部分
            int baseIndex = left;
            Comparable baseNum = a[left];  // 基数
            int start = left;
            int end = right;
            while (left < right) {
                while (left < right && a[right].compareTo(baseNum) > 0) {
                    --right;
                }
                if (left < right) {
                    swap(a, baseIndex, right);
                    baseIndex = right;
                }

                while (left < right && a[left].compareTo(baseNum) <= 0) {
                    ++left;
                }
                if (left < right) {
                    swap(a, baseIndex, left);
                    baseIndex = left;
                }
            }
            quickSort(a, start, baseIndex - 1);
            quickSort(a, baseIndex + 1, end);
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
        quickSort(a, 0, a.length - 1);
        long end = System.currentTimeMillis();
        System.out.println("结束时间：" + end + "毫秒");
        System.out.println("耗时：" + (end - start) + "毫秒");
        System.out.println(StringUtils.join(a, ','));
        System.out.println(SortUtil.isMonotoneInc(a));
    }
}
