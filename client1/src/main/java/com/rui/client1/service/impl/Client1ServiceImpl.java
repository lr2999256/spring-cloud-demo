package com.rui.client1.service.impl;

import com.rui.client1.service.Client1Service;
import org.springframework.stereotype.Component;

/**
 * @author : Rui
 * @Date : 2018/11/4
 * @Time : 9:56
 **/
@Component
public class Client1ServiceImpl implements Client1Service {

    @Override
    public String client1SayBye(String name) {
        return name + ", Bye !";
    }
}
