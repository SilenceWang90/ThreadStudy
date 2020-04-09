package com.wp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Classname ThreadStudyApplication
 * @Description 主类
 * @Date 2020/4/2 18:31
 * @Created by wangpeng116
 */
@SpringBootApplication
@Slf4j
public class ThreadStudyApplication {
    public static void main(String[] args) {
        log.info("第一1次提交");
        log.info("第二2次提交");
        log.info("第三3次提交");
        log.info("第四4次提交");
        SpringApplication.run(ThreadStudyApplication.class, args);
    }
}
