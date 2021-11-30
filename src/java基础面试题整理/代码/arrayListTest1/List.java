package java基础面试题整理.代码.arrayListTest1;

/**
 * @Author Leslie
 * @Date 2021/8/1 11:28
 * @Version 1.0
 */
public interface List {

    boolean add(Object obj);

    void add(int index, Object obj);

    int size();

    boolean isEmpty();

    Object get(int index);

    Object remove(int index);

    void clear();

}
