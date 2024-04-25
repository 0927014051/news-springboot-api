package com.javaspringboot.news.services;
import java.util.List;

import com.javaspringboot.news.entities.Post;
public interface PostService {

    List<Post> findAll();

    Post findById(Long id) throws IllegalAccessException;

    Post findPostBySlug(String slug) throws IllegalAccessException;

    Post updatePostById(Long id,Post post) throws IllegalAccessException;

}
