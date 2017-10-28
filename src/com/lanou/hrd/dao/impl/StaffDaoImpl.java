package com.lanou.hrd.dao.impl;

import com.lanou.hrd.dao.StaffDao;
import com.lanou.hrd.domain.Staff;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dllo on 17/10/25.
 */
public class StaffDaoImpl extends BaseDaoImpl<Staff> implements StaffDao {
    @Override
    public Staff login(String name, String pwd) {
        String hql ="from Staff where loginName =? and loginPwd =?";
        Object[] params = {name,pwd};

        List<Staff> staffs = find(hql, params);
        System.out.println("登录信息 : "+staffs );
        if (staffs.size()>0) {
            return staffs.get(0);
        }
        return null;
    }

    @Override
    public Staff findByNAme(String loginName) {
        String hql ="from Staff where loginName =?";
        Object[] params = {loginName};

        List<Staff> staffs = find(hql, params);
        if (staffs.size()>0) {
            return staffs.get(0);
        }
        return null;
    }


}
