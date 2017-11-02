package com.lanou.test;

import com.lanou.hrd.action.StaffAction;
import com.lanou.trd.action.CourseAction;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by dllo on 17/10/26.
 */
public class MainTest {

    private ApplicationContext context;

    @Before
    public void init(){
        context = new ClassPathXmlApplicationContext("spring-config.xml");

    }

    @Test
    public void test(){
//        StaffAction staffAction = (StaffAction) context.getBean("staffAction");
//        staffAction.listStaff();
        CourseAction courseAction = (CourseAction) context.getBean("courseAction");
        courseAction.advFindCourse();

    }
    @Test
    public void test2(){
        double a=3.0;
        int b = 4;
        System.out.println(a/=b);
    }
}
