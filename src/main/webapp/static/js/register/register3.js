var regex = new RegExp("^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$");
function register3Forward() {
    if (checkIfEmpty($("#adminEmail").val())||checkIfEmpty($("#code").val())){
        if(checkIfEmpty($("#adminEmail").val())){
            setInputInvalid("#adminEmail","#adminEmailFeedback","管理员邮箱不能为空");
        }
        if(checkIfEmpty($("#code").val())){
            setInputInvalid("#code","#codeFeedback","验证码不能为空");
            $("#code").addClass( "col-6 col-sm-6");
        }
        return;
    }
    else if(!regex.test($("#adminEmail").val())){
        setInputInvalid("#adminEmail","#adminEmailFeedback","邮箱地址不正确");
    }

    allLock();
    $.post({
        url:rootUrl+"/register4",
        data:{
            email:$("#adminEmail").val(),
            code:$("#code").val()
        },
        success:function (result) {
            var s=result.toString();
            if(result==2){
                setInputInvalid("#code","#codeFeedback","验证码不正确");
                $("#code").addClass( "col-6 col-sm-6");
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
            $("#code").addClass( "col-6 col-sm-6");
        }

    });
}

function register3Backward() {
    allLock();
    $.post({
        url:rootUrl+"/register2",
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

function getCode() {
    if(checkIfEmpty($("#adminEmail").val())){
        setInputInvalid("#adminEmail","#adminEmailFeedback","管理员邮箱不能为空");
    }
    else if(!regex.test($("#adminEmail").val())){
        setInputInvalid("#adminEmail","#adminEmailFeedback","邮箱地址不正确");
    }
    else{
        $("#adminEmail,#getCode").attr("disabled",true);
        $.post({
            url:rootUrl+"/ajax/getCode",
            data:{email:$("#adminEmail").val()},
            success:function (result) {
                if(result.toString()==='true'){
                    setInputValid("#adminEmail","#adminEmailFeedback","验证码发送成功");
                }
                else if(result.toString()==='EMAIL_HAS_BEEN_USED'){
                    setInputInvalid("#adminEmail","#adminEmailFeedback","该邮箱已被使用");
                }
                else if(result.toString()==='EMAIL_IS_CHECKING'){
                    setInputInvalid("#adminEmail","#adminEmailFeedback","该邮箱正在审核，请耐心等待");
                }
                else if(result.toString()==='INVALID_EMAIL'){
                    setInputInvalid("#adminEmail","#adminEmailFeedback","邮箱地址不正确");
                }
                else{
                    setInputInvalid("#adminEmail","#adminEmailFeedback","请"+result.toString()+"分钟后再试");
                }
            },
            error:function () {
                showErrorAlert("网络未连接")
            },
            complete:function () {
                $("#adminEmail,#getCode").attr("disabled",false);
            }
        });
    }
}

function checkEmail() {
    if(checkIfEmpty($("#adminEmail").val())){
        setInputInvalid("#adminEmail","#adminEmailFeedback","邮箱不能为空");
    }
    else{
        setInputValid("#adminEmail","#adminEmailFeedback","");
    }
}

function checkCode() {
    if(checkIfEmpty($("#code").val())){
        setInputInvalid("#code","#codeFeedback","邮箱不能为空");
    }
    else{
        setInputValid("#codeEmail","#codeEmailFeedback","");
    }
}



function allLock() {
    $("#adminEmail,#getCode,#code,#regist3Next,#regist3Previous").attr("disabled",true);
}

function allUnlock() {
    $("#adminEmail,#getCode,#code,#regist3Next,#regist3Previous").attr("disabled",false);

}