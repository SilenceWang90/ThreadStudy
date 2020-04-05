package com.wp.stopthreads;

/**
 * @Classname RightWayStopThreadWithSleepEveryLoop
 * @Description 如果在执行过程中，每次循环都会调用sleep或者wait方法，那么不需要每次迭代都检查是否已中断
 * @Date 2020/4/5 15:15
 * @Created by wangpeng116
 */
public class RightWayStopThreadWithSleepEveryLoop {
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            int num = 0;
            try {
                while (num <= 10000) {
                    if (num % 100 == 0) {
                        System.out.println(num + "是100的倍数");
                    }
                    num++;
                    Thread.sleep(10);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
        Thread.sleep(5000);
        thread.interrupt();
    }
}
