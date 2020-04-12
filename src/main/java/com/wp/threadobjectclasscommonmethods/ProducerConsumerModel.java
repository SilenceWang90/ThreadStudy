package com.wp.threadobjectclasscommonmethods;

import com.google.common.collect.Lists;

import java.util.Date;
import java.util.LinkedList;

/**
 * @Classname ProducerConsumerModel
 * @Description 用wait/notify来实现
 * @Date 2020/4/12 16:02
 * @Created by wangpeng116
 */
public class ProducerConsumerModel {
    public static void main(String[] args) {
        EventStorage eventStorage = new EventStorage();
        Producer producer = new Producer(eventStorage);
        Consumer consumer = new Consumer(eventStorage);
        new Thread(producer).start();
        new Thread(consumer).start();
    }
}


/**
 * 生产者
 */
class Producer implements Runnable {

    private EventStorage storage;

    public Producer(EventStorage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            storage.put();
        }
    }
}

/**
 * 消费者
 */
class Consumer implements Runnable {

    private EventStorage storage;

    public Consumer(EventStorage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            storage.take();
        }
    }
}

/**
 * 仓库存储/获取物品
 */
class EventStorage {
    private int maxSize;
    private LinkedList<Date> storage;

    public EventStorage() {
        maxSize = 10;
        storage = Lists.newLinkedList();
    }

    /**
     * 仓库存入物品
     */
    public synchronized void put() {
        //物品放满了，进入阻塞状态
        while (storage.size() == maxSize) {
            try {
                System.out.println("生产者停止生产");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        storage.add(new Date());
        System.out.println("仓库里有了" + storage.size());
        notify();
    }

    /**
     * 仓库获取物品
     */
    public synchronized void take() {
        //物品取没了，进入阻塞状态
        while (storage.size() == 0) {
            try {
                System.out.println("消费者停止获取");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //storage.poll()取值并删除队列中的该值
        System.out.println("拿到了" + storage.poll() + ",现在仓库还剩下" + storage.size());
        notify();
    }
}

