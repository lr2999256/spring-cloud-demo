package com.rui.client2.service.impl;

import com.rui.client2.service.Client2Service;
import org.springframework.stereotype.Component;

/**
 * @author : Rui
 * @Date : 2018/11/4
 * @Time : 9:56
 **/
@Component
public class Client2ServiceImpl implements Client2Service {

    @Override
    public String client2SayHello(String name) {
        return name + ", nice to meet you too !";
    }
}
