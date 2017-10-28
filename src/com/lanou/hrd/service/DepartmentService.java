package com.lanou.hrd.service;

import com.lanou.hrd.domain.Department;
import com.lanou.hrd.domain.PageBean;

import java.util.List;

/**
 * Created by dllo on 17/10/25.
 */
public interface DepartmentService {


    public PageBean<Department> findAll(Department department, int pageNum, int pageSize);


    void add(Department department);

    List<Department> findAllDept();

    void update(Department department);


    Department findById(String depId);

}
