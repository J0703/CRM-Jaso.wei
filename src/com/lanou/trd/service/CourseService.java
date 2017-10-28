package com.lanou.trd.service;

import com.lanou.trd.domain.Course;

import java.util.List;
import java.util.Map;

/**
 * Created by dllo on 17/10/28.
 */
public interface CourseService {
    void addCourse(Course course);

    List<Course> findAllCourse();

    Course findCourseByID(String courseTypeID);

    void updateCourse(Course course);

    List<Course> advFindCourse(Course course, Map<String, Object> params);
}
