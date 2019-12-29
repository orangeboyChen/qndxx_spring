var rootUrl;
var systemFail="系统拒绝了你的请求";
var netFail="网络连接失败";
var todoObj;

$(document).ready(function(){
    rootUrl = $("#requestUrl").val()+"/admin";
    $("#alert").css("display","none");
    // $("#ck").on('click',function(){
    //     $("input[name='requiredYes']").attr("disabled",true);
    //     $("input[name='requiredYes']").removeClass("btn-primary");
    //     $("input[name='requiredYes']").removeClass("btn-light");
    //     $("input[name='requiredYes']").addClass("btn-light");
    //
    //     $("input[name='requiredNo']").attr("disabled",false);
    //     $("input[name='requiredNo']").removeClass("btn-primary");
    //     $("input[name='requiredNo']").removeClass("btn-light");
    //     $("input[name='requiredNo']").addClass("btn-primary");
    // });
});

function alertSuccess(str) {
    $("#alertTitle").html("操作成功！");
    $("#alertTxt").html(str);
    $("#alertDiv").css("display","block");
    $("#alert").removeClass();
    $("#alert").addClass("alert alert-success alert-dismissible");
    $("#alert").stop();
    $("#alert").fadeIn(100);
    $("#alert").fadeOut(3000,function () {
        $("#alertDiv").css("display","none");
    });
}

function alertFail(str) {
    $("#alertTitle").html("操作失败！");
    $("#alertTxt").html(str);
    $("#alertDiv").css("display","block");
    $("#alert").removeClass();
    $("#alert").addClass("alert alert-danger alert-dismissible");
    $("#alert").stop();
    $("#alert").fadeIn(100);
    $("#alert").fadeOut(3000,function () {
        $("#alertDiv").css("display","none");
    });
}

function setSaying() {
    $.post({
        url:rootUrl+"/setSaying",
        data:{'saying':$("#sayingTxt").val()},
        success:function (result) {
            if(result==='true'){
                alertSuccess("");
            }
            else{
                alertFail(systemFail);
            }
        },
        error:function () {
            alertFail(netFail);
        }
    });
}

function newTerm() {
    $.post({
        url:rootUrl+"/newTerm",
        data:{'saying':$("#sayingTxt").val()},
        success:function (result) {
            if(result==='true'){
                alertSuccess("");
                $("#completedTable").remove();
                $("#completedText").remove();
                $("#newCompletedText").text("暂未有学生完成")
            }
            else{
                alertFail(systemFail);
            }
        },
        error:function () {
            alertFail(netFail);
        },
        complete:function () {
            $("#modalSubmittedBtn").attr("disabled",false);
            $("#makeSureModal").modal("hide");
        }
    });

}

function deleteStudent(obj) {
    var id=obj.id;
    $.post({
        url:rootUrl+"/deleteStudent",
        data:{'id':id},
        success:function (result) {
            if(result==='true'){
                alertSuccess("");
                $("tr[name='"+id+"']").remove();
            }
            else{
                alertFail(systemFail);
            }
        },
        error:function () {
            alertFail(netFail);
        },
        complete:function () {
            $("#modalSubmittedBtn").attr("disabled",false);
            $("#makeSureModal").modal("hide");
        }
    });



}

function changeRequireStateToYes(obj) {
    var id=obj.id;
    $.post({
        url:rootUrl+"/changeRequireStateToYes",
        data:{'id':id},
        success:function (result) {
            if(result==='true'){
                alertSuccess("");
                $("#"+id).val("当前为是");
                $("#"+id).removeClass();
                $("#"+id).addClass("btn btn-primary");
                $("#"+id).attr("name","requiredYes");
                $("#"+id).attr("onclick","changeRequireStateToNo(this)");
            }
            else{
                alertFail(systemFail);
            }
        },
        error:function () {
            alertFail(netFail);
        }
    });
}

function changeRequireStateToNo(obj) {
    var id=obj.id;
    $.post({
        url:rootUrl+"/changeRequireStateToNo",
        data:{'id':id},
        success:function (result) {
            if(result==='true'){
                alertSuccess("");
                $("#"+id).val("当前为否");
                $("#"+id).removeClass();
                $("#"+id).addClass("btn btn-light");
                $("#"+id).attr("name","requiredNo");
                $("#"+id).attr("onclick","changeRequireStateToYes(this)");
            }
            else{
                alertFail(systemFail);
            }
        },
        error:function () {
            alertFail(netFail);
        }
    });
}

