package com.lanou.hrd.domain;

/**
 * Created by dllo on 17/10/24.
 */
public class Post {
    private String postId;//主键id
    private String postName;//职务名称

    private Department department;//职务所属部门

    public Post(String postName) {
        this.postName = postName;
    }

    public Post() {

    }

    public Post(String postId, String postName, Department department) {
        this.postId = postId;
        this.postName = postName;
        this.department = department;
    }

    public Post(String postId, String postName) {
        this.postId = postId;
        this.postName = postName;
    }

    public Post(String postName, Department department) {
        this.postName = postName;
        this.department = department;
    }

    @Override
    public String toString() {
        return "Post{" +
                "postId='" + postId + '\'' +
                ", postName='" + postName + '\'' +
                '}';
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }
}
