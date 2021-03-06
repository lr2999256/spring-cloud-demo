package com.rui.app2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author : rui
 * @date : 10:37
 */
@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class App2Application {

    public static void main(String[] args) {
        SpringApplication.run(App2Application.class, args);
    }
}
