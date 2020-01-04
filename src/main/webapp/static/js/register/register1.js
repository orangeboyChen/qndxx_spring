function register1Forward() {
    if (checkIfEmpty($("#school").val())||checkIfEmpty($("#institution").val())){
        if(checkIfEmpty($("#school").val())){
            setInputInvalid("#school","#schoolFeedback","学校不能为空");
        }
        if(checkIfEmpty($("#institution").val())){
            setInputInvalid("#institution","#institutionFeedback","学院不能为空");
        }
        return 0;
    }

    allLock();

    $.post({
        url:rootUrl+"/register2",
        data:{
            school:$("#school").val(),
            institution:$("#institution").val()
        },
        success:function (data) {
            pageForwardFadeOut(function () {
                $(".container").css("position","");
                $(".container").empty();
                $(".container").html(data);
                pageForwardFadeIn();
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

function register1Backward() {
    allLock();
    $.post({
        url:rootUrl,
        data:{
            school:$("#school").val(),
            institution:$("#institution").val()
        },
        success:function (data) {
            pageBackwardFadeOut(function () {
                $(".container").css("position","");
                $(".container").empty();
                $("body").fadeOut(150);
                window.location.href=rootUrl;

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


function checkSchool() {
    initProgress()
    if (checkIfEmpty($("#school").val())){
        pn1(false);

        setInputInvalid("#school","#schoolFeedback","学校不能为空");
        return;
    }
    else{
        pn1(true);

        $("#school").attr("disabled",true);
        $.post({
            url:rootUrl+"/ajax/checkSchool",
            data:{ school: $("#school").val()},
            success:function (result) {
                if(result.toString()==='true'){
                    setInputValid("#school","#schoolFeedback","已找到学校");
                }
                else{
                    setInputValid("#school","#schoolFeedback","未找到学校，但你可以继续注册");
                }
            },
            error:function () {
                showErrorAlert("网络未连接")
            },
            complete:function () {
                $("#school").attr("disabled",false);
            }
        });
    }
}

function checkInstitution() {
    initProgress()
    if (checkIfEmpty($("#school").val())||checkIfEmpty($("#institution").val())){
        if(checkIfEmpty($("#school").val())){
            pn1(false);

            setInputInvalid("#school","#schoolFeedback","学校不能为空");
        }
        else{
            pn1(true);

            setInputValid("#school","#schoolFeedback","");
        }
        if(checkIfEmpty($("#institution").val())){
            pn2(false);

            setInputInvalid("#institution","#institutionFeedback","学院不能为空");
        }
        else{
            pn2(true);

            setInputValid("#institution","#institutionFeedback","");
        }
    }
    else{
        pn1(true);
        pn2(true);

        $("#school").attr("disabled",true);
        $("#institution").attr("disabled",true);
        $.post({
            url:rootUrl+"/ajax/checkInstitution",
            data:{
                school: $("#school").val(),
                institution:$("#institution").val()

            },
            success:function (result) {
                if(result.toString()==='true'){
                    setInputValid("#school","#schoolFeedback","已找到学校与学院","");
                    setInputValid("#institution","#institutionFeedback","");
                }
                else if(result.toString()==='middle'){
                    setInputValid("#school","#schoolFeedback","已找到学校","");
                    setInputValid("#institution","#institutionFeedback","未找到学院，但你可以继续注册","");
                }
                else{
                    setInputValid("#school","#schoolFeedback","未找到学校与学院，但你可以继续注册","");
                    setInputValid("#institution","#institutionFeedback","");

                }
            },
            error:function () {
                showErrorAlert("网络未连接");
            },
            complete:function () {
                $("#school").attr("disabled",false);
                $("#institution").attr("disabled",false);
            }
        });
    }
}

function allLock(){
    $("#school,#institution,#regist1Next,#regist1Previous").attr("disabled",true);
}

function allUnlock(){
    $("#school,#institution,#regist1Next,#regist1Previous").attr("disabled",false);
}


var ani1=0;
var ani2=0;
var MAX=22;

var checkProgress = false;
var preProgress=0;




