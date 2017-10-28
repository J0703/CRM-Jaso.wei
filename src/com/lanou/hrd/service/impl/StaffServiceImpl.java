package com.lanou.hrd.service.impl;

import com.lanou.hrd.dao.StaffDao;
import com.lanou.hrd.domain.Department;
import com.lanou.hrd.domain.PageBean;
import com.lanou.hrd.domain.Staff;
import com.lanou.hrd.service.StaffService;
import org.apache.commons.lang3.StringUtils;

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
        return staffDao.login(name, pwd);
    }

    @Override
    public void addStaff(Staff staff) {
        staffDao.add(staff);
    }


    @Override
    public Staff findById(Serializable id, Class<Staff> tClass) {
        return staffDao.findById(id, tClass);
    }

    @Override
    public void updateStaff(Staff staff) {
        staffDao.update(staff);
    }

    @Override
    public Staff findByName(String loginName) {
        return staffDao.findByNAme(loginName);
    }

    @Override
    public PageBean<Staff> findAll(Staff staff, int pageNum, int pageSize) {
        if (pageNum == 0) pageNum++;
        String hql = "select count(d) from Staff d where 1=1";
        Object[] params = {};
        String hql2 = "from Staff";
        //总记录数
        int totalRecord = staffDao.getTotalRecord(hql, params);
        System.out.println("总记录数 : " + totalRecord);
        //创建对象
        PageBean<Staff> pageBean = new PageBean<Staff>();
        pageBean.setPageNum(pageNum);
        pageBean.setPageSize(pageSize);
        pageBean.setTotalRecord(totalRecord);
        //分页数据
        List<Staff> data = staffDao.findAll(hql2, params, pageBean.getStartIndex(), pageBean.getPageSize());
        //将分页数据封装到pagebean
        pageBean.setData(data);

        return pageBean;
    }

    @Override
    public PageBean<Staff> conFindAll(Staff staff, int pageNum, int pageSize, Map<String, String> conMap) {
        if (pageNum == 0) pageNum++;
        StringBuilder sbHql = new StringBuilder();
        List<String> condition = new ArrayList<>();
        sbHql.append("from Staff s where 1=1");

        if (!conMap.get("depId").equals("-1")){
            sbHql.append(" and s.post.department.depID =?");
            condition.add(conMap.get("depId"));
        }
        if (!conMap.get("postID").equals("-1")){
            sbHql.append(" and s.post.postId =?");
            condition.add(conMap.get("postID"));
        }
        if (!conMap.get("staffName").trim().equals("")){
            sbHql.append(" and staffName like?");
            condition.add(conMap.get("staffName"));
        }
        String sql = "select count(s) "+sbHql.toString();
        //总记录数
        int totalRecord = staffDao.getTotalRecord(sql,condition.toArray() );
        if (totalRecord==0) totalRecord++;
        System.out.println("总记录数 : " + totalRecord);
        //创建对象
        PageBean<Staff> pageBean = new PageBean<Staff>();
        pageBean.setPageNum(pageNum);
        pageBean.setPageSize(pageSize);
        pageBean.setTotalRecord(totalRecord);
        //分页数据
        List<Staff> data = staffDao.findAll(sbHql.toString(), condition.toArray(), pageBean.getStartIndex(), pageBean.getPageSize());
        //将分页数据封装到pagebean
        pageBean.setData(data);

        return pageBean;
    }

    ///////////////////////////////////////////////////////////////////////

    public StaffDao getStaffDao() {
        return staffDao;
    }

    public void setStaffDao(StaffDao staffDao) {
        this.staffDao = staffDao;
    }
}
