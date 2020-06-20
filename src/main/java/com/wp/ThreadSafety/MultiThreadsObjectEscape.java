package com.wp.ThreadSafety;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * @Classname MultiThreadsObjectEscape
 * @Description 对象逸出：对象被public方法逸出，导致内容被篡改
 * @Date 2020/6/20 17:12
 * @Created by wangpeng116
 */
@Slf4j
public class MultiThreadsObjectEscape {
    private Map<String, String> states;

    public static void main(String[] args) {
        MultiThreadsObjectEscape multiThreadsObjectEscape = new MultiThreadsObjectEscape();
        Map<String, String> states = multiThreadsObjectEscape.getStates();
        System.out.println(states.get("1"));
        states.remove("1");
        System.out.println(states.get("1"));
    }

    public MultiThreadsObjectEscape() {
        states = new HashMap<>();
        states.put("1", "周一");
        states.put("2", "周二");
        states.put("3", "周三");
        states.put("4", "周四");
    }

    public Map<String, String> getStates() {
        return states;
    }
}
