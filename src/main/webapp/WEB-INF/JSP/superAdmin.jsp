<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: chene
  Date: 2020/1/2
  Time: 17:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>超级管理员端</title>
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/popper.js/1.15.0/umd/popper.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/superAdmin.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
</head>

<body>
<div class="container">
    <div class="row">
        <input type="hidden" id="rootUrl" value="${pageContext.request.contextPath}" class="school form-control">
        <table class="table table-striped">
            <tr>
                <th>学校</th>
                <th>学院</th>
                <th>班级</th>
                <th>班级代号</th>
                <th>真实姓名</th>
                <th>Email</th>
                <th>时间</th>
                <th>操作</th>
            </tr>
            <c:forEach var="register" items="${registerList}">
                <tr name="${register.getNo()}">
                    <td name="${register.getNo()}">
                        <input type="text" name="${register.getNo()}" value="${register.getSchool()}" class="school form-control">
                    </td>

                    <td name="${register.getNo()}">
                        <input type="text" name="${register.getNo()}" value="${register.getInstitution()}" class="institution form-control">
                    </td>

                    <td name="${register.getNo()}">
                        <input type="text" name="${register.getNo()}" value="${register.getGroupName()}" class="groupName form-control">
                    </td>

                    <td name="${register.getNo()}">${register.getGroupSec()}</td>
                    <td name="${register.getNo()}">${register.getRealName()}</td>
                    <td name="${register.getNo()}" class="email">${register.getEmail()}</td>
                    <td name="${register.getNo()}">${register.getTime()}</td>
                    <td>
                        <input type="button" class="btn btn-primary" value="同意" name="${register.getNo()}" onclick="onagree(this)">
                        <input type="button" class="btn btn-danger" value="拒绝" name="${register.getNo()}" onclick="onreject(this)">
                    </td>
                </tr>

            </c:forEach>


            <tr>
                <td>武汉大学</td>
                <td>计算机学院</td>
                <td>19软卓</td>
                <td>19ruanzhuo</td>
                <td>orangeboy</td>
                <td>chenenhan@qq.com</td>
                <td>2019</td>
                <td>
                    <input type="button" class="btn btn-primary" value="同意">
                    <input type="button" class="btn btn-danger" value="拒绝">
                </td>
            </tr>
        </table>
    </div>
</div>
</body>
</html>

