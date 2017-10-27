package com.lanou.hrd.test;

import com.lanou.hrd.action.StaffAction;
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
        StaffAction staffAction = (StaffAction) context.getBean("staffAction");
        staffAction.listStaff();

    }
}
