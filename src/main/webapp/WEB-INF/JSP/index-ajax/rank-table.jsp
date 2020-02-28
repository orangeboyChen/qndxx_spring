<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<meta charset="utf-8">
<div id="moreData" class="collapse ${collapse}">
    <div style="height: 1rem"></div>
    <div class="bs-callout ${goodStudentsCount>5?'bs-callout-success':'bs-callout-danger'}">
        <h5 class="d-inline text-dark m-auto"><span class="${goodStudentsCount>5?'text-success':'text-danger'}" id="goodStudentCount">${goodStudentsCount}</span>
            人已交，
            <span class="text-dark" id="badStudentCount">${badStudentsCount}</span>
            人未交
        </h5>
        <input class="btn btn-sm btn-outline-secondary float-right" type="button" style="float: right" value="刷新" onclick="refreshRank()"/>
    </div>
    <h4>光荣榜</h4>
    <table class="table table-striped col">
        <tr>
            <th>姓名</th>
            <th>提交时间</th>
        </tr>
        <tr>
            <c:choose>
                <c:when test="${goodStudents[0]!=null}">
                    <td>${goodStudents[0].getName()}</td>
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
                    <td>${goodStudents[1].getName()}</td>
                    <td>${goodStudents[1].getTimeStr()}</td>
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
                    <td>${goodStudents[2].getName()}</td>
                    <td>${goodStudents[2].getTimeStr()}</td>
                </c:when>
                <c:otherwise>
                    <td>NULL</td>
                    <td>NULL</td>
                </c:otherwise>
            </c:choose>
        </tr>
    </table>
    <br>
    <h4>未交名单</h4>
    <table class="table table-striped col">
        <tr>
            <th>姓名</th>
        </tr>
        <c:forEach var="badStudent" items="${badStudents}">
            <tr>
                <td>${badStudent.name}</td>
            </tr>
        </c:forEach>
    </table>
</div>



