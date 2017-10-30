package com.lanou.trd.action;

import com.lanou.trd.domain.Classes;
import com.lanou.trd.domain.Course;
import com.lanou.trd.service.ClassesService;
import com.lanou.trd.service.CourseService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * Created by dllo on 17/10/30.
 */
@Controller("classesAction")
@Scope("prototype")
public class ClassesAction extends ActionSupport implements ModelDriven<Classes> {
    private Classes classes;
    private String courseID;

    @Autowired
    @Qualifier("classesService")
    private ClassesService classesService;
    @Autowired
    @Qualifier("courseService")
    private CourseService courseService;

    private List<Classes> classesList;
    private List<Course> courseList;
    private Classes classes1;

    /**
     * 查询所有班级
     *
     * @return
     */
    public String findAllClasses() {
        classesList = classesService.findAllClasses();
        return SUCCESS;
    }

    /**
     * 添加班级
     *
     * @return
     */
    public String findCourse() {
        courseList = courseService.findAllCourse();
        return SUCCESS;
    }

    public String addClasses() {
        //设置所属课程
        Course course1 = courseService.findCourseByID(courseID);
        classes.setCourse(course1);
        if (!classes.getClassID().equals("")) {
            //如果存在id,执行编辑操作
            classesService.updateClases(classes);
            return SUCCESS;
        }
        //添加
        classesService.addClasses(classes);
        return SUCCESS;
    }

    public String editClasses() {
        //表单回填
        classes1 = classesService.findByID(classes.getClassID());
        courseList = courseService.findAllCourse();
        return SUCCESS;
    }

    public void validateAddClasses() {
        if (StringUtils.isBlank(classes.getName()) || StringUtils.isBlank(classes.getRemark()) ||
                classes.getEndTime() == null || classes.getStartTime() == null || courseID.equals("-1")) {
            addActionError("请填写完整!");
        }
    }

    /**
     * 上传
     * @return
     */
    public String upLoad(){
        //回填表单
        classes1 = classesService.findByID(classes.getClassID());
        return SUCCESS;
    }




    public Classes getClasses() {
        return classes;
    }

    public void setClasses(Classes classes) {
        this.classes = classes;
    }

    public List<Classes> getClassesList() {
        return classesList;
    }

    public void setClassesList(List<Classes> classesList) {
        this.classesList = classesList;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public Classes getClasses1() {
        return classes1;
    }

    public void setClasses1(Classes classes1) {
        this.classes1 = classes1;
    }

    @Override
    public Classes getModel() {
        classes = new Classes();
        return classes;
    }
}