function changeInfo() {
    $.post({
        url:rootUrl+"/changeInfo",
        data:{
            //'groupName':$("#groupNameInput").val(),
            'groupSec':$("#groupSecInput").val()
        },
        success:function (result) {
            if(result==='true'){
                alertSuccess("");
                $("#changeInfo").attr("type","button");
                $("#submitInfo").attr("type","hidden");
                $("#groupSec").text($("#groupSecInput").val());
                $("#groupSecInput").attr("type","hidden");
                $("#groupSec").show();
            }
            else{
                alertFail(systemFail);
            }
        },
        error:function () {
            alertFail(netFail);
        }
    });
}

function showSubmitInfo() {
    $("#changeInfo").attr("type","hidden");
    $("#submitInfo").attr("type","button");
    $("#groupSecInput").val($("#groupSec").text());
    $("#groupSecInput").attr("type","text");
    $("#groupSec").hide();
}


function startModal(obj) {
    todoObj=obj;
    var id=todoObj.id;
    console.log(id);
    var todo=obj.name;
    switch (todo){
        case 'new':
            $("#modalContent").html("你准备开启新的大学习。这意味着当前大学习所有完成的数据将会丢失。");
            break;
        case 'delete':
            var name= $("h6[name='"+id+"']").text();
            $("#modalContent").html("你真的要删除"+name+"吗？你的良心不会痛吗？");
            break;
    }
    $("#makeSureModal").modal("show");
}

function modalCanceled() {
    todoObj=null;
}

function modalSubmitted() {
    var todo=todoObj.name;
    $("#modalSubmittedBtn").attr("disabled",true);
    switch (todo){
        case 'new':
            newTerm();
            break;
        case 'delete':
            deleteStudent(todoObj)
            break;
    }
    todoObj=null;
}

function allRequireYes() {
    $("#ck").attr("disabled",true);
    $("input[name='requiredYes']").attr("disabled",true);
    $("input[name='requiredNo']").attr("disabled",true);
    $.post({
        url:rootUrl+"/initAllRequest",
        data:{},
        success:function (result) {
            if(result==='true'){
                alertSuccess("");
                $("input[name='requiredNo']").attr("onclick","changeRequireStateToNo(this)");
                $("input[name='requiredNo']").removeClass();
                $("input[name='requiredNo']").addClass("btn btn-primary");
                $("input[name='requiredNo']").attr("value","当前为是");
                $("input[name='requiredNo']").attr("name","requiredYes");
            }
            else{
                alertFail(systemFail);
            }


        },
        error:function () {
            alertFail(netFail);
        },
        complete:function () {
            $("input[name='requiredNo']").attr("disabled",false);
            $("input[name='requiredYes']").attr("disabled",false);
            $("#ck").attr("disabled",false);
        }

    })
}

var canChangePassword1=false;
var canChangePassword2=false;
var canChangePassword3=false;
function passwordSubmitted() {
    oldPasswordOnblur();
    newPasswordOnblur();
    newPasswordCommittedOnblur();
    if(canChangePassword1&&canChangePassword2&&canChangePassword3){
        $("#passwordSubmittedBtn").attr("disabled",true);
        $.post({
            url:rootUrl+"/changePassword",
            data:{
                oldPassword:$("#oldPassword").val(),
                newPassword:$("#newPasswordCommitted").val()
            },
            success:function (result) {
                if(result==='true'){
                    $("#changePasswordModal").modal("hide");
                    alertSuccess("修改密码成功");
                    changePasswordModalCanceled();
                }
                else{
                    $("#changePasswordModal").modal("hide");
                    alertFail("修改密码失败");
                }

            },
            error:function (result) {
                $("#changePasswordModal").modal("hide");
                alertFail("修改密码失败，请检查网络连接。");
            },
            complete:function () {
                $("#passwordSubmittedBtn").attr("disabled",false);
            }
        });
    }

}

function oldPasswordOnblur() {
    if($("#oldPassword").val().length===0){
        $("#oldPassword").removeClass();
        $("#oldPassword").addClass("form-control is-invalid");
        $("#oldPasswordFeedback").show();
        $("#oldPasswordFeedback").removeClass();
        $("#oldPasswordFeedback").addClass("invalid-feedback");
        $("#oldPasswordFeedback").html("原密码不能为空");
        canChangePassword1=false;
    }
    else{
        $.post({
            url:rootUrl+"/checkPassword",
            data:{password:$("#oldPassword").val()},
            success:function (data) {
                if(data==='true'){
                    $("#oldPassword").removeClass();
                    $("#oldPassword").addClass("form-control is-valid");
                    $("#oldPasswordFeedback").hide();
                    $("#oldPasswordFeedback").removeClass();
                    canChangePassword1=true;
                }
                else {
                    $("#oldPassword").removeClass();
                    $("#oldPassword").addClass("form-control is-invalid");
                    $("#oldPasswordFeedback").show();
                    $("#oldPasswordFeedback").removeClass();
                    $("#oldPasswordFeedback").addClass("invalid-feedback");
                    $("#oldPasswordFeedback").html("原密码不正确");
                    canChangePassword1=false;
                }
            },
            error:function (data) {
                $("#oldPasswordFeedback").show();
                $("#oldPasswordFeedback").removeClass();
                $("#oldPasswordFeedback").addClass("invalid-feedback");
                $("#oldPasswordFeedback").html("网络连接失败");
                canChangePassword1=false;
            }
        });

    }
}

