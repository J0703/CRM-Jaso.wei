<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>无标题文档</title>

    <link href="${pageContext.request.contextPath}/css/sys.css" type="text/css" rel="stylesheet"/>
    <script src="/js/jquery-3.2.1.js"></script>
    <script type="text/javascript">
        $(function () {

            $.post(
                    "${pageContext.request.contextPath}/listDept.action",
                    function (data) {
                        var _html = "";
                        $.each(data, function (i, n) {
                            _html += '<option value=' + n.depID + ' >' + n.depName + '</option>';

                        });
                        $("#deptSelectId").append(_html);
                    },
                    'json'
            );

            $("#deptSelectId").change(function () {
                $("#postSelectId").empty();
                $("#postSelectId").append('<option value="-1">---请选择职务---</option>');
                $.post(
                        "${pageContext.request.contextPath}/listPost.action",
                        {
                            departid: $("#deptSelectId").val()
                        },
                        function (data) {

                            $.each(data, function (i, n) {
                                $("#postSelectId").append('<option value=' + n.postId + '>' + n.postName + '</option>');
                            });
                        },
                        'json'
                );
            });
        });
    </script>

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
        <td width="39%" align="left">[员工管理]</td>

        <td width="57%" align="right">
            <%--高级查询 --%>
            <a href="javascript:void(0)" onclick="document.forms[0].submit()"><img
                    src="${pageContext.request.contextPath}/images/button/gaojichaxun.gif"/></a>
            <%--员工注入 --%>
            <a href="${pageContext.request.contextPath}/pages/staff/addStaff.jsp">
                <img src="${pageContext.request.contextPath}/images/button/tianjia.gif"/>
            </a>

        </td>
        <td width="3%" align="right"><img src="${pageContext.request.contextPath}/images/tright.gif"/></td>
    </tr>
</table>

<!-- 查询条件：马上查询 -->
<form id="conditionFormId" action="${pageContext.request.contextPath}/queryStaff.action" method="post">
    <table width="88%" border="0" style="margin: 20px;">
        <tr>
            <td width="80px">部门：</td>
            <td width="200px">

                <select name="depId" id="deptSelectId">
                    <option value="-1">--请选择部门--</option>
                </select>

            </td>
            <td width="80px">职务：</td>
            <td width="200px">

                <select name="postID" id="postSelectId">
                    <option value="-1">--请选择职务--</option>
                </select>

            </td>
            <td width="80px">姓名：</td>
            <td width="200px"><input type="text" name="staffName" size="12"/></td>
            <td></td>
        </tr>
    </table>
</form>


<table border="0" cellspacing="0" cellpadding="0" style="margin-top:5px;">
    <tr>
        <td><img src="${pageContext.request.contextPath}/images/result.gif"/></td>
    </tr>
</table>

<table width="100%" border="1">
    <tr class="henglan" style="font-weight:bold;">
        <td width="10%" align="center">员工姓名</td>
        <td width="6%" align="center">性别</td>
        <td width="12%" align="center">入职时间</td>
        <td width="15%" align="center">所属部门</td>
        <td width="10%" align="center">职务</td>
        <td width="10%" align="center">编辑</td>
    </tr>

    <c:forEach var="staff" items="${pageBean.data}">
        <tr class="tabtd2">
            <td align="center">${staff.staffName}</td>
            <td align="center">${staff.gender}</td>
            <td align="center">${staff.onDutyDate}</td>
            <td align="center">${staff.post.department.depName}</td>
            <td align="center">${staff.post.postName}</td>
            <td width="7%" align="center">

                <a href="${pageContext.request.contextPath}/editStaff.action?staffId=${staff.staffId}"><img
                        src="${pageContext.request.contextPath}/images/button/modify.gif" class="img"/></a>
            </td>
        </tr>
    </c:forEach>>

</table>
<c:choose>
    <c:when test="${not empty conMap}">
        <table border="0" cellspacing="0" cellpadding="0" align="center">
            <tr>
                <td align="right">

                    <span>

                <s:if test="#pageBean.pageNum gt 1">
                    <a href="${pageContext.request.contextPath}/queryStaff.action?pageNum=1&depId=${conMap["depId"]}&postID=${conMap["postID"]}&staffName=${conMap["staffName"]}">[首页]</a>&nbsp;&nbsp;
                    <a href="${pageContext.request.contextPath}/queryStaff.action?pageNum=<s:property value="#pageBean.pageNum - 1"/>&depId=${conMap["depId"]}&postID=${conMap["postID"]}&staffName=${conMap["staffName"]}">[上一页]</a>&nbsp;&nbsp;
                </s:if>
                <s:if test="#pageBean.pageNum lt #pageBean.totalPage">
                    <a href="${pageContext.request.contextPath}/queryStaff.action?pageNum=<s:property value="#pageBean.pageNum + 1"/>&depId=${conMap["depId"]}&postID=${conMap["postID"]}&staffName=${conMap["staffName"]}">[下一页]</a>&nbsp;&nbsp;
                    <a href="${pageContext.request.contextPath}/queryStaff.action?pageNum=<s:property value="#pageBean.totalPage"/>&depId=${conMap["depId"]}&postID=${conMap["postID"]}&staffName=${conMap["staffName"]}">[尾页]</a>
                </s:if>
            </span>&nbsp;&nbsp;&nbsp;&nbsp;
                    <span>第<s:property value="#pageBean.pageNum"/>/<s:property value="#pageBean.totalPage"/>页</span>
                </td>
            </tr>
        </table>
    </c:when>
    <c:otherwise>
        <table border="0" cellspacing="0" cellpadding="0" align="center">
            <tr>
                <td align="center">

                    <span>

                <s:if test="#pageBean.pageNum gt 1">
                    <a href="${pageContext.request.contextPath}/listStaff.action?pageNum=1">[首页]</a>&nbsp;&nbsp;
                    <a href="${pageContext.request.contextPath}/listStaff.action?pageNum=<s:property value="#pageBean.pageNum - 1"/>">[上一页]</a>&nbsp;&nbsp;
                </s:if>
                <s:if test="#pageBean.pageNum lt #pageBean.totalPage">
                    <a href="${pageContext.request.contextPath}/listStaff.action?pageNum=<s:property value="#pageBean.pageNum + 1"/>">[下一页]</a>&nbsp;&nbsp;
                    <a href="${pageContext.request.contextPath}/listStaff.action?pageNum=<s:property value="#pageBean.totalPage"/>">[尾页]</a>
                </s:if>
            </span>&nbsp;&nbsp;&nbsp;&nbsp;
                    <span>第<s:property value="#pageBean.pageNum"/>/<s:property value="#pageBean.totalPage"/>页</span>
                </td>
            </tr>
        </table>
    </c:otherwise>
</c:choose>

</body>
</html>
