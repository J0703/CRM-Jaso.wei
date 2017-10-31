package com.lanou.trd.domain;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by dllo on 17/10/28.
 */
public class Classes {
    private String classID;//主键
    private String name;//班级名称
    private Date startTime;//开班时间
    private Date endTime;//毕业时间
    private String status;//状态
    private int totalCount;//学生总数
    private int upgradeCount;//升级数
    private int changeCount;//转班数
    private int runoffCount;//退费数
    private String remark;//其他说明
    private String uploadPath;//课表路径
    private String uploadFileName;//课表名称
    private Date uploadTime;//上传时间

    private Course course;

    //班级的学生
//    private Set<Student> students = new HashSet<>();




    public Classes() {

    }

    public Classes(String name, Date startTime, Date endTime, String status, int totalCount, int upgradeCount, int changeCount, int runoffCount, String remark, String uploadPath, String uploadFileName, Date uploadTime) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.totalCount = totalCount;
        this.upgradeCount = upgradeCount;
        this.changeCount = changeCount;
        this.runoffCount = runoffCount;
        this.remark = remark;
        this.uploadPath = uploadPath;
        this.uploadFileName = uploadFileName;
        this.uploadTime = uploadTime;
    }

    public Classes(String name, Date startTime, Date endTime, String status, int totalCount, int upgradeCount, int changeCount, int runoffCount, String remark, String uploadPath, String uploadFileName, Date uploadTime, Course course) {

        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.totalCount = totalCount;
        this.upgradeCount = upgradeCount;
        this.changeCount = changeCount;
        this.runoffCount = runoffCount;
        this.remark = remark;
        this.uploadPath = uploadPath;
        this.uploadFileName = uploadFileName;
        this.uploadTime = uploadTime;
        this.course = course;
    }

    public Classes(String classID, String name, Date startTime, Date endTime, String status, int totalCount, int upgradeCount, int changeCount, int runoffCount, String remark, String uploadPath, String uploadFileName, Date uploadTime, Course course) {

        this.classID = classID;
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.totalCount = totalCount;
        this.upgradeCount = upgradeCount;
        this.changeCount = changeCount;
        this.runoffCount = runoffCount;
        this.remark = remark;
        this.uploadPath = uploadPath;
        this.uploadFileName = uploadFileName;
        this.uploadTime = uploadTime;
        this.course = course;
    }

    public String getClassID() {
        return classID;
    }

    public void setClassID(String classID) {
        this.classID = classID;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getUpgradeCount() {
        return upgradeCount;
    }

    public void setUpgradeCount(int upgradeCount) {
        this.upgradeCount = upgradeCount;
    }

    public int getChangeCount() {
        return changeCount;
    }

    public void setChangeCount(int changeCount) {
        this.changeCount = changeCount;
    }

    public int getRunoffCount() {
        return runoffCount;
    }

    public void setRunoffCount(int runoffCount) {
        this.runoffCount = runoffCount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUploadPath() {
        return uploadPath;
    }

    public void setUploadPath(String uploadPath) {
        this.uploadPath = uploadPath;
    }

    public String getUploadFileName() {
        return uploadFileName;
    }

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }
}
