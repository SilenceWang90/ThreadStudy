package com.wp.threadobjectclasscommonmethods;

import lombok.extern.slf4j.Slf4j;

/**
 * @Classname WaitNotifyPrintOddEven
 * @Description 两个线程交替打印奇数偶数(通过synchronized实现)
 * @Date 2020/6/7 13:03
 * @Created by wangpeng116
 */
@Slf4j
public class WaitNotifyPrintOddEvenSyn {
    //计数器
    private static int count;
    //创建一个锁
    private static Object lock = new Object();

    //新建2个线程，一个处理偶数，一个处理技奇数(用位运算)
    //用synchronized来通信
    public static void main(String[] args) {
        new Thread(() -> {
            while (count < 100) {
                synchronized (lock) {
                    //判断是否为偶数(用下面的位运算方式效率更高)
                    //1的二进制是00000001，除了最后一位是1其他都是0；
                    //&运算要求除非2个位都是1，否则结果是0，由于1的二进制除了最后一位都是0，所以前面的值都是0；
                    //比较的就是最后一位，偶数的最后一位肯定0，奇数的最后一位肯定是1。
                    //因此“偶数&1”的结果一定是0，“奇数&1”的结果一定是1.
                    if ((count & 1) == 0) {
                        System.out.println(Thread.currentThread().getName() + "：" + count++);
                    }
                }
            }
        },"偶数线程").start();
        new Thread(() -> {
            while (count < 100) {
                synchronized (lock) {
                    if ((count & 1) == 1) {
                        System.out.println(Thread.currentThread().getName() + "：" + count++);
                    }
                }
            }
        },"奇数线程").start();
    }


}
