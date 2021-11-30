package java基础面试题整理.代码.arrayListTest2;

/**
 * @Author Leslie
 * @Date 2021/8/3 21:51
 * @Version 1.0
 */
public class MyArrayList<T> {

    /**
     * 初始默认容量
     */
    private static final int DEFAULT_CAPACITY = 10;
    /**
     * 创建空数组实例。
     */
    private static final Object[] EMPTY_ELEMENTDATA = {};
    /**
     *  数组元素
     */
    transient Object[] elementData;
    /**
     * 长度大小
     */
    private int size;

    public MyArrayList() {
        this.elementData = EMPTY_ELEMENTDATA;
    }

    public MyArrayList(int initCapacity) {
        if (initCapacity > 0){
            this.elementData = new Object[DEFAULT_CAPACITY];
        }else if (initCapacity == 0){
            this.elementData = EMPTY_ELEMENTDATA;
        }else {
            throw new IllegalArgumentException("参数异常");
        }
    }

    public boolean add(T t){
        //判断容量
        ensureCapacity(size + 1);
        elementData[size++] = t;
        return true;
    }
    public boolean add(int index, T t){
        checkArrayRange(index);
        ensureCapacity(size + 1);
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = t;
        size++;
        return true;
    }

    /**
     * 容量计算
     */
    public void ensureCapacity(int minCapacity){

        //如果是初次扩容则使用默认容量
        if (elementData == EMPTY_ELEMENTDATA){
            minCapacity = Math.max(DEFAULT_CAPACITY, minCapacity);
        }
        //判断是否需要扩容
        if (minCapacity - elementData.length > 0){
            int newCapacity = elementData.length + (elementData.length >> 1);
            if (newCapacity - minCapacity < 0){
                newCapacity = minCapacity;
            }
            //将旧数组指向新数组
            Object[] objects = new Object[newCapacity];
            System.arraycopy(elementData, 0, objects, 0, elementData.length);
            //修改引用
            elementData = objects;
        }
    }

    public Object get(int index){
        //判断index是否越界
        checkArrayRange(index);
        return elementData[index];
    }

    private void checkArrayRange(int index) {

        if (index > size || index < 0){
            throw new IndexOutOfBoundsException("下标越界");
        }

    }

    /**
     * 查询相应对象的下标
     * @param obj
     * @return
     */
    public int getIndex(Object obj){
        if (obj == null){
            for (int i = 0; i < size; i++){
                if (elementData[i] == null){
                    return i;
                }
            }
        }else {
           for (int i = 0; i < size; i++){
                if (obj.equals(elementData[i])){
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * 根据下标移除元素
     * @param index 下标位置
     * @return
     */
    public Object remove(int index){
        checkArrayRange(index);
        Object old = elementData[index];
        //计算要删除的位置后面有几个元素
        int moved = size - index -1;
        if (moved > 0){
            System.arraycopy(elementData, index+1, elementData, 0, moved);
        }
        elementData[--size] = null;
        return old;
    }

    public int size(){
        return this.size;
    }



}
