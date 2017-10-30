package com.lanou.hrd.service.impl;

import com.lanou.hrd.dao.PostDao;
import com.lanou.util.PageBean;
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

    @Override
    public PageBean<Post> findAll(Post post, int pageNum, int pageSize) {
        if (pageNum == 0) pageNum++;
        String hql = "select count(d) from Post d where 1=1";
        Object[] params={};
        String hql2 = "from Post";
        //总记录数
        int totalRecord = postDao.getTotalRecord(hql,params);
        if (totalRecord==0) totalRecord++;
        System.out.println("总记录数 : "+totalRecord);
        //创建对象
        PageBean<Post> pageBean =new PageBean<Post>();
        pageBean.setPageNum(pageNum);
        pageBean.setPageSize(pageSize);
        pageBean.setTotalRecord(totalRecord);
        //分页数据
        List<Post> data = postDao.findAll(hql2,params,pageBean.getStartIndex(),pageBean.getPageSize());
        //将分页数据封装到pagebean
        pageBean.setData(data);

        return pageBean;

    }


    public PostDao getPostDao() {
        return postDao;
    }

    public void setPostDao(PostDao postDao) {
        this.postDao = postDao;
    }
}
