<%--
  Created by IntelliJ IDEA.
  User: chene
  Date: 2020/2/27
  Time: 23:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="row">
    <div class="col-12 col-lg-7">
        <h2>更改信息</h2><br>
    </div>
    <div class="col-12 col-xl-6 col-lg-6">
        <h5>班级信息</h5>
        <div style="height: 0.5rem"></div>
        <div class="doneList">
            <table class="table table-hover" border="0px" width="400px">
                <tr><td width="100rem">学校</td><td>${group.getSchool()}</td></tr>
                <tr><td>学院</td><td>${group.getInstitution()}</td></tr>
                <tr><td>班级</td><td>${group.getGroupName()}</td></tr>
                <tr><td>班级代号</td>
                    <td>
                        <p id="groupSec">${group.getGroupSec()}</p>
                        <input type="hidden" id="groupSecInput" class="form-control">
                    </td></tr>
            </table>
            <input type="button" class="btn btn-primary" value="更改信息" id="changeInfo" onclick="showSubmitInfo()"/>
            <input type="hidden" class="btn btn-primary" value="提交" id="submitInfo" onclick="changeInfo()"/>

        </div>
        <br>
    </div>

    <div class="col-12 col-xl-6 col-lg-6">
        <h5>个人信息</h5>
        <div style="height: 0.5rem"></div>
        <input type="button" class="btn btn-secondary" value="修改管理员密码" data-toggle="modal" data-target="#changePasswordModal"/>
        <div style="height: 0.6rem"></div>
        <input type="button" class="btn btn-danger" onclick="logOut()" value="退出登录"/>

    </div>

</div>
