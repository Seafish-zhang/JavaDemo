package linear;

import java.lang.reflect.Array;

/**
 * 栈（stack），是一种线性存储结构，它有以下几个特点：
 * (01) 栈中数据是按照"后进先出（LIFO, Last In First Out）"方式进出栈的。
 * (02) 向栈中添加/删除数据时，只能从栈顶进行操作。
 * <p>
 * 栈通常包括的三种操作：push、peek、pop。
 * push -- 向栈中添加元素。
 * peek -- 返回栈顶元素。
 * pop  -- 返回并删除栈顶元素的操作。
 * <p>
 * JDK包中也提供了"栈"的实现，它就是集合框架中的Stack类。
 */
public class Stack<T> {

    private static final int DEFAULT_SIZE = 10;
    private T[] mArray;
    private int count;

    public Stack(Class<T> type) {
        this(type, DEFAULT_SIZE);
    }

    public Stack(Class<T> type, int size) {
        // 不能直接使用mArray = new T[DEFAULT_SIZE];
        mArray = (T[]) Array.newInstance(type, size);
        count = 0;
    }

    // 将val添加到栈中
    public void push(T val) {
        mArray[count++] = val;
    }

    // 返回“栈顶元素值”
    public T peek() {
        return mArray[count - 1];
    }

    // 返回“栈顶元素值”，并删除“栈顶元素”
    public T pop() {
        T ret = mArray[count - 1];
        count--;
        return ret;
    }

    // 返回“栈”的大小
    public int size() {
        return count;
    }

    // 返回“栈”是否为空
    public boolean isEmpty() {
        return size() == 0;
    }

}
