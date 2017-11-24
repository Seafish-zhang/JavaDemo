package linear;

/**
 * 双向链表(双链表)是链表的一种。
 * 和单链表一样，双链表也是由节点组成，
 * 它的每个数据结点中都有两个指针，
 * 分别指向直接后继和直接前驱。
 * 一般我们都构造双向循环链表。
 * <p>
 * 注：java自带的集合包中有实现双向链表，路径是:java.util.LinkedList
 */
public class DoubleLink<T> {
    private DNode<T> head;
    private int count;


    private class DNode<T> {
        private DNode<T> prev;
        private DNode<T> next;
        public T data;

        public DNode(T data, DNode<T> prev, DNode<T> next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
    }

    // 初始化
    public DoubleLink() {
        // 表头不存储数据
        head = new DNode<T>(null, null, null);
        head.prev = head.next = head;
        count = 0;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public T get(int index) {
        return getNode(index).data;
    }

    private DNode<T> getNode(int index) {
        if (index < 0 || index >= count)
            throw new IndexOutOfBoundsException();
        // 正向查找
        if (index <= count / 2) {
            DNode<T> node = head.next;
            for (int i = 0; i < index; i++)
                node = node.next;
            return node;
        }
        // 反向查找
        DNode<T> rnode = head.prev;
        int rindex = count - index - 1;
        for (int j = 0; j < rindex; j++)
            rnode = rnode.prev;
        return rnode;
    }

    // 获取第1个节点的值
    public T getFirst() {
        return getNode(0).data;
    }

    // 获取最后一个节点的值
    public T getLast() {
        return getNode(count - 1).data;
    }

    // 将节点插入到第index位置之前
    public void insert(int index, T t) {
        if (index == 0) {
            DNode<T> node = new DNode<T>(t, head, head.next);
            head.next.prev = node;
            head.next = node;
            count++;
            return;
        }

        DNode<T> inode = getNode(index);
        DNode<T> tnode = new DNode<T>(t, inode.prev, inode);
        inode.prev.next = tnode;
        inode.next = tnode;
        count++;
    }

    // 将节点插入第一个节点处。
    public void insertFirst(T t) {
        insert(0, t);
    }

    // 将节点追加到链表的末尾
    public void appendLast(T t) {
        DNode<T> node = new DNode<T>(t, head.prev, head);
        head.prev.next = node;
        head.prev = node;
        count++;
    }

    // 删除index位置的节点
    public void del(int index) {
        DNode<T> inode = getNode(index);
        inode.prev.next = inode.next;
        inode.next.prev = inode.prev;
        inode = null;
        count--;
    }

    // 删除第一个节点
    public void deleteFirst() {
        del(0);
    }

    // 删除最后一个节点
    public void deleteLast() {
        del(count - 1);
    }
}