function newPasswordOnblur() {
    canChangePassword2=false;
    if($("#newPassword").val().length===0){
        $("#newPassword").removeClass();
        $("#newPassword").addClass("form-control is-invalid");
        $("#newPasswordFeedback").show();
        $("#newPasswordFeedback").removeClass();
        $("#newPasswordFeedback").addClass("invalid-feedback");
        $("#newPasswordFeedback").html("新密码不能为空");
    }
    else{
        if($("#newPassword").val()===$("#oldPassword").val()){
            $("#newPassword").removeClass();
            $("#newPassword").addClass("form-control is-invalid");
            $("#newPasswordFeedback").show();
            $("#newPasswordFeedback").removeClass();
            $("#newPasswordFeedback").addClass("invalid-feedback");
            $("#newPasswordFeedback").html("新密码不能与原密码相同");

        }
        else {
            $("#newPassword").removeClass();
            $("#newPassword").addClass("form-control is-valid");
            $("#newPasswordFeedback").hide();
            $("#newPasswordFeedback").removeClass();
            canChangePassword2 = true;
        }
    }
}

function newPasswordCommittedOnblur() {
    canChangePassword3=false;
    if($("#newPasswordCommitted").val().length===0){
        $("#newPasswordCommitted").removeClass();
        $("#newPasswordCommitted").addClass("form-control is-invalid");
        $("#newPasswordCommittedFeedback").show();
        $("#newPasswordCommittedFeedback").removeClass();
        $("#newPasswordCommittedFeedback").addClass("invalid-feedback");
        $("#newPasswordCommittedFeedback").html("确认密码不能为空");
    }
    else{
        if($("#newPasswordCommitted").val()===$("#newPassword").val()){
            $("#newPasswordCommitted").removeClass();
            $("#newPasswordCommitted").addClass("form-control is-valid");
            $("#newPasswordCommittedFeedback").hide();
            $("#newPasswordCommittedFeedback").removeClass();
            canChangePassword3=true;
        }
        else {
            $("#newPasswordCommitted").removeClass();
            $("#newPasswordCommitted").addClass("form-control is-invalid");
            $("#newPasswordCommittedFeedback").show();
            $("#newPasswordCommittedFeedback").removeClass();
            $("#newPasswordCommittedFeedback").addClass("invalid-feedback");
            $("#newPasswordCommittedFeedback").html("确认密码与新密码不一致");
        }
    }
}

function changePasswordModalCanceled() {
    $("#oldPassword").removeClass();
    $("#oldPassword").addClass("form-control");
    $("#oldPassword").val("");
    $("#oldPasswordFeedback").hide();

    $("#newPassword").removeClass();
    $("#newPassword").addClass("form-control");
    $("#newPassword").val("");
    $("#newPasswordFeedback").hide();

    $("#newPasswordCommitted").removeClass();
    $("#newPasswordCommitted").addClass("form-control");
    $("#newPasswordCommitted").val("");
    $("#newPasswordCommittedFeedback").hide();
}

var addStudent1=false;
var addStudent2=false;
function addStudentIdOnblur() {
    addStudent1=false;
    if($("#addStudentId").val().length===0){
        $("#addStudentIdFeedback").show();
        $("#addStudentIdFeedback").removeClass();
        $("#addStudentIdFeedback").addClass("invalid-feedback");
        $("#addStudentIdFeedback").html("学号不能为空");
        $("#addStudentId").removeClass();
        $("#addStudentId").addClass("form-control is-invalid");
    }
    else{
        $.post({
            url:rootUrl+"/sameIdCheck",
            data:{id:$("#addStudentId").val()},
            success:function (data) {
                if(data.toString()==='true'){
                    $("#addStudentId").removeClass();
                    $("#addStudentId").addClass("form-control is-valid");
                    $("#addStudentIdFeedback").hide();
                    $("#addStudentIdFeedback").removeClass();
                    addStudent1=true;
                }
                else{
                    $("#addStudentIdFeedback").show();
                    $("#addStudentIdFeedback").removeClass();
                    $("#addStudentIdFeedback").addClass("invalid-feedback");
                    $("#addStudentIdFeedback").html("学号已被使用");
                    $("#addStudentId").removeClass();
                    $("#addStudentId").addClass("form-control is-invalid");

                }
            },
            error:function () {
                $("#addStudentId").removeClass();
                $("#addStudentId").addClass("form-control is-invalid");
                $("#addStudentIdFeedback").show();
                $("#addStudentIdFeedback").removeClass();
                $("#addStudentIdFeedback").addClass("invalid-feedback");
                $("#addStudentIdFeedback").html("网络连接失败");
            }
        });
    }
}

