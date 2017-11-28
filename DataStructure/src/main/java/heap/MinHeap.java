package heap;


import tree.HuffmanTree;

import java.util.ArrayList;
import java.util.List;

/**
 * huffman树 辅助 最小堆
 */
public class MinHeap {
    private List<HuffmanTree.HuffmanNode> heap;  // 用动态数组（ArrayList<>）来表达二叉堆

    public MinHeap() {
        this.heap = new ArrayList<>();
    }

    public MinHeap(int a[]){
        heap = new ArrayList<>();
        HuffmanTree huffmanTree = new HuffmanTree();
        for (int data : a){
            HuffmanTree.HuffmanNode huffmanNode = huffmanTree.new HuffmanNode(data, null, null, null);
            insert(huffmanNode);
        }
    }

    /**
     * 插入
     *
     * @param data 插入的数据
     */
    public void insert(HuffmanTree.HuffmanNode data) {
        int size = heap.size(); // 获取数组大小，相当于获取新插入数据的数组中下标
        heap.add(data);  // 新数据插入数组最后
        adjustUp(size);
    }

    /**
     * 向上调整
     *
     * @param current 向上调整当前索引值
     */
    private void adjustUp(int current) {
        int parentIndex = (current - 1) / 2; // 获取父结点位置
        HuffmanTree.HuffmanNode insertData = heap.get(current);  // 插入结点的值
        while (current > 0) {  // 从start开始向上直到0，调整堆
            int compare = insertData.compareTo(heap.get(parentIndex));
            if (compare > 0) {  // 当前结点 大于 的父结点 符合二叉堆
                break; // 退出循环
            } else {  // 当前结点 小于 父结点
                heap.set(current, heap.get(parentIndex)); // 把父结点的值设置到当前结点
                // 向上
                current = parentIndex;
                parentIndex = (current - 1) / 2;
            }
        }
        heap.set(current, insertData); // 将插入的值设置到合理的位置
    }

    public int delete(HuffmanTree.HuffmanNode data) {
        if (heap.isEmpty()) { // 空堆
            return -1;
        }
        int index = heap.indexOf(data);
        if (index == -1) {  // 堆中没有该数据
            return -1;
        }

        int size = heap.size();
        heap.set(index, heap.get(size - 1));  // 将“堆”中最后一个数据 填补到删除元素位置
        heap.remove(size - 1); // 删除最后元素
        if (index < heap.size() - 1) {  // 删除最后元素不需要调整
            adjustDown(index);
        }
        return 0;

    }

    private void adjustDown(int current) {
        int left = 2 * current + 1; // 左孩子结点 索引值
        HuffmanTree.HuffmanNode data = heap.get(current); // 调整位置数据
        while (left <= heap.size() - 1) {
            // 先比较孩子中那个比较大， left + 1即右孩子索引值
            if (left < heap.size() - 1) {  // 有右孩子
                int lrCompare = heap.get(left).compareTo(heap.get(left + 1));
                if (lrCompare > 0) {
                    left++;
                }
            }
            int compare = data.compareTo(heap.get(left));
            if (compare < 0) {  // 当前结点 小于 的子结点最小值 符合二叉堆
                break; // 退出循环
            } else {  // 当前结点 大于 子结点
                heap.set(current, heap.get(left)); // 把子结点最大的值设置到当前结点
                // 向下
                current = left;
                left = 2 * current + 1;
            }
        }
        heap.set(current, data); // 将调整的值设置到合理的位置
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (HuffmanTree.HuffmanNode aHeap : heap) {
            sb.append(aHeap.getData());
            sb.append(" ");
        }
        return sb.toString();
    }

    public HuffmanTree.HuffmanNode pollMin(){
        if (heap.size() == 0){
            return null;
        }

        HuffmanTree.HuffmanNode huffmanNode = heap.get(0);
        delete(huffmanNode);
        return huffmanNode;
    }

    public void destroy() {
        heap.clear();
        heap = null;
    }
}
