package tree;

public class BinaryTree<T extends Comparable<T>> {

    private BinaryNode<T> root;

    public class BinaryNode<T extends Comparable<T>> {
        T data;
        BinaryNode<T> left;
        BinaryNode<T> right;
        BinaryNode<T> parent;

        public BinaryNode(T data, BinaryNode<T> parent, BinaryNode<T> left, BinaryNode<T> right) {
            this.data = data;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }
    }

    // 前序遍历
    public void preOrder() {
        preOrder(root);
    }

    private void preOrder(BinaryNode<T> tree) {
        if (tree != null) {
            System.out.print(tree.data + " ");
            preOrder(tree.left);
            preOrder(tree.right);
        }
    }

    // 中序遍历
    public void inOrder() {
        inOrder(root);
    }

    private void inOrder(BinaryNode<T> tree) {
        if (tree != null) {
            inOrder(tree.left);
            System.out.print(tree.data + " ");
            inOrder(tree.right);
        }
    }

    // 后序遍历
    public void postOrder() {
        postOrder(root);
    }

    private void postOrder(BinaryNode<T> tree) {
        if (tree != null) {
            postOrder(tree.left);
            postOrder(tree.right);
            System.out.print(tree.data + " ");
        }
    }

    // 查找
    public BinaryNode<T> search(T data) {
        return search(root, data);
    }

    private BinaryNode<T> search(BinaryNode<T> x, T data) {
        if (x == null) {
            return null;
        }
        // 比较
        int compare = data.compareTo(x.data);
        if (compare < 0) {  // 小于当前 查左子树
            return search(x.left, data);
        } else if (compare > 0) {  // 大于当前 查右子树
            return search(x.right, data);
        } else { // 等于当前 返回当前
            return x;
        }
    }

    // 查找最小结点：返回tree为根结点的二叉树的最小结点。
    public T minimum() {
        BinaryNode<T> p = minimum(root);
        if (p != null)
            return p.data;

        return null;
    }

    private BinaryNode<T> minimum(BinaryNode<T> tree) {
        if (tree == null)
            return null;

        while (tree.left != null)
            tree = tree.left;
        return tree;
    }

    //查找最大结点：返回tree为根结点的二叉树的最大结点。
    public T maximum() {
        BinaryNode<T> p = maximum(root);
        if (p != null)
            return p.data;

        return null;
    }

    private BinaryNode<T> maximum(BinaryNode<T> tree) {
        if (tree == null)
            return null;

        while (tree.right != null)
            tree = tree.right;
        return tree;
    }

    // 查找前驱结点 即 查找"二叉树中数据值小于该结点"的"最大结点"
    public BinaryNode<T> predecessor(BinaryNode<T> x) {
        // 如果存在左结点 则以左结点为根的二叉树的最大结点为所求
        if (x.left != null) {
            return maximum(x.left);
        }
        // 如果x没有左孩子 则x的前驱结点有两种可能
        // 1、 x是个右孩子 则所求为x的父节点
        // 2、 x是个左孩子 则查找"x的最低的父结点，并且该父结点要具有右孩子"，找到的这个"最低的父结点"就是"x的前驱结点"
        BinaryNode<T> y = x.parent;
        while (y != null && x == y.left) {
            x = y;
            y = y.parent;
        }
        return y;
    }

    // 找结点(x)的后继结点。即，查找"二叉树中数据值大于该结点"的"最小结点"。

    public BinaryNode<T> successor(BinaryNode<T> x) {
        // 如果x存在右孩子，则"x的后继结点"为 "以其右孩子为根的子树的最小结点"。
        if (x.right != null) {
            return minimum(x.right);
        }
        // 如果x没有右孩子。则x有以下两种可能：
        // 1、 x是"一个左孩子"，则"x的后继结点"为 "它的父结点"。
        // 2、 x是"一个右孩子"，则查找"x的最低的父结点，并且该父结点要具有左孩子"，找到的这个"最低的父结点"就是"x的后继结点"。
        BinaryNode<T> y = x.parent;
        while ((y != null) && (x == y.right)) {
            x = y;
            y = y.parent;
        }
        return y;
    }

    // 插入
    public void insert(T data) {
        insert(this, data);
    }

    private void insert(BinaryTree<T> tree, T data) {
        BinaryNode<T> newNode = new BinaryNode<>(data, null, null, null);
        insert(tree, newNode);
    }

    private void insert(BinaryTree<T> tree, BinaryNode<T> newNode) {
        int cmp;
        BinaryNode<T> y = null;
        BinaryNode<T> x = tree.root;

        // 查找z的插入位置
        while (x != null) {
            y = x;
            cmp = newNode.data.compareTo(x.data);
            if (cmp < 0)
                x = x.left;
            else
                x = x.right;
        }

        newNode.parent = y;
        if (y == null)  // tree的root为空
            tree.root = newNode;
        else {
            cmp = newNode.data.compareTo(y.data);
            if (cmp < 0)
                y.left = newNode;
            else
                y.right = newNode;
        }
    }

