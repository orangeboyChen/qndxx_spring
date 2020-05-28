<%--
  Created by IntelliJ IDEA.
  User: chene
  Date: 2020/1/1
  Time: 19:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/register/register4.js"></script>
<div class="row">
    <div class="col-12 col-xl-8 offset-xl-2 col-lg-8 offset-lg-2">
        <div style="height: 3rem"></div>
        <h2>注册</h2>
        <h5>欢迎使用青年作业提交系统！</h5>
        <p class="text-muted">(4/4)填写个人信息</p>
    </div>
    <div class="col-12 col-xl-8 offset-xl-2 col-lg-8 offset-lg-2">
        <div class="progress">
            <div class="progress-bar" id="progress-bar" role="progressbar" style="width: ${progress}%" aria-valuenow="${progress}" aria-valuemin="0" aria-valuemax="100"></div>
        </div>
        <br>
    </div>

    <div class="col-12 col-lg-8 offset-xl-2 offset-lg-2">
        <label for="adminRealName">管理员真实姓名<br>
            <small class="text-muted" id="adminRealNameLabel">输入真实姓名以便通过审核</small>
        </label>
        <input type="text" value="${realName}" class="form-control" id="adminRealName" aria-describedby="adminRealNameDescription" onblur="checkRealName()">
        <div id="adminRealNameFeedback"></div>

        <br>
        <label for="adminPassword">管理员密码<br>
            <small class="text-muted" id="adminPasswordLabel">验证管理员的依据且必须大于8位，小于16位</small>
        </label><input type="password" value="${password}" class="form-control" id="adminPassword" oninput="checkPassword()" maxlength="16">
        <div id="adminPasswordFeedback"></div>

        <br>
        <label for="adminPassword2">再次输入密码<br>
            <small class="text-muted" id="adminPasswordLabel2">请输入相同的密码</small>
        </label><input type="password" value="${password}" class="form-control" id="adminPassword2" oninput="checkPassword()" maxlength="16">
        <div id="adminPassword2Feedback"></div>

    </div>

    <div class="col-12 col-lg-8 offset-xl-2 offset-lg-2">
        <div style="height: 1.5rem"></div>
        <input type="button" class="btn btn-primary float-left" value="上一步" id="register4Previous" onclick="register4Backward()">
        <input type="button" class="btn btn-primary float-right" value="完成" id="register4Next" onclick="register4Forward()">
    </div>

</div>