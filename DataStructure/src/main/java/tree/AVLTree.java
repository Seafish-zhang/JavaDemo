package tree;

/**
 * AVL树，它的任何节点的两个子树的高度差别都<=1
 * AVL树的查找、插入和删除在平均和最坏情况下都是O(log n)。
 * <p>
 * 如果在AVL树中插入或删除节点后，使得高度之差大于1。
 * 此时，AVL树的平衡状态就被破坏，它就不再是一棵二叉树；
 * 为了让它重新维持在一个平衡状态，就需要对其进行 "旋转" 处理。
 */
public class AVLTree<T extends Comparable<T>> {
    private AVLNode<T> root;

    private class AVLNode<T> {
        T data;
        int height;
        AVLNode<T> left;
        AVLNode<T> right;

        public AVLNode(T data, AVLNode<T> left, AVLNode<T> right) {
            this.data = data;
            this.left = left;
            this.right = right;
            this.height = 0;
        }
    }

    public int height() {
        return height(this.root);
    }

    /**
     * 获取结点高度
     * 如果结点为null，则高度为0
     *
     * @param root
     * @return
     */
    private int height(AVLNode<T> root) {
        if (root != null) {
            return root.height;
        }
        return 0;
    }

    /*
       * 前序遍历"AVL树"
       */
    private void preOrder(AVLNode<T> root) {
        if (root != null) {
            System.out.print(root.data + " ");
            preOrder(root.left);
            preOrder(root.right);
        }
    }

    public void preOrder() {
        preOrder(root);
    }

    /*
* 中序遍历"AVL树"
*/
    private void inOrder(AVLNode<T> root) {
        if (root != null) {
            inOrder(root.left);
            System.out.print(root.data + " ");
            inOrder(root.right);
        }
    }

    public void inOrder() {
        inOrder(root);
    }

    /*
* 后序遍历"AVL树"
*/
    private void postOrder(AVLNode<T> root) {
        if (root != null) {
            postOrder(root.left);
            postOrder(root.right);
            System.out.print(root.data + " ");
        }
    }

    public void postOrder() {
        postOrder(root);
    }


    private AVLNode<T> search(AVLNode<T> x, T data) {
        if (x == null)
            return x;
        int cmp = data.compareTo(x.data);
        if (cmp < 0)
            return search(x.left, data);
        else if (cmp > 0)
            return search(x.right, data);
        else
            return x;
    }

    public AVLNode<T> search(T data) {
        return search(root, data);
    }


    // 查找最小结点：返回tree为根结点的二叉树的最小结点。
    public T minimum() {
        AVLNode<T> p = minimum(root);
        if (p != null)
            return p.data;

        return null;
    }

    private AVLNode<T> minimum(AVLNode<T> tree) {
        if (tree == null)
            return null;

        while (tree.left != null)
            tree = tree.left;
        return tree;
    }

    //查找最大结点：返回tree为根结点的二叉树的最大结点。
    public T maximum() {
        AVLNode<T> p = maximum(root);
        if (p != null)
            return p.data;

        return null;
    }

    private AVLNode<T> maximum(AVLNode<T> tree) {
        if (tree == null)
            return null;

        while (tree.right != null)
            tree = tree.right;
        return tree;
    }

    private int max(int a, int b) {
        return a > b ? a : b;
    }

    /**
     * 如果在AVL树中进行插入或删除节点后，可能导致AVL树失去平衡。
     * 这种失去平衡的可以概括为4种姿态：LL(左左)，LR(左右)，RR(右右)和RL(右左)。
     * <p>
     * LL 左左对应的情况(左单旋转)。
     *
     * @param k2 失去平衡的根结点
     * @return k1 返回旋转后的根结点
     */
    private AVLNode<T> leftLeftRotate(AVLNode<T> k2) {
        AVLNode<T> k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k2.height = max(height(k2.left), height(k2.right)) + 1;
        k1.height = max(height(k1.left), k2.height) + 1;
        return k1;
    }

    /**
     * RR 右右对应的情况(右单旋转)。
     * <p>
     * RR与LL对称的
     *
     * @param k1 失去平衡的根结点
     * @return AVLNode 返回旋转后的根结点
     */
    private AVLNode<T> rightRightRotate(AVLNode<T> k1) {
        AVLNode<T> k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
        k1.height = max(height(k1.left), height(k1.right)) + 1;
        k2.height = max(height(k2.right), k1.height) + 1;
        return k2;
    }

    /**
     * LR：左右对应的情况(左双旋转)。
     * <p>
     * LR失去平衡的情况，需要经过两次旋转才能让AVL树恢复平衡。
     * 1、围绕需要旋转结点的左结点进行的"RR旋转"
     * 2、围绕需要旋转结点进行的"LL旋转"。
     *
     * @param k3 失去平衡的根结点
     * @return AVLNode 返回旋转后的根结点
     */
    private AVLNode<T> leftRightRotate(AVLNode<T> k3) {
        k3.left = rightRightRotate(k3.left);
        return leftLeftRotate(k3);
    }

