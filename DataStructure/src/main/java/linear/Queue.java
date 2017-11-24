package linear;

import java.lang.reflect.Array;

/**
 * 队列（Queue），是一种线性存储结构。它有以下几个特点：
 * (01) 队列中数据是按照"先进先出（FIFO, First-In-First-Out）"方式进出队列的。
 * (02) 队列只允许在"队首"进行删除操作，而在"队尾"进行插入操作。
 * <p>
 * <p>
 * 队列通常包括的两种操作：入队列 和 出队列。
 * <p>
 * JDK包Queue中的也提供了"队列"的实现。JDK中的Queue接口就是"队列"，它的实现类也都是队列
 */
public class Queue<T> {

    private static final int DEFAULT_SIZE = 10;
    private T[] array;
    private int count;

    public Queue(Class<T> type) {
        this(type, DEFAULT_SIZE);
    }

    public Queue(Class<T> type, int size) {
        // 不能直接使用mArray = new T[DEFAULT_SIZE];
        array = (T[]) Array.newInstance(type, size);
        count = 0;
    }

    // 将val添加到队列的末尾
    public void add(T val) {
        array[count++] = val;
    }

    // 返回“队列开头元素”
    public T front() {
        return array[0];
    }

    public T pop() {
        T ret = array[0];
        count--;
        for (int i = 1; i <= count; i++)
            array[i - 1] = array[i];
        return ret;
    }


    public int size() {
        return count;
    }

    public boolean isEmpty() {
        return size() == 0;
    }


}
