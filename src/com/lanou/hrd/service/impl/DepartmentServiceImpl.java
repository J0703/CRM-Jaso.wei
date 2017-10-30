package com.lanou.hrd.service.impl;

import com.lanou.hrd.dao.DepartmentDao;
import com.lanou.hrd.domain.Department;
import com.lanou.util.PageBean;
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
    public PageBean<Department> findAll(Department department, int pageNum, int pageSize) {
        if (pageNum == 0) pageNum++;
        String hql = "select count(d) from Department d where 1=1";
        Object[] params={};
        String hql2 = "from Department";
        //总记录数
        int totalRecord = departmentDao.getTotalRecord(hql,params);
        if (totalRecord==0) totalRecord++;
        System.out.println("总记录数 : "+totalRecord);
        //创建对象
        PageBean<Department> pageBean =new PageBean<Department>();
        pageBean.setPageNum(pageNum);
        pageBean.setPageSize(pageSize);
        pageBean.setTotalRecord(totalRecord);
        //分页数据
        List<Department> data = departmentDao.findAll(hql2,params,pageBean.getStartIndex(),pageBean.getPageSize());
        //将分页数据封装到pagebean
        pageBean.setData(data);

        return pageBean;
    }

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
