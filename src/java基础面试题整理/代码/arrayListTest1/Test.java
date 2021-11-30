package java基础面试题整理.代码.arrayListTest1;

/**
 * @Author Leslie
 * @Date 2021/8/2 21:04
 * @Version 1.0
 */
public class Test {


    public static void main(String[] args) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("aaa");
        arrayList.add("bbb");
        arrayList.add("ccc");

        // arrayList.add(1,"ddd");
        //
        // Object o = arrayList.get(2);
        // System.out.println("===>o:" + o);
        //
        // Object remove = arrayList.remove(2);
        // System.out.println("remove:" + remove);

        arrayList.clear();

        String foreach = arrayList.foreach();

        System.out.println(foreach);

    }


}