    // 删除
    public void delete(T data) {
        delete(this, data);
    }

    private void delete(BinaryTree<T> tree, T data) {
        BinaryNode<T> deleteNode = search(data);
        if (deleteNode != null) {
            delete(tree, deleteNode);
        }
    }

    private void delete(BinaryTree<T> tree, BinaryNode<T> deleteNode) {
        /**
         * 删除策略
         * 1、删除的结点没有还在时，直接删除
         * 2、删除的结点 只有一个孩子时，将孩子替换到删除结点的位置
         *      deleteNode.children.parent = deleteNode.parent
         *      再删除结点
         * 3、既有左孩子又有右孩子，找到删除结点的前驱（predecessor）或者后驱（successor），
         *      将其替换删除结点，再删除结点
         */
        BinaryNode<T> x;
        BinaryNode<T> y;

        if ((deleteNode.left == null) || (deleteNode.right == null))
            y = deleteNode;
        else
            y = successor(deleteNode);

        if (y.left != null)
            x = y.left;
        else
            x = y.right;

        if (x != null)
            x.parent = y.parent;

        if (y.parent == null)
            tree.root = x;
        else if (y == y.parent.left)
            y.parent.left = x;
        else
            y.parent.right = x;

        if (y != deleteNode)
            deleteNode.data = y.data;

//        return y;
//        if (deleteNode.left == null && deleteNode.right == null) {
//            if (deleteNode == tree.root) {  // 是根节点 删除根
//                tree.root = null;
//            } else if (deleteNode == deleteNode.parent.left) {
//                deleteNode.parent.left = null;
//            } else if (deleteNode == deleteNode.parent.right) {
//                deleteNode.parent.right = null;
//            }
//        } else if (deleteNode.left != null && deleteNode.right == null) {  // 没有孩子或者只有一个孩子
//            if (deleteNode == tree.root) {
//                root = deleteNode.left;
//            } else if (deleteNode == deleteNode.parent.left) {
//                deleteNode.parent.left = deleteNode.left;
//                deleteNode.left.parent = deleteNode.parent;
//            } else if (deleteNode == deleteNode.parent.right) {
//                deleteNode.parent.right = deleteNode.left;
//                deleteNode.left.parent = deleteNode.parent;
//            }
//        } else if (deleteNode.left == null) {
//            if (deleteNode == root) {
//                root = deleteNode.right;
//            } else if (deleteNode == deleteNode.parent.left) {
//                deleteNode.parent.left = deleteNode.right;
//                deleteNode.right.parent = deleteNode.parent;
//            } else if (deleteNode == deleteNode.parent.right) {
//                deleteNode.parent.right = deleteNode.right;
//                deleteNode.right.parent = deleteNode.parent;
//            }
//        } else {  // 有双孩子
//            BinaryNode<T> predecessor = predecessor(deleteNode);
//            BinaryNode<T> x;
//            if (predecessor.left != null) {
//                x = predecessor.left;
//            } else {
//                x = predecessor.right;
//            }
//
//            if (x != null) {
//                x.parent = predecessor.parent;
//            }
//            if (predecessor.parent == null) {
//                tree.root = x;
//            } else if (predecessor == predecessor.parent.left) {
//                predecessor.parent.left = x;
//            } else {
//                predecessor.parent.right = x;
//            }
//
//            // 前驱（后驱）替换删除结点
//            predecessor.parent = deleteNode.parent;
//            predecessor.left = deleteNode.left;
//            predecessor.right = deleteNode.right;
//            if (deleteNode.parent != null) {
//                if (deleteNode == deleteNode.parent.left) {
//                    deleteNode.parent.left = predecessor;
//                } else if (deleteNode == deleteNode.parent.right) {
//                    deleteNode.parent.right = predecessor;
//                }
//            }
//        }
    }

    public void clear() {
        destroy(root);
    }

    private void destroy(BinaryNode<T> root) {
        if (root == null) {
            return;
        }

        if (root.left != null) {
            destroy(root.left);
        }

        if (root.right != null) {
            destroy(root.right);
        }
        delete(root.data);
    }

    private void print(BinaryNode<T> tree, T data, int direction) {

        if (tree != null) {
            if (direction == 0)    // tree是根节点
                System.out.printf("%2d is root\n", tree.data);
            else                // tree是分支节点
                System.out.printf("%2d is %2d's %6s child\n",
                        tree.data, data, direction == 1 ? "right" : "left");
            print(tree.left, tree.data, -1);
            print(tree.right, tree.data, 1);
        }
    }

    public void print() {
        if (root != null)
            print(root, root.data, 0);
    }
}


