package com.wp.stopthreads;

/**
 * @Classname RightWayStopThreadInProd
 * @Description 最佳实践1：catch了InterruptedException之后的优先选择：在方法签名中抛出异常
 * @Date 2020/4/5 16:09
 * @Created by wangpeng116
 */
public class RightWayStopThreadInProd1 implements Runnable {
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            System.out.println("go");
            try {
                throwInMethod();
            } catch (InterruptedException e) {
                //捕获到中断信息，重新设置中断
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }
    }

    private void throwInMethod() throws InterruptedException {
        Thread.sleep(2000);
    }

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = new RightWayStopThreadInProd1();
        Thread thread = new Thread(runnable);
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }
}
