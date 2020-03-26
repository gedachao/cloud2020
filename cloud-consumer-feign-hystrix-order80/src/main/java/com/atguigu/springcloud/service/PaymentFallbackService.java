package com.atguigu.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * @author gedachao
 * @description
 * @date 2020/3/26 13:44
 */
@Component
public class PaymentFallbackService implements PaymentHystrixService {
    @Override
    public String paymentInfo_OK(Integer id) {
        return "-------PaymentFallbackService fallback_info_ok -^-";
    }

    @Override
    public String paymentInfo_timeOut(Integer id) {
        return "-------PaymentFallbackService fallback_info_timeout -^-";
    }
}
