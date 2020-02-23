package com.rui.app1.controller;

import com.rui.app1.feign.FeignApp2Service;
import com.rui.app1.service.App1Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author : Rui
 * @Date : 2018/11/4
 * @Time : 9:57
 **/
@RestController
public class App1Controller {

    @Resource
    private App1Service app1Service;

    @Resource
    private FeignApp2Service feignClient2Service;

    @Value("${my.name2}")
    private String myName;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(App1Controller.class);

    @GetMapping("/app1/hello/{name}")
    public String app2SayHello(@PathVariable("name") String name){
        LOGGER.info("Nice to meet you app2 !");
        LOGGER.info(feignClient2Service.toApp2SayHello(name));
        return "success";
    }

    @GetMapping("/config/test")
    public String configTest(){
        LOGGER.info("config test!,{}",myName);
        return "success";
    }

    @PostMapping("/app1/bye")
    public String app1SayBye(@RequestParam("name") String name){
        LOGGER.info("app1 is tired too.");
        LOGGER.info(app1Service.app1SayBye(name));
        return "success";
    }
}
