package com.wp.ThreadSafety;

/**
 * @Classname MultiThreadsDeadLockError
 * @Description 死锁
 * @Date 2020/6/17 21:06
 * @Created by wangpeng116
 */
public class MultiThreadsDeadLockError implements Runnable {
    int flag = 1;
    static Object o1 = new Object();
    static Object o2 = new Object();

    public static void main(String[] args) {
        MultiThreadsDeadLockError r1 = new MultiThreadsDeadLockError();
        MultiThreadsDeadLockError r2 = new MultiThreadsDeadLockError();
        r1.flag = 1;
        r2.flag = 0;
        new Thread(r1).start();
        new Thread(r2).start();
    }

    @Override
    public void run() {
        System.out.println("flag = " + flag);
        if (flag == 1) {
            synchronized (o1) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o2) {
                    System.out.println("1");
                }
            }
        }
        if (flag == 0) {
            synchronized (o2) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o1) {
                    System.out.println("0");
                }
            }
        }
    }
}
