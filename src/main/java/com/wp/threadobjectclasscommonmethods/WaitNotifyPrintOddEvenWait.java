package com.wp.threadobjectclasscommonmethods;

/**
 * @Classname WaitNotifyPrintOddEvenWait
 * @Description 两个线程交替打印奇数偶数(通过wait / notify实现)
 * @Date 2020/6/7 15:36
 * @Created by wangpeng116
 */
public class WaitNotifyPrintOddEvenWait {
    private static int count = 0;
    private static Object lock = new Object();

    //1、拿到锁，我们就打印
    //2、打印完，唤醒其他线程，自己就休眠
    public static void main(String[] args) {
        new Thread(new TurningRunner(), "偶数线程").start();
        new Thread(new TurningRunner(), "奇数线程").start();
    }

    static class TurningRunner implements Runnable {
        @Override
        public void run() {
            while (count <= 100) {
                synchronized (lock) {
                    System.out.println(Thread.currentThread().getName() + ":" + count++);
                    lock.notify();
                    if (count <= 100) {
                        try {
                            //任务还未结束则让出当前锁且进入休眠状态
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
