var rootUrl;
var homeUrl;
$(document).ready(function () {

    rootUrl=$("#rootUrl").val()+"/register";
    homeUrl=$("#rootUrl").val();
    //pageForwardFadeIn();
    $("body").hide().fadeIn(100);
});

function registerForward() {
    $.post({
        url:rootUrl+"/register1",
        success:function (data) {
            pageForwardFadeOut(function () {
                $(".container").css("position","");
                $(".container").empty();
                $(".container").html(data);
                pageForwardFadeIn();
            });
        }
    });
}

function registerBackward() {
    pageBackwardFadeOut(function () {
        $(".container").css("position","");
        window.location.href=homeUrl;
    });
}

function pageForwardFadeIn(onFinish) {
    if(onFinish==null) {
        onFinish = function () {
            $(".container").css("position", "");
        }
    }
    $(".container").css("position","relative");
    $(".container").css("opacity","0");
    $(".container").css("right","-1rem");
    $(".container").animate({
        right:'0rem',
        opacity:'1'
    },100,onFinish);
}

function pageForwardFadeOut(onFinish) {
    if(onFinish==null) {
        onFinish = function () {
            $(".container").css("position", "");
        }
    }
    $(".container").css("position","relative");
    $(".container").css("opacity","1");
    $(".container").css("right","0rem");
    $(".container").animate({
        right:'1rem',
        opacity:'0'
    },100,onFinish);
}

function pageBackwardFadeIn(onFinish) {
    if(onFinish==null) {
        onFinish = function () {
            $(".container").css("position", "");
        }
    }
    $(".container").css("position","relative");
    $(".container").css("opacity","0");
    $(".container").css("right","1rem");
    $(".container").animate({
        right:'0rem',
        opacity:'1'
    },100,onFinish);
}

function pageBackwardFadeOut(onFinish) {
    if(onFinish==null) {
        onFinish = function () {
            $(".container").css("position", "");
        }
    }
    $(".container").css("position","relative");
    $(".container").css("opacity","1");
    $(".container").css("right","0rem");
    $(".container").animate({
        right:'-1rem',
        opacity:'0'
    },100,onFinish);
}

function checkIfEmpty(obj) {
    return typeof obj == "undefined" || obj == null || obj.trim() === "";
}

function setInputValid(inputId,feedbackId,str) {
    $(inputId).removeClass().addClass("form-control is-valid");
    $(feedbackId).removeClass().addClass("valid-feedback").html(str).show();
}

function setInputInvalid(inputId,feedbackId,str) {
    $(inputId).removeClass().addClass("form-control is-invalid");
    $(feedbackId).removeClass().addClass("invalid-feedback").html(str).show();
}

function setInputNormal(inputId,feedbackId,str) {
    $(inputId).removeClass().addClass("form-control");
    $(feedbackId).removeClass().hide();
}

function showErrorAlert(str) {
    $("#alertTitle").html("尝试连接网络失败！");
    $("#alertTxt").html(str);
    $("#alertDiv").css("display","block");
    $("#alert").removeClass();
    $("#alert").addClass("alert alert-danger alert-dismissible");
    $("#alert").stop();
    $("#alert").fadeIn(100);
    $("#alert").fadeOut(3000,function () {
        $("#alertDiv").css("display","none");
    });
}