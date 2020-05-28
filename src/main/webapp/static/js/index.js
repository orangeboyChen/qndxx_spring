var rootUrl;
function getGroupBySec(obj) {
    $.post({
        url:rootUrl + "/ajax/checkGroupSec",
        data:{'groupSec':$("#groupSec").val()},
        success:function (data) {
            if(data.toString() == ''){
                $("#groupSec").removeClass();
                $("#groupSec").addClass("form-control is-invalid");
                $("#groupSecFeedback").removeClass();
                $("#groupSecFeedback").addClass("invalid-feedback");
                $("#groupSecFeedback").html("没有找到相应的班级");
                $("#saying").css("display","none");

                $("#helpMsg").html("如果你没有班级代号，请联系团支书获取");
                $("#helpDiv")
                    .removeClass()
                    .addClass("bs-callout bs-callout-danger")
                    .css("display","block");
                $("#rank").fadeOut(150,function () {
                    $("#rank").load($("#requestUrl").val() + "/indexRight","",function () {
                        $("#rank").fadeIn(150);
                    });
                });
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
                $("#helpDiv").css("display","none");
                $("blockquote").html(data.saying);
                $("#rank").fadeOut(150,function () {
                    $("#rank").load($("#requestUrl").val() + "/rankTable","",function () {
                        $("#rank").fadeIn(150);
                    });
                });
                $("#groupStartTime").html("新的作业已于"+data.timeStr+"开始");
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
    $("body").hide();
    $("#rank").load($("#requestUrl").val() + "/indexRight");
    rootUrl=$("#requestUrl").val();
    if($("#groupSec").val()!==""){
        getGroupBySec();
    }

    $("body").fadeIn(100);
    }
);

function toRegister() {
    $(".container").css("position","relative");
    $(".container").animate({
       right:'1rem',
       opacity:'0'
    },100,function () {
        window.location.href=rootUrl+"/register";
    });
}

function refreshRank() {
    $("#rank").fadeOut(150,function () {
        $("#rank").load($("#requestUrl").val() + "/rankTable","",function () {
            $("#rank").fadeIn(150);
        });
    });
}