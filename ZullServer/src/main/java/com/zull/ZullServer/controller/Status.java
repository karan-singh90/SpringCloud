package com.zull.ZullServer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class Status {
    @GetMapping("status")
    public String checkStatus(){
        return "Zuul is up";
    }
}
