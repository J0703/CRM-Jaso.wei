package com.lanou.trd.service;

import com.lanou.trd.domain.Classes;

import java.util.List;

/**
 * Created by dllo on 17/10/28.
 */
public interface ClassesService {


    List<Classes> findAllClasses();

    void addClasses(Classes classes);

    Classes findByID(String classID);

    void updateClases(Classes classes);
}
