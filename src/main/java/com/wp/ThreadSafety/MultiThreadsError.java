package com.wp.ThreadSafety;

/**
 * @Classname MultiThreadsError
 * @Description 运行结果出错
 * 演示计数不准确，找出具体出错的位置
 * @Date 2020/5/12 20:38
 * @Created by wangpeng116
 */
public class MultiThreadsError implements Runnable {
    int index = 0;
    static MultiThreadsError instance = new MultiThreadsError();


    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(instance, "thread1");
        Thread thread2 = new Thread(instance, "thread2");
        thread1.start();
        thread2.start();
        //主线程等线程1执行完
        thread1.join();
        //主线程等线程2执行完
        thread2.join();
        System.out.println("表面上结果是：" + instance.index);
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            index++;
        }
    }
}
