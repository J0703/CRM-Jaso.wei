<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>无标题文档</title>
    <link href="${pageContext.request.contextPath}/css/sys.css" type="text/css" rel="stylesheet"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/Calendar.js"></script>

</head>

<body class="emp_body">
<table border="0" cellspacing="0" cellpadding="0" width="100%">
    <tr>
        <td class="topg"></td>
    </tr>
</table>

<table border="0" cellspacing="0" cellpadding="0" class="wukuang" width="100%">
    <tr>
        <td width="1%"><img src="${pageContext.request.contextPath}/images/tleft.gif"/></td>
        <td width="44%" align="left">[编辑班级]</td>

        <td width="52%" align="right">
            <a href="javascript:void(0)" onclick="document.forms[0].submit()">
                <img src="${pageContext.request.contextPath}/images/button/save.gif"/>
            </a>
            <a href="${pageContext.request.contextPath}/findAllClasses.action"><img
                    src="${pageContext.request.contextPath}/images/button/tuihui.gif"/></a>

        </td>
        <td width="3%" align="right"><img src="${pageContext.request.contextPath}/images/tright.gif"/></td>
    </tr>
</table>

<form id="f" action="${pageContext.request.contextPath}/addClasses.action" method="post">
    <input type="hidden" name="classID" value="${classes1.classID}">
    <table width="88%" border="0" class="emp_table" style="width:80%;">
        <tr>
            <td width="10%">班级名称：</td>
            <td width="20%"><input type="text" name="name" value="${classes1.name}"/></td>
            <td width="8%">所属类别：</td>
            <td width="62%">
                <select name="courseID" id="course_id">
                    <option value="-1">---请选择---</option>

                    <c:forEach var="c" items="${courseList}">
                        <option value="${c.courseTypeID}"
                                <c:if test="${c.courseTypeID == classes1.course.courseTypeID}">
                                    selected = "selected"
                                </c:if>
                        >${c.courseName}</option>
                    </c:forEach>

                </select>
            </td>
        </tr>
        <tr>
            <td>开课时间：</td>
            <td>
                <input type="text" name="startTime" value="${classes1.startTime}" readonly="readonly"
                       onfocus="c.showMoreDay=true; c.show(this);"/>
            </td>
            <td>结业时间：</td>
            <td>
                <input type="text" name="endTime" value="${classes1.endTime}" readonly="readonly"
                       onfocus="c.showMoreDay=true; c.show(this);"/>
            </td>
        </tr>
        <tr>
            <td>其他说明：</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
        </tr>
        <tr>
            <td colspan="4"><textarea name="remark" cols="60" rows="10">${classes1.remark}</textarea></td>
        </tr>
    </table>
</form>
<s:actionerror/>

</body>
</html>
