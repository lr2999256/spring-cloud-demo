package com.rui.app1.service.impl;

import com.rui.app1.service.App1Service;
import org.springframework.stereotype.Component;

/**
 * @author : Rui
 * @Date : 2018/11/4
 * @Time : 9:56
 **/
@Component
public class App1ServiceImpl implements App1Service {

    @Override
    public String app1SayBye(String name) {
        return name + ", Bye !";
    }
}
