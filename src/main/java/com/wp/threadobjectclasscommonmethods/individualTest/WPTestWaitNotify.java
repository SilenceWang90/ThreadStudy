package com.wp.threadobjectclasscommonmethods.individualTest;

/**
 * @Classname WPTestWaitNotify
 * @Description 自定义测试WaitNotify的使用
 * @Date 2020/5/6 18:18
 * @Created by wangpeng116
 */
public class WPTestWaitNotify {
    public static Boy boy = new Boy();
    public static Girl girl = new Girl();

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            synchronized (boy) {
                try {
                    System.out.println("线程一获取boy锁");
                    boy.wait();
                    System.out.println("线程一被boy唤醒");
                    //此处会报错：因为girl.wait()必须是在synchronized(girl){}方法体中执行，否则运行时报错
                    girl.wait();
                    System.out.println("线程一被girl唤醒");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread thread2 = new Thread(() -> {
            synchronized (boy) {
                boy.notify();
            }
        });
        thread1.start();
        Thread.sleep(100);
        thread2.start();
        Thread.sleep(1000);
        System.out.println("thread1的状态：" + thread1.getState());
        System.out.println("thread2的状态：" + thread2.getState());
    }
}
