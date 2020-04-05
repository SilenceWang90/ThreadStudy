package com.wp.threadcoreknowledge.createthreads;

/**
 * @Classname ThreadStyle
 * @Description 用Thread方式实现线程
 * @Date 2020/4/4 17:41
 * @Created by wangpeng116
 */
public class ThreadStyle extends Thread{
    @Override
    public void run() {
        System.out.println("用Thread类实现线程");
    }

    public static void main(String[] args) {
        new ThreadStyle().start();
    }
}
