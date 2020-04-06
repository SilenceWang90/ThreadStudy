package com.wp.stopthreads.volatiledemo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @Classname WrongWayVolatileCantStop
 * @Description 演示用volatile的局限part2
 * 陷入阻塞时，volatile是无法停止线程的
 * 此例中，生产者的生产速度很快，消费者消费速度慢，所以阻塞队列满了以后，生产者会阻塞，等待消费者进一步消费
 * @Date 2020/4/6 14:41
 * @Created by wangpeng116
 */
public class WrongWayVolatileCantStop {
    public static void main(String[] args) throws InterruptedException {
        //生产者向队列中放入消息
        BlockingQueue storage = new ArrayBlockingQueue<>(10);
        Producer producer = new Producer(storage);
        Thread producerThread = new Thread(producer);
        producerThread.start();
        Thread.sleep(1000);
        //消费者从队列中取出消息
        Consumer consumer = new Consumer(storage);
        while (consumer.needMoreNums()) {
            System.out.println(consumer.storage.take() + "被消费了");
            Thread.sleep(100);
        }
        //数据消费完，不需要创建数据了
        System.out.println("消费者不需要更多数据了");
        //停止线程，但实际情况
        producer.canceled = true;
        System.out.println(producer.canceled);
    }
}

class Producer implements Runnable {
    public volatile boolean canceled = false;
    BlockingQueue storage;

    public Producer(BlockingQueue storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        int num = 0;
        try {
            while (num <= 100000 && !canceled) {
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
