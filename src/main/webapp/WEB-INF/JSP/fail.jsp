<%--
  Created by IntelliJ IDEA.
  User: chene
  Date: 2019/12/27
  Time: 13:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/popper.js/1.15.0/umd/popper.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/result.js"></script>
    <title>提交失败</title>
</head>
<body>
<nav class="navbar navbar-dark bg-dark sticky-top">
    <div class="justify-content">
        <span class="navbar-brand">青年大学习在线提交</span>
    </div>
</nav>
<div class="container">
    <div style="height:4rem"></div>
    <div class="row justify-content-center">
        <img src="${pageContext.request.contextPath}/img/fail.png" width="100px" height="100px" alt="successPic" class="img-round"/>
    </div>
    <br>
    <div class="text-center">
        <h4>提交失败</h4>
        <p>${info}</p>
        <div class=" col-lg-4 offset-lg-4 col-md-6 offset-md-3 col-sm-8 offset-sm-2">
            <input type="button" class="btn btn-primary col" value="返回" onclick="window.location.href='${pageContext.request.contextPath}/'"/>
        </div>
    </div>
</div>
</body>
</html>
