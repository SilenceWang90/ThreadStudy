package com.wp.sixstates;

/**
 * @Classname BlockedWaitingTimedWating
 * @Description 展示Blocked，Waiting，TimedWaiting
 * @Date 2020/4/12 10:25
 * @Created by wangpeng116
 */
public class BlockedWaitingTimedWaiting implements Runnable {
    @Override
    public void run() {
        syn();
    }

    private synchronized void syn() {
        try {
            Thread.sleep(1000);
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = new BlockedWaitingTimedWaiting();
        Thread thread1 = new Thread(runnable);
        thread1.start();
        Thread thread2 = new Thread(runnable);
        thread2.start();
        //如果不休眠一小会线程一可能打印出runnble状态：因为线程一方法可能还没进入到执行Thread.sleep(1000)这一行就要求被打印状态
        //休眠一小会是为了保证可以执行到休眠状态
        Thread.sleep(10);
        //打印TIMED_WAITING
        System.out.println("thread1：" + thread1.getState());
        //打印BLOCKED，因为线程一还在休眠，未释放syn()的锁
        System.out.println("thread2：" + thread2.getState());

        //保证线程一休眠时间结束，并执行到wait()方法
        Thread.sleep(1500);
        //打印出waiting状态
        System.out.println("thread1：" + thread1.getState());
        //打印出TIMED_WAITING状态，因为wait()方法释放了syn()锁
        System.out.println("thread2：" + thread2.getState());
        Thread.sleep(1000);
        //打印出WAITING状态，因为线程二也进入了wait()
        System.out.println("thread2：" + thread2.getState());
    }
}
