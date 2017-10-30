package com.lanou.hrd.service;

import com.lanou.util.PageBean;
import com.lanou.hrd.domain.Post;

/**
 * Created by dllo on 17/10/25.
 */
public interface PostService {


    void add(Post post);

    Post findById(String postId);

    void update(Post post1);

    PageBean<Post> findAll(Post post, int pageNum, int pageSize);
}
