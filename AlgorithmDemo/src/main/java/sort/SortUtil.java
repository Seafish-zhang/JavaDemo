package sort;

/**
 * 排序算法 辅助工具类
 */
class SortUtil {
    /**
     * 判断数组是否单调递增
     *
     * @param a 数组
     * @return boolean
     */
    static boolean isMonotoneInc(Comparable[] a) {
        if (a.length <= 1) {
            return true;
        }
        for (int i = 1; i < a.length - 1; i++) {
            if (a[i].compareTo(a[i - 1]) < 0) {
                return false;
            }
        }
        return true;
    }

    static boolean isMonotoneDec(Comparable[] a) {
        if (a.length <= 1) {
            return true;
        }
        for (int i = 1; i < a.length - 1; i++) {
            if (a[i].compareTo(a[i - 1]) > 0) {
                return false;
            }
        }
        return true;
    }

    static void swap(Comparable[] a, int i, int j) {
        if (a.length <= 0 || a.length < i || a.length < j) {
            throw new IndexOutOfBoundsException();
        }
        Comparable tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }


}
