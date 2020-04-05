package com.wp.threadcoreknowledge.createthreads;

/**
 * @Classname RunnableStyle
 * @Description 用Runnable方式创建线程
 * @Date 2020/4/4 17:39
 * @Created by wangpeng116
 */
public class RunnableStyle implements Runnable {

    public static void main(String[] args) {
        Thread thread = new Thread(new RunnableStyle());
        thread.start();
    }

    @Override
    public void run() {
        System.out.println("用Runnbale方法实现线程");
    }
}
