package java基础面试题整理;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author Leslie
 * @Date 2021/7/31 19:59
 * @Version 1.0
 */
public class Main {
    public static void main(String[] args) {

        String a = "String";

        for (int i = 0; ; i++) {
            a += a;
            System.out.println("第" + i + "次打印->" + a);
        }

    }
}
