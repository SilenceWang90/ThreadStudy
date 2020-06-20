package com.wp.ThreadSafety;

/**
 * @Classname MultiThreadsObjectEscapeInit
 * @Description 对象逸出：初始化未完毕，就用this赋值。导致不同时间内同一个对象的值发生了变化。
 * @Date 2020/6/20 17:24
 * @Created by wangpeng116
 */
public class MultiThreadsObjectEscapeInit {
    static Point point;

    public static void main(String[] args) throws InterruptedException {
        PointMaker pointMaker = new PointMaker();
        pointMaker.start();
        /**我们会发现休眠时长不一样，point对象属性不一样。
         * 这很危险，因为对象发布出来后就不能有变化，否则对实际业务影响会很大**/
        Thread.sleep(10);
        System.out.println(point);
        Thread.sleep(200);
        System.out.println(point);

    }
}

class Point {
    private final int x, y;

    /**
     * 构造函数：构造point对象时，将point对象赋值给MultiThreadsObjectEscapeInit的point对象
     *
     * @param x
     * @param y
     * @throws InterruptedException
     */
    public Point(int x, int y) throws InterruptedException {
        this.x = x;
        //构造point对象时，将point对象(this)赋值给MultiThreadsObjectEscapeInit的point对象
        MultiThreadsObjectEscapeInit.point = this;
        Thread.sleep(100);
        this.y = y;
    }

    public String toString() {
        return x + "," + y;
    }
}

class PointMaker extends Thread {
    @Override
    public void run() {
        try {
            new Point(1, 1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
