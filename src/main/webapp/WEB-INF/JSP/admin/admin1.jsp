<%--
  Created by IntelliJ IDEA.
  User: chene
  Date: 2020/2/27
  Time: 21:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="row">
    <div class="col-12">
        <h2>${greetingMsg}</h2><br>
    </div>
    <div class="col-md-6 col-sm-8 col-12 col-xl-4 col-lg-5">
        <div class="card">
            <div class="card-body">
                <h5 class="card-title">完成人数</h5>
                <h3 class="d-inline"><span class="badge badge-success">${completedCount}</span><small> 人</small></h3>
                <p class="card-text text-muted">从${startTime}开始学习</p>
                <a href="javascript:toSetting()" class="card-link">开始新的作业</a>
                <a href="javascript:toCompletedList()" class="card-link">看看谁交了</a>
            </div>
        </div>
        <br>
    </div>

    <div class="col-md-6 col-sm-8 col-12 col-xl-4 col-lg-5">
        <div class="card">
            <div class="card-body">
                <h5 class="card-title">上次登录时间</h5>
                <h3 class="d-inline"><span class="badge badge-success">${lastTime}</span></h3>
                <p class="card-text text-muted">如果这不是你的操作，请更改密码。</p>
                <a href="javascript:toChangeInfo()" class="card-link">更改密码</a>
            </div>
        </div>
    </div>
</div>
<br>
<div class="col-12">
    <h6>如需更改设定，请从上方选项卡选择功能进行操作。</h6>
</div>
