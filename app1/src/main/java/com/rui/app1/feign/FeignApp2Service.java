package com.rui.app1.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author : Rui
 * @Date : 2018/11/4
 * @Time : 9:56
 **/
@FeignClient(value = "APP2-SERVICE")
public interface FeignApp2Service {

    /**
     * 自己对对方打招呼，调用此方法
     * @param name 名字
     * @return
     */
    @GetMapping("/app2/hello/{name}")
    String toApp2SayHello(@PathVariable("name") String name);
}
