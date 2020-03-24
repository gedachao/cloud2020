package com.atguigu.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @author gedachao
 * @description
 * @date 2020/3/24 15:04
 */

public interface LoadBalancer {
    ServiceInstance instances(List<ServiceInstance> serviceInstances);



}
