package tree;

/**
 * 伸展树(Splay Tree)是特殊的二叉查找树。
 * 它的特殊是指，它除了本身是棵二叉查找树之外，它还具备一个特点:
 * 当某个节点被访问时，伸展树会通过旋转使该节点成为树根。
 * 这样做的好处是，下次要访问该节点时，能够迅速的访问到该节点。
 */
/**
 * Java 语言: 伸展树
 *
 * @author skywang
 * @date 2014/02/03
 */

public class SplayTree<T extends Comparable<T>> {

    private SplayNode<T> root;    // 根结点

    public class SplayNode<T extends Comparable<T>> {
        T data;                // 关键字(键值)
        SplayNode<T> left;    // 左孩子
        SplayNode<T> right;    // 右孩子

        public SplayNode() {
            this.left = null;
            this.right = null;
        }

        public SplayNode(T data, SplayNode<T> left, SplayNode<T> right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    public SplayTree() {
        root=null;
    }

    /*
     * 前序遍历"伸展树"
     */
    private void preOrder(SplayNode<T> tree) {
        if(tree != null) {
            System.out.print(tree.data+" ");
            preOrder(tree.left);
            preOrder(tree.right);
        }
    }

    public void preOrder() {
        preOrder(root);
    }

    /*
     * 中序遍历"伸展树"
     */
    private void inOrder(SplayNode<T> tree) {
        if(tree != null) {
            inOrder(tree.left);
            System.out.print(tree.data+" ");
            inOrder(tree.right);
        }
    }

    public void inOrder() {
        inOrder(root);
    }


    /*
     * 后序遍历"伸展树"
     */
    private void postOrder(SplayNode<T> tree) {
        if(tree != null)
        {
            postOrder(tree.left);
            postOrder(tree.right);
            System.out.print(tree.data+" ");
        }
    }

    public void postOrder() {
        postOrder(root);
    }

    /*
     * (非递归实现)查找"伸展树x"中键值为data的节点
     */
    private SplayNode<T> iterativeSearch(SplayNode<T> x, T data) {
        while (x!=null) {
            int cmp = data.compareTo(x.data);
            if (cmp < 0)
                x = x.left;
            else if (cmp > 0)
                x = x.right;
            else
                return x;
        }
        return x;
    }

    public SplayNode<T> iterativeSearch(T data) {
        return iterativeSearch(root, data);
    }

    /*
     * 查找最小结点：返回tree为根结点的伸展树的最小结点。
     */
    private SplayNode<T> minimum(SplayNode<T> tree) {
        if (tree == null)
            return null;

        while(tree.left != null)
            tree = tree.left;
        return tree;
    }

    public T minimum() {
        SplayNode<T> p = minimum(root);
        if (p != null)
            return p.data;

        return null;
    }

    /*
     * 查找最大结点：返回tree为根结点的伸展树的最大结点。
     */
    private SplayNode<T> maximum(SplayNode<T> tree) {
        if (tree == null)
            return null;

        while(tree.right != null)
            tree = tree.right;
        return tree;
    }

    public T maximum() {
        SplayNode<T> p = maximum(root);
        if (p != null)
            return p.data;

        return null;
    }

    /*
     * 旋转data对应的节点为根节点，并返回根节点。
     *
     * 注意：
     *   1、伸展树中存在"键值为data的节点"。
     *          将"键值为data的节点"旋转为根节点。
     *   2、伸展树中不存在"键值为data的节点"，并且data < tree.data。
     *      2.1、"键值为data的节点"的前驱节点存在的话，将"键值为data的节点"的前驱节点旋转为根节点。
     *      2.2、"键值为data的节点"的前驱节点不存在的话，则意味着，data比树中任何键值都小，那么此时，将最小节点旋转为根节点。
     *   3、伸展树中不存在"键值为data的节点"，并且data > tree.data。
     *      3.1、 "键值为data的节点"的后继节点存在的话，将"键值为data的节点"的后继节点旋转为根节点。
     *      3.2、 "键值为data的节点"的后继节点不存在的话，则意味着，data比树中任何键值都大，那么此时，将最大节点旋转为根节点。
     */
    private SplayNode<T> splay(SplayNode<T> root, T data) {
        if (root == null)
            return null;

        SplayNode<T> N = new SplayNode<>();
        SplayNode<T> l = N;
        SplayNode<T> r = N;
        SplayNode<T> c;

        for (;;) {
            int cmp = data.compareTo(root.data);
            if (cmp < 0) {
                if (root.left == null)
                    break;
                if (data.compareTo(root.left.data) < 0) {
                    c = root.left;                           /* rotate right */
                    root.left = c.right;
                    c.right = root;
                    root = c;
                    if (root.left == null)
                        break;
                }
                r.left = root;                               /* link right */
                r = root;
                root = root.left;
            } else if (cmp > 0) {
                if (root.right == null)
                    break;
                if (data.compareTo(root.right.data) > 0) {
                    c = root.right;                          /* rotate left */
                    root.right = c.left;
                    c.left = root;
                    root = c;
                    if (root.right == null)
                        break;
                }
                l.right = root;                              /* link left */
                l = root;
                root = root.right;
            } else {
                break;  // 当前根的值为data
            }
        }
        l.right = root.left;                                /* assemble */
        r.left = root.right;
        root.left = N.right;
        root.right = N.left;
        return root;
    }

    public void splay(T data) {
        root = splay(root, data);
    }

    /*
     * 将结点插入到伸展树中，并返回根节点
     *
     * 参数说明：
     *     tree 伸展树的
     *     z 插入的结点
     */
    private SplayNode<T> insert(SplayNode<T> tree, SplayNode<T> z) {
        int cmp;
        SplayNode<T> y = null;
        SplayNode<T> x = tree;

        // 查找z的插入位置
        while (x != null) {
            y = x;
            cmp = z.data.compareTo(x.data);
            if (cmp < 0)
                x = x.left;
            else if (cmp > 0)
                x = x.right;
            else {
                System.out.println("不允许插入相同节点!" +  z.data.toString());
                return tree;
            }
        }

        if (y==null)
            tree = z;
        else {
            cmp = z.data.compareTo(y.data);
            if (cmp < 0)
                y.left = z;
            else
                y.right = z;
        }

        return tree;
    }

    public void insert(T data) {
        SplayNode<T> z=new SplayNode<T>(data,null,null);

        // 插入节点
        root = insert(root, z);
        // 将节点(data)旋转为根节点
        root = splay(root, data);
    }

    /*
     * 删除结点(z)，并返回被删除的结点
     *
     * 参数说明：
     *     bst 伸展树
     *     z 删除的结点
     */
    private SplayNode<T> remove(SplayNode<T> tree, T data) {
        SplayNode<T> x;

        if (tree == null)
            return null;

        // 查找键值为data的节点，找不到的话直接返回。
        if (iterativeSearch(tree, data) == null)
            return tree;

        // 将data对应的节点旋转为根节点。
        tree = splay(tree, data);

        if (tree.left != null) {
            // 将"tree的前驱节点"旋转为根节点
            x = splay(tree.left, data);
            // 移除tree节点
            x.right = tree.right;
        }
        else
            x = tree.right;
        return x;
    }

    public void remove(T data) {
        root = remove(root, data);
    }

    /*
     * 销毁伸展树
     */
    private void destroy(SplayNode<T> tree) {
        if (tree==null)
            return ;
        if (tree.left != null)
            destroy(tree.left);
        if (tree.right != null)
            destroy(tree.right);

        remove(tree.data);
    }

    public void clear() {
        destroy(root);
        root = null;
    }

    private void print(SplayNode<T> tree, T data, int direction) {

        if(tree != null) {

            if(direction==0)    // tree是根节点
                System.out.printf("%2d is root\n", tree.data);
            else                // tree是分支节点
                System.out.printf("%2d is %2d's %6s child\n", tree.data, data, direction==1?"right" : "left");

            print(tree.left, tree.data, -1);
            print(tree.right,tree.data,  1);
        }
    }

    public void print() {
        if (root != null)
            print(root, root.data, 0);
    }
}
