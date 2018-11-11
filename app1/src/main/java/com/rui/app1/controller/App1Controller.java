package com.rui.app1.controller;

import com.rui.app1.feign.FeignApp2Service;
import com.rui.app1.service.App1Service;
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
public class App1Controller {

    @Resource
    private App1Service app1Service;

    @Resource
    private FeignApp2Service feignClient2Service;

    @GetMapping("/app1/hello/{name}")
    public String app2SayHello(@PathVariable("name") String name){
        log.info("Nice to meet you app2 !");
        log.info(feignClient2Service.toApp2SayHello(name));
        return "success";
    }

    @PostMapping("/app1/bye")
    public String app1SayBye(@RequestParam("name") String name){
        log.info("app1 is tired too.");
        log.info(app1Service.app1SayBye(name));
        return "success";
    }
}
