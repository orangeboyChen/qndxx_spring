<%--
  Created by IntelliJ IDEA.
  User: chene
  Date: 2020/1/1
  Time: 14:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/register/register2.js"></script>
<div class="row">
    <div class="col-12 col-xl-8 offset-xl-2 col-lg-8 offset-lg-2">
        <div style="height: 3rem"></div>
        <h2>注册</h2>
        <h5>欢迎使用青年大学习提交系统！</h5>
        <p class="text-muted">(2/4)核对班级信息</p>
    </div>
    <div class="col-12 col-xl-8 offset-xl-2 col-lg-8 offset-lg-2">
        <div class="progress">
            <div class="progress-bar" id="progress-bar" role="progressbar" style="width: ${progress}%" aria-valuenow="${progress}" aria-valuemin="0" aria-valuemax="100"></div>
        </div>
        <br>
    </div>

    <div class="col-lg-8 col-12 offset-xl-2 offset-lg-2">
        <label for="groupName">班级<br>
            <small class="text-muted" id="groupNameLabel">请同时输入届级</small>
        </label>
        <input type="text" value="${groupName}" class="form-control" id="groupName" placeholder="例如：19-计科7" onblur="checkGroupName()" maxlength="15">
        <div id="groupNameFeedback"></div>
        <br>
        <label for="groupSec">班级代号<br>
            <small class="text-muted" id="groupSecLabel">可以帮助同学们快速找到班级</small>
        </label>
        <input type="text" value="${groupSec}" class="form-control" id="groupSec" placeholder="例如：pku19zihuan" onblur="checkGroupSec()" maxlength="15">
        <div id="groupSecFeedback"></div>
    </div>
    <div class="col-12 col-lg-8 offset-xl-2 offset-lg-2">
        <div style="height: 1.5rem"></div>
        <input type="button" class="btn btn-primary float-left" id="register2Previous" value="上一步" onclick="register2Backward()">
        <input type="button" class="btn btn-primary float-right" id="register2Next" value="下一步" onclick="register2Forward()">
    </div>
</div>


