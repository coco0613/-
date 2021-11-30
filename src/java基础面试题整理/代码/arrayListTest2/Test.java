package java基础面试题整理.代码.arrayListTest2;

/**
 * @Author Leslie
 * @Date 2021/8/4 21:29
 * @Version 1.0
 */
public class Test {



    public static void main(String[] args) {
        MyArrayList list = new MyArrayList();
        for (int i = 0; i<15; i++){
            list.add(""+i);
        }

        list.add(2,555);

        for (int i = 0;i < list.size(); i++){
            System.out.println(list.get(i));
        }


    }

}
