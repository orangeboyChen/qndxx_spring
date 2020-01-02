<%--
  Created by IntelliJ IDEA.
  User: chene
  Date: 2020/1/1
  Time: 12:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>注册</title>
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/popper.js/1.15.0/umd/popper.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/register/register.js"></script>


</head>

<body>
<input type="hidden" value="${pageContext.request.contextPath}" id="rootUrl">
<div class="container">
    <div id="alertDiv" style="height: 0px;position: sticky;z-index: 9999;top:10px;display: none;">
        <div class="alert alert-danger alert-dismissible" style="position:sticky;" id="alert">
            <%--            <button type="button" class="close" onclick="alertClose()">&times;</button>--%>
            <strong id="alertTitle">操作失败！</strong><br>
            <p id="alertTxt"></p>
        </div>
    </div>
    <div class="row">
        <div class="col-12 offset-xl-2 col-xl-8 col-lg-8 offset-lg-2">
            <div style="height: 3rem"></div>
            <h2>注册</h2>
            <h5>欢迎使用青年大学习提交系统！</h5>
            <p class="text-muted">欢迎</p>
        </div>
        <div class="col-12 col-lg-8 offset-xl-2 offset-lg-2">
            <div style="height: 2rem"></div>
            <h5>接下来的向导将帮助您注册</h5>
            <h6>如果您是普通学生或您已经拥有了管理员账号，请点击登录</h6>
            <h6>如果不是，请点击下一步继续</h6>
        </div>
        <div class="col-12 col-lg-8 offset-xl-2 offset-lg-2">
            <div style="height: 1.5rem"></div>
            <input type="button" class="btn btn-primary float-left" value="登录" onclick="registerBackward()">
            <input type="button" class="btn btn-primary float-right" value="下一步" onclick="registerForward()">
        </div>
    </div>
</div>
</body>
</html>

