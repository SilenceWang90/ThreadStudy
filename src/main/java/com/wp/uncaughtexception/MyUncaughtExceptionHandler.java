package com.wp.uncaughtexception;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @Classname MyUncaughtExceptionHandler
 * @Description 自定义的UncaughtExceptionHandler
 * @Date 2020/5/6 20:48
 * @Created by wangpeng116
 */
@Slf4j
@Data
public class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    private String name;

    public MyUncaughtExceptionHandler(String name) {
        this.name = name;
    }

    /**
     * 统一异常处理
     * @param t 子线程对象
     * @param e 异常信息
     */
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        log.warn("{}线程异常，终止辣。异常信息：{}", t.getName(), e);
        System.out.println(name + "捕获了异常，" + t.getName() + "异常");
    }
}
