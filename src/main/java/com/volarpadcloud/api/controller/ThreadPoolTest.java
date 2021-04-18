package com.volarpad.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
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


    @RequestMapping("testThreadPool")
    public String testThread(){

        Future<?> submit = fixedThreadPool.submit(() -> {
            System.out.println("线程名：" + Thread.currentThread().getName());
        });
        return "ok";
    }

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            Future<?> submit = fixedThreadPool.submit(() -> {
                System.out.println("线程名：" + Thread.currentThread().getName());
                try {
                    Thread.sleep(4000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

    }


}
