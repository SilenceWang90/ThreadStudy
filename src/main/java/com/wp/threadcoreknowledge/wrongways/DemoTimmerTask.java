package com.wp.threadcoreknowledge.wrongways;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @Classname DemoTimmerTask
 * @Description 定时器创建线程
 * @Date 2020/4/5 10:16
 * @Created by wangpeng116
 */
public class DemoTimmerTask {
    public static void main(String[] args) {
        Timer timer = new Timer();
        //每隔一秒钟打印一下线程名称
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        }, 1000, 1000);
    }
}
