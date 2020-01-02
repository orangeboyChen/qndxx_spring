function register2Forward() {
    if (checkIfEmpty($("#groupName").val())||checkIfEmpty($("#groupSec").val())){
        if(checkIfEmpty($("#groupName").val())){
            setInputInvalid("#groupName","#groupNameFeedback","班级名称不能为空");
        }
        if(checkIfEmpty($("#groupSec").val())){
            setInputInvalid("#groupSec","#groupSecFeedback","班级代号不能为空");
        }
        return 0;
    }

    allLock();
    $.post({
        url:rootUrl+"/register3",
        data:{
            groupName:$("#groupName").val(),
            groupSec:$("#groupSec").val()
        },
        success:function (result) {
            if(result.toString()==='EXISTING_GROUP'){
                setInputInvalid("#groupName","#groupNameFeedback","班级名称已经存在");
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
    if(checkIfEmpty($("#groupName").val())){
        setInputInvalid("#groupName","#groupNameFeedback","班级名称不能为空");
    }
    else{
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
                }
            },
            error:function () {
                showErrorAlert("网络未连接");
            },
            complete:function () {
                $("#groupName").attr("disabled",false);
            }
        });
    }
}

function checkGroupSec() {
    if(checkIfEmpty($("#groupSec").val())){
        setInputInvalid("#groupSec","#groupSecFeedback","班级名称不能为空");
    }
    else{
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
                }
            },
            error:function () {
                showErrorAlert("网络未连接");
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