package com.lanou.hrd.action;

import com.lanou.hrd.domain.Department;
import com.lanou.util.PageBean;
import com.lanou.hrd.domain.Post;
import com.lanou.hrd.domain.Staff;
import com.lanou.hrd.service.DepartmentService;
import com.lanou.hrd.service.PostService;
import com.lanou.hrd.service.StaffService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by dllo on 17/10/25.
 */
@Controller("staffAction")
@Scope("prototype")
public class StaffAction extends ActionSupport implements ModelDriven<Staff> {

    private Staff staff;//bean

    @Autowired
    @Qualifier("staffService")
    private StaffService staffService;
    @Autowired
    @Qualifier("departmentService")
    private DepartmentService departmentService;
    @Autowired
    @Qualifier("postService")
    private PostService postService;

    private List<Department> departments;//查到的部门集合
    private String departid;//部门id,用来查找对应职务
    private Set<Post> posts;//根据部门id查到的职务集合
    private List<Staff> staffs;//查询到的所有员工

    private String postID;//要编辑员工职务id
    private String depId;//要编辑员工部门id
    private Staff staff1;//要编辑的员工

    private String oldPassword;//旧密码
    private String newPassword;//新密码
    private String reNewPassword;//确认新密码

    private Staff loginStaff;//存入域中登录对象

    private int pageNum;//第一页开始
    private int pageSize=3;//每页显示三条
    private Map<String, String> conMap;//参数集合

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 登录
     *
     * @return
     */
    public String login() {
        Staff logStaff = staffService.login(staff.getLoginName(), staff.getLoginPwd());
        if (logStaff != null) {
            //将登录信息存入session
            System.out.println("session中登录人信息 : "+logStaff);
            ActionContext.getContext().getApplication().put("staffLogin",logStaff);
            return SUCCESS;
        } else {
            addActionError("用户名或密码错误!");
            return INPUT;
        }

    }

    /**
     * 针对登录验证
     */
    public void validateLogin() {
        if (StringUtils.isBlank(staff.getLoginName()) || StringUtils.isBlank(staff.getLoginPwd())) {
            addActionError("用户名或密码不能为空,请填写!");
        }
    }

    /**
     * 添加员工
     *
     * @return
     */
    public String addStaff() {
        if (StringUtils.isBlank(staff.getLoginName()) || StringUtils.isBlank(staff.getLoginPwd())
                || staff.getOnDutyDate() == null || StringUtils.isBlank(staff.getStaffName())
                || StringUtils.isBlank(staff.getGender()) || postID.equals("-1")) {
            addActionError("请填写完整信息!");
            return INPUT;
        }
        Staff staff1 = staffService.findByName(staff.getLoginName());
        if (staff1 != null){
            addActionError("此登录名已存在");
            return INPUT;
        }
        Post post1 = postService.findById(postID);
        staff.setPost(post1);
        staffService.addStaff(staff);
        return SUCCESS;
    }




    /**
     * 查询所有部门
     *
     * @return
     */
    public String findAllDept() {
        departments = departmentService.findAllDept();
        return SUCCESS;
    }

    /**
     * 根据部门id查职务
     *
     * @return
     */
    public String findPostByDeptId() {
        Department department1 = departmentService.findById(departid);
        posts = department1.getPosts();
        return SUCCESS;
    }


    /**
     * 条件查询
     * 显示所有员工
     *
     * @return
     */
    public String listStaff() {
        PageBean<Staff> pageBean = staffService.findAll(staff, pageNum, pageSize);
        System.out.println("pageBean : "+pageBean);
        ActionContext.getContext().put("pageBean",pageBean);
        return SUCCESS;
    }

    public String queryStaff() {
        conMap = new HashedMap();
        conMap.put("staffName",staff.getStaffName());
        conMap.put("depId",depId);
        conMap.put("postID",postID);
        PageBean<Staff> pageBean = staffService.conFindAll(staff, pageNum, pageSize,conMap);
        System.out.println("pageBean : "+pageBean);
        ActionContext.getContext().put("pageBean",pageBean);
        return SUCCESS;
    }


