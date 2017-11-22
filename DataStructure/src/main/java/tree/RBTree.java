package tree;

public class RBTree<T extends Comparable<T>> extends BinaryTree{

    private RBTNode<T> root;

    private static final boolean RED = false;
    private static final boolean BLACK = true;

    //红黑树结点
    public class RBTNode<T extends Comparable<T>>{
        boolean color;  // 颜色
        T data; // 关键字（节点数据）
        RBTNode<T> left; // 左孩子
        RBTNode<T> right; // 右孩子
        RBTNode<T> parent; // 父结点

        public RBTNode(T data, boolean color, RBTNode<T> parent, RBTNode<T> left, RBTNode<T> right) {
            this.data = data;
            this.color = color;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }

        public T getData() {
            return data;
        }

        public String toString() {
            return "" + data + (this.color == RED ? "R" : "B");
        }
    }

    public RBTree() {
        root = null;
    }

    private RBTNode<T> parentOf(RBTNode<T> node) {
        return node != null ? node.parent : null;
    }

    private boolean colorOf(RBTNode<T> node) {
        return node != null ? node.color : BLACK;
    }

    private boolean isRed(RBTNode<T> node) {
        return ((node != null) && (node.color == RED)) ? true : false;
    }

    private boolean isBlack(RBTNode<T> node) {
        return !isRed(node);
    }

    private void setBlack(RBTNode<T> node) {
        if (node != null)
            node.color = BLACK;
    }

    private void setRed(RBTNode<T> node) {
        if (node != null)
            node.color = RED;
    }

    private void setParent(RBTNode<T> node, RBTNode<T> parent) {
        if (node != null)
            node.parent = parent;
    }

    private void setColor(RBTNode<T> node, boolean color) {
        if (node != null)
            node.color = color;
    }


}
