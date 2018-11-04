package com.rui.client1.controller;

import com.rui.client1.feign.FeignClient2Service;
import com.rui.client1.service.Client1Service;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author : Rui
 * @Date : 2018/11/4
 * @Time : 9:57
 **/
@RestController
@Log
public class Client1Controller {

    @Resource
    private Client1Service client1Service;

    @Resource
    private FeignClient2Service feignClient2Service;

    @GetMapping("/client1/hello/{name}")
    public String client2SayHello(@PathVariable("name") String name){
        log.info("Nice to meet you client2 !");
        log.info(feignClient2Service.toClient2SayHello(name));
        return "success";
    }

    @PostMapping("/client1/bye")
    public String client1SayBye(@RequestParam("name") String name){
        log.info("client1 is tired too.");
        log.info(client1Service.client1SayBye(name));
        return "success";
    }
}
