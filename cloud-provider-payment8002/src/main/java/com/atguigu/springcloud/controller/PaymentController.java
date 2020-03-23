package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author gedachao
 * @description
 * @date 2020/3/21 16:01
 */
@Slf4j
@RestController
public class PaymentController {

    @Resource
    private DiscoveryClient discoveryClient;

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        log.info("插入结果："+result);
        if(result>0){
            return new CommonResult(200,"插入数据库成功");
        }else{
            return new CommonResult(400,"插入数据库失败");
        }
    }

    @GetMapping("/payment/getPaymentById/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("检索结果："+payment);
        if(payment != null){
            return new CommonResult(200,"成功",payment);
        }else{
            return new CommonResult(200,"未查询到数据",null);
        }
    }

    @GetMapping(value = "/payment/discovery")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();
        for(String service : services){
            log.info("***element:"+service);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance: instances) {
            log.info("serviceId:"+instance.getServiceId()+"\t"+"host:"+instance.getHost()+"\tport:"+instance.getPort()+"\turi:"+instance.getUri());
        }
        return  this.discoveryClient;
    }
}
