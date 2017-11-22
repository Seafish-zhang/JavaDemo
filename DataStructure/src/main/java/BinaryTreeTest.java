import tree.BinaryTree;

public class BinaryTreeTest {

    private static final int arr[] = {5, 3, 4, 7, 2, 9, 6, 8};

    public static void main(String[] args) {
        BinaryTree<Integer> tree = new BinaryTree<>();
        System.out.println("添加");
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            System.out.print(arr[i] + " ");
            tree.insert(arr[i]);
        }

        System.out.print("\n== 前序遍历: ");
        tree.preOrder();

        System.out.print("\n== 中序遍历: ");
        tree.inOrder();

        System.out.print("\n== 后序遍历: ");
        tree.postOrder();
        System.out.println();

//        System.out.println("== 最小值: " + tree.minimum());
//        System.out.println("== 最大值: " + tree.maximum());
//        System.out.println("== 树的详细信息: ");
//        tree.print();
        tree.print();
        System.out.print("\n== 删除根节点: " + 7);
        tree.delete(7);
        System.out.println();
        tree.print();
        System.out.println();
//        for (int j = 0 ;j < len; j++) {
//            System.out.print("\n== 删除根节点: " + arr[j]);
//            tree.delete(arr[j]);
//            System.out.println();
//            tree.print();
//            System.out.println();
//        }
//        System.out.print("\n== 删除根节点: " + arr[3]);
//        tree.delete(arr[3]);


        // 销毁二叉树
        tree.clear();
        tree.print();
    }
}
