package com.atguigu.springcloud.service;

import ch.qos.logback.core.util.TimeUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author gedachao
 * @description
 * @date 2020/3/25 9:40
 */
@Service
public class PaymentService {

    /**
     * 正常访问
     * @param id
     * @return
     */
    public String paymentInfo_OK(Integer id){
        return "线程池："+Thread.currentThread().getName()+" paymentInfo_OK,id: "+id+"\t"+"O(∩_∩)O";
    }


    @HystrixCommand(fallbackMethod = "paymentInfo_TimeoutHandler",commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "5000")
    })
    public String paymentInfo_timeout(Integer id){
        Integer timeNumber = 2;
        try {
            //TimeUnit.MILLISECONDS.sleep(500);
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池："+Thread.currentThread().getName()+" paymentInfo_Timeout,id: "+id+"\t"+"O(∩_∩)O"+"耗时"+timeNumber+"s";
    }

    public String paymentInfo_TimeoutHandler(Integer id){
        return "服务提供方线程池："+Thread.currentThread().getName()+" paymentInfo_TimeoutHandler,id: "+id+"\t"+"呜呜呜......";
    }
}
