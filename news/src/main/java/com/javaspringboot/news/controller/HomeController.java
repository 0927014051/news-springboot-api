package com.javaspringboot.news.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

    @RequestMapping("/404")
    public String pageError(){
        return "/404.html";
    }

}
