package com.lanou.hrd.action;

import com.lanou.hrd.domain.Department;
import com.lanou.hrd.service.DepartmentService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

/**
 * Created by dllo on 17/10/25.
 */
public class DeptAction extends ActionSupport implements ModelDriven<Department> {

    private Department department;

    @Autowired
    @Qualifier("departmentService")
    private DepartmentService departmentService ;
    private List<Department> departments;//查询所有部门

    @Override
    public Department getModel() {
        department = new Department();
        return department;
    }

    /**
     * 查询所有部门
     */
    @Override
    public String execute() throws Exception {
        departments = departmentService.findAllDept();
        return SUCCESS;
    }

    /**
     * 添加/编辑 部门
     */
    public String addOrEditDepartment(){
        System.out.println("表单 : "+department.getDepID()+">>>"+department);
        if (department.getDepName().trim().equals("")){
            addActionError("请填写完整!");
            return INPUT;
        }

        //如果存在此部门,执行编辑功能
        if (!department.getDepID().trim().equals("")){
            Department department1 = new Department(department.getDepID(),department.getDepName());
            departmentService.update(department1);
            return SUCCESS;
        }

        departmentService.add(department);
        return SUCCESS;

    }







    /**
     * get/set
     */
    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }
}
