package com.lanou.hrd.service.impl;

import com.lanou.hrd.dao.DepartmentDao;
import com.lanou.hrd.domain.Department;
import com.lanou.hrd.service.DepartmentService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dllo on 17/10/25.
 */
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentDao departmentDao;

    @Override
    public void add(Department department) {
        departmentDao.add(department);
    }

    @Override
    public List<Department> findAllDept() {
        return departmentDao.findAll("from Department");
    }

    @Override
    public void update(Department department) {
        departmentDao.update(department);
    }

    @Override
    public Department findSingle(String depName) {
        String hql = "from Department where depName =:na";
        Map<String ,Object> params = new HashMap();
        params.put("na",depName);
        return departmentDao.findSingle(hql,params);
    }

    @Override
    public Department findById(String depId) {
        String hql = "from Department where depID =:id";
        Map<String ,Object> params = new HashMap();
        params.put("id",depId);
        return departmentDao.findSingle(hql,params);
    }


    public DepartmentDao getDepartmentDao() {
        return departmentDao;
    }

    public void setDepartmentDao(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }
}
