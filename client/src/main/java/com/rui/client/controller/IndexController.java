package com.rui.client.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.*;

/**
 * @author : Rui
 * @Date : 2018/11/8
 * @Time : 17:18
 **/
@Controller
public class IndexController {

    @RequestMapping({"/","/index"})
    public String index(){
        return "login";
    }

    @RequestMapping("/admin/getToken")
    @ResponseBody
    @PreAuthorize("hasAuthority('admin')")
    public String getToken(){
        ExecutorService executors = Executors.newFixedThreadPool(10);
        Future<Integer> future = executors.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return 1;
            }
        });
        try {
            future.get();
            Set set = new HashSet();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        RestTemplate restTemplate = new RestTemplate();
        String oauthTokenUrl = "http://localhost:8099/oauth/token?username={username}&password={password}" +
                "&grant_type={grant_type}&scope={scope}&client_id={client_id}&client_secret={client_secret}";
        Map<String,String> param = new HashMap<>(6);
        param.put("username","admin");
        param.put("password","123456");
        param.put("grant_type","password");
        param.put("scope","write");
        param.put("client_id","test");
        param.put("client_secret","secret");
        Map responseMap = restTemplate.getForObject(oauthTokenUrl,Map.class,param);
        return responseMap.toString();
    }

    @RequestMapping("/admin/refreshToken")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String refreshToken(@RequestParam String refreshToken){
        RestTemplate restTemplate = new RestTemplate();
        String refreshTokenUrl = "http://localhost:8099/oauth/token?grant_type={grant_type}" +
                "&refresh_token={refresh_token}&client_id={client_id}&client_secret={client_secret}";
        Map<String,String> param = new HashMap<>(4);
        param.put("grant_type","refresh_token");
        param.put("refresh_token",refreshToken);
        param.put("client_id","test");
        param.put("client_secret","secret");
        Map responseMap = restTemplate.getForObject(refreshTokenUrl,Map.class,param);
        return responseMap.toString();
    }
}
