package com.lanou.hrd.service.impl;

import com.lanou.hrd.dao.PostDao;
import com.lanou.hrd.domain.Post;
import com.lanou.hrd.service.PostService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dllo on 17/10/25.
 */
public class PostServiceImpl implements PostService {

    private PostDao postDao;

    @Override
    public List<Post> findAllPost() {
        return postDao.findAll("from Post");
    }

    @Override
    public void add(Post post) {
        postDao.add(post);
    }

    @Override
    public Post findById(String postId) {
        String hql = "from Post where postId =:id";
        Map<String ,Object> params = new HashMap();
        params.put("id",postId);
        return postDao.findSingle(hql,params);
    }

    @Override
    public void update(Post post1) {
        postDao.update(post1);
    }


    public PostDao getPostDao() {
        return postDao;
    }

    public void setPostDao(PostDao postDao) {
        this.postDao = postDao;
    }
}
