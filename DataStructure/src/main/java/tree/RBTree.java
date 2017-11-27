package tree;

/**
 * R-B Tree，全称是Red-Black Tree，又称为“红黑树”，它一种特殊的二叉查找树。
 * <p>
 * 红黑树的特性:
 * （1）每个结点或者是黑色，或者是红色。
 * （2）根结点是黑色。
 * （3）每个叶子结点（NIL）是黑色。 [注意：这里叶子结点，是指为空(NIL或NULL)的叶子结点！]
 * （4）如果一个结点是红色的，则它的子结点必须是黑色的。
 * （5）从一个结点到该结点的子孙结点的所有路径上包含相同数目的黑结点。
 * <p>
 * 注意：
 * (01) 特性(3)中的叶子结点，是只为空(NIL或null)的结点。
 * (02) 特性(5)，确保没有一条路径会比其他路径长出俩倍。因而，红黑树是相对是接近平衡的二叉树。
 * <p>
 * 红黑树的应用比较广泛，主要是用它来存储有序的数据，它的时间复杂度是O(lgn)。
 * 如 ： Java集合中的TreeSet和TreeMap
 *
 * @param <T>
 */
public class RBTree<T extends Comparable<T>> {

    private RBTNode<T> root;

    private static final boolean RED = false;
    private static final boolean BLACK = true;

    //红黑树结点
    public class RBTNode<T extends Comparable<T>> {
        boolean color;  // 颜色
        T data; // 关键字（结点数据）
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
        return (node != null) && (node.color == RED);
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

    //前序遍历"红黑树"
    private void preOrder(RBTNode<T> tree) {
        if (tree != null) {
            System.out.print(tree.data + " ");
            preOrder(tree.left);
            preOrder(tree.right);
        }
    }

    public void preOrder() {
        preOrder(root);
    }

    //中序遍历"红黑树"
    private void inOrder(RBTNode<T> tree) {
        if (tree != null) {
            inOrder(tree.left);
            System.out.print(tree.data + " ");
            inOrder(tree.right);
        }
    }

    public void inOrder() {
        inOrder(root);
    }


    //后序遍历"红黑树"
    private void postOrder(RBTNode<T> tree) {
        if (tree != null) {
            postOrder(tree.left);
            postOrder(tree.right);
            System.out.print(tree.data + " ");
        }
    }

    public void postOrder() {
        postOrder(root);
    }

