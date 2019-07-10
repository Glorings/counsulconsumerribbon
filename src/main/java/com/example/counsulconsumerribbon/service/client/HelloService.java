package com.example.counsulconsumerribbon.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("consulServer")
public interface HelloService {

    @RequestMapping(value = "/sayHello",method = RequestMethod.GET)
    String sayHello(@RequestParam("name") String name);

}
