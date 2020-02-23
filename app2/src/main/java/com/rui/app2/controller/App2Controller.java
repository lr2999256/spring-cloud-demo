package com.rui.app2.controller;

import com.rui.app2.feign.FeignApp1Service;
import com.rui.app2.service.App2Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author : Rui
 * @Date : 2018/11/4
 * @Time : 9:57
 **/
@RestController
public class App2Controller {

    @Resource
    private App2Service app2Service;

    @Resource
    private FeignApp1Service feignClient1Service;

    private static final Logger LOGGER = LoggerFactory.getLogger(App2Controller.class);

    @GetMapping("/app2/hello/{name}")
    public String app2Hello(@PathVariable("name") String name){
        LOGGER.info("app2 meet "+name+" for the first time");
        LOGGER.info(app2Service.app2SayHello(name));
        return "success";
    }

    @PostMapping("/app2/bye")
    public String app2Bye(String name){
        LOGGER.info("app2 do something just he like");
        LOGGER.info("app2 is tired");
        LOGGER.info(feignClient1Service.toApp1SayBye(name));
        return "successs";
    }
}
