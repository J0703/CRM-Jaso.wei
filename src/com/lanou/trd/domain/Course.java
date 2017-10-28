package com.lanou.trd.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by dllo on 17/10/28.
 */
public class Course {
    private String courseTypeID;//主键
    private Double courseCost;//课程费用
    private int total;//总课时
    private String courseName;//课程类别
    private String remark;//课程介绍

    //该课程的班级们
    private Set<Classes> classes = new HashSet<>();


    public Course(String courseTypeID, Double courseCost, int total, String courseName, String remark, Set<Classes> classes) {
        this.courseTypeID = courseTypeID;
        this.courseCost = courseCost;
        this.total = total;
        this.courseName = courseName;
        this.remark = remark;
        this.classes = classes;
    }

    public Course() {

    }

    public Course(Double courseCost, int total, String courseName, String remark) {
        this.courseCost = courseCost;
        this.total = total;
        this.courseName = courseName;
        this.remark = remark;
    }

    public Course(Double courseCost, int total, String courseName, String remark, Set<Classes> classes) {

        this.courseCost = courseCost;
        this.total = total;
        this.courseName = courseName;
        this.remark = remark;
        this.classes = classes;
    }

    public Set<Classes> getClasses() {
        return classes;
    }

    public void setClasses(Set<Classes> classes) {
        this.classes = classes;
    }

    public String getCourseTypeID() {

        return courseTypeID;
    }

    public void setCourseTypeID(String courseTypeID) {
        this.courseTypeID = courseTypeID;
    }

    public Double getCourseCost() {
        return courseCost;
    }

    public void setCourseCost(Double courseCost) {
        this.courseCost = courseCost;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
