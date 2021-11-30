package java基础面试题整理.代码.DeadLockDemo;

/**
 * @Author Leslie
 * @Description 处理死锁方法，调整范围
 * @Date 2021/8/14 8:18
 * @Version 1.0
 */
public class DellDeadLockDemo {

    private static String lockA = "lockA";
    private static String lockB = "lockB";

    public void methodA() throws Exception {
        synchronized (lockA) {
            System.out.println("我是锁A获取lockA" + Thread.currentThread().getName());
            Thread.sleep(2000);
        }
        synchronized (lockB) {
            System.out.println("我是锁A获取锁lockB" + Thread.currentThread().getName());
        }
    }

    public void methodB() throws Exception {
        System.out.println("我是锁B获取lockB" + Thread.currentThread().getName());
        synchronized (lockB) {
            Thread.sleep(2000);
        }
        synchronized (lockA) {
            System.out.println("我是锁B获取锁lockA" + Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) {
        DellDeadLockDemo deadLockDemo = new DellDeadLockDemo();
        System.out.println("主线程开始运行->" + Thread.currentThread().getName());
        new Thread(()->{
            try {
                deadLockDemo.methodA();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(()->{
            try {
                deadLockDemo.methodB();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
        System.out.println("主线程运行结束->" + Thread.currentThread().getName());
    }
}
