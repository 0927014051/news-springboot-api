package com.javaspringboot.news.services;

import com.javaspringboot.news.entities.Post;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PostService {

    List<Post> findAll();

    Post findById(Long id) throws IllegalAccessException;

    Post findPostBySlug(String slug) throws IllegalAccessException;

    Post updatePostById(Long id, Post post) throws IllegalAccessException;

    Page<Post> findAllWithPage(Integer pageNo);

    List<Post> findLatestPosts();

    Post createPost(Post post, Long user_id);
}
