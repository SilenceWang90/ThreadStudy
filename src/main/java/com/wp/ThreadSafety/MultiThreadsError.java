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
    static AtomicInteger realIndex = new AtomicInteger();
    static AtomicInteger wrongCount = new AtomicInteger();
    static MultiThreadsError instance = new MultiThreadsError();
    final boolean[] marked = new boolean[1000000];
    //构造函数中的参数代表需要等待几个线程。当指定数量的线程执行完await()方法后才会被放行。
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
        System.out.println("真正运行的次数：" + realIndex.get());
        System.out.println("错误次数：" + wrongCount.get());
    }

    @Override
    public void run() {
        marked[0] = true;
        for (int i = 0; i < 10000; i++) {
            //防止某个线程执行完mark[n]=true后直接又把index值修改了，导致另一个线程本来想对index=1进行处理，结果index的值直接被改成了2。
            try {
                cyclicBarrier2.reset();
                cyclicBarrier1.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            index++;
            try {
                cyclicBarrier1.reset();
                cyclicBarrier2.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            realIndex.incrementAndGet();
            synchronized (instance) {
                if (marked[index] && marked[index - 1]) {
                    System.out.println("发生了错误" + index);
                    wrongCount.incrementAndGet();
                }
                marked[index] = true;
            }
        }
    }
}
