package java基础面试题整理.代码.arrayListTest1;

/**
 * @Author Leslie
 * @Date 2021/8/1 11:27
 * @Version 1.0
 */
public class ArrayList implements List{

    /**
     * 初始默认容量
     */
    private static final int DEFAULT_CAPACITY = 10;
    /**
     * 创建空数组实例。
     */
    private static final Object[] EMPTY_ELEMENTDATA = {};
    /**
     * 默认容量空元素数据
     */
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};

    /**
     *  数组元素
     */
    transient Object[] elementData;
    /**
     * 长度大小
     */
    private int size;

    public ArrayList() {
        //初始化容量
        this.elementData = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public boolean add(Object obj) {
        //扩容
        add(size, obj);
        return true;
    }

    /**
     * 根据下标插入
     * @param index 下标
     * @param obj 元素
     * @return
     */
    @Override
    public void add(int index, Object obj) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        //扩容
        addCapacity();
        for (int i = size; i > index; i--){
            elementData[i] = elementData[i-1];
        }
        elementData[index] = obj;
        size++;
    }

    /**
     * 扩容机制
     */
    public void addCapacity(){
        if (size < elementData.length){
            return;
        }
        int newCapacity = elementData.length + (elementData.length >> 1);
        Object[] newElementData = new Object[newCapacity];
        for (int i = 0; i < size; i++){
            newElementData[i] = elementData[i];
        }
        elementData = newElementData;
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if (elementData.length == 0){
            return true;
        }
        return false;
    }

    @Override
    public Object get(int index) {
        return elementData[index];
    }

    @Override
    public Object remove(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        Object old = elementData[index];
        for (int i = index; i < size; i++){
            elementData[i] = elementData[i+1];
        }
        size--;
        return old;
    }

    @Override
    public void clear() {
        final Object[] es = elementData;
        for (int to = size, i = size = 0; i < to; i++)
            es[i] = null;
    }

    public String foreach(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("size:" + size + "====>");
        for (int i = 0; i < size; i++){
            stringBuilder.append(elementData[i] + "  ");
        }
        return stringBuilder.toString();
    }


}
