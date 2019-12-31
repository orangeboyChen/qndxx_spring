function getGroupBySec(obj) {
    $.post({
        url:$("#requestUrl").val() + "/ajax/checkGroupSec",
        data:{'groupSec':$("#groupSec").val()},
        success:function (data) {
            if(data.toString() == ''){
                $("#groupSec").removeClass();
                $("#groupSec").addClass("form-control is-invalid");
                $("#groupSecFeedback").removeClass();
                $("#groupSecFeedback").addClass("invalid-feedback");
                $("#groupSecFeedback").html("没有找到相应的班级");
                $("#saying").css("display","none");
                $("#rank").empty();
                $("#rank").load($("#requestUrl").val() + "/indexRight");
                $("#groupStartTime").html("");
            }
            else{
                $("#groupSec").removeClass();
                $("#groupSec").addClass("form-control is-valid");
                $("#groupSecFeedback").removeClass();
                $("#groupSecFeedback").addClass("valid-feedback");
                $("#groupSecFeedback").html("已找到班级："+data.school+data.institution+"-"+data.groupName);
                $("#groupId").val(data.groupId);
                $("#saying").css("display","block");
                $("blockquote").html(data.saying);
                $("#rank").load($("#requestUrl").val() + "/rankTable");
                $("#groupStartTime").html("新的大学习已于"+data.timeStr+"开始");

            }
        }
    });
}

function changeAdminLabel(){
    if($("#id").val()==='admin'){
        $("#idLabel").html("账号");
        $("#nameLabel").html("密码");
        $("#name").attr("type","password");
        $("#id").removeClass();
        $("#id").addClass("form-control is-valid");
    }
    else{
        $("#idLabel").html("学号");
        $("#nameLabel").html("姓名");
        $("#name").attr("type","text");
        $("#id").removeClass();
        $("#id").addClass("form-control");
    }
}

$("document").ready(function () {
        $("#rank").load($("#requestUrl").val() + "/indexRight");
    }

)