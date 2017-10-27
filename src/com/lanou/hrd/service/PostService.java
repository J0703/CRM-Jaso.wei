package com.lanou.hrd.service;

import com.lanou.hrd.domain.Post;

import java.util.List;

/**
 * Created by dllo on 17/10/25.
 */
public interface PostService {

    List<Post> findAllPost();

    void add(Post post);

    Post findById(String postId);

    void update(Post post1);
}
