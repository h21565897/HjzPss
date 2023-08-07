package com.junzhou.infop.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.scheduling.config.Task;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Slf4j
public class LearnToMutiThread {
    @Test
    void test1(){
        Integer coreCount=Runtime.getRuntime().availableProcessors();
        log.info(coreCount.toString());
        ExecutorService service= Executors.newFixedThreadPool(10);
        Future<String> submit = service.submit(new Callable<String>() {
            public String call(){
                return "finished";
            }
        });
        for(int i=0;i<10;i++){
            service.execute(new Runnable(){
                public void run(){
                    System.out.println("ThreadName:"+Thread.currentThread().getName());
                }
            });
        }
    }

    @Test
    void test2(){
        LinkedList<Integer> al=new LinkedList<>();
        List<List<Integer>> aa=new ArrayList<>();
        al.add(33);
        aa.add(al);
        List<Integer> l1=aa.get(0);
        al.add(34);
    }

}
