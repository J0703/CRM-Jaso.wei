package com.lanou.trd.action;

import com.lanou.trd.domain.Course;
import com.lanou.trd.service.CourseService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dllo on 17/10/28.
 */
@Controller("courseAction")
@Scope("prototype")
public class CourseAction extends ActionSupport implements ModelDriven<Course> {
    private Course course;

    private String courseTypeID;//要编辑课程id
    private Course course1;//要编辑课程信息,(回显)

    //高级查询条件
    private int totalStart;
    private int totalEnd;
    private Double lessonCostStart;
    private Double lessonCostEnd;


    private List<Course> courses;//查询结果


    @Autowired
    @Qualifier("courseService")
    private CourseService courseService;

    @Override
    public Course getModel() {
        course = new Course();
        return course;
    }

    /**
     * 添加/编辑课程
     */
    public String addOrEditCourse() {
        if (course.getCourseTypeID().equals("")) {
            courseService.addCourse(course);
        }else {
            courseService.updateCourse(course);
        }
        return SUCCESS;
    }

    public void validateAddOrEditCourse() {
        if (StringUtils.isBlank(course.getCourseName()) || StringUtils.isBlank(course.getRemark()) ||
                course.getCourseCost() == 0.0 || course.getTotal()==0){
            addActionError("请填写完整!");
        }
    }

    //表单回填
    public String editCourse() {
        course1 = courseService.findCourseByID(courseTypeID);
        return SUCCESS;
    }

    /**
     * 查询所有课程
     */
    public String findAllCourse() {
        courses = courseService.findAllCourse();
        return SUCCESS;
    }

    /**
     * 高级查询
     */
    public String advFindCourse(){
        Map<String,Object> params = new HashMap<>();
        params.put("totalStart", totalStart);
        params.put("totalEnd", totalEnd);
        params.put("lessonCostStart",lessonCostStart);
        params.put("lessonCostEnd",lessonCostEnd);
        courses = courseService.advFindCourse(course,params);
        return SUCCESS;
    }



    /**
     * get/set
     */


    public Course getCourse() {
        return course;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public void setCourse(Course course) {


        this.course = course;
    }

    public String getCourseTypeID() {
        return courseTypeID;
    }

    public void setCourseTypeID(String courseTypeID) {
        this.courseTypeID = courseTypeID;
    }

    public Course getCourse1() {
        return course1;
    }

    public void setCourse1(Course course1) {
        this.course1 = course1;
    }

    public int getTotalStart() {
        return totalStart;
    }

    public void setTotalStart(int totalStart) {
        this.totalStart = totalStart;
    }

    public int getTotalEnd() {
        return totalEnd;
    }

    public void setTotalEnd(int totalEnd) {
        this.totalEnd = totalEnd;
    }

    public Double getLessonCostStart() {
        return lessonCostStart;
    }

    public void setLessonCostStart(Double lessonCostStart) {
        this.lessonCostStart = lessonCostStart;
    }

    public Double getLessonCostEnd() {
        return lessonCostEnd;
    }

    public void setLessonCostEnd(Double lessonCostEnd) {
        this.lessonCostEnd = lessonCostEnd;
    }
}
