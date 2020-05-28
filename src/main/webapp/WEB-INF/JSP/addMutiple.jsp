<%--
  Created by IntelliJ IDEA.
  User: chene
  Date: 2019/12/30
  Time: 21:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>批量添加学生</title>
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/popper.js/1.15.0/umd/popper.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/addMutiple.js"></script>
    <link href="https://cdn.bootcss.com/font-awesome/5.12.1/css/all.min.css" rel="stylesheet">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
</head>
<body>
<nav class="navbar navbar-dark bg-dark sticky-top">
    <div class="justify-content">
        <button class="btn btn-outline-primary my-2 my-sm-0" onclick="cancelToAdmin()">
            <i class="fa fa-chevron-left d-inline color-primary" aria-hidden="true"></i>
            <p class="d-inline color-primary">返回</p>
        </button>
        <span class="navbar-brand" style="margin-left: 0.5rem;">青年作业在线提交</span>
    </div>
</nav>
<div class="container">
    <div class="row">
        <div class="col-12">


            <div style="height:1rem">
            </div>
            <h2>批量添加学生</h2>
            <p>使用该功能时，请确保学生姓名与学生学号在同一行内。</p>
            <input name="requestUrl" id="requestUrl" type="hidden" value="${pageContext.request.contextPath}"/>
        </div>

            <div class="col-12 col-lg-6 col-sm-6">
                <label for="studentId">学生学号</label><br>
                <textarea class="form-control" rows="12" style="resize:none;" id="studentId" oninput="createTable()"></textarea>
                <div id="studentIdFeedback"></div>
                <br><br>
            </div>
            <div class="col-12 col-lg-6 col-sm-6">
                <label for="studentName">学生姓名</label><br>
                <textarea class="form-control" rows="12" style="resize:none;" id="studentName" oninput="createTable()"></textarea>
                <div id="studentNameFeedback"></div>
                <br><br>
            </div>
            <div class="col-lg-9">
                <table class="table table-striped" id="table">
                    <tr id="tableHead">
                        <th style="width: 50%">学号</th>
                        <th>姓名</th>
                    </tr>
                    <tr name="data">
                        <td>无</td>
                        <td>无</td>
                    </tr>
                </table>
                <br>
            </div>
            <div class="col-lg-3 offset-lg-0">
                <input type="button" class="btn btn-primary col" value="提交" onclick="recheck()">
            </div>
    </div>

    <div class="modal fade" id="submitModal">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title" id="submitModalTitle"></h4>
                    <button type="button" class="close" data-dismiss="modal" onclick="cancelModal()">&times;</button>
                </div>
                <div class="modal-body">
                    <p id="submitModalText1"></p>
                    <table class="table table-striped" id="submitModalTable1">


                    </table>

                    <p id="submitModalText2"></p>
                    <table class="table table-striped" id="submitModalTable2">
                    </table>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-danger" data-dismiss="modal" id="submitCancelledBtn" onclick="cancelModal()">返回</button>
                    <input type="button" class="btn btn-primary" onclick="submit()" id="modalSubmittedBtn" value="确定">
                    <br><br>
                </div>
            </div>
        </div>
    </div>
</div>


</body>

</html>
