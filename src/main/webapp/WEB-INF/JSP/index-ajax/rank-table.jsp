<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<meta charset="utf-8">
<br>
<br>
<br>
            <h4>光荣榜<input class="btn btn-sm btn-secondary" type="button" style="float: right" value="刷新"/></h4>

            <table class="table table-striped col">
                <tr>
                    <th>姓名</th>
                    <th>提交时间</th>
                </tr>
                <tr>
                <c:choose>
                    <c:when test="${goodStudents[0]!=null}">
                        <td>${goodStudents[0].getName()}<span class="badge badge-success" style="margin-left: 0.5rem">第一名</span></td>
                        <td>${goodStudents[0].getTimeStr()}</td>
                    </c:when>
                    <c:otherwise>
                        <td>NULL</td>
                        <td>NULL</td>
                    </c:otherwise>
                </c:choose>
                </tr>

                <tr>
                    <c:choose>
                        <c:when test="${goodStudents[1]!=null}">
                            <td>${goodStudents[1].getName()}<span class="badge badge-success" style="margin-left: 0.5rem">第二名</span></td>
                            <td>${goodStudents[1].getLastCompleteTime()}</td>
                        </c:when>
                        <c:otherwise>
                            <td>NULL</td>
                            <td>NULL</td>
                        </c:otherwise>
                    </c:choose>
                </tr>

                <tr>
                    <c:choose>
                        <c:when test="${goodStudents[2]!=null}">
                            <td>${goodStudents[2].getName()}<span class="badge badge-success" style="margin-left: 0.5rem">第三名</span></td>
                            <td>${goodStudents[2].getLastCompleteTime()}</td>
                        </c:when>
                        <c:otherwise>
                            <td>NULL</td>
                            <td>NULL</td>
                        </c:otherwise>
                    </c:choose>
                </tr>

            </table>

            <br>

            <h4>${goodStudentsCount}人已交，${badStudentsCount}人未交<input class="btn btn-sm btn-secondary" type="button" style="float: right" value="刷新"/></h4>

            <label>未交名单</label>
            <table class="table table-striped col">
                <tr>
                    <th>姓名</th>
                </tr>
                <c:forEach var="badStudent" items="${badStudents}">
                    <tr>
                        <td>${badStudent.getName()}</td>
                    </tr>
                </c:forEach>
            </table>
