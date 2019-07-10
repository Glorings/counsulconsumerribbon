package com.example.counsulconsumerribbon.controller;


import com.example.counsulconsumerribbon.service.client.HelloService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class TestController {
    protected final org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private HelloService helloService;
    @Autowired
    private RestTemplate restTemplate;

    /** 创建 RestTemplate Bean,并用 @LaodBalanced 注解*/
    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /** 接收前端传来的参数，使用 feign 的方式调用远程接口，并返回调用结果 */
    @GetMapping("/hello1")
    public String hello1(String name){
        return helloService.sayHello(name);
    }

    /** 接收前端传来的参数，使用 restTemplate 的方式调用远程接口，并返回调用结果 */
    @GetMapping("/hello2")
    public String hello2(String name){
        return restTemplate.getForObject("http://consulServer/sayHello?name="+name,String.class);
    }

    @GetMapping("/actuator/health")
    public String health(){
        return "SUCCESS";
    }
}
