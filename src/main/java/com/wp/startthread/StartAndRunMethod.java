package com.wp.startthread;

/**
 * @Classname StartAndRunMethod
 * @Description 对比start和run两种启动线程的方式
 * @Date 2020/4/5 13:47
 * @Created by wangpeng116
 */
public class StartAndRunMethod {
    public static void main(String[] args) {
        Runnable runnable = ()->{
            System.out.println(Thread.currentThread().getName());
        };
        runnable.run();

        new Thread(runnable).start();
    }
}
