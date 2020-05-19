package com.wp.ThreadSafety;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Classname MultiThreadsError
 * @Description 运行结果出错
 * 演示计数不准确，找出具体出错的位置
 * @Date 2020/5/12 20:38
 * @Created by wangpeng116
 */
public class MultiThreadsError implements Runnable {
    int index = 0;
    final boolean[] marked = new boolean[10000000];
    static AtomicInteger realIndex = new AtomicInteger();
    static AtomicInteger wrongIndex = new AtomicInteger();
    static MultiThreadsError instance = new MultiThreadsError();
    //参数2代表需要等待几个线程
    static volatile CyclicBarrier cyclicBarrier1 = new CyclicBarrier(2);
    static volatile CyclicBarrier cyclicBarrier2 = new CyclicBarrier(2);

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
        System.out.println("真正运行的参数" + realIndex.get());
        System.out.println("错误次数" + wrongIndex.get());
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            index++;
            realIndex.incrementAndGet();
            synchronized (instance) {
                if (marked[index] && marked[index - 1]) {
                    System.out.println("发生错误" + index);
                    wrongIndex.incrementAndGet();
                }
                marked[index] = true;
            }
        }
    }
}
