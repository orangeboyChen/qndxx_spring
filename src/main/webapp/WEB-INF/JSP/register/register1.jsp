<%--
  Created by IntelliJ IDEA.
  User: chene
  Date: 2020/1/1
  Time: 14:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/register/register1.js"></script>
<div class="row">
    <div class="col-12 col-xl-9 offset-xl-2 col-lg-9 offset-lg-2">
        <div style="height: 3rem"></div>
        <h2>注册</h2>
        <h5>欢迎使用青年作业提交系统！</h5>
        <p class="text-muted">(1/4)核对学校信息</p>

    </div>
    <div class="col-12 col-xl-8 offset-xl-2 col-lg-8 offset-lg-2">
        <div class="progress">
            <div class="progress-bar" id="progress-bar" role="progressbar" style="width: ${progress}%" aria-valuenow="${progress}" aria-valuemin="0" aria-valuemax="100"></div>
        </div>
        <br>
    </div>
    <div class="col-lg-4 col-12 offset-xl-2 offset-lg-2">
        <label for="school">学校</label>
        <input type="text" value="${school}" class="form-control" id="school" placeholder="例如：北京大学" maxlength="25" onblur="checkSchool()">
        <div id="schoolFeedback"></div>
    </div>
    <div class="col-lg-4 col-12">
        <label for="institution">学院</label>
        <input type="text" value="${institution}" class="form-control" id="institution" placeholder="例如：计算机学院" maxlength="25" onblur="checkInstitution()">
        <div id="institutionFeedback"></div>
    </div>
    <div class="col-12 col-lg-8 offset-xl-2 offset-lg-2">
        <div style="height: 1.5rem"></div>
        <input type="button" class="btn btn-primary float-left" value="上一步" id="register1Previous" onclick="register1Backward()">
        <input type="button" class="btn btn-primary float-right" value="下一步" id="register1Next" onclick="register1Forward()">
    </div>
</div>

