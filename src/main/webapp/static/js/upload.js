$(document).ready(function () {
    $("body").hide().fadeIn(100);
});

function showFilename(file){
    $("#filename_label").html(file.name);
}

var interval;
function upload(){
    if($("#inputGroupFile04").val()===""){
        $("#tip").html("别皮哦");
        return 0;
    }

    let fileName = $("#inputGroupFile04")[0].files[0].name;
    let fileType = fileName.substring(fileName.indexOf('.') + 1, fileName.length);
    console.log(fileType);
    let acceptType = ['zip', 'rar', 'doc', 'docx', 'pdf'];


    if(acceptType.indexOf(fileType) < 0){
        $("#tip").html("仅支持上传zip rar doc docx pdf格式的文件");
        return 0;
    }

    if($("#inputGroupFile04")[0].files[0].size>1024*1024*100){
        $("#tip").html("超过100M的文件无法上传哦");
        return 0;
    }
    $("#tip").html("");
    getProcess();
    interval = setInterval(getProcess,500);
    var formData = new FormData($("#form")[0]);

    $("#form").css("display","none");
    $("#progressDiv").css("display","block");
    formData.append("file",$("#inputGroupFile04")[0].files[0]);
    $.ajax({
        url:$("#url").val()+"/uploadPic",
        data:formData,
        type:"POST",
        contentType:false,
        processData: false,
        success:function (result) {
            if(result==="SUCCESS"){
                $("body").hide().fadeOut(100,function () {
                    window.location.href = $("#url").val()+"/success";
                });
            }
            else if(result==="NOT_A_SCREENSHOT"){
                reshow("别皮哦");
            }
            else{
                reshow("发生了不可抗力的入侵哦");
            }
        },
        fail:function () {
            reshow("网络连接失败哦");
        },
        finally:function () {
            clearInterval(interval);
        }
    })
}

function getProcess() {
    $.ajax({
        url:$("#url").val()+"/uploadProcess",
        async:true,
        type: "POST",
        success:function (result) {
            $("#progress-bar").attr("aria-valuenow",result).css("width",result+"%").html(result+"%");
            if(result==="100"){
                clearInterval(interval);
            }
        }
    })
}

function reshow(str) {

    $("#tip").html(str);
    $("#inputGroupFile04").val("");
    $("#filename_label").html("选择文件");

    $("#tip").fadeIn(100);
    $("#form").css("display","block").hide().fadeIn(100);
    $("#progressDiv").hide().fadeOut(100,function () {
        $("#progressDiv").css("display","none");
    });


}
