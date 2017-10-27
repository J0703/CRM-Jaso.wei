package com.lanou.hrd.service.impl;

import com.lanou.hrd.dao.StaffDao;
import com.lanou.hrd.domain.Staff;
import com.lanou.hrd.service.StaffService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dllo on 17/10/25.
 */
public class StaffServiceImpl implements StaffService {

    private StaffDao staffDao;

    @Override
    public Staff login(String name, String pwd) {
        return staffDao.login(name,pwd);
    }

    @Override
    public void addStaff(Staff staff) {
        staffDao.add(staff);
    }

    @Override
    public List<Staff> findAllStaff() {
        return staffDao.findAll("from Staff");
    }

    @Override
    public List<Staff> findStaff(Staff staff, String depId, String postID) {
        List<String> params = new ArrayList<>();
        StringBuilder hql = new StringBuilder("from Staff s where 1=1");
        //按部门查   或   按部门和职务
        if (!depId.equals("-1") && postID.equals("-1")){

            hql.append(" and s.post.department.depID =?");
            params.add(depId);
        }else if (!depId.equals("-1") && !postID.equals("-1")){

            hql.append(" and s.post.department.depID =?");
            params.add( depId);
            hql.append(" and s.post.postId =?");
            params.add(postID);

        }
        //姓名查询
        if (!staff.getStaffName().trim().equalsIgnoreCase("")){

            hql.append(" and staffName like?");
            params.add(staff.getStaffName());
        }
        //无条件,查所有
        return staffDao.find(hql.toString(),params.toArray());
    }

    @Override
    public Staff findById(Serializable id, Class<Staff> tClass) {
        return staffDao.findById(id,tClass);
    }

    @Override
    public void updateStaff(Staff staff) {
        staffDao.update(staff);
    }

    ///////////////////////////////////////////////////////////////////////

    public StaffDao getStaffDao() {
        return staffDao;
    }

    public void setStaffDao(StaffDao staffDao) {
        this.staffDao = staffDao;
    }
}
