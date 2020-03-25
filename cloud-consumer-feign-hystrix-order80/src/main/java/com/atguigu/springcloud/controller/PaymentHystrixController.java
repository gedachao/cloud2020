package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gedachao
 * @description
 * @date 2020/3/25 10:56
 */
@RestController
@Slf4j
public class PaymentHystrixController {

    @Autowired
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id){
        String result = paymentHystrixService.paymentInfo_OK(id);
        log.info("****ok结果："+result);
        return result;


    }

    @HystrixCommand(fallbackMethod = "paymentInfo_TimeoutHandler",commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value = "5000")
    })
    @GetMapping("/payment/hystrix/timeout/{id}")
    public String paymentInfo_timeOut(@PathVariable("id") Integer id){
        String result = paymentHystrixService.paymentInfo_timeOut(id);
        log.info("****result:"+result);
        return result;

    }

    public String paymentInfo_TimeoutHandler(Integer id){
        return "consumer-线程池："+Thread.currentThread().getName()+" paymentInfo_TimeoutHandler,id: "+id+"\t"+"呜呜呜......";
    }
}
