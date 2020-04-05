package com.wp.threadcoreknowledge.createthreads;

/**
 * @Classname BothRunnableThread
 * @Description 同时实现两种方式
 * @Date 2020/4/5 9:42
 * @Created by wangpeng116
 */
public class BothRunnableThread {
    public static void main(String[] args) {
        new Thread(new RunnableStyle() {
            //匿名类，完成接口的实现
            @Override
            public void run() {
                System.out.println("我来自Runnable");
            }
        }) {
            //匿名类，完成继承的实现
            @Override
            public void run() {
                System.out.println("我来自Thread");
            }
        }.start();
    }

}
