package com.wp.stopthreads.volatiledemo;

/**
 * @Classname WrongWayVolatile
 * @Description 使用volatile的局限：当前逻辑看似可行(因为没有发生“特殊”情况哦)
 * @Date 2020/4/6 14:24
 * @Created by wangpeng116
 */
public class WrongWayVolatile implements Runnable {
    private volatile boolean canceled = false;

    @Override
    public void run() {
        int num = 0;
        try {
            while (num <= 100000 && !canceled) {
                if (num % 100 == 0) {
                    System.out.println(num + "是100的倍数。");
                }
                num++;
                Thread.sleep(1);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        WrongWayVolatile wrongWayVolatile = new WrongWayVolatile();
        Thread thread = new Thread(wrongWayVolatile);
        thread.start();
        Thread.sleep(5000);
        wrongWayVolatile.canceled = true;
    }
}
