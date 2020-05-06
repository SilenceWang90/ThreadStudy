package com.wp.uncaughtexception;

/**
 * @Classname ExceptionInChildThread
 * @Description 多线程时，子线程
 * @Date 2020/5/6 20:23
 * @Created by wangpeng116
 */
public class ExceptionInChildThread implements Runnable {
    public static void main(String[] args) {
        new Thread(new ExceptionInChildThread()).start();
        for (int i = 0; i < 1000; i++) {
            System.out.println(i);
        }
    }

    @Override
    public void run() {
        throw new RuntimeException();
    }
}
