package com.wp.jmm;

import java.util.concurrent.CountDownLatch;

/**
 * @Classname OutOfOrderExecution
 * @Description 重排序
 * @Date 2020/6/21 16:44
 * @Created by wangpeng116
 */
public class OutOfOrderExecution {
    private static int x = 0, y = 0;
    private static int a = 0, b = 0;

    public static void main(String[] args) throws InterruptedException {
        int i = 0;
        for (; ;) {
            i++;
            x = 0;
            y = 0;
            a = 0;
            b = 0;
            CountDownLatch countDownLatch = new CountDownLatch(1);
            Thread one = new Thread(() -> {
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                a = 1;
                x = b;
            });
            Thread two = new Thread(() -> {
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                b = 1;
                y = a;
            });
            one.start();
            two.start();
            countDownLatch.countDown();
            one.join();
            two.join();
            String result = "第" + i + "次(" + x + "," + y + ")";
            if (x == 0 && y == 0) {
                System.out.println(result);
                break;
            } else {
                System.out.println(result);
            }
        }
    }
}
