package com.rui.app2.service.impl;

import com.rui.app2.service.App2Service;
import org.springframework.stereotype.Component;

/**
 * @author : Rui
 * @Date : 2018/11/4
 * @Time : 9:56
 **/
@Component
public class App2ServiceImpl implements App2Service {

    @Override
    public String app2SayHello(String name) {
        return name + ", nice to meet you too !";
    }
}
