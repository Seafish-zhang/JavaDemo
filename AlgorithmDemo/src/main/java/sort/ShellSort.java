package sort;

import org.apache.commons.lang3.StringUtils;

import java.util.Random;

import static sort.SortUtil.swap;

/**
 * 希尔排序(Shell Sort)是插入排序的一种。也称缩小增量排序，
 * 是直接插入排序算法的一种更高效的改进版本。
 * <p>
 * 希尔排序是把记录按下标的一定增量分组，对每组使用直接插入排序算法排序；
 * 随着增量逐渐减少，每组包含的关键词越来越多，
 * 当增量减至1时，整个文件恰被分成一组，算法便终止
 * <p>
 * 希尔排序是不稳定的算法
 * 希尔排序的空间复杂度是O(1)
 * 希尔排序的时间复杂度与增量序列的选取有关
 * 效率比直接插入排序高
 */
public class ShellSort {

    /**
     * 希尔排序
     *
     * @param a   要排序的数组
     * @param inc 增量系列
     */
    private static void shellSort(Comparable[] a, int inc) {
        int length = a.length;
        int h = 1;
        while (h < length / inc) {
            h = inc * h + 1;
        }
        while (h >= 1) {
            for (int i = h; i < length; i++) {
                for (int j = i; j >= h && a[j].compareTo(a[j - h]) < 0; j -= h) {
                    swap(a, j, j - h);
                }
            }
            h = h / inc;
        }
    }

    public static void main(String[] args) {
        int inc = 4;
        int length = 1000;
        Integer[] a = new Integer[length];
        for (int i = 0; i < length; i++) {
            a[i] = new Random().nextInt(10000);
        }
        System.out.println(StringUtils.join(a, ','));
        long start = System.currentTimeMillis();
        System.out.println("开始时间：" + start + "毫秒");
        shellSort(a, inc);
        long end = System.currentTimeMillis();
        System.out.println("结束时间：" + end + "毫秒");
        System.out.println("耗时：" + (end - start) + "毫秒");
        System.out.println(StringUtils.join(a, ','));
        System.out.println(SortUtil.isMonotoneInc(a));
    }
}
