package com.lanou.trd.service.impl;

import com.lanou.trd.dao.CourseDao;
import com.lanou.trd.domain.Course;
import com.lanou.trd.service.CourseService;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by dllo on 17/10/28.
 */
public class CourseServiceImpl implements CourseService {


    private CourseDao courseDao;

    public CourseDao getCourseDao() {
        return courseDao;
    }

    public void setCourseDao(CourseDao courseDao) {


        this.courseDao = courseDao;
    }

    /**
     * 方法们
     * @param course
     */
    @Override
    public void addCourse(Course course) {
        courseDao.add(course);
    }

    @Override
    public List<Course> findAllCourse() {
        return courseDao.findAll("from Course");
    }

    @Override
    public Course findCourseByID(String courseTypeID) {
        return courseDao.findById(courseTypeID,Course.class);
    }

    @Override
    public void updateCourse(Course course) {
        courseDao.update(course);
    }

    @Override
    public List<Course> advFindCourse(Course course, Map<String, Object> params) {
        StringBuilder sbHQL = new StringBuilder();
        List param = new ArrayList<>();
        sbHQL.append("from Course where 1=1");
        if (!StringUtils.isBlank(course.getCourseName())){
            sbHQL.append(" and courseName =?");
            param.add(course.getCourseName());
        }
        if (!StringUtils.isBlank(course.getRemark())){
            sbHQL.append(" and remark like ?");
            param.add("%"+course.getRemark()+"%");
        }
        if ((int)params.get("totalStart") != 0){
            sbHQL.append(" and total >=?");
            param.add(params.get("totalStart"));
        }
        if ((int)params.get("totalEnd") !=0){
            sbHQL.append(" and total <=?");
            param.add(params.get("totalEnd"));
        }
        if (params.get("lessonCostStart") !=null){
            sbHQL.append(" and courseCost >=?");
            param.add(params.get("lessonCostStart"));
        }
        if (params.get("lessonCostEnd") !=null){
            sbHQL.append(" and courseCost <=?");
            param.add(params.get("lessonCostEnd"));
        }

        return courseDao.find(sbHQL.toString(),param.toArray());
    }
}
