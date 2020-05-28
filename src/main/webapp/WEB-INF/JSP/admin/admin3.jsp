
<%--
  Created by IntelliJ IDEA.
  User: chene
  Date: 2020/2/27
  Time: 23:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row">
    <div class="col-12 col-lg-7">
        <h2>已完成名单</h2><br>
        <div class="bs-callout bs-callout-success">
            <h5 class="d-inline text-dark"><span class="text-success">${goodStudents==null?0:goodStudents.size()}</span> 人已完成学习</h5>
            <button class="btn btn-outline-success float-right btn-sm" onclick="toCompletedList()">刷新</button>
        </div>
        <br>
    </div>
    <div class="col col-lg-7 col-xl-7">
        <div class="doneList">
            <a class="btn btn-outline-primary float-right" href="${pageContext.request.contextPath}/admin/downloadAll">下载全部截图</a><br><br>
            <c:choose>
                <c:when test="${goodStudents!=null}">
                    <table class="table table-hover" border="0px" width="400px" id="completedTable">
                        <tr><th>姓名</th><th>时间</th><th>选项</th></tr>
                        <c:forEach var="goodStudent" items="${goodStudents}">
                        <tr><td>${goodStudent.getName()}</td><td>${goodStudent.getTimeStr()}</td><td><a class="btn-link" href="${pageContext.request.contextPath}/admin/download/${goodStudent.getId()}">下载截图</a> </td>
                            </c:forEach></tr>
                    </table>
                </c:when>
                <c:otherwise>
                    <p id="completedText">暂未有学生完成</p>
                </c:otherwise>
            </c:choose>
            <p id="newCompletedText"></p>
        </div>

    </div>

</div>
