<%--
  Created by IntelliJ IDEA.
  User: chene
  Date: 2020/1/1
  Time: 19:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/register/register3.js"></script>
<div class="row">
    <div class="col-12 col-xl-8 offset-xl-2 col-lg-8 offset-lg-2">
        <div style="height: 3rem"></div>
        <h2>注册</h2>
        <h5>欢迎使用青年大学习提交系统！</h5>
        <p class="text-muted">(3/4)填写联系方式</p>
    </div>
    <div class="col-12 col-xl-8 offset-xl-2 col-lg-8 offset-lg-2">
        <div class="progress">
            <div class="progress-bar" id="progress-bar" role="progressbar" style="width: ${progress}%" aria-valuenow="${progress}" aria-valuemin="0" aria-valuemax="100"></div>
        </div>
        <br>
    </div>

    <div class="col-sm-12 col-lg-8 col-12 offset-xl-2 offset-lg-2">

        <label for="adminEmail">管理员邮箱<br>
            <small class="text-muted" id="adminEmailLabel">输入正确的邮箱以接收审核邮件</small>
            <small class="text-danger" id="adminEmailLabel2">（请使用国内邮箱，并将nowcent.cn加入域名白名单或将register@nowcent.cn加入白名单，否则将收不到邮件）</small>
            <br>
            <a data-toggle="modal" data-target="#helpModal" onclick="startHelpModal()" href="">我需要协助</a>
        </label>

        <div class="input-group mb-3">
            <input type="text" value="${email}" class="form-control" id="adminEmail" aria-describedby="getCode" onblur="checkEmail()" maxlength="30">
            <div class="input-group-append">
                <input type="button" class="btn btn-outline-secondary" value="获取验证码" id="getCode" onclick="getCode()">
            </div>
        </div>
        <div id="adminEmailFeedback"></div>
    </div>
    <div id="checkDiv" class="col-md-7 col-lg-6 offset-xl-2 offset-lg-2">
        <label for="code">验证码</label>
        <input type="text" class="form-control col-6 col-sm-6" id="code" onblur="checkCode()" maxlength="6">
        <div id="codeFeedback"></div>
    </div>
    <div class="col-12 col-lg-8 offset-xl-2 offset-lg-2">
        <div style="height: 1.5rem"></div>
        <input type="button" class="btn btn-primary float-left" value="上一步" id="register3Previous" onclick="register3Backward()">
        <input type="button" class="btn btn-primary float-right" value="下一步" id="register3Next" onclick="register3Forward()">
    </div>

    <div class="modal fade bd-example-modal-xl" tabindex="-1" role="dialog" id="helpModal" aria-labelledby="helpModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-xl" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="helpModalLabel">协助</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body" id="helpContent">

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>
                </div>
            </div>
        </div>
    </div>

</div>

