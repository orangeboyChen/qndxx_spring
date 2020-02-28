<%--
  Created by IntelliJ IDEA.
  User: chene
  Date: 2020/2/27
  Time: 23:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="row">
    <div class="col-12">
        <h2>设置大学习</h2><br>
    </div>
    <div class="col col-lg-7 col-xl-7">
        <label id="sayingLabel" for="sayingTxt">团支书寄语<br>
            <small class="text-muted" id="groupNameLabel">同学们登录网页时，将会看到团支书寄语</small>
        </label>
        <div class="input-group mb-3">
            <input type="text" class="form-control"  aria-label="sayingLabel" aria-describedby="button-addon2" name="sayingTxt" id="sayingTxt" value="${saying}">
            <div class="input-group-append">
                <button class="btn btn-outline-primary" type="button" id="button-addon2" name="saying" onclick="setSaying()">更改</button>
            </div>
        </div>
        <br>
    </div>
    <div class="col-12 col-xl-7 offset-xl-0 col-lg-7">
        <input class="btn btn-primary col-6 float-right col-sm-4 col-md-3 col-lg-4" type="submit" name="new" value="开始新的大学习" onclick="startModal(this)">
    </div>
</div>