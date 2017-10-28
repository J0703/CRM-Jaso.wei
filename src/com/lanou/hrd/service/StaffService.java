package com.lanou.hrd.service;

import com.lanou.hrd.domain.PageBean;
import com.lanou.hrd.domain.Staff;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by dllo on 17/10/25.
 */
public interface StaffService {

    Staff login(String name,String pwd);

    void addStaff(Staff staff);


    Staff findById(Serializable id, Class<Staff> tClass);

    void updateStaff(Staff staff);

    Staff findByName(String loginName);

    PageBean<Staff> findAll(Staff staff, int pageNum, int pageSize);

    PageBean<Staff> conFindAll(Staff staff, int pageNum, int pageSize, Map<String, String> conMap);
}
