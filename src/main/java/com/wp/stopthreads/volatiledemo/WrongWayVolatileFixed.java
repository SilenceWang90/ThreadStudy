package com.wp.stopthreads.volatiledemo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @Classname WrongWayVolatileFixed
 * @Description 用中断来修复无尽等待的问题
 * @Date 2020/4/6 15:28
 * @Created by wangpeng116
 */
public class WrongWayVolatileFixed {
    public static void main(String[] args) throws InterruptedException {
        WrongWayVolatileFixed wrongWayVolatileFixed = new WrongWayVolatileFixed();
        //生产者向队列中放入消息
        BlockingQueue storage = new ArrayBlockingQueue<>(10);
        Producer producer = wrongWayVolatileFixed.new Producer(storage);
        Thread producerThread = new Thread(producer);
        producerThread.start();
        Thread.sleep(1000);
        //消费者从队列中取出消息
        Consumer consumer = wrongWayVolatileFixed.new Consumer(storage);
        while (consumer.needMoreNums()) {
            System.out.println(consumer.storage.take() + "被消费了");
            Thread.sleep(100);
        }
        //数据消费完，不需要创建数据了
        System.out.println("消费者不需要更多数据了");
        //停止线程，但实际情况
        producerThread.interrupt();
    }

    class Producer implements Runnable {
        BlockingQueue storage;

        public Producer(BlockingQueue storage) {
            this.storage = storage;
        }

        @Override
        public void run() {
            int num = 0;
            try {
                while (num <= 100000 && !Thread.currentThread().isInterrupted()) {
                    if (num % 100 == 0) {
                        storage.put(num);
                        System.out.println(num + "是100的倍数，被放到仓库中了。");
                    }
                    num++;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("生产者停止运行");
            }
        }
    }

    class Consumer {
        BlockingQueue storage;

        public Consumer(BlockingQueue storage) {
            this.storage = storage;
        }

        public boolean needMoreNums() {
            if (Math.random() > 0.95) {
                return false;
            }
            return true;
        }
    }
}




