var rootUrl;
var canContinue=false;
var ids;
var names;

$(document).ready(function () {
    rootUrl=$("#requestUrl").val()+"/admin";
    $("body").hide().fadeIn(150);

});



function createTable() {
    ids=$("#studentId").val().split("\n");
    names=$("#studentName").val().split("\n");
    var min=ids.length<names.length?ids.length:names.length;

    canContinue=true;
    $("tr[name='data']").remove();
    $("#tableHead").after("<tr name=\"data\"><td>无</td><td>无</td></tr>");
    setInputValid("#studentId","#studentIdFeedback");
    setInputValid("#studentName","#studentNameFeedback");

    if(checkIfSame(ids)){
        setInputInvalid("#studentId","#studentIdFeedback","学号有重复");
        canContinue=false;
    }

    if(ids.length!==names.length){
        setInputInvalid("#studentId","#studentIdFeedback","学号与姓名行数不一致");
        setInputInvalid("#studentName","#studentNameFeedback","");
        canContinue=false;

    }

    for(var i=0;i<min;i++){
        if(checkIfEmpty(ids[i])||checkIfEmpty(names[i])){
            if(checkIfEmpty(ids[i])){
                setInputInvalid("#studentId","#studentIdFeedback","发现了空行");
            }
            if(checkIfEmpty(names[i])){
                setInputInvalid("#studentName","#studentNameFeedback","发现了空行");
            }
            canContinue=false;
        }
    }

    if(!canContinue)return;
    $("tr[name='data']").remove();
    for(var k=0;k<min;k++){
        $("#tableHead").after("<tr name=\"data\"><td>" +
            ids[k] +
            "</td><td>" +
            names[k] +
            "</td></tr>");

    }
}


function checkIfEmpty(obj) {
    return typeof obj == "undefined" || obj == null || obj.trim() === "";
}

function setInputValid(id,feedbackId) {
    $(id).removeClass();
    $(id).addClass("form-control is-valid");
    $(feedbackId).hide();
}

function setInputInvalid(id,feedbackId,feedbackStr) {
    $(id).removeClass();
    $(id).addClass("form-control is-invalid");
    $(feedbackId).removeClass();
    $(feedbackId).addClass("invalid-feedback");
    $(feedbackId).text(feedbackStr);
    $(feedbackId).show();
}

function checkIfSame(ids) {
    for(var i=0;i<ids.length-1;i++){
        for(var j=i+1;j<ids.length;j++){
            if(ids[i]===ids[j]){
                return true;
            }
        }
    }
    return false;
}

function recheck() {
    createTable();
    if(canContinue){
        $("#submitCancelledBtn").removeClass();
        $("#submitCancelledBtn").addClass("btn btn-danger");
        clearSubmitModal();
        $("#modalSubmittedBtn").attr("type","button");
        $("#submitModalTitle").text("请确认新增学生信息");
        $("#submitModalText1").text("以下是即将添加的学生");
        $("#submitModalTable1").html("<tr id=\"submitModalHead1\"><th>" +
            "学号" +
            "</th><th>" +
            "姓名" +
            "</th></tr>");

        for(var m=0;m<ids.length;m++){
            $("#submitModalHead1").after("<tr name=\"submitModalData1\"><td>" +
                ids[m] +
                "</td><td>" +
                names[m] +
                "</td></tr>");
        }

        $("#submitModal").modal("show");
    }
}

function submit() {
    $("#modalSubmittedBtn").attr("disabled",true);
    $.post({
        url:rootUrl+"/addMutiple",
        traditional:true,
        data:{
            ids:ids,
            names:names
        },
        success:function (result) {
            var sameIdStudents=result.sameIdStudents;
            var completedStudents=result.completedStudents;
            clearSubmitModal();
            $("#submitModalTitle").text("添加请求成功");
            if(checkIfEmpty(sameIdStudents.toString())&&checkIfEmpty(completedStudents.toString())){
                $("#submitModalText1").text("没有相应的数据");
                return;
            }

            if(!checkIfEmpty(sameIdStudents.toString())){
                setInputInvalid("#studentId","#studentIdFeedback","");
                setInputInvalid("#studentName","#studentNameFeedback","");
                $("#submitModalTitle").text("请求成功，但出现了错误");
                $("#submitModalText1").text("添加失败的学生");
                $("#submitModalTable1").html("<tr id=\"submitModalHead1\"><th>" +
                    "学号" +
                    "</th><th>" +
                    "姓名" +
                    "</th></tr>" +
                    "失败原因" +
                    "</td></tr>");
                for(var i=0;i<sameIdStudents.length;i++){
                    $("#submitModalHead1").after("<tr name=\"submitModalData1\"><td style=\"color:orangered\">" +
                        sameIdStudents[i].id +
                        "</td><td>" +
                        sameIdStudents[i].name +
                        "</td></tr>" +
                        "班级中存在相同的学号" +
                        "</td></tr>");
                }
            }

            if(!checkIfEmpty(completedStudents.toString())){
                if(checkIfEmpty(sameIdStudents.toString())){
                    $("#studentId").val("").removeClass().addClass("form-control");
                    $("#studentName").val("").removeClass().addClass("form-control");
                    $("#submitModalText1").hide();
                    $("#submitModalTable1").hide();
                }
                $("#submitModalText2").text("添加成功的学生");
                $("#submitModalTable2").html("<tr id=\"submitModalHead2\"><th>" +
                    "学号" +
                    "</th><th>" +
                    "姓名" +
                    "</th></tr>");
                for(var j=0;j<completedStudents.length;j++){
                    $("#submitModalHead2").after("<tr name=\"submitModalData2\"><td style=\"color:green\">" +
                        completedStudents[j].id +
                        "</td><td>" +
                        completedStudents[j].name +
                        "</td></tr>");
                }
            }
            $("#modalSubmittedBtn").attr("type","hidden");
        },
        error:function () {
            clearSubmitModal();
            $("#submitModalText1").text("网络连接超时");
        },
        complete:function () {
            $("#modalSubmittedBtn").attr("disabled",false);
            $("#submitCancelledBtn").removeClass();
            $("#submitCancelledBtn").addClass("btn btn-primary");
        }
    });
}

function clearSubmitModal() {
    $("#submitModalTitle").empty();
    $("#submitModalText1").empty();
    $("#submitModalText2").empty();
    $("#submitModalTable1").empty();
    $("#submitModalTable2").empty();
    $("#submitModalText1").show();
    $("#submitModalTable1").show();
}

function cancelModal() {
    $("#submitModal").modal("hide");
}

function cancelToAdmin() {
    $("html").fadeOut(150,function () {
        window.location.href = rootUrl + "#4";
    });
}