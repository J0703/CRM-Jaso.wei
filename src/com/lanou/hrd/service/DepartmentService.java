package com.lanou.hrd.service;

import com.lanou.hrd.domain.Department;

import java.util.List;

/**
 * Created by dllo on 17/10/25.
 */
public interface DepartmentService {

    void add(Department department);

    List<Department> findAllDept();

    void update(Department department);

    Department findSingle(String depName);

    Department findById(String depId);

}
