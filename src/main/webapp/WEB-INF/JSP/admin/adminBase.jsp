<%--
  Created by IntelliJ IDEA.
  User: chene
  Date: 2020/2/27
  Time: 18:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>青年大学习在线提交-管理员</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/popper.js/1.15.0/umd/popper.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/admin.js"></script>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark navbar-collapse sticky-top" id="navbar">
    <div class="justify-content">
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarToggler" aria-controls="navbarToggler" aria-expanded="false" aria-label="Toggle navigation" style="margin-right: 0.5rem">
            <span class="navbar-toggler-icon"></span>
        </button>
        <span class="navbar-brand">青年大学习在线提交</span>
    </div>
    <div class="collapse navbar-collapse" id="navbarToggler">
        <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
            <li class="nav-item active" id="toIndexLi">
                <a class="nav-link" href="javascript:toIndex()">概述<span class="sr-only"></span></a>
            </li>
            <li class="nav-item" id="toSettingLi">
                <a class="nav-link" href="javascript:toSetting()">设置大学习<span class="sr-only"></span></a>
            </li>
            <li class="nav-item" id="toCompletedListLi">
                <a class="nav-link" href="javascript:toCompletedList()">已完成名单</a>
            </li>
            <li class="nav-item" id="toChangeListLi">
                <a class="nav-link" href="javascript:toChangeList()">更改名单</a>
            </li>
            <li class="nav-item" id="toChangeInfoLi">
                <a class="nav-link" href="javascript:toChangeInfo()">更改信息</a>
            </li>

        </ul>
        <form class="form-inline my-2 my-lg-0">
            <button class="btn btn-outline-danger my-2 my-sm-0" type="button" onclick="logOut()">退出登录</button>
        </form>
    </div>
</nav>

<div id="alertDiv" style="height: 0px;position: sticky;z-index: 9999; margin-left: 0.5rem;margin-right: 0.5rem;top:70px;" class="sticky-top">
    <div class="alert alert-success alert-dismissible fade show" style="position:sticky;" id="alert">
        <%--            <button type="button" class="close" onclick="alertClose()">&times;</button>--%>
        <strong id="alertTitle">操作成功！</strong><br>
        <p id="alertTxt"></p>
    </div>
</div>
<input name="requestUrl" id="requestUrl" type="hidden" value="${pageContext.request.contextPath}"/>

<div style="height: 2rem"></div>
<div class="container" id="content">

</div>

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
                    <label for="oldPassword">原密码</label>
                    <input class="form-control" type="password" id="oldPassword" onblur="oldPasswordOnblur()" maxlength="16"/>
                    <div id="oldPasswordFeedback"></div>
                    <label for="newPassword">新密码</label>
                    <small class="text-muted" for="newPassword">新密码须不小于8位且不大于16位</small>
                    <input class="form-control" type="password" id="newPassword" oninput="newPasswordOninput();newPasswordCommittedOninput()" maxlength="16"/>
                    <div id="newPasswordFeedback"></div>
                    <label for="newPasswordCommitted">确认新密码</label>
                    <input class="form-control" type="password" id="newPasswordCommitted" oninput="newPasswordCommittedOninput();newPasswordOninput()" maxlength="16"/>
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
</body>
</html>

