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
    initProgress()
    if(checkIfEmpty($("#adminRealName").val())){
        setInputInvalid("#adminRealName","#adminRealNameFeedback","姓名不能为空");
        canContinue1=false;
        pn1(false);
    }
    else{
        setInputValid("#adminRealName","#adminRealNameFeedback","");
        canContinue1=true;
        pn1(true);
    }
}

function checkPassword() {
    initProgress()
    canContinue2=true;
    setInputValid("#adminPassword","#adminPasswordFeedback","");
    setInputValid("#adminPassword2","#adminPassword2Feedback","");
    var password1=$("#adminPassword").val();
    var password2=$("#adminPassword2").val();

    pn2(true);
    pn3(true);

    if(password1!==password2){
        setInputInvalid("#adminPassword2","#adminPassword2Feedback","确认密码与原密码不一致");
        canContinue2=false;
        pn3(false);
    }
    if(checkIfEmpty(password1)||checkIfEmpty(password2)){
        if(checkIfEmpty(password1)){
            setInputInvalid("#adminPassword","#adminPasswordFeedback","密码不能为空");
            pn2(false);
        }
        if(checkIfEmpty(password2)){
            setInputInvalid("#adminPassword2","#adminPassword2Feedback","确认密码不能为空");
            pn3(false);
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

var ani1=0;
var ani2=0;
var ani3=0;
var MAX=93;
function progressAnimation() {
    console.log("ani4");
    $("#progress-bar").css("width",(preProgress+ani1+ani2+ani3)+"%");
    $("#progress-bar").attr("aria-valuenow",ani1+ani2+ani3+preProgress);
}

function pn3(bool) {
    if(preProgress<MAX){
        if(bool===true){
            ani3=11;
        }
        else{
            ani3=0;
        }
    }
    else{
        if(bool===true){
            ani3=0;
        }
        else{
            ani3=-11;
        }
    }
    progressAnimation();
}

var checkProgress = false;
var preProgress=66;