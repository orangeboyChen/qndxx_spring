var regex = new RegExp("^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$");
function register3Forward() {
    initProgress();
    if (checkIfEmpty($("#adminEmail").val())||checkIfEmpty($("#code").val())){
        if(checkIfEmpty($("#adminEmail").val())){
            setInputInvalid("#adminEmail","#adminEmailFeedback","管理员邮箱不能为空");
            pn1(false);
        }
        if(checkIfEmpty($("#code").val())){
            setInputInvalid("#code","#codeFeedback","验证码不能为空");
            $("#code").addClass( "col-6 col-sm-6");
            pn2(false);
        }
        return;
    }

    var isReturn = false;
    if(!regex.test($("#adminEmail").val())){
        setInputInvalid("#adminEmail","#adminEmailFeedback","邮箱地址不正确");
        pn1(false);
        isReturn=true;
    }

    if($("#code").val().length!==6){
        setInputInvalid("#code","#codeFeedback","验证码长度不正确");
        $("#code").addClass( "col-6 col-sm-6");
        pn2(false);
        isReturn=true;
    }
    if(isReturn) return;

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
                pn2(false);
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
    pn1(false);
    if(checkIfEmpty($("#adminEmail").val())){
        setInputInvalid("#adminEmail","#adminEmailFeedback","管理员邮箱不能为空");
    }
    else if(!regex.test($("#adminEmail").val())){
        setInputInvalid("#adminEmail","#adminEmailFeedback","邮箱地址不正确");
    }
    else{
        pn1(true);
        $("#adminEmail,#getCode").attr("disabled",true);
        $.post({
            url:rootUrl+"/ajax/getCode",
            data:{email:$("#adminEmail").val()},
            success:function (result) {
                pn1(false);
                if(result.toString()==='true'){
                    setInputValid("#adminEmail","#adminEmailFeedback","验证码发送成功");
                    pn1(true);
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
    initProgress();
    if(checkIfEmpty($("#adminEmail").val())){
        setInputInvalid("#adminEmail","#adminEmailFeedback","管理员邮箱不能为空");
        pn1(false);
    }
    else if(!regex.test($("#adminEmail").val())){
        setInputInvalid("#adminEmail","#adminEmailFeedback","邮箱地址不正确");
        pn1(false);
    }
    else{
        setInputValid("#adminEmail","#adminEmailFeedback","");
        pn1(true);
    }
}

function checkCode() {
    initProgress();
    if(checkIfEmpty($("#code").val())){
        setInputInvalid("#code","#codeFeedback","验证码不能为空");
        $("#code").addClass( "col-6 col-sm-6");
        pn2(false);
    }
    else if($("#code").val().length!==6){
        setInputInvalid("#code","#codeFeedback","验证码长度不正确");
        $("#code").addClass( "col-6 col-sm-6");
        pn2(false);
    }
    else{
        setInputNormal("#code","#codeEmailFeedback","");
        $("#code").addClass( "col-6 col-sm-6");
        pn2(true);
    }
}



function allLock() {
    $("#adminEmail,#getCode,#code,#regist3Next,#regist3Previous").attr("disabled",true);
}

function allUnlock() {
    $("#adminEmail,#getCode,#code,#regist3Next,#regist3Previous").attr("disabled",false);
}

function startHelpModal() {
    $.post({
        url:rootUrl+"/ajax/emailHelp",
        success:function (data) {
                $("#helpContent").empty().html(data);
        },
        error:function () {
            $("#helpContent").empty().html("<h6 class='text-danger'>网络未连接</h6>");
        },
    });
}

var ani1=0;
var ani2=0;
var MAX=66;

var checkProgress = false;
var preProgress=44;

