package com.volarpadcloud.api.controller;

import com.volarpadcloud.api.utils.ThreadPollUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 线程池知识理解
 */
@RestController
@RequestMapping("/testThread")
public class ThreadPoolTest {

    /**
     * 固定大小的线程池
     */
    static ExecutorService fixedThreadPool = Executors.newFixedThreadPool(4);

    @RequestMapping(value = "testone",method = RequestMethod.POST)
    public String testone(){
        System.out.println(333);

        return "";
    }


    @RequestMapping(value = "testThreadPool1",method = RequestMethod.POST)
    public String testThread(){

            Future<?> submit = ThreadPollUtils.fixedThreadPool.submit(() -> {
                System.out.println("线程名1：" + Thread.currentThread().getName());
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

        return "ok";
    }



}
