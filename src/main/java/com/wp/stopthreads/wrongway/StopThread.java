package com.wp.stopthreads.wrongway;

/**
 * @Classname StopThread
 * @Description 错误的停止方法：
 * 用stop()来停止线程，会导致线程运行一半突然停滞，没办法完成一个基本单位的操作，会造成脏数据
 * @Date 2020/4/6 14:01
 * @Created by wangpeng116
 */
public class StopThread implements Runnable {
    @Override
    public void run() {
        //模拟指挥军队：一共有5个连队，每个连队100人，以连队为单位，发放武器弹药，叫到号的士兵前去领取
        for (int i = 0; i < 5; i++) {
            System.out.println("连队" + i + "开始领取武器");
            for (int j = 0; j < 10; j++) {
                System.out.println(j);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("连队" + i + "已经领取完毕");
        }
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new StopThread());
        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.stop();
    }
}