    private RBTNode<T> search(RBTNode<T> x, T data) {
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

    public RBTNode<T> search(T data) {
        return search(root, data);
    }


    // 左旋
    private void leftRotate(RBTNode<T> x) {
        RBTNode<T> y = x.right;  // 获取左旋结点右孩子
        //将y的设置到x位置
        y.parent = x.parent;
        if (x.parent == null) {
            this.root = y;
        } else {
            if (x == x.parent.left) {
                x.parent.left = y;
            } else {
                x.parent.right = y;
            }
        }
        // 设置y的左孩子为x的右孩子
        x.right = y.left;
        if (y.left != null) {
            y.left.parent = x;
        }
        // 设置x为y的左孩子
        x.parent = y;
        y.left = x;
    }

    // 右旋 (和左旋是对称的)
    private void rightRotate(RBTNode<T> y) {
        RBTNode<T> x = y.left;  // 获取右旋结点左孩子
        //将x的设置到y位置
        x.parent = y.parent;
        if (y.parent == null) {
            this.root = x;
        } else {
            if (y == y.parent.left) {
                y.parent.left = x;
            } else {
                y.parent.right = x;
            }
        }
        // 设置x的右孩子为y的左孩子
        y.left = x.right;
        if (x.right != null) {
            x.right.parent = y;
        }
        // 设置y为x的右孩子
        y.parent = x;
        x.right = y;
    }

    // 查找最小结点：返回tree为根结点的二叉树的最小结点。
    public T minimum() {
        RBTNode<T> p = minimum(root);
        if (p != null)
            return p.data;

        return null;
    }

    private RBTNode<T> minimum(RBTNode<T> tree) {
        if (tree == null)
            return null;

        while (tree.left != null)
            tree = tree.left;
        return tree;
    }

    //查找最大结点：返回tree为根结点的二叉树的最大结点。
    public T maximum() {
        RBTNode<T> p = maximum(root);
        if (p != null)
            return p.data;

        return null;
    }

    private RBTNode<T> maximum(RBTNode<T> tree) {
        if (tree == null)
            return null;

        while (tree.right != null)
            tree = tree.right;
        return tree;
    }


    // 查找前驱结点 即 查找"二叉树中数据值小于该结点"的"最大结点"
    public RBTNode<T> predecessor(RBTNode<T> x) {
        // 如果存在左结点 则以左结点为根的二叉树的最大结点为所求
        if (x.left != null) {
            return maximum(x.left);
        }
        // 如果x没有左孩子 则x的前驱结点有两种可能
        // 1、 x是个右孩子 则所求为x的父结点
        // 2、 x是个左孩子 则查找"x的最低的父结点，并且该父结点要具有右孩子"，找到的这个"最低的父结点"就是"x的前驱结点"
        RBTNode<T> y = x.parent;
        while (y != null && x == y.left) {
            x = y;
            y = y.parent;
        }
        return y;
    }

    // 找结点(x)的后继结点。即，查找"二叉树中数据值大于该结点"的"最小结点"。

    public RBTNode<T> successor(RBTNode<T> x) {
        // 如果x存在右孩子，则"x的后继结点"为 "以其右孩子为根的子树的最小结点"。
        if (x.right != null) {
            return minimum(x.right);
        }
        // 如果x没有右孩子。则x有以下两种可能：
        // 1、 x是"一个左孩子"，则"x的后继结点"为 "它的父结点"。
        // 2、 x是"一个右孩子"，则查找"x的最低的父结点，并且该父结点要具有左孩子"，找到的这个"最低的父结点"就是"x的后继结点"。
        RBTNode<T> y = x.parent;
        while ((y != null) && (x == y.right)) {
            x = y;
            y = y.parent;
        }
        return y;
    }

    public void insert(T data) {
        insert(this, data);
    }

    private void insert(RBTree<T> tree, T data) {
        RBTNode<T> newNode = new RBTNode<>(data, RED, null, null, null);
        insert(tree, newNode);
    }

    /**
     * @param tree
     * @param z    插入的新结点
     */
    private void insert(RBTree<T> tree, RBTNode<T> z) {
        RBTNode<T> y = null;  // 用于表示z的父结点
        RBTNode<T> x = tree.root; // 获取树的根结点，用于找出z结点插入位置
        boolean isLeft = false;
        while (x != null) {
            y = x;
            int compare = z.data.compareTo(x.data);
            if (compare < 0) {
                x = x.left;
                isLeft = true;
            } else {
                x = x.right;
                isLeft = false;
            }
        }
        z.parent = y; // 设置y为z的父结点
        if (y == null) { // y是空结点，则标识x也是空的，即tree是空树
            tree.root = z; // 设置z为树的根结点
        } else {
//            int compare = z.data.compareTo(y.data);  // 和上面比较重复了
            if (isLeft) {
                y.left = z;
            } else {
                y.right = z;
            }
        }
        insertFixUp(z);  // 添加修正
    }

    /**
     * 添加结点修正树
     * <p>
     * 根据被插入结点情况，划分为三种情况来处理。
     * 1、被插入的结点是根结点
     * 直接把此结点涂黑
     * <p>
     * 2、被插入的结点的父结点是黑色
     * 不用处理，因为插入一个红色结点不影响红黑树结构
     * <p>
     * 3、被插入的结点的父结点是红色
     * 发生冲突！要修正颜色
     * 根据叔结点（插入结点的祖父结点的非父子结点，红黑树定义null结点算黑色结点）情况
     * <p>
     * 3.1、叔结点红色
     * (01) 将“父结点”设为黑色。
     * (02) 将“叔叔结点”设为黑色。
     * (03) 将“祖父结点”设为“红色”。
     * (04) 将“祖父结点”设为“当前结点”(红色结点)；即，之后继续对“当前结点”进行操作。
     * <p>
     * 3.2、叔结点黑色，且插入结点为父结点右孩子
     * (01) 将“父结点”作为“新的当前结点”。
     * (02) 以“新的当前结点”为支点进行左旋。
     * <p>
     * 3.3、叔结点黑色，且插入结点为父结点左孩子
     * (01) 将“父结点”设为“黑色”。
     * (02) 将“祖父结点”设为“红色”。
     * (03) 以“祖父结点”为支点进行右旋。
     *
     * @param z 插入结点
     */
    private void insertFixUp(RBTNode<T> z) {
        RBTNode<T> parent;
        RBTNode<T> gparent;
        RBTNode<T> uncle;
        // 3 条件 若“父结点存在，并且父结点的颜色是红色”
        while (((parent = parentOf(z)) != null) && isRed(parent)) {
            gparent = parentOf(parent);
            //若“父结点”是“祖父结点的左孩子”
            if (parent == gparent.left) {
                // 3.1条件：叔叔结点是红色
                uncle = gparent.right;
                if ((uncle != null) && isRed(uncle)) {
                    setBlack(uncle);
                    setBlack(parent);
                    setRed(gparent);
                    z = gparent;
                    continue;
                }
                // 3.2条件：叔叔是黑色，且当前结点是右孩子
                if (parent.right == z) {
                    RBTNode<T> tmp;
                    leftRotate(parent);
                    tmp = parent;
                    parent = z;
                    z = tmp;
                }
                //3.3条件：叔叔是黑色，且当前结点是左孩子。
                setBlack(parent);
                setRed(gparent);
                rightRotate(gparent);
            } else {    //若“z的父结点”是“z的祖父结点的右孩子”
                // 3.1条件：叔叔结点是红色
                uncle = gparent.left;
                if ((uncle != null) && isRed(uncle)) {
                    setBlack(uncle);
                    setBlack(parent);
                    setRed(gparent);
                    z = gparent;
                    continue;
                }
                // 3.2条件：叔叔是黑色，且当前结点是左孩子
                if (parent.left == z) {
                    RBTNode<T> tmp;
                    rightRotate(parent);
                    tmp = parent;
                    parent = z;
                    z = tmp;
                }
                // 3.3条件：叔叔是黑色，且当前结点是右孩子。
                setBlack(parent);
                setRed(gparent);
                leftRotate(gparent);
            }
        }
        // 将根结点设为黑色
        setBlack(root);
    }

    // 删除结点
    public void delete(T data) {
        RBTNode<T> deleteNode = search(data);
        if (deleteNode != null) {
            delete(deleteNode);
        }
    }

    private void delete(RBTNode<T> deleteNode) {
        RBTNode<T> child, parent;
        boolean color;
        // 被删除结点的"左右孩子都不为空"的情况。
        if ((deleteNode.left != null) && (deleteNode.right != null)) {
            // 被删结点的后继结点。(称为"取代结点")
            RBTNode<T> replace = deleteNode;
            // 获取后继节点
            replace = replace.right;
            while (replace.left != null)
                replace = replace.left;

            if (parentOf(deleteNode) != null) {
                if (parentOf(deleteNode).left == deleteNode)
                    parentOf(deleteNode).left = replace;
                else
                    parentOf(deleteNode).right = replace;
            } else {
                // "deleteNode结点"是根结点，更新根结点。
                this.root = replace;
            }
            // child是"取代结点"的右孩子，也是需要"调整的结点"。
            // "取代结点"肯定不存在左孩子！因为它是一个后继结点。
            child = replace.right;
            parent = parentOf(replace);
            // 保存"取代结点"的颜色
            color = colorOf(replace);
            // "被删除结点"是"它的后继结点的父结点"
            if (parent == deleteNode) {
                parent = replace;
            } else {
                // child不为空
                if (child != null)
                    setParent(child, parent);
                parent.left = child;
                replace.right = deleteNode.right;
                setParent(deleteNode.right, replace);
            }
            replace.parent = deleteNode.parent;
            replace.color = deleteNode.color;
            replace.left = deleteNode.left;
            deleteNode.left.parent = replace;
            if (color == BLACK)
                removeFixUp(child, parent);
            return;
        }
        if (deleteNode.left != null) {
            child = deleteNode.left;
        } else {
            child = deleteNode.right;
        }
        parent = deleteNode.parent;
        // 保存"取代结点"的颜色
        color = deleteNode.color;
        if (child != null)
            child.parent = parent;
        // "deleteNode结点"不是根结点
        if (parent != null) {
            if (parent.left == deleteNode)
                parent.left = child;
            else
                parent.right = child;
        } else {
            this.root = child;
        }
        if (color == BLACK) {
            removeFixUp(child, parent);
        }
    }

    /**
     * 删除修正
     * <p>
     * 1、node是“红+黑”节点
     * 直接把x设为黑色，结束。此时红黑树性质全部恢复。
     * <p>
     * 2、node是“黑+黑”节点，且node是根。
     * 什么都不做，结束。此时红黑树性质全部恢复。
     * <p>
     * 3、node是“黑+黑”节点，且node不是根。
     * 这种情况又可以划分为4种子情况。
     * <p>
     * 3.1、node是"黑+黑"节点，node的兄弟节点是红色。(此时node的父节点和node的兄弟节点的子节点都是黑节点)
     * 3.2、node是“黑+黑”节点，node的兄弟节点是黑色，node的兄弟节点的两个孩子都是黑色。
     * 3.3、node是“黑+黑”节点，node的兄弟节点是黑色；node的兄弟节点的左孩子是红色，右孩子是黑色的。
     * 3.4、node是“黑+黑”节点，node的兄弟节点是黑色；node的兄弟节点的右孩子是红色的，node的兄弟节点的左孩子任意颜色。
     *
     * @param node
     * @param parent
     */
    private void removeFixUp(RBTNode<T> node, RBTNode<T> parent) {
        RBTNode<T> other;
        while ((node == null || isBlack(node)) && (node != this.root)) {
            if (parent.left == node) {
                other = parent.right;
                if (isRed(other)) {
                    // 3.1、node是"黑+黑"节点，node的兄弟节点是红色。
                    setBlack(other);
                    setRed(parent);
                    leftRotate(parent);
                    other = parent.right;
                }
                if ((other.left == null || isBlack(other.left)) &&
                        (other.right == null || isBlack(other.right))) {
                    // 3.2、node是“黑+黑”节点，node的兄弟节点是黑色
                    setRed(other);
                    node = parent;
                    parent = parentOf(node);
                } else {
                    if (other.right == null || isBlack(other.right)) {
                        // 3.3、node是“黑+黑”节点，node的兄弟节点是黑色；node的兄弟节点的左孩子是红色，右孩子是黑色的。
                        setBlack(other.left);
                        setRed(other);
                        rightRotate(other);
                        other = parent.right;
                    }
                    // 3.4、node是“黑+黑”节点，node的兄弟节点是黑色；node的兄弟节点的右孩子是红色的，node的兄弟节点的左孩子任意颜色。
                    setColor(other, colorOf(parent));
                    setBlack(parent);
                    setBlack(other.right);
                    leftRotate(parent);
                    node = this.root;
                    break;
                }
            } else {
                other = parent.left;
                if (isRed(other)) {
                    // 3.1、node是"黑+黑"节点，node的兄弟节点是红色。
                    setBlack(other);
                    setRed(parent);
                    rightRotate(parent);
                    other = parent.left;
                }
                if ((other.left == null || isBlack(other.left)) &&
                        (other.right == null || isBlack(other.right))) {
                    // 3.2、node是“黑+黑”节点，node的兄弟节点是黑色
                    setRed(other);
                    node = parent;
                    parent = parentOf(node);
                } else {
                    if (other.left == null || isBlack(other.left)) {
                        // 3.3、node是“黑+黑”节点，node的兄弟节点是黑色；node的兄弟节点的左孩子是红色，右孩子是黑色的。
                        setBlack(other.right);
                        setRed(other);
                        leftRotate(other);
                        other = parent.left;
                    }
                    // 3.4、node是“黑+黑”节点，node的兄弟节点是黑色；node的兄弟节点的右孩子是红色的，node的兄弟节点的左孩子任意颜色。
                    setColor(other, colorOf(parent));
                    setBlack(parent);
                    setBlack(other.left);
                    rightRotate(parent);
                    node = this.root;
                    break;
                }
            }
        }
        if (node != null)
            setBlack(node);
    }

    /*
     * 销毁红黑树
     */
    private void destroy(RBTNode<T> tree) {
        if (tree == null)
            return;
        if (tree.left != null)
            destroy(tree.left);
        if (tree.right != null)
            destroy(tree.right);
        delete(tree.data);
    }

    public void clear() {
        destroy(root);
    }

    /**
     * 打印"红黑树"
     * <p>
     * key        -- 节点的键值
     * direction  --  0，表示该节点是根节点;
     * -1，表示该节点是它的父结点的左孩子;
     * 1，表示该节点是它的父结点的右孩子。
     */
    private void print(RBTNode<T> tree, T data, int direction) {
        if (tree != null) {
            if (direction == 0)    // tree是根节点
                System.out.printf("%2d(B) is root\n", tree.data);
            else                // tree是分支节点
                System.out.printf("%2d(%s) is %2d's %6s child\n", tree.data, isRed(tree) ? "R" : "B", data, direction == 1 ? "right" : "left");
            print(tree.left, tree.data, -1);
            print(tree.right, tree.data, 1);
        }
    }

    public void print() {
        if (root != null)
            print(root, root.data, 0);
    }
}
