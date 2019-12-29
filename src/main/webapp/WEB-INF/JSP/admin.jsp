<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: chene
  Date: 2019/12/27
  Time: 16:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>Admin</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/popper.js/1.15.0/umd/popper.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/admin.js"></script>
</head>

<body>

<div class="container">
    <div id="alertDiv" style="height: 0px;position: sticky;z-index: 9999;top:10px;display: none;">
        <div class="alert alert-success alert-dismissible fade show" style="position:sticky;" id="alert">
<%--            <button type="button" class="close" onclick="alertClose()">&times;</button>--%>
            <strong id="alertTitle">操作成功！</strong><br>
            <p id="alertTxt"></p>
        </div>
    </div>
    <div class="row">
        <div class="col-md-6">
            <div style="height: 1rem"></div>
            <h1>管理端</h1>
            <h5>上次登录时间：${lastTime}</h5>
            <label>当前团支书寄语:</label>
            <input class="form-control" type="text" id="sayingTxt" value="${group.getSaying()}">
            <br>
            <div class="btn-group-vertical col">
                <input class="btn btn-primary col" type="submit" name="saying" value="更改团支书寄语" onclick="setSaying()">
                <input class="btn btn-warning col" type="submit" name="new" value="开始新的大学习" onclick="startModal(this)">
            </div>
            <br><br>
        </div>
        <input name="requestUrl" id="requestUrl" type="hidden" value="${pageContext.request.contextPath}"/>

        <div class="col-md-6">
            <br>
            <h5>当前个人及管理信息</h5>
            <div class="doneList">
                <input type="button" class="btn btn-primary" value="更改信息" id="changeInfo" onclick="showSubmitInfo()"/>
                <input type="hidden" class="btn btn-primary" value="提交" id="submitInfo" onclick="changeInfo()"/>
                <input type="button" class="btn btn-secondary" value="修改管理员密码" data-toggle="modal" data-target="#changePasswordModal"/>
                <br><br>
                <table class="table table-hover" border="0px" width="400px">
                    <tr><td>学校</td><td>${group.getSchool()}</td></tr>
                    <tr><td>班级</td><td>${group.getGroupName()}</td></tr>
                    <tr><td>班级代号</td>
                        <td>
                            <p id="groupSec">${group.getGroupSec()}</p>
                            <input type="hidden" id="groupSecInput" class="form-control">
                        </td></tr>
                </table>

            </div>
        </div>
        <br>
        <br>
        <div class="col-md-6">
            <h5>已完成名单</h5>
            <div class="doneList">
                <c:choose>
                    <c:when test="${goodStudents!=null}">
                        <table class="table table-hover" border="0px" width="400px" id="completedTable">
                            <tr><th>姓名</th><th>时间</th></tr>
                            <c:forEach var="goodStudent" items="${goodStudents}">
                                <tr><td>${goodStudent.getName()}</td><td>${goodStudent.getTimeStr()}</td></tr>
                            </c:forEach>
                        </table>
                    </c:when>
                    <c:otherwise>
                        <p id="completedText">暂未有学生完成</p>
                    </c:otherwise>
                </c:choose>
                <p id="newCompletedText"></p>
            </div>
        </div>
        <div class="col-md-6 offset-lg-0 offset-md-0">
            <h5>更改名单</h5>

            <input class="btn btn-secondary" type="button" name="ck" id="ck" value="全部需要完成" onClick="allRequireYes()">
            <input class="btn btn-primary" type="button" value="添加成员" id="showAddStudent" data-toggle="collapse" data-target="#addStudent">

            <br>
            <div id="addStudent" class="collapse">
                <br>
                <label for="addStudentId">学号</label>
                <input class="form-control" type="text" id="addStudentId" onblur="addStudentIdOnblur()">
                <div id="addStudentIdFeedback"></div>

                <label for="addStudentName">姓名</label>
                <input class="form-control" type="text" id="addStudentName" onblur="addStudentNameOnblur()">
                <div id="addStudentNameFeedback"></div><br>

                <input class="btn btn-primary" type="button" value="提交" id="addStudentSubmit" onclick="addSubmit()">
            </div>


            <br><br>
            <table class="table table-hover" border="0px" width="400px" id="studentTable">
                <tr id="tableHead"><th>姓名及学号</th><th>是否要求完成</th><th>指令</th></tr>
                <c:forEach var="student" items="${studentsList}">
                    <tr name="${student.getId()}">
                        <td>
                            <h6 name="${student.getId()}">${student.getName()}</h6>
                            <p name="${student.getId()}">${student.getId()}</p>
                        </td>
                        <td style="vertical-align: middle;">
                            <div class="btn-group">
                                <c:choose>
                                    <c:when test="${student.isRequireState()}">
<%--                                        <input	class="btn btn-light requiredYes" type="button" value="更改为是" name="requiredYes" id="${student.getId()}" onclick="changeRequireStateToYes(this)" disabled/>--%>
                                        <input	class="btn btn-primary" name="requiredYes" type="button" value="当前为是" id="${student.getId()}" onclick="changeRequireStateToNo(this)"/>

                                    </c:when>
                                    <c:otherwise>
                                        <input	class="btn btn-light" name="requiredNo" type="button" value="当前为否" id="${student.getId()}" onclick="changeRequireStateToYes(this)"/>
<%--                                        <input	class="btn btn-light requiredNo" type="button" value="更改为否" name="requiredNo"  id="${student.getId()}" onclick="changeRequireStateToNo(this)" disabled/>--%>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </td>
                        <td style="vertical-align: middle;">
                            <input type="button" class="btn btn-danger" value="删除" name="delete" id="${student.getId()}" onclick="startModal(this)"/>
                        </td>
                    </tr>
                </c:forEach>
            </table>
<%--            <input class="btn btn-danger col" type="submit" name="Init" id="button2" value="重置名单">--%>
        </div>
        <br>
    </div>

<br>

<div class="modal fade" id="makeSureModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">你知道你在干嘛吗</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <div class="modal-body" id="modalContent">

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="modalCanceled()">返回</button>
                <button type="button" class="btn btn-danger" onclick="modalSubmitted()" id="modalSubmittedBtn">好</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="changePasswordModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">修改管理员密码</h4>
                <button type="button" class="close" data-dismiss="modal" onclick="changePasswordModalCanceled()">&times;</button>
            </div>
            <div class="modal-body">
                <form>
                    <label>班级：${group.getGroupName()}</label><br>
                    <label>班级代号：${group.getGroupSec()}</label><br>
                    <label for="oldPassword">原密码：</label>
                    <input class="form-control" type="password" id="oldPassword" onblur="oldPasswordOnblur()"/>
                    <div id="oldPasswordFeedback"></div>
                    <label for="newPassword">新密码：</label>
                    <input class="form-control" type="password" id="newPassword" onblur="newPasswordOnblur();newPasswordCommittedOnblur()"/>
                    <div id="newPasswordFeedback"></div>
                    <label for="newPasswordCommitted">确认新密码：</label>
                    <input class="form-control" type="password" id="newPasswordCommitted" onblur="newPasswordCommittedOnblur();newPasswordOnblur()"/>
                    <div id="newPasswordCommittedFeedback"></div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="changePasswordModalCanceled()">返回</button>
                <button type="button" class="btn btn-danger" onclick="passwordSubmitted()" id="passwordSubmittedBtn">更改密码</button>
            </div>
        </div>
    </div>
</div>
</div>
</body>
</html>

