package com.lanou.trd.action;

import com.lanou.trd.domain.Classes;
import com.lanou.trd.domain.Course;
import com.lanou.trd.service.ClassesService;
import com.lanou.trd.service.CourseService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by dllo on 17/10/30.
 */
@Controller("classesAction")
@Scope("prototype")
public class ClassesAction extends ActionSupport implements ModelDriven<Classes> {
    private Classes classes;
    private String courseID;

    private File schedule;//文件
    private String scheduleFileName;//提交文件名
    private String scheduleContentType;//提交文件格式

    private InputStream inputStream;//
    private String fileName;//下载文件名称

    @Autowired
    @Qualifier("classesService")
    private ClassesService classesService;
    @Autowired
    @Qualifier("courseService")
    private CourseService courseService;

    private List<Classes> classesList;
    private List<Course> courseList;
    private Classes classes1;


    /**
     * 上传
     */
    public String upLoad(){
        //回填表单
        classes1 = classesService.findByID(classes.getClassID());
        return SUCCESS;
    }
    public String scheduleUpLoad(){
        if (schedule == null){
            return INPUT;
        }
        String path = ServletActionContext.getServletContext().getRealPath("schedule");

        File destDirectory = new File(path);
        if (!destDirectory.exists() || !destDirectory.isDirectory()){
            destDirectory.mkdirs();
        }
        File file = new File(destDirectory,scheduleFileName);
        try {
            FileUtils.copyFile(schedule,file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    /**
     * 下载
     */
    public String downLoad(){
        String dirPath = ServletActionContext.getServletContext().getRealPath("schedule");
        File file = new File(dirPath,fileName);
        try {
            inputStream = new FileInputStream(file);
            //中文处理
            fileName = filenameEncoding(fileName,ServletActionContext.getRequest(),ServletActionContext.getResponse());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return SUCCESS;
    }
    /**
     * 中文处理
     */
    public String filenameEncoding(String filename, HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String agent = request.getHeader("User-Agent"); //         System.out.println(agent);
        if (agent.contains("Firefox")) {
            BASE64Encoder base64Encoder = new BASE64Encoder();
            filename = "=?utf-8?B?" + base64Encoder.encode(filename.getBytes("utf-8"))
                    + "?=";
        } else if (agent.contains("MSIE")) {
            filename = URLEncoder.encode(filename, "utf-8");
        } else if (agent.contains("Safari")) {
            filename = new String(filename.getBytes("UTF-8"), "ISO8859-1");
        } else {
            filename = URLEncoder.encode(filename, "utf-8");
        }
        return filename;
    }


    /**
     * 查询所有班级
     *
     * @return
     */
    public String findAllClasses() {
        classesList = classesService.findAllClasses();
        return SUCCESS;
    }

    /**
     * 添加/编辑 班级
     *
     * @return
     */
    public String findCourse() {
        courseList = courseService.findAllCourse();
        return SUCCESS;
    }

    public String addClasses() {
        //设置所属课程
        Course course1 = courseService.findCourseByID(courseID);
        classes.setCourse(course1);

        Date d = new Date();
        System.out.println(d);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateNowStr = sdf.format(d);
        System.out.println("格式化后的日期：" + dateNowStr);

        String startTime = String.valueOf(classes.getStartTime());
        String endTime = String.valueOf(classes.getEndTime());

        if(Long.valueOf(dateNowStr.replaceAll("-","")) < Long.valueOf(startTime.replaceAll("-", ""))){
            classes.setStatus("未开课");
        }else if(Long.valueOf(dateNowStr.replaceAll("-","")) < Long.valueOf(endTime.replaceAll("-", ""))){
            classes.setStatus("进行中");
        }
        if(Long.valueOf(dateNowStr.replaceAll("-","")) > Long.valueOf(endTime.replaceAll("-", ""))){
            classes.setStatus("已结束");
        }


        if (!classes.getClassID().equals("")) {
            //如果存在id,执行编辑操作
            classesService.updateClases(classes);
            return SUCCESS;
        }
        //添加
        classesService.addClasses(classes);
        return SUCCESS;
    }

    public String editClasses() {
        //表单回填
        classes1 = classesService.findByID(classes.getClassID());
        courseList = courseService.findAllCourse();
        return SUCCESS;
    }

    public void validateAddClasses() {
        if (StringUtils.isBlank(classes.getName()) || StringUtils.isBlank(classes.getRemark()) ||
                classes.getEndTime() == null || classes.getStartTime() == null || courseID.equals("-1")) {
            addActionError("请填写完整!");
        }
    }







    public Classes getClasses() {
        return classes;
    }

    public void setClasses(Classes classes) {
        this.classes = classes;
    }

    public List<Classes> getClassesList() {
        return classesList;
    }

    public void setClassesList(List<Classes> classesList) {
        this.classesList = classesList;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public Classes getClasses1() {
        return classes1;
    }

    public void setClasses1(Classes classes1) {
        this.classes1 = classes1;
    }

    public File getSchedule() {
        return schedule;
    }

    public void setSchedule(File schedule) {
        this.schedule = schedule;
    }

    public String getScheduleFileName() {
        return scheduleFileName;
    }

    public void setScheduleFileName(String scheduleFileName) {
        this.scheduleFileName = scheduleFileName;
    }

    public String getScheduleContentType() {
        return scheduleContentType;
    }

    public void setScheduleContentType(String scheduleContentType) {
        this.scheduleContentType = scheduleContentType;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public Classes getModel() {
        classes = new Classes();
        return classes;
    }
}
