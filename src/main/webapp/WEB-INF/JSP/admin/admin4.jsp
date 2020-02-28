<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: chene
  Date: 2020/2/27
  Time: 23:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="row">
    <div class="col-12 col-lg-7">
        <h2>更改名单</h2><br>
        <h4 class="d-inline"><span class="badge badge-success" id="requiredCount">${requiredStudentCount}</span><small> 人需要学习</small></h4>
        <br><br>
    </div>
    <div class="col col-lg-7 col-xl-7">
        <input class="btn btn-primary" type="button" value="添加成员" id="showAddStudent" data-toggle="collapse" data-target="#addStudent">
        <div id="addStudent" class="collapse">
            <div style="height: 1rem"></div>
            <div class="card">
                <div class="card-body">
                    <input type="button" class="btn btn-primary" value="批量添加学生" onclick="toAddMutiple()">
                    <div style="height: 1rem"></div>
                    <label for="addStudentId">学号</label>
                    <input class="form-control" type="text" id="addStudentId" onblur="addStudentIdOnblur()">
                    <div id="addStudentIdFeedback"></div>
                    <label for="addStudentName">姓名</label>
                    <input class="form-control" type="text" id="addStudentName" onblur="addStudentNameOnblur()">
                    <div id="addStudentNameFeedback"></div><br>
                    <input class="btn btn-primary" type="button" value="提交" id="addStudentSubmit" onclick="addSubmit()">
                </div>
            </div>
            <div style="height: 1rem"></div>
        </div>
        <div style="height: 0.5rem"></div>
        <input class="btn btn-secondary" type="button" name="ck" id="ck" value="全部需要完成" onClick="allRequireYes()">
        <input class="btn btn-danger float-right" type="button" id="deleteOn" value="显示危险指令" onClick="dangerOn()">
        <div style="height: 0.5rem"></div>

        <table class="table table-hover" border="0px" width="400px">
            <tr id="tableHead"><th width="33%">姓名</th><th class="text-right">是否要求完成</th><th name="deleteTd" style="display:none;">指令</th></tr>
            <c:forEach var="student" items="${studentsList}">
                <tr name="${student.getId()}">
                    <td>
                        <h6 name="${student.getId()}">${student.getName()}</h6>
                        <p name="${student.getId()}">${student.getId()}</p>
                    </td>
                    <td style="vertical-align: middle;">
                        <c:choose>
                            <c:when test="${student.isRequireState()}">
                                <div class="custom-control custom-switch text-right">
                                    <input type="checkbox" class="custom-control-input" id="${student.getId()}" onclick="changeRequireState(this)" checked >
                                    <label class="custom-control-label" for="${student.getId()}"></label>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="custom-control custom-switch text-right">
                                    <input type="checkbox" class="custom-control-input" id="${student.getId()}" onclick="changeRequireState(this)">
                                    <label class="custom-control-label" for="${student.getId()}"></label>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td style="vertical-align: middle;display: none;" name="deleteTd" class="align-items-center">
                        <input type="button" class="btn btn-danger btn-sm" value="删除" name="delete" id="${student.getId()}" onclick="startModal(this)" style="vertical-align: middle;"/>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <input class="btn btn-danger col-4 col-sm-3 float-right" type="hidden" name="init" id="initBtn" value="重置名单" onclick="startModal(this)">
        <br><br>
    </div>
</div>
