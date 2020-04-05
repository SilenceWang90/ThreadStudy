package com.wp.stopthreads;

/**
 * @Classname RightWayStopThreadInProd
 * @Description 最佳实践：catch了InterruptedException之后的优先选择：在方法签名中抛出异常
 * @Date 2020/4/5 16:09
 * @Created by wangpeng116
 */
public class RightWayStopThreadInProd implements Runnable {
    @Override
    public void run() {
        while (true && !Thread.currentThread().isInterrupted()) {
            System.out.println("go");
            try {
                throwInMethod();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void throwInMethod() throws InterruptedException {
        Thread.sleep(2000);
    }

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = new RightWayStopThreadInProd();
        Thread thread = new Thread(runnable);
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }
}
