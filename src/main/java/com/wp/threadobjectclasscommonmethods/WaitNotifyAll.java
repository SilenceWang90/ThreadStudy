package com.wp.threadobjectclasscommonmethods;

/**
 * @Classname WaitNotifyAll
 * @Description 3个线程，线程1和线程2被阻塞，线程3唤醒他们
 * 分别使用notify和notifyAll
 * start()先执行不代表线程会先启动
 * @Date 2020/4/12 14:04
 * @Created by wangpeng116
 */
public class WaitNotifyAll implements Runnable {
    private static final Object resourceA = new Object();

    @Override
    public void run() {
        synchronized (resourceA) {
            System.out.println(Thread.currentThread().getName() + "got resourceA lock.");
            try {
                System.out.println(Thread.currentThread().getName() + "waits to start.");
                resourceA.wait();
                System.out.println(Thread.currentThread().getName() + "'s waiting to end.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = new WaitNotifyAll();
        Thread threadA = new Thread(runnable);
        Thread threadB = new Thread(runnable);
        Thread threadC = new Thread(() -> {
            synchronized (resourceA) {
                resourceA.notifyAll();
                System.out.println("ThreadC notified");
            }
        });
        threadA.start();
        threadB.start();
        //确保A,B线程都执行到wait()进入waiting状态
        Thread.sleep(200);
        threadC.start();
    }
}
