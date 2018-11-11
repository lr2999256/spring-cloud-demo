package com.rui.app2.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author : Rui
 * @Date : 2018/11/4
 * @Time : 9:56
 **/
@FeignClient(value = "APP1-SERVICE")
public interface FeignApp1Service {

    /**
     * 自己累了，调用对方此接口，告诉对方自己累了
     * @param name 名字
     * @return
     */
    @PostMapping("/app1/bye")
    String toApp1SayBye(@RequestParam("name") String name);
}
