package com.rui.client2.controller;

import com.rui.client2.feign.FeignClient1Service;
import com.rui.client2.service.Client2Service;
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
public class Client2Controller {

    @Resource
    private Client2Service client2Service;

    @Resource
    private FeignClient1Service feignClient1Service;

    @GetMapping("/client2/hello/{name}")
    public String client2Hello(@PathVariable("name") String name){
        log.info("client2 meet "+name+" for the first time");
        log.info(client2Service.client2SayHello(name));
        return "success";
    }

    @PostMapping("/client2/bye")
    public String client2Bye(String name){
        log.info("client2 do something just he like");
        log.info("client2 is tired");
        log.info(feignClient1Service.toClient1SayBye(name));
        return "successs";
    }
}
