package com.javaspringboot.news.controller;


import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.javaspringboot.news.entities.Post;
import com.javaspringboot.news.services.PostService;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private PostService postService;

    @RequestMapping("/home")
    public ModelAndView homeUser(Model model){
        ModelAndView modelAndView = new ModelAndView();
        List<Post> allPost = postService.findAll();
        List<Post> postBanner = postService.findLatestPosts();
        model.addAttribute("post", allPost);
        model.addAttribute("postBanner", postBanner);

        modelAndView.setViewName("/index.html");
        return modelAndView;
    }

    @RequestMapping("/post/{slug}")
    public ModelAndView postUser(Model model, @PathVariable String slug) throws IllegalAccessException{
        ModelAndView modelAndView = new ModelAndView();
        Post findPost = postService.findPostBySlug(slug);
        model.addAttribute("post", findPost);
        modelAndView.setViewName("/page.html");
        return modelAndView;
    }
}
