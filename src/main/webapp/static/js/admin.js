var rootUrl;
var systemFail="系统拒绝了你的请求";
var netFail="网络连接失败";

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
    $("#alert").removeClass();
    $("#alert").addClass("alert alert-success alert-dismissible");
    $("#alert").stop();
    $("#alert").fadeIn(100);
    $("#alert").fadeOut(3000);
}

function alertFail(str) {
    $("#alertTitle").html("操作失败！");
    $("#alertTxt").html(str);
    $("#alert").removeClass();
    $("#alert").addClass("alert alert-danger alert-dismissible");
    $("#alert").stop();
    $("#alert").fadeIn(100);
    $("#alert").fadeOut(3000);
}

function alertClose() {
    $("#alert").fadeOut(100);
}

function setSaying() {
    $.post({
        url:rootUrl+"/setSaying",
        data:{'saying':$("#sayingTxt").val()},
        success:function (result) {
            if(result==='true'){
                alertSuccess();
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
                alertSuccess();
                location.reload();
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
                alertSuccess();
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
                alertSuccess();
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
                alertSuccess();
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
                alertSuccess();
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

var todoObj;
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
            var name= $("p[name='"+id+"']").text();
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
                alertSuccess();
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


