package com.atguigu.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
 * @author gedachao
 * @description
 * @date 2020/3/23 14:25
 */
@RestController
@Slf4j
public class PaymentController {
    @Resource
    private DiscoveryClient discoveryClient;

    @Value("${server.port}")
    private String serverPort;

    @RequestMapping(value = "/payment/zk")
    public String paymentzk(){
        return "springcloud with zookeeper:"+serverPort+"\t"+ UUID.randomUUID().toString()+"\t"+discoveryClient.getServices();
    }

    @RequestMapping(value = "/payment/zkStatus")
    public String getZkStatus(){
        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-provider-payment");
        for(ServiceInstance instance : instances){
            log.info("instance info:\t"+instance.getUri());
        }
        return instances.toString();
    }
}
