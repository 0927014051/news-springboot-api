package com.javaspringboot.news.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    @RequestMapping("/home")
    public ModelAndView homeUser(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/index.html");
        return modelAndView;
    }

    @RequestMapping("/post")
    public ModelAndView postUser(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/page.html");
        return modelAndView;
    }
}
