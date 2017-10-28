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


</head>

<body>
<table border="0" cellspacing="0" cellpadding="0" width="100%">
    <tr>
        <td class="topg"></td>
    </tr>
</table>

<table border="0" cellspacing="0" cellpadding="0" class="wukuang" width="100%">
    <tr>
        <td width="1%"><img src="${pageContext.request.contextPath}/images/tleft.gif"/></td>
        <td width="39%" align="left">[部门管理]</td>

        <td width="57%" align="right">
            <%--添加部门 --%>
            <a href="${pageContext.request.contextPath}/pages/department/addOrEditDepartment.jsp">
                <img src="${pageContext.request.contextPath}/images/button/tianjia.gif"/>
            </a>

        </td>
        <td width="3%" align="right"><img src="${pageContext.request.contextPath}/images/tright.gif"/></td>
    </tr>
</table>
<table border="0" cellspacing="0" cellpadding="0" style="margin-top:5px;">
    <tr>
        <td><img src="${pageContext.request.contextPath}/images/result.gif"/></td>
    </tr>
</table>

<table width="100%" border="1" id="dept">

    <tr class="henglan" style="font-weight:bold;">
        <td width="6%" align="center">部门名称</td>
        <td width="7%" align="center">编辑</td>
    </tr>

    <c:forEach var="dept" items="${pageBean.data}">
        <tr class="tabtd">
            <td align="center">${dept.depName}</td>
            <td width="7%" align="center">
                <a href="${pageContext.request.contextPath}/pages/department/addOrEditDepartment.jsp?depName=${dept.depName}&depID=${dept.depID}"><img
                        src="${pageContext.request.contextPath}/images/button/modify.gif" class="img"/></a>
            </td>
        </tr>
    </c:forEach>


</table>


<table border="0" cellspacing="0" cellpadding="0" align="center">
    <tr>
        <td align="center">
            <span>
                <s:if test="#pageBean.pageNum gt 1">
                    <a href="${pageContext.request.contextPath}/findAllDept.action?pageNum=1">[首页]</a>&nbsp;&nbsp;
                    <a href="${pageContext.request.contextPath}/findAllDept.action?pageNum=<s:property value="#pageBean.pageNum - 1"/>">[上一页]</a>&nbsp;&nbsp;
                </s:if>
                <s:if test="#pageBean.pageNum lt #pageBean.totalPage">
                    <a href="${pageContext.request.contextPath}/findAllDept.action?pageNum=<s:property value="#pageBean.pageNum + 1"/>">[下一页]</a>&nbsp;&nbsp;
                    <a href="${pageContext.request.contextPath}/findAllDept.action?pageNum=<s:property value="#pageBean.totalPage"/>">[尾页]</a>
                </s:if>
            </span>&nbsp;&nbsp;&nbsp;&nbsp;
            <span>第<s:property value="#pageBean.pageNum"/>/<s:property value="#pageBean.totalPage"/>页</span>
        </td>
    </tr>
</table>
</body>

</html>
