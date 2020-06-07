package com.wp.threadobjectclasscommonmethods;

/**
 * @Classname JoinInterrupt
 * @Description 演示join期间被中断
 * @Date 2020/6/7 17:40
 * @Created by wangpeng116
 */
public class JoinInterrupt {
    public static void main(String[] args) {
        Thread mainThread = Thread.currentThread();
        Thread thread1 = new Thread(() -> {
            try {
                mainThread.interrupt();
                Thread.sleep(5000);
                System.out.println("Thread1 finished.");
            } catch (InterruptedException e) {
                System.out.println("子线程中断");
            }
        });
        thread1.start();
        System.out.println("等待子线程运行完毕");
        try {
            thread1.join();
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + "主线程被中断了");
            thread1.interrupt();
        }
        System.out.println("子线程运行完毕");
    }
}
