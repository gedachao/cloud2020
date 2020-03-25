#### 模块说明
使用zookeeper：`cloud-consumerzk-order80`+`cloud-provider-payment8004`
#### consul
显示注册状态检查：http://localhost:8500/v1/agent/checks

#### openFeign使用
openFeign用于消费端，配合eureka使用：
1. 在主启动类添加注解：`@EnableFeignClients`
2. 新建service，添加`@Component`和`@FeignClient(value = "#服务提供方名称CLOUD-PAYMENT-SERVICE")`；
添加和服务提供方service相同的接口名称：
```java
 @GetMapping(value = "/payment/getPaymentById/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);
```
在controller中直接调用：
```java
@RestController
@Slf4j
public class OrderFeignController {

    @Autowired
    private PaymentFeignService paymentFeignService;

    @GetMapping(value = "consumer/payment/getPaymentById/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        return paymentFeignService.getPaymentById(id);
    }


}
```
   