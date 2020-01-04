var r = new RegExp("^[012]\\d-[\\S][^-]+$");
function register2Forward() {
    if (checkIfEmpty($("#groupName").val())||checkIfEmpty($("#groupSec").val())){
        if(checkIfEmpty($("#groupName").val())){
            setInputInvalid("#groupName","#groupNameFeedback","班级名称不能为空");
        }
        if(checkIfEmpty($("#groupSec").val())){
            setInputInvalid("#groupSec","#groupSecFeedback","班级代号不能为空");
        }
        return;
    }
    else if(!r.test($("#groupName").val())){
        setInputInvalid("#groupName","#groupNameFeedback","请以 届级-班级 的方式填写班级");
        return;
    }


    allLock();
    $.post({
        url:rootUrl+"/register3",
        data:{
            groupName:$("#groupName").val(),
            groupSec:$("#groupSec").val()
        },
        success:function (result) {
            if(result==1){
                setInputInvalid("#groupName","#groupNameFeedback","班级代号已经存在");
            }
            else if(result==2){
                setInputInvalid("#groupSec","#groupSecFeedback","班级代号已经存在");
            }
            else{
                $(".container").css("position","");
                $(".container").empty();
                $(".container").html(result);
                pageForwardFadeIn();
            }
        },
        error:function () {
            showErrorAlert("网络未连接");
        },
        complete:function () {
            allUnlock();
        }

    });
}

function register2Backward() {
    allLock();
    $.post({
        url:rootUrl+"/register1",
        success:function (data) {
            pageBackwardFadeOut(function () {
                $(".container").css("position","");
                $(".container").empty();
                $(".container").html(data);
                pageBackwardFadeIn();
            });
        },
        error:function () {
            showErrorAlert("网络未连接");
        },
        complete:function () {
            allUnlock();
        }
    });
}

function checkGroupName() {
    initProgress();
    if(checkIfEmpty($("#groupName").val())){
        pn1(false);
        setInputInvalid("#groupName","#groupNameFeedback","班级名称不能为空");
    }
    else if(!r.test($("#groupName").val())){
        pn1(false);
        setInputInvalid("#groupName","#groupNameFeedback","请以 届级-班级 的方式填写班级");
    }
    else{
        pn1(true);
        $("#groupName").attr("disabled",true);
        $.post({
            url:rootUrl+"/ajax/checkGroupName",
            data:{groupName:$("#groupName").val()},
            success:function (result) {
                if(result.toString()==='true'){
                    setInputValid("#groupName","#groupNameFeedback","班级名称可用");
                }
                else{
                    setInputInvalid("#groupName","#groupNameFeedback","班级名称已经存在");
                    pn1(false);
                }
            },
            error:function () {
                showErrorAlert("网络未连接");
                pn1(false);
            },
            complete:function () {
                $("#groupName").attr("disabled",false);
            }
        });
    }
}

function checkGroupSec() {
    initProgress();
    if(checkIfEmpty($("#groupSec").val())){
        pn2(false);
        setInputInvalid("#groupSec","#groupSecFeedback","班级名称不能为空");
    }
    else{
        pn2(true);
        $("#groupSec").attr("disabled",true);
        $.post({
            url:rootUrl+"/ajax/checkGroupSec",
            data:{groupSec:$("#groupSec").val()},
            success:function (result) {
                if(result.toString()==='true'){
                    setInputValid("#groupSec","#groupSecFeedback","班级代号可用");
                }
                else{
                    setInputInvalid("#groupSec","#groupSecFeedback","班级代号已经存在");
                    pn2(false);
                }
            },
            error:function () {
                showErrorAlert("网络未连接");
                pn2(false);
            },
            complete:function () {
                $("#groupSec").attr("disabled",false);
            }
        });
    }


}

function allLock() {
    $("#groupName,#groupSec,#regist2Next,#regist2Previous").attr("disabled",true);
}

function allUnlock() {
    $("#groupName,#groupSec,#regist2Next,#regist2Previous").attr("disabled",false);
}

var ani1=0;
var ani2=0;
var MAX=44;


var checkProgress = false;
var preProgress=22;
