<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: chene
  Date: 2019/12/25
  Time: 22:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/popper.js/1.15.0/umd/popper.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/index.js"></script>
    <title>青年大学习在线提交</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-sm-12 offset-lg-1 offset-md-1 col-lg-5 col-md-6">
            <form id="form1" name="form1" method="post" action="${pageContext.request.contextPath}/Login">
                <div style="height:4rem"></div>
                <h2 class="title">青年大学习在线提交</h2>
                <div id="saying" style="display: none">
                    <p id="groupStartTime"></p>
                    <h4>团支书寄语：</h4>
                    <blockquote></blockquote>
                </div>


                <input name="requestUrl" id="requestUrl" type="hidden" value="${pageContext.request.contextPath}"/>
                <label for="groupSec">班级代号</label>
                <input class="form-control" type="text" value="${groupSec}" name="groupSec" id="groupSec" onblur="getGroupBySec(this)" maxlength="15"/>
                <div id="groupSecFeedback"></div>
                <input type="hidden" name="groupId" id="groupId" value="-1"/>
                <label for="id" id="idLabel">学号</label>
                <input id="id" class="form-control" type="text" name="id" oninput="changeAdminLabel()"/>
                <label for="name" id="nameLabel">姓名</label>
                <input id="name" class="form-control" type="text" name="name"/>
                <br>
                <input type="submit" class="btn btn-primary col">
            </form>

        </div>
        <div id="rank" class="col-md-5 col-sm-12">

        </div>

<%--        <div class="col-md-5 col-sm-12">--%>
<%--            <br>--%>
<%--            <h4>光荣榜<input class="btn btn-sm btn-secondary" type="button" style="float: right" value="刷新"/></h4>--%>

<%--            <table class="table table-striped col">--%>
<%--                <tr>--%>
<%--                    <th>姓名</th>--%>
<%--                    <th>提交时间</th>--%>
<%--                </tr>--%>
<%--                <tr>--%>
<%--                <c:choose>--%>
<%--                    <c:when test="${goodStudents[0]!=null}">--%>
<%--                        <td>${goodStudents[0].getName()}<span class="badge badge-success" style="margin-left: 0.5rem">第一名</span></td>--%>
<%--                        <td>${goodStudents[0].getTimeStr()}</td>--%>
<%--                    </c:when>--%>
<%--                    <c:otherwise>--%>
<%--                        <td>NULL</td>--%>
<%--                        <td>NULL</td>--%>
<%--                    </c:otherwise>--%>
<%--                </c:choose>--%>
<%--                </tr>--%>

<%--                <tr>--%>
<%--                    <c:choose>--%>
<%--                        <c:when test="${goodStudents[1]!=null}">--%>
<%--                            <td>${goodStudents[1].getName()}<span class="badge badge-success" style="margin-left: 0.5rem">第二名</span></td>--%>
<%--                            <td>${goodStudents[1].getLastCompleteTime()}</td>--%>
<%--                        </c:when>--%>
<%--                        <c:otherwise>--%>
<%--                            <td>NULL</td>--%>
<%--                            <td>NULL</td>--%>
<%--                        </c:otherwise>--%>
<%--                    </c:choose>--%>
<%--                </tr>--%>

<%--                <tr>--%>
<%--                    <c:choose>--%>
<%--                        <c:when test="${goodStudents[2]!=null}">--%>
<%--                            <td>${goodStudents[2].getName()}<span class="badge badge-success" style="margin-left: 0.5rem">第三名</span></td>--%>
<%--                            <td>${goodStudents[2].getLastCompleteTime()}</td>--%>
<%--                        </c:when>--%>
<%--                        <c:otherwise>--%>
<%--                            <td>NULL</td>--%>
<%--                            <td>NULL</td>--%>
<%--                        </c:otherwise>--%>
<%--                    </c:choose>--%>
<%--                </tr>--%>

<%--            </table>--%>

<%--            <br>--%>

<%--            <h4>${goodStudentsCount}人已交，${badStudentsCount}人未交<input class="btn btn-sm btn-secondary" type="button" style="float: right" value="刷新"/></h4>--%>

<%--            <label>未交名单</label>--%>
<%--            <table class="table table-striped col">--%>
<%--                <tr>--%>
<%--                    <th>姓名</th>--%>
<%--                </tr>--%>
<%--                <c:forEach var="badStudent" items="${badStudents}">--%>
<%--                    <tr>--%>
<%--                        <td>${badStudent.getName()}</td>--%>
<%--                    </tr>--%>
<%--                </c:forEach>--%>
<%--            </table>--%>
<%--        </div>--%>
    </div>
</div>
<br><br><br><br>
<footer style="text-align:center;font-size: 10px;">互联网ICP备案:粤ICP备19129362号 ©2020 orangeboy. All rights reserved.
    <div style="width:300px;margin:0 auto; padding:20px 0;color:#939393;">
        <a target="_blank" href="http://www.beian.gov.cn/portal/registerSystemInfo?recordcode=42011102003625" style="display:inline-block;text-decoration:none;height:20px;line-height:20px;font-size: 10px"><img src="${pageContext.request.contextPath}/img/beian.png" style="float:left;"/><p style="float:left;height:20px;line-height:20px;margin: 0px 0px 0px 5px; color:#939393;font-size: 10px;">鄂公网安备 42011102003625号</p></a>
    </div>
</footer>
</body>
</html>