    /**
     * 编辑员工
     */
    public String editStaff() {
        staff1 = staffService.findById(staff.getStaffId(), Staff.class);
        staff1.setLoginPwd("");
        departments = departmentService.findAllDept();
        System.out.println("要编辑的员工 : " + staff1);

        return SUCCESS;
    }

    public String updateStaff() {
        if (StringUtils.isBlank(staff.getLoginName()) || StringUtils.isBlank(staff.getLoginPwd())
                || staff.getOnDutyDate() == null || StringUtils.isBlank(staff.getStaffName())
                || StringUtils.isBlank(staff.getGender()) || postID.equals("-1")) {
            addActionError("请填写完整信息!");
            return INPUT;
        }
        Staff staff1 = staffService.findByName(staff.getLoginName());
        if (staff1 != null && !staff.getStaffId().trim().equalsIgnoreCase(staff1.getStaffId()) ) {
            addActionError("此登录名已存在");
            return INPUT;
        }
        Post post1 = postService.findById(postID);
        staff.setPost(post1);
        System.out.println("修改后员工 : "+staff);
        staffService.updateStaff(staff);
        return SUCCESS;
    }

    /**
     * 修改密码
     * @return
     */

    public String updPwd(){
        loginStaff  = (Staff) ActionContext.getContext().getApplication().get("staffLogin");
        Staff staffServiceById = staffService.findById(loginStaff.getStaffId(), Staff.class);
        System.out.println("已登录的用户 : "+loginStaff);
        Staff staff2 = loginStaff;
        staff2.setLoginPwd(reNewPassword);
        System.out.println("修改密码后用户:"+staff2);
        staffService.updateStaff(staff2);
        return SUCCESS;

    }

    public void validateUpdPwd() {
        loginStaff  = (Staff) ActionContext.getContext().getApplication().get("staffLogin");
        Staff staffServiceById = staffService.findById(loginStaff.getStaffId(), Staff.class);
        System.out.println("已登录的用户(yz) : "+loginStaff);
        if (staffService.login(staffServiceById.getLoginName(),oldPassword) == null){
            addActionError("旧密码输入错误!");
        }
        if (!newPassword.equals(reNewPassword)) {
            addActionError("两次输入密码不同!请重新输入!");
        }
        if (staffServiceById.getLoginPwd().equals(reNewPassword)){
            addActionError("新密码不得与旧密码相同!");
        }
        if (oldPassword.trim().equals("") || newPassword.trim().equals("") || reNewPassword.trim().equals("")){
            addActionError("请填写完整!");
        }

    }

    /**
     * 退出登录
     * @return
     */
    public String outLogin(){
        ActionContext.getContext().getApplication().remove("staffLogin");
        return SUCCESS;
    }

    @Override
    public Staff getModel() {
        staff = new Staff();
        return staff;
    }


    /**
     * get/set方法------------------------------------------------------------------------------------
     *
     * @return
     */


    public String getPostID() {
        return postID;
    }


    public void setPostID(String postID) {
        this.postID = postID;
    }

    public List<Staff> getStaffs() {
        return staffs;
    }

    public void setStaffs(List<Staff> staffs) {
        this.staffs = staffs;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    public String getDepartid() {
        return departid;
    }

    public void setDepartid(String departid) {
        this.departid = departid;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {

        this.staff = staff;
    }


    public Staff getStaff1() {
        return staff1;
    }

    public void setStaff1(Staff staff1) {
        this.staff1 = staff1;
    }

    public String getDepId() {
        return depId;
    }

    public void setDepId(String depId) {
        this.depId = depId;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getReNewPassword() {
        return reNewPassword;
    }

    public void setReNewPassword(String reNewPassword) {

        this.reNewPassword = reNewPassword;
    }

    public Staff getLoginStaff() {
        return loginStaff;
    }

    public void setLoginStaff(Staff loginStaff) {

        this.loginStaff = loginStaff;
    }

    public Map<String, String> getConMap() {
        return conMap;
    }

    public void setConMap(Map<String, String> conMap) {
        this.conMap = conMap;
    }
}
