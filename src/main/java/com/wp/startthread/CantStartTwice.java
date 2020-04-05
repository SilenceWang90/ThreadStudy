package com.wp.startthread;

/**
 * @Classname CantStartTwice
 * @Description 演示不能2次调用start，否则会报错
 * @Date 2020/4/5 13:59
 * @Created by wangpeng116
 */
public class CantStartTwice {
    public static void main(String[] args) {
        Thread thread = new Thread();
        thread.start();
        thread.start();
    }
}
