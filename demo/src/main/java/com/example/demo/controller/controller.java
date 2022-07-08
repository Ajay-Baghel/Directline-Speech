package com.example.demo.controller;


import com.example.demo.TokenService;
import com.example.demo.constants.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class controller {
    @Autowired
    Environment env;

    @RequestMapping("/getToken")
    private TokenService getToken(){
        Constants.key = env.getProperty("speechService.key");
        System.out.println(env.getProperty("speechService.key"));
        TokenService token = new TokenService();
        return token;
    }
}
