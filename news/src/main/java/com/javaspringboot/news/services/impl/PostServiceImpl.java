package com.javaspringboot.news.services.impl;

import com.javaspringboot.news.entities.Post;
import com.javaspringboot.news.repository.PostRepo;
import com.javaspringboot.news.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepo postRepo;

    @Override
    public List<Post> findAll() {
        return postRepo.findAll();
    }

    @Override
    public Post findById(Long id) throws IllegalAccessException {
        Optional<Post> op = postRepo.findById(id);
        if (op.isPresent()) {
            return op.get();
        }
        throw new IllegalAccessException("not found post by id " + id);
    }

    @Override
    public Post findPostBySlug(String slug) throws IllegalAccessException {
        try {
            Post findPost = postRepo.findPostBySlug(slug);
            return findPost;
        } catch (Exception e) {
            throw new IllegalAccessException("not found post by slug " + slug);
        }
    }

    @Override
    @Transactional
    public Post updatePostById(Long id, Post post) throws IllegalAccessException {
        Post findPost = findById(id);
        findPost.setPost_data(post.getPost_data());
        return postRepo.save(findPost);

    }

    @Override
    public Page<Post> findAllWithPage(Integer pageNo) {
        Pageable pageable = PageRequest.of(pageNo - 1, 5);
        return postRepo.findAll(pageable);
    }

    @Override
    public List<Post> findLatestPosts() {
        return postRepo.findLatestPosts();
    }

    @Override
    @Transactional
    public Post createPost(Post post, Long user_id) {
        Post create = new Post();
        create.setPost_data(post.getPost_data());
        create.setCreated_at(LocalDateTime.now());
        create.setCreated_by(user_id);
        create.setAuthor(post.getAuthor());
        create.setHint(post.getHint());
        create.setCategory(post.getCategory());
        create.setImage(post.getImage());
        create.setImage_name(post.getImage_name());
        create.setSlug(post.getSlug());
        create.setStatus("Active");
        create.setTags(post.getTags());
        create.setTitle(post.getTitle());
        create.setUpdated_by(user_id);
        return postRepo.save(create);
    }

}
