package com.rui.app2.controller;

import com.rui.app2.feign.FeignApp1Service;
import com.rui.app2.service.App2Service;
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
public class App2Controller {

    @Resource
    private App2Service app2Service;

    @Resource
    private FeignApp1Service feignClient1Service;

    @GetMapping("/app2/hello/{name}")
    public String app2Hello(@PathVariable("name") String name){
        log.info("app2 meet "+name+" for the first time");
        log.info(app2Service.app2SayHello(name));
        return "success";
    }

    @PostMapping("/app2/bye")
    public String app2Bye(String name){
        log.info("app2 do something just he like");
        log.info("app2 is tired");
        log.info(feignClient1Service.toApp1SayBye(name));
        return "successs";
    }
}
