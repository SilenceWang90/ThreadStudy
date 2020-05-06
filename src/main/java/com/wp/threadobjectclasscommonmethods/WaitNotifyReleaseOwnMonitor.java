package com.wp.threadobjectclasscommonmethods;

/**
 * @Classname WaitNotifyReleaseOwnMonitor
 * @Description 证明wait只释放当前锁
 * @Date 2020/4/12 14:40
 * @Created by wangpeng116
 */
public class WaitNotifyReleaseOwnMonitor {

    private static volatile Object resourceA = new Object();
    private static volatile Object resourceB = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            synchronized (resourceA) {
                System.out.println("ThreadA got resourceA lock.");
                synchronized (resourceB) {
                    System.out.println("ThreadA got resourceB lock.");
                    try {
                        System.out.println("ThreadA releases resourceA lock.");
                        resourceA.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                //休眠一下为了保证线程1可以执行完成
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (resourceA) {
                System.out.println("ThreadB got resourceA lock.");
                System.out.println("ThreadB tries to get resourceB lock.");
                synchronized (resourceB) {
                    System.out.println("ThreadB got resourceB lock.");
                }
            }
        });
        thread1.start();
        thread2.start();
        Thread.sleep(2000);
        System.out.println("thread1's state is：" + thread1.getState());
        System.out.println("thread2's state is：" + thread2.getState());
    }
}
