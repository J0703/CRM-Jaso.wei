<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>无标题文档</title>
    <link href="${pageContext.request.contextPath}/css/sys.css" type="text/css" rel="stylesheet"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/Calendar.js"></script>
    <script src="/js/jquery-3.2.1.js"></script>
    <script type="text/javascript">
        $(function () {
            $("#deptSelectId").change(function () {
                $("#postSelectId").empty();
                $("#postSelectId").append('<option value="-1">----请--选--择----</option>');
                $.post(
                        "${pageContext.request.contextPath}/listPost.action",
                        {
                            departid: $("#deptSelectId").val()
                        },
                        function (data) {

                            $.each(data, function (i, n) {
                                $("#postSelectId").append('<option  value=' + n.postId + '>' + n.postName + '</option>');
                            });
                        },
                        'json'
                );
            });
        });
    </script>

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
        <td width="44%" align="left">[员工管理]</td>

        <td width="52%" align="right">
            <!-- 提交表单 -->
            <a href="javascript:void(0)" onclick="document.forms[0].submit()">
                <img src="${pageContext.request.contextPath}/images/button/save.gif"/>
            </a>
            <!-- 执行js，进行返回 -->
            <a href="${pageContext.request.contextPath}/listStaff.action"><img
                    src="${pageContext.request.contextPath}/images/button/tuihui.gif"/></a>

        </td>
        <td width="3%" align="right"><img src="${pageContext.request.contextPath}/images/tright.gif"/></td>
    </tr>
</table>

<form action="${pageContext.request.contextPath}/updateStaff.action" method="post">

    <input type="hidden" name="staffId" value="${staff1.staffId}"/>

    <table width="88%" border="0" class="emp_table" style="width:80%;">
        <tr>
            <td>登录名：</td>
            <td><input type="text" name="loginName" value="${staff1.loginName}"/></td>
            <td>密码：</td>
            <td><input type="password" name="loginPwd" value="${staff1.loginPwd}"/></td>
        </tr>
        <tr>
            <td>姓名：</td>
            <td><input type="text" name="staffName" value="${staff1.staffName}"/></td>
            <td>性别：</td>
            <td>
                <input type="radio" name="gender"
                        <c:if test="${staff1.gender == '男'}">
                            checked="checked"
                        </c:if>
                       value="男"/>男
                <input type="radio" name="gender"
                        <c:if test="${staff1.gender == '女'}">
                            checked="checked"
                        </c:if>
                       value="女"/>女
            </td>
        </tr>
        <tr>
            <td width="10%">所属部门：</td>
            <td width="20%">
                <select name="depId" id="deptSelectId">
                    <option value="-1">----请--选--择----</option>
                    <c:forEach var="department" items="${departments}">
                        <option value="${department.depID}"
                                <c:if test="${staff1.post.department.depID == department.depID}">
                                    selected="selected"
                                </c:if>
                        >${department.depName}</option>
                    </c:forEach>
                </select>

            </td>
            <td width="8%">职务：</td>
            <td width="62%">
                <select name="postID" id="postSelectId">
                    <option value="-1">----请--选--择----</option>
                    <c:forEach var="dept" items="${departments}">
                        <c:if test="${staff1.post.department.depID == dept.depID}">
                        <c:forEach var="post" items="${dept.posts}">
                        <option value="${post.postId}"
                                <c:if test="${post.postId == staff1.post.postId}">
                                    selected="selected"
                                </c:if>
                        >${post.postName}</option>
                        </c:forEach>
                        </c:if>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td width="10%">入职时间：</td>
            <td width="20%">
                <input type="text" name="onDutyDate" value="${staff1.onDutyDate}" readonly="readonly"
                       onfocus="c.showMoreDay=true; c.show(this);"/>
            </td>
            <td width="8%"></td>
            <td width="62%"></td>
        </tr>
    </table>
</form>
<s:actionerror/>

</body>
</html>
