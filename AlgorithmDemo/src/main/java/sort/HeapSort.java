package sort;

import heap.BinaryHeap;
import org.apache.commons.lang3.StringUtils;

import java.util.Random;

/**
 * 堆排序(Heapsort)是指利用堆积树（堆）这种数据结构所设计的一种排序算法，
 * 它是选择排序的一种。可以利用数组的特点快速定位指定索引的元素。
 * 堆分为大根堆和小根堆，是完全二叉树。
 * 大根堆的要求是每个节点的值都不大于其父节点的值，
 * 在数组的非降序排序中，需要使用的就是大根堆，
 * 因为根据大根堆的要求可知，最大的值一定在堆顶。
 *
 * 堆排序是不稳定的算法
 * 堆排序的最差时间分析是O(N*logN)
 * 堆排序的时间复杂度是O(N*logN)
 * 堆排序的空间复杂度是O(1) - -
 */
public class HeapSort {

    private static void heapSort(Comparable[] a) {
        BinaryHeap<? extends Comparable> heap = new BinaryHeap<>(a);
        for (int i = 0; i < a.length; i++) {
            a[i] = heap.pollTop();
        }
    }


    public static void main(String[] args) {
        int length = 10000;
        Integer[] a = new Integer[length];
        for (int i = 0; i < length; i++) {
            a[i] = new Random().nextInt(10000);
        }
        System.out.println(StringUtils.join(a, ','));
        long start = System.currentTimeMillis();
        System.out.println("开始时间：" + start + "毫秒");
        heapSort(a);
        long end = System.currentTimeMillis();
        System.out.println("结束时间：" + end + "毫秒");
        System.out.println("耗时：" + (end - start) + "毫秒");
        System.out.println(StringUtils.join(a, ','));
        System.out.println(SortUtil.isMonotoneDec(a));
    }
}
