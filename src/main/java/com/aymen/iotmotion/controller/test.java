package com.aymen.iotmotion.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class test {


    @RequestMapping("/")
    public String test(){
        return "Welcom to IOT API";
    }
}