function addStudentNameOnblur() {
    addStudent2=false;
    if($("#addStudentName").val().length===0){
        $("#addStudentName").removeClass();
        $("#addStudentName").addClass("form-control is-invalid");
        $("#addStudentNameFeedback").show();
        $("#addStudentNameFeedback").removeClass();
        $("#addStudentNameFeedback").addClass("invalid-feedback");
        $("#addStudentNameFeedback").html("姓名不能为空");
    }
    else{
        $("#addStudentName").removeClass();
        $("#addStudentName").addClass("form-control is-valid");
        $("#addStudentNameFeedback").hide();
        $("#addStudentNameFeedback").removeClass();
        addStudent2=true;
    }
}

function addSubmit() {
    if($("#addStudentId").val().length===0){
        $("#addStudentIdFeedback").show();
        $("#addStudentIdFeedback").removeClass();
        $("#addStudentIdFeedback").addClass("invalid-feedback");
        $("#addStudentIdFeedback").html("学号不能为空");
        $("#addStudentId").removeClass();
        $("#addStudentId").addClass("form-control is-invalid");
        addStudent1=false;
    }
    addStudentNameOnblur();
    if(addStudent1&&addStudent2){
        $.post({
            url:rootUrl+"/addStudent",
            data:{
                id:$("#addStudentId").val(),
                name:$("#addStudentName").val()
            },
            success:function (result) {
                if(result.toString()==='true'){
                    alertSuccess($("#addStudentName").val()+"添加成功");
                    $("#addStudentId").removeClass();
                    $("#addStudentId").addClass("form-control");
                    $("#addStudentName").removeClass();
                    $("#addStudentName").addClass("form-control");

                    $("#tableHead").after("<tr name=\""+$("#addStudentId").val()+"\">\n    " +
                        "<td>\n        " +
                        "<h6 name=\""+$("#addStudentId").val()+"\">"+$("#addStudentName").val()+"</h6>\n        " +
                        "<p name=\""+$("#addStudentId").val()+"\">"+$("#addStudentId").val()+"</p>\n    " +
                        "</td>\n    <td style=\"vertical-align: middle;\">\n        <div class=\"btn-group\">\n            <input\tclass=\"btn btn-primary\" name=\"requiredYes\" type=\"button\" value=\"当前为是\" id=\""+$("#addStudentId").val()+"\" onclick=\"changeRequireStateToNo(this)\"/>\n        </div>\n    </td>\n    <td style=\"vertical-align: middle;\">\n        <input type=\"button\" class=\"btn btn-danger\" value=\"删除\" name=\"delete\" id=\""+$("#addStudentId").val()+"\" onclick=\"startModal(this)\"/>\n    </td>\n</tr>\n    \n"
                    );
                    // $("#studentTable").prepend("<tr name=\""+$("#addStudentId").val()+"\">\n    " +
                    //     "<td>\n        " +
                    //     "<h6 name=\""+$("#addStudentId").val()+"\">"+$("#addStudentName").val()+"</h6>\n        " +
                    //     "<p name=\""+$("#addStudentId").val()+"\">"+$("#addStudentId").val()+"</p>\n    " +
                    //     "</td>\n    <td style=\"vertical-align: middle;\">\n        <div class=\"btn-group\">\n            <input\tclass=\"btn btn-primary\" name=\"requiredYes\" type=\"button\" value=\"当前为是\" id=\""+$("#addStudentId").val()+"\" onclick=\"changeRequireStateToNo(this)\"/>\n        </div>\n    </td>\n    <td style=\"vertical-align: middle;\">\n        <input type=\"button\" class=\"btn btn-danger\" value=\"删除\" name=\"delete\" id=\""+$("#addStudentId").val()+"\" onclick=\"startModal(this)\"/>\n    </td>\n</tr>\n    \n"
                    // );

                    addStudent1=false;
                    addStudent2=false;
                    $("#addStudentId").val("");
                    $("#addStudentName").val("");


                }
                else{
                    alertFail("服务器拒绝了你的请求");
                }
            },
            error:function () {
                alertFail(netFail);
            }
        });
    }

}


