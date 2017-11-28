package tree;

import heap.MinHeap;

/**
 * Huffman Tree，中文名是哈夫曼树或霍夫曼树，它是最优二叉树。
 * <p>
 * 定义：给定n个权值作为n个叶子结点，构造一棵二叉树，
 * 若树的带权路径长度达到最小，则这棵树被称为哈夫曼树。
 */
public class HuffmanTree {
    private HuffmanNode root;

    public HuffmanTree() {
        root = null;
    }

    public class HuffmanNode implements Comparable {
        int data;
        HuffmanNode left;
        HuffmanNode right;
        HuffmanNode parent;

        public HuffmanNode(int data, HuffmanNode left,
                           HuffmanNode right,
                           HuffmanNode parent) {
            this.data = data;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }

        public int compareTo(Object obj) {
            if (obj instanceof HuffmanNode) {
                return data - ((HuffmanNode) obj).data;
            } else {
                throw new ClassCastException();
            }
        }

        public int getData() {
            return data;
        }

    }

    public HuffmanTree(int a[]) {
        HuffmanNode parent = null;
        MinHeap heap;
        heap = new MinHeap(a);

        for (int i = 0; i < a.length - 1; i++) {
            HuffmanNode left = heap.pollMin();
            HuffmanNode right = heap.pollMin();

            parent = new HuffmanNode(left.data + right.data, left, right, null);
            left.parent = parent;
            right.parent = parent;

            heap.insert(parent);
        }

        root = parent;
        heap.destroy();
    }

    private void print(HuffmanNode tree, int key, int direction) {

        if (tree != null) {

            if (direction == 0)    // tree是根节点
                System.out.printf("%2d is root\n", tree.data);
            else                // tree是分支节点
                System.out.printf("%2d is %2d's %6s child\n", tree.data, key, direction == 1 ? "right" : "left");

            print(tree.left, tree.data, -1);
            print(tree.right, tree.data, 1);
        }
    }

    public void print() {
        if (root != null)
            print(root, root.data, 0);
    }
}
