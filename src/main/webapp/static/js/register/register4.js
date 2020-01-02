var canContinue1=false;
var canContinue2=false;
function register4Forward() {
    checkRealName();
    checkPassword();
    if(canContinue1&&canContinue2){
        allLock();
        $.post({
            url:rootUrl+"/register5",
            data:{
                realName:$("#adminRealName").val(),
                password:$("#adminPassword2").val()
            },
            success:function (result) {
                $(".container").css("position","");
                $(".container").empty();
                $(".container").html(result);
                pageForwardFadeIn();
            },
            error:function () {
                showErrorAlert("网络未连接");
            },
            complete:function () {
                allUnlock();
            }
        });
    }
}

function register4Backward() {
    allLock();
    $.post({
        url:rootUrl+"/register3",
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

function checkRealName() {
    if(checkIfEmpty($("#adminRealName").val())){
        setInputInvalid("#adminRealName","#adminRealNameFeedback","姓名不能为空");
        canContinue1=false;
    }
    else{
        setInputValid("#adminRealName","#adminRealNameFeedback","");
        canContinue1=true;
    }
}

function checkPassword() {
    canContinue2=true;
    setInputValid("#adminPassword","#adminPasswordFeedback","");
    setInputValid("#adminPassword2","#adminPassword2Feedback","");
    var password1=$("#adminPassword").val();
    var password2=$("#adminPassword2").val();
    if(password1!==password2){
        setInputInvalid("#adminPassword2","#adminPassword2Feedback","确认密码与原密码不一致");
        canContinue2=false;
    }
    if(checkIfEmpty(password1)||checkIfEmpty(password2)){
        if(checkIfEmpty(password1)){
            setInputInvalid("#adminPassword","#adminPasswordFeedback","密码不能为空");
        }
        if(checkIfEmpty(password2)){
            setInputInvalid("#adminPassword2","#adminPassword2Feedback","确认密码不能为空");
        }
        canContinue2=false;
    }

}


function allLock() {
    $("#adminRealName,#adminPassword,#adminPassword2,#regist4Next,#regist4Previous").attr("disabled",true);
}

function allUnlock() {
    $("#adminRealName,#adminPassword,#adminPassword2,#regist4Next,#regist4Previous").attr("disabled",false);
}
