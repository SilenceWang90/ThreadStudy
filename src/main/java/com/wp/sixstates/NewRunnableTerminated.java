package com.wp.sixstates;

/**
 * @Classname NewRunnableTerminated
 * @Description 展示线程的NEW、RUNNABLE、TERMINATED状态
 *
 * @Date 2020/4/12 10:07
 * @Created by wangpeng116
 */
public class NewRunnableTerminated implements Runnable {
    @Override
    public void run() {
        for(int i=0;i<1000;i++){
            System.out.println(i);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new NewRunnableTerminated());
        //打印出NEW状态
        System.out.println(thread.getState());
        thread.start();
        //打印出Runnable状态(线程可能立即被执行也可能延后被执行，取决于CPU)
        System.out.println(thread.getState());
        //休眠10毫秒是为了在打印i的时候，能打印出线程的状态，来确认线程是否在执行时也是runnable状态
        Thread.sleep(10);
        //打印出Runnable状态(线程可能立即被执行也可能延后被执行，取决于CPU)
        System.out.println(thread.getState());
        //打印出Terminated。100毫秒后线程执行完毕
        Thread.sleep(100);
        System.out.println(thread.getState());
    }
}
