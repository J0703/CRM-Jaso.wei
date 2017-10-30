package com.lanou.trd.service.impl;

import com.lanou.trd.dao.ClassesDao;
import com.lanou.trd.domain.Classes;
import com.lanou.trd.service.ClassesService;

import java.util.List;

/**
 * Created by dllo on 17/10/28.
 */
public class ClassesServiceImpl implements ClassesService{

    private ClassesDao classesDao;

    @Override
    public List<Classes> findAllClasses() {
        return classesDao.findAll("from Classes");
    }

    @Override
    public void addClasses(Classes classes) {
        classesDao.add(classes);
    }

    @Override
    public Classes findByID(String classID) {

        return classesDao.findById(classID,Classes.class);
    }

    @Override
    public void updateClases(Classes classes) {
        classesDao.update(classes);
    }


    public ClassesDao getClassesDao() {
        return classesDao;
    }

    public void setClassesDao(ClassesDao classesDao) {
        this.classesDao = classesDao;
    }
}
