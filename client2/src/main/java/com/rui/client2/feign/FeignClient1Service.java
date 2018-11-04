package com.rui.client2.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author : Rui
 * @Date : 2018/11/4
 * @Time : 9:56
 **/
@FeignClient(value = "CLIENT1-SERVICE")
public interface FeignClient1Service {

    /**
     * 自己累了，调用对方此接口，告诉对方自己累了
     * @param name 名字
     * @return
     */
    @PostMapping("/client1/bye")
    String toClient1SayBye(@RequestParam("name") String name);
}