    /**
     * RL：左右对应的情况(左双旋转)。
     * RL与LR对称的
     * <p>
     * RL失去平衡的情况，需要经过两次旋转才能让AVL树恢复平衡。
     * 1、围绕需要旋转结点的右结点进行的"LL旋转"
     * 2、围绕需要旋转结点进行的"RR旋转"。
     *
     * @param k1 失去平衡的根结点
     * @return AVLNode 返回旋转后的根结点
     */
    private AVLNode<T> rightLeftRotate(AVLNode<T> k1) {
        k1.right = leftLeftRotate(k1.right);
        return rightRightRotate(k1);
    }

    public void insert(T data) {
        this.root = insert(this.root, data);
    }

    /**
     * 将结点插入到AVL树中，并返回根节点
     * <p>
     * 参数说明：
     *
     * @param root AVL树的根结点
     * @param data 插入的结点的键值
     * @return AVLNode 返回根结点
     */
    private AVLNode<T> insert(AVLNode<T> root, T data) {
        if (root == null) {
            root = new AVLNode<>(data, null, null);
        } else {
            // 找到对应插入位置
            int compareTo = data.compareTo(root.data);
            if (compareTo < 0) {
                root.left = insert(root.left, data);
                // 破坏平衡
                if (height(root.left) - height(root.right) == 2) {
                    // 插入在左孩子的左孩子位置 （即LL）
                    if (data.compareTo(root.left.data) < 0) {
                        root = leftLeftRotate(root);
                    } else { // LR
                        root = leftRightRotate(root);
                    }
                }
            } else {
                root.right = insert(root.right, data);
                // 破坏平衡
                if (height(root.right) - height(root.left) == 2) {
                    // 插入在右孩子的右孩子位置 （即RR）
                    if (data.compareTo(root.right.data) > 0) {
                        root = rightRightRotate(root);
                    } else { // RL
                        root = rightLeftRotate(root);
                    }
                }
            }
        }
        root.height = max(height(root.left), height(root.right)) + 1;
        return root;
    }

    public void delete(T data) {
        AVLNode<T> deleteNode = search(root, data);
        if (deleteNode != null){
            root = delete(root, deleteNode);
        }
    }

    private AVLNode<T> delete(AVLNode<T> root, AVLNode<T> deleteNode) {
        // 根为空 或者 没有要删除的节点，直接返回null。
        if (root==null || deleteNode==null)
            return null;
        int cmp = deleteNode.data.compareTo(root.data);
        if (cmp < 0) {        // 待删除的节点在"root的左子树"中
            root.left = delete(root.left, deleteNode);
            // 删除节点后，若AVL树失去平衡，则进行相应的调节。
            if (height(root.right) - height(root.left) == 2) {
                AVLNode<T> r =  root.right;
                if (height(r.left) > height(r.right))
                    root = rightLeftRotate(root);
                else
                    root = rightRightRotate(root);
            }
        } else if (cmp > 0) {    // 待删除的节点在"root的右子树"中
            root.right = delete(root.right, deleteNode);
            // 删除节点后，若AVL树失去平衡，则进行相应的调节。
            if (height(root.left) - height(root.right) == 2) {
                AVLNode<T> l =  root.left;
                if (height(l.right) > height(l.left))
                    root = leftRightRotate(root);
                else
                    root = leftLeftRotate(root);
            }
        } else {    // root是对应要删除的节点。
            // root的左右孩子都非空
            if ((root.left!=null) && (root.right!=null)) {
                if (height(root.left) > height(root.right)) {
                    // 如果root的左子树比右子树高；
                    // 则(01)找出root的左子树中的最大节点
                    //   (02)将该最大节点的值赋值给root。
                    //   (03)删除该最大节点。
                    // 这类似于用"root的左子树中最大节点"做"root"的替身；
                    // 采用这种方式的好处是：删除"root的左子树中最大节点"之后，AVL树仍然是平衡的。
                    AVLNode<T> max = maximum(root.left);
                    root.data = max.data;
                    root.left = delete(root.left, max);
                } else {
                    // 如果root的左子树不比右子树高(即它们相等，或右子树比左子树高1)
                    // 则(01)找出root的右子树中的最小节点
                    //   (02)将该最小节点的值赋值给root。
                    //   (03)删除该最小节点。
                    // 这类似于用"root的右子树中最小节点"做"root"的替身；
                    // 采用这种方式的好处是：删除"root的右子树中最小节点"之后，AVL树仍然是平衡的。
                    AVLNode<T> min = maximum(root.right);
                    root.data = min.data;
                    root.right = delete(root.right, min);
                }
            } else {
                root = (root.left!=null) ? root.left : root.right;
            }
        }
        return root;
    }
}
