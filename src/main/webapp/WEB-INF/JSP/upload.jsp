<%--
  Created by IntelliJ IDEA.
  User: chene
  Date: 2020/2/24
  Time: 0:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!Doctype html>
<html>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/popper.js/1.15.0/umd/popper.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/upload.js"></script>
    <title>提交截图</title>
</head>
<body>
<style>
    .custom-file-input:lang(zh) ~ .custom-file-label::after {
        content: "浏览";
    }
    .custom-file-label::after {
        content: "浏览";
    }
</style>

<div class="container">
    <div style="height:4rem"></div>
    <div class="row justify-content-center">
        <img src="${pageContext.request.contextPath}/img/upload.jpg" width="100px" height="100px" alt="successPic" class="img-round"/>
    </div>
    <input type="hidden" value="${pageContext.request.contextPath}" id="url">
    <br>
    <div class="text-center">
        <h4>请提交截图</h4>
        <h6 id="tip" style="color:red;"></h6>
    </div>
    <div style="height:20px"></div>

    <form enctype="multipart/form-data" method="post" id="form">
        <div class="custom-file col-lg-4 offset-lg-4 col-md-6 offset-md-3 col-sm-8 offset-sm-2">
            <input onchange="showFilename(this.files[0])" name="file" type="file" class="custom-file-input" id="inputGroupFile04" aria-describedby="inputGroupFileAddon04" lang="zh" accept="image/*">
            <label id="filename_label" class="custom-file-label" for="inputGroupFile04">选择文件</label>
        </div>
        <div style="height:20px"></div>
        <div class="col-lg-4 offset-lg-4 col-md-6 offset-md-3 col-sm-8 offset-sm-2">
            <input type="button" class="btn btn-primary col" value="下一步" onclick="upload()"/>
        </div>
    </form>

    <div class="col-12 col-xl-8 offset-xl-2 col-lg-8 offset-lg-2" style="display: none" id="progressDiv">
        <div class="progress">
            <div class="progress-bar progress-bar-striped progress-bar-animated" id="progress-bar" role="progressbar" style="width: ${progress}%" aria-valuenow="${progress}" aria-valuemin="0" aria-valuemax="100"></div>
        </div>
        <br>
    </div>


</div>

</body>
</html>

