package com.lanou.hrd.action;

import com.lanou.hrd.domain.Department;
import com.lanou.hrd.domain.Post;
import com.lanou.hrd.service.DepartmentService;
import com.lanou.hrd.service.PostService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

/**
 * Created by dllo on 17/10/25.
 */
public class PostAction extends ActionSupport implements ModelDriven<Post>{


    private Post post ;
    private String deptId;

    @Autowired
    @Qualifier("departmentService")
    private DepartmentService departmentService ;

    @Autowired
    @Qualifier("postService")
    private PostService postService;

    private List<Post> posts;//获取所有职务
    private List<Department> departments;//查询所有部门


    /**
     * 显示所有职务
     * @return
     */
    @Override
    public String execute() throws Exception {
        posts = postService.findAllPost();
        return SUCCESS;
    }

    /**
     * 查询所有部门
     */
    public String findAllDept(){
        post = postService.findById(post.getPostId());
        departments = departmentService.findAllDept();
        return SUCCESS;
    }
    /**
     *添加/编辑职务
     * @return
     */
    public String addOrEditPost(){
        Department department1 = departmentService.findById(deptId);
        System.out.println("要编辑职务的部门id : "+deptId);

        if (post.getPostName().trim().equals("") || deptId.equals("-1")) {
            addActionError("请填写完整!");
            post = postService.findById(post.getPostId());
            departments = departmentService.findAllDept();
            return INPUT;
        }
        //如果有id,执行编辑
        if (!post.getPostId().equals("")){
            post.setDepartment(department1);
            System.out.println("修改后职务"+post);
            postService.update(post);
            return SUCCESS;
        }

        post.setDepartment(department1);
        postService.add(post);
        return SUCCESS;
    }









    @Override
    public Post getModel() {
        post = new Post();
        return post;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }
}
