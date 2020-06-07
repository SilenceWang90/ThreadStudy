package com.wp.threadobjectclasscommonmethods;

/**
 * @Classname JoinPrinciple
 * @Description 通过分析join原理写出Join的代替写法
 * @Date 2020/6/7 18:10
 * @Created by wangpeng116
 */
public class JoinPrinciple {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "执行完毕");
        });

        thread.start();
        System.out.println("开始等待子线程运行完毕");
        /*thread.join();*/
        //替代方法：线程执行完成后会自动调用notifyAll的方法唤醒线程。
        /*synchronized (thread){
            thread.wait();
        }*/
        System.out.println("所有子线程执行完毕");
    }
}
