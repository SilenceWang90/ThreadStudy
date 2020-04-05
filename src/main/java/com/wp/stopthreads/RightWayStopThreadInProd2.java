package com.wp.stopthreads;

/**
 * @Classname RightWayStopThreadInProd2
 * @Description 最佳实践2：在catch子句中调用Thread.currentThread().interrupt()来恢复设置中断状态，
 * 以便于在后续的执行中，依然能够检查到刚才发生了中断
 * 回到刚才RightWayStopThreadInProd补上中断，让它跳出
 * @Date 2020/4/5 16:59
 * @Created by wangpeng116
 */
public class RightWayStopThreadInProd2 implements Runnable {
    @Override
    public void run() {
        while (true) {
            if (Thread.currentThread().isInterrupted()) {
                System.out.println("Interrupted，程序运行结束");
                break;
            }
            System.out.println("go");
            reInterrupt();
        }
    }

    /**
     * 线程被中断时，重复中断操作，使得上层run方法可以接收到中断信息。
     */
    private void reInterrupt() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = new RightWayStopThreadInProd2();
        Thread thread = new Thread(runnable);
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }
}
