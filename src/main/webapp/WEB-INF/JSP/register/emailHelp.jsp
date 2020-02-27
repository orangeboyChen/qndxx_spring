<%--
  Created by IntelliJ IDEA.
  User: chene
  Date: 2020/2/26
  Time: 23:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<h5>收不到验证码邮件怎么办</h5>
<div style="height: 1rem"></div>
<h6>由于QQ邮箱的封杀，一般情况下该验证邮件会被认作拦截邮件处理。请根据以下步骤找回验证邮件。</h6>
<br>
<div class="accordion" id="accordionExample">
    <div class="card">
        <div class="card-header" id="headingOne">
            <h2 class="mb-0">
                <button class="btn btn-link" type="button" data-toggle="collapse" data-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                    QQ邮箱网页版
                </button>
            </h2>
        </div>
        <div id="collapseOne" class="collapse" aria-labelledby="headingOne" data-parent="#accordionExample">
            <div class="card-body">
                <h6>1. 登录QQ邮箱网页版，点击右下角“自助查询”</h6>
                <img src="img/emailHelp/QQMailWeb1.png" width="100%" ><br><br>
                <h6>2. 点击选项卡“收信查询”</h6>
                <img src="img/emailHelp/QQMailWeb2.png" width="100%"><br><br>
                <h6>3. 找到发件人为“register”的邮件(验证码的发送邮箱为register@nowcent.cn)，在右侧点击“添加到白名单”。</h6>
                <img src="img/emailHelp/QQMailWeb3.png" width="100%"><br><br>
                <h6>4. 提示成功后，在收件箱即可找到验证码邮件。</h6>
                <img src="img/emailHelp/QQMailWeb4.png" width="100%"><br><br>
            </div>
        </div>
    </div>
    <div class="card">
        <div class="card-header" id="headingTwo">
            <h2 class="mb-0">
                <button class="btn btn-link collapsed" type="button" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                    QQ邮箱手机版
                </button>
            </h2>
        </div>
        <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionExample">
            <div class="card-body">
                <h6>1. 登录QQ邮箱APP（此处以安卓为例），点击右上角的加号。</h6>
                <img src="img/emailHelp/QQMailApp1.png" width="80%" ><br><br>
                <h6>2. 点击“设置”。</h6>
                <img src="img/emailHelp/QQMailApp2.png" width="80%"><br><br>
                <h6>3. 点击“白名单”。</h6>
                <img src="img/emailHelp/QQMailApp3.png" width="80%"><br><br>
                <h6>4. 点击白名单右上角的加号。</h6>
                <img src="img/emailHelp/QQMailApp4.png" width="80%"><br><br>
                <h6>5. 在输入框输入nowcent.cn，并点击右上角添加。然后在收件箱即可找到验证邮件，或在注册界面再次获取验证码重新获得邮件。</h6>
                <img src="img/emailHelp/QQMailApp5.png" width="80%"><br><br>
            </div>
        </div>
    </div>
</div>
