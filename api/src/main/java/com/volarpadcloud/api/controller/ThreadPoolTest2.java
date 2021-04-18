package com.volarpadcloud.api.controller;

import com.volarpadcloud.api.utils.ThreadPollUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 线程池知识理解
 */
@RestController
@RequestMapping("/testThread11")
public class ThreadPoolTest2 {


    @RequestMapping(value = "testont",method = RequestMethod.POST)
    public String testont() throws InterruptedException {
        System.out.println(111);

        Thread.currentThread().sleep(10000);
        System.out.println(222);

        return "";
    }



    @RequestMapping(value = "testThreadPool2",method = RequestMethod.POST)
    public String testThread2() throws ExecutionException, InterruptedException {

            Future<?> submit = ThreadPollUtils.fixedThreadPool.submit(() -> {
                System.out.println("线程名2：" + Thread.currentThread().getName());
                try {
                    Thread.sleep(9000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

        return "ok";
    }


}
