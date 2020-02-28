var rootUrl;
var homeUrl;
var systemFail='系统拒绝了你的请求';
var netFail='网络连接失败';
var todoObj;

$(document).ready(function(){
    homeUrl = $('#requestUrl').val();
    rootUrl = $('#requestUrl').val()+'/admin';

    var page =  window.location.hash.substr(1);
    if(page==null || page === '')page = "1";

    $.get({
        url:rootUrl+'/page/' + page,
        success: function (result) {
            switch (page){
                case "1":
                    switchPage("toIndex",result);
                    break;
                case "2":
                    switchPage("toSetting",result);
                    break;
                case "3":
                    switchPage("toCompletedList",result);
                    break;
                case "4":
                    switchPage("toChangeList",result);
                    break;
                case "5":
                    switchPage("toChangeInfo",result);
                    break;
                default:
                    switchPage("toIndex",result);
            }


        }
    });
    $('#alert').css('display','none');
    $('body').hide().fadeIn(150);
});

function alertSuccess(str) {
    $('#alertTitle').html('操作成功！');
    $('#alertTxt').html(str);
    $('#alertDiv').css('display','block');
    $('#alert').removeClass().addClass('alert alert-success alert-dismissible')
        .stop()
        .fadeIn(100)
        .fadeOut(3000,
            function () {
        $('#alertDiv').css('display','none');
    });
}

function alertFail(str) {
    $('#alertTitle').html('操作失败！');
    $('#alertTxt').html(str);
    $('#alertDiv').css('display','block');
    $('#alert').removeClass().addClass('alert alert-danger alert-dismissible')
        .stop()
        .fadeIn(100)
        .fadeOut(
            3000,
            function () {
        $('#alertDiv').css('display','none');
    });
}

function setSaying() {
    $.post({
        url:rootUrl+'/setSaying',
        data:{'saying':$('#sayingTxt').val()},
        success:function (result) {
            if(result==='true'){
                alertSuccess('');
            }
            else{
                alertFail(systemFail);
            }
        },
        error:function () {
            alertFail(netFail);
        }
    });
}

function newTerm() {
    $.post({
        url:rootUrl+'/newTerm',
        data:{'saying':$('#sayingTxt').val()},
        success:function (result) {
            if(result==='true'){
                alertSuccess('');
                toIndex();
            }
            else{
                alertFail(systemFail);
            }
        },
        error:function () {
            alertFail(netFail);
        },
        complete:function () {
            $('#modalSubmittedBtn').attr('disabled',false);
            $('#makeSureModal').modal('hide');
        }
    });

}

function deleteStudent(obj) {
    var id=obj.id;
    $.post({
        url:rootUrl+'/deleteStudent',
        data:{'id':id},
        success:function (result) {
            if(result==='true'){
                alertSuccess('');
                $("tr[name='"+id+"']").remove();
                changeCount();
            }
            else{
                alertFail(systemFail);
            }
        },
        error:function () {
            alertFail(netFail);
        },
        complete:function () {
            $('#modalSubmittedBtn').attr('disabled',false);
            $('#makeSureModal').modal('hide');
        }
    });



}


function changeRequireState(obj) {
    var url;
    var isChecked = obj.checked;
    if(obj.checked){
        url = rootUrl + '/changeRequireStateToYes';
    }
    else{
        url = rootUrl+'/changeRequireStateToNo';
    }
    $.post({
        url: url,
        data:{'id':obj.id},
        success:function (result) {
            if(result==='true'){
                alertSuccess('');
                if(isChecked){
                    $('#'+obj.id).prop('checked',true);
                    changeCount();
                }
                else{
                    $('#'+obj.id).prop('checked',false);
                    changeCount();
                }
            }
            else{
                alertFail(systemFail);
            }
        },
        error:function () {
            alertFail(netFail);
        }
    });
}

function changeInfo() {
    $.post({
        url:rootUrl+'/changeInfo',
        data:{
            //'groupName':$('#groupNameInput').val(),
            'groupSec':$('#groupSecInput').val()
        },
        success:function (result) {
            if(result==='true'){
                alertSuccess('');
                $('#changeInfo').attr('type','button');
                $('#submitInfo').attr('type','hidden');
                $('#groupSec').text($('#groupSecInput').val());
                $('#groupSecInput').attr('type','hidden');
                $('#groupSec').show();
            }
            else{
                alertFail(systemFail);
            }
        },
        error:function () {
            alertFail(netFail);
        }
    });
}

function showSubmitInfo() {
    $('#changeInfo').attr('type','hidden');
    $('#submitInfo').attr('type','button');
    $('#groupSecInput').val($('#groupSec').text());
    $('#groupSecInput').attr('type','text');
    $('#groupSec').hide();

}


function startModal(obj) {
    todoObj=obj;
    var id=todoObj.id;
    var todo=obj.name;
    switch (todo){
        case 'new':
            $('#modalContent').html('你准备开启新的大学习。这意味着当前大学习所有完成的数据将会丢失。');
            break;
        case 'delete':
            var name= $("h6[name='"+id+"']").text();
            $('#modalContent').html('你真的要删除'+name+'吗？你的良心不会痛吗？');
            break;
        case 'init':
            $('#modalContent').html('你真的想要删掉所有人以体验删库的快感？');
            break;
    }
    $('#makeSureModal').modal('show');
}

function modalCanceled() {
    todoObj=null;
}

function modalSubmitted() {
    var todo=todoObj.name;
    $('#modalSubmittedBtn').attr('disabled',true);
    switch (todo){
        case 'new':
            newTerm();
            break;
        case 'delete':
            deleteStudent(todoObj);
            break;
        case 'init':
            initStudents();
    }
    todoObj=null;
}

function initStudents() {
    $.post({
        url:rootUrl+'/init',
        success:function () {
            $('#completedTable').remove();
            $('#completedText').remove();
            $('#newCompletedText').text('暂未有学生完成');
            $('#studentTable').empty().html('<tr id=\'tableHead\'><th>姓名及学号</th><th>是否要求完成</th><th>指令</th></tr>');
            alertSuccess('已删库');
        },
        error:function(){
            alertFail('删库失败，你网络太差了。要不要试试\'rm -rf /\'？');
        },
        complete:function () {
            $('#modalSubmittedBtn').attr('disabled',false);
            $('#makeSureModal').modal('hide');
        }
    });
}

function allRequireYes() {
    $('#ck').attr('disabled',true);
    $("input[type='checkbox']").prop('disabled',true);
    var count = $('tr');
    $('#requiredCount').text(count.length-1);
    $.post({
        url:rootUrl+'/initAllRequest',
        data:{},
        success:function (result) {
            if(result==='true'){
                alertSuccess('');
                $("input[type='checkbox']")
                    .prop('checked',true);
            }
            else{
                alertFail(systemFail);
            }


        },
        error:function () {
            alertFail(netFail);
        },
        complete:function () {
            $("input[type='checkbox']").prop('disabled',false);
            $('#ck').attr('disabled',false);
        }

    })
}

var canChangePassword1=false;
var canChangePassword2=false;
var canChangePassword3=false;
function passwordSubmitted() {
    oldPasswordOnblur();
    newPasswordCommittedOninput();
    newPasswordOninput();
    if(canChangePassword1&&canChangePassword2&&canChangePassword3){
        $('#passwordSubmittedBtn').attr('disabled',true);
        $.post({
            url:rootUrl+'/changePassword',
            data:{
                oldPassword:$('#oldPassword').val(),
                newPassword:$('#newPasswordCommitted').val()
            },
            success:function (result) {
                if(result==='true'){
                    $('#changePasswordModal').modal('hide');
                    alertSuccess('修改密码成功');
                    changePasswordModalCanceled();
                }
                else{
                    $('#changePasswordModal').modal('hide');
                    alertFail('修改密码失败');
                }

            },
            error:function (result) {
                $('#changePasswordModal').modal('hide');
                alertFail('修改密码失败，请检查网络连接。');
            },
            complete:function () {
                $('#passwordSubmittedBtn').attr('disabled',false);
            }
        });
    }

}

function oldPasswordOnblur() {
    if(checkIfEmpty($('#oldPassword').val())){
        $('#oldPassword').removeClass();
        $('#oldPassword').addClass('form-control is-invalid');
        $('#oldPasswordFeedback').show();
        $('#oldPasswordFeedback').removeClass();
        $('#oldPasswordFeedback').addClass('invalid-feedback');
        $('#oldPasswordFeedback').html('原密码不能为空');
        canChangePassword1=false;
    }
    else{
        $.post({
            url:rootUrl+'/checkPassword',
            data:{password:$('#oldPassword').val()},
            success:function (data) {
                if(data==='true'){
                    $('#oldPassword')
                        .removeClass()
                        .addClass('form-control is-valid');
                    $('#oldPasswordFeedback')
                        .hide()
                        .removeClass();
                    canChangePassword1=true;
                }
                else {
                    $('#oldPassword')
                        .removeClass()
                        .addClass('form-control is-invalid');
                    $('#oldPasswordFeedback')
                        .show()
                        .removeClass()
                        .addClass('invalid-feedback')
                        .html('原密码不正确');
                    canChangePassword1=false;
                }
            },
            error:function (data) {
                $('#oldPasswordFeedback')
                    .show()
                    .removeClass()
                    .addClass('invalid-feedback')
                    .html('网络连接失败');
                canChangePassword1=false;
            }
        });

    }
}

function newPasswordOninput() {
    canChangePassword2=false;
    if($('#newPassword').val().length===0){
        $('#newPassword')
            .removeClass()
            .addClass('form-control is-invalid');
        $('#newPasswordFeedback')
            .show()
            .removeClass().addClass('invalid-feedback').html('新密码不能为空');
    }
    else if($('#newPassword').val()===$('#oldPassword').val()){
        $('#newPassword').removeClass().addClass('form-control is-invalid');
        $('#newPasswordFeedback')
            .show()
            .removeClass()
            .addClass('invalid-feedback')
            .html('新密码不能与原密码相同');
    }
    else if(!ifValidPassword($('#newPassword').val())){
        $('#newPassword').removeClass().addClass('form-control is-invalid');
        $('#newPasswordCommitted').addClass('form-control is-invalid');
        $('#newPasswordFeedback')
            .show()
            .removeClass()
            .addClass('invalid-feedback')
            .html('密码长度不符合要求');
    }
    else {
        $('#newPassword')
            .removeClass()
            .addClass('form-control is-valid');
        $('#newPasswordFeedback')
            .hide()
            .removeClass();
        canChangePassword2 = true;
    }

}

function newPasswordCommittedOninput() {
    canChangePassword3=false;
    if($('#newPasswordCommitted').val().length===0){
        $('#newPasswordCommitted').removeClass().addClass('form-control is-invalid');
        $('#newPasswordCommittedFeedback').show();
        $('#newPasswordCommittedFeedback').removeClass().addClass('invalid-feedback').html('确认密码不能为空');
    }
    else if($('#newPasswordCommitted').val()===$('#newPassword').val()){
        $('#newPasswordCommitted').removeClass();
        $('#newPasswordCommitted').addClass('form-control is-valid');
        $('#newPasswordCommittedFeedback').hide();
        $('#newPasswordCommittedFeedback').removeClass();
        canChangePassword3=true;
    }
    else {
        $('#newPasswordCommitted').removeClass();
        $('#newPasswordCommitted').addClass('form-control is-invalid');
        $('#newPasswordCommittedFeedback').show();
        $('#newPasswordCommittedFeedback').removeClass().addClass('invalid-feedback').html('确认密码与新密码不一致');
    }
}

function changePasswordModalCanceled() {
    $('#oldPassword').removeClass().addClass('form-control').val('');
    $('#oldPasswordFeedback').hide();

    $('#newPassword').removeClass().addClass('form-control').val('');
    $('#newPasswordFeedback').hide();

    $('#newPasswordCommitted').removeClass().addClass('form-control').val('');
    $('#newPasswordCommittedFeedback').hide();
}

var addStudent1=false;
var addStudent2=false;
function addStudentIdOnblur() {
    addStudent1=false;
    if(checkIfEmpty($('#addStudentId').val())){
        $('#addStudentIdFeedback')
            .show()
            .removeClass().addClass('invalid-feedback').html('学号不能为空');
        $('#addStudentId').removeClass().addClass('form-control is-invalid');
    }
    else{
        $.post({
            url:rootUrl+'/sameIdCheck',
            data:{id:$('#addStudentId').val()},
            success:function (data) {
                if(data.toString()==='true'){
                    $('#addStudentId')
                        .removeClass()
                        .addClass('form-control is-valid');
                    $('#addStudentIdFeedback')
                        .hide()
                        .removeClass();
                    addStudent1=true;
                }
                else{
                    $('#addStudentIdFeedback')
                        .show()
                        .removeClass()
                        .addClass('invalid-feedback')
                        .html('学号已被使用');
                    $('#addStudentId').removeClass().addClass('form-control is-invalid');

                }
            },
            error:function () {
                $('#addStudentId')
                    .removeClass()
                    .addClass('form-control is-invalid');
                $('#addStudentIdFeedback')
                    .show()
                    .removeClass().addClass('invalid-feedback').html('网络连接失败');
            }
        });
    }
}

function addStudentNameOnblur() {
    addStudent2=false;
    if(checkIfEmpty(($('#addStudentName').val()))){
        $('#addStudentName')
            .removeClass()
            .addClass('form-control is-invalid');
        $('#addStudentNameFeedback')
            .show()
            .removeClass()
            .addClass('invalid-feedback')
            .html('姓名不能为空');
    }
    else{
        $('#addStudentName')
            .removeClass()
            .addClass('form-control is-valid');
        $('#addStudentNameFeedback')
            .hide()
            .removeClass();
        addStudent2=true;
    }
}

function addSubmit() {
    if($('#addStudentId').val().length===0){
        $('#addStudentIdFeedback')
            .show()
            .removeClass()
            .addClass('invalid-feedback')
            .html('学号不能为空');
        $('#addStudentId')
            .removeClass()
            .addClass('form-control is-invalid');
        addStudent1=false;
    }
    addStudentNameOnblur();
    if(addStudent1&&addStudent2){
        $.post({
            url:rootUrl+'/addStudent',
            data:{
                id:$('#addStudentId').val(),
                name:$('#addStudentName').val()
            },
            success:function (result) {
                if(result.toString()==='true'){
                    alertSuccess($('#addStudentName').val()+'添加成功');
                    $('#addStudentId').removeClass().addClass('form-control');
                    $('#addStudentName').removeClass().addClass('form-control');

                    var deleteDisplay;
                    if($('#deleteOn').attr('type')==='button'){
                        deleteDisplay = 'none';
                    }
                    else{
                        deleteDisplay = 'table-cell';
                    }


                    $('#tableHead').after('<tr name="' + $('#addStudentId').val() + '" class="table-success">' +
                        '<td>' +
                        '<h6 name="' + $('#addStudentId').val() +'">' + $('#addStudentName').val() + '</h6>' +
                        '<p name="' + $('#addStudentId').val() +'">' + $('#addStudentId').val() +'</p>' +
                        '</td>' +
                        '<td style="vertical-align: middle;">' +
                        '<div class="custom-control custom-switch text-right">' +
                        '<input type="checkbox" class="custom-control-input" id="' + $('#addStudentId').val() +'" checked onclick="changeRequireState(this)">' +
                        '<label class="custom-control-label" for="' + $('#addStudentId').val() +'"></label>' +
                        '</div>' +
                        '</td>' +
                        '<td style="vertical-align: middle;display: ' + deleteDisplay + ';" name="deleteTd" class="align-items-center" name="deleteTd">' +
                        '<input type="button" class="btn btn-danger btn-sm" value="删除" name="delete" id="' + $('#addStudentId').val() +'" onclick="startModal(this)" style="vertical-align: middle;"/>' +
                        '</td>' +
                        '</tr>');

                    // $('#tableHead').after('<tr name="'+$('#addStudentId').val()+'">\n    ' +
                    //     '<td>\n        ' +
                    //     '<h6 name="'+$('#addStudentId').val()+'">'+$('#addStudentName').val()+'</h6>\n        ' +
                    //     '<p name="'+$('#addStudentId').val()+'">'+$('#addStudentId').val()+'</p>\n    ' +
                    //     '</td>\n    <td style="vertical-align: middle;">\n        <div class="btn-group">\n            ' +
                    //     '<input\tclass="btn btn-primary" name="requiredYes" type="button" value="当前为是" id="'+$('#addStudentId').val()+' "onclick=\'changeRequireStateToNo(this)\'/>\n        </div>\n    </td>\n    <td style="vertical-align: middle;">\n        <input type="button" class="btn btn-danger" value="删除" name="delete" id="'+$('#addStudentId').val()+'" onclick="startModal(this)"/>\n    </td>\n</tr>\n    \n'
                    // );
                    // $('#studentTable').prepend('<tr name=\''+$('#addStudentId').val()+'\'>\n    ' +
                    //     '<td>\n        ' +
                    //     '<h6 name=\''+$('#addStudentId').val()+'\'>'+$('#addStudentName').val()+'</h6>\n        ' +
                    //     '<p name=\''+$('#addStudentId').val()+'\'>'+$('#addStudentId').val()+'</p>\n    ' +
                    //     '</td>\n    <td style=\'vertical-align: middle;\'>\n        <div class=\'btn-group\'>\n            <input\tclass=\'btn btn-primary\' name=\'requiredYes\' type=\'button\' value=\'当前为是\' id=\''+$('#addStudentId').val()+'\' onclick=\'changeRequireStateToNo(this)\'/>\n        </div>\n    </td>\n    <td style=\'vertical-align: middle;\'>\n        <input type=\'button\' class=\'btn btn-danger\' value=\'删除\' name=\'delete\' id=\''+$('#addStudentId').val()+'\' onclick=\'startModal(this)\'/>\n    </td>\n</tr>\n    \n'
                    // );

                    addStudent1=false;
                    addStudent2=false;
                    changeCount();
                    $('#addStudentId').val('');
                    $('#addStudentName').val('');
                }
                else{
                    alertFail('服务器拒绝了你的请求');
                }
            },
            error:function () {
                alertFail(netFail);
            }
        });
    }
}

function checkIfEmpty(obj) {
    return typeof obj == 'undefined' || obj == null || obj.trim() === '';
}

function toAddMutiple() {
    $('html').fadeOut(150,function () {
        window.location.href=rootUrl+'/addStudents';
    });
}

function logOut() {
    $.post({
        url:rootUrl+"/logOut",
        success:function (result) {
            $('html').fadeOut(150,function () {
                window.location.href=homeUrl+'/';
            });
        }
    });

}

function ifValidPassword(password) {
    return password.length >= 8 && password.length <= 16;
}

function toIndex() {
    $.get({
        url: rootUrl + '/page/1',
        success: function (result) {
            switchPage("toIndex",result);
            window.location.hash = "";
        }
    })
}

function toSetting() {
    $.get({
        url: rootUrl + '/page/2',
        success: function (result) {
            switchPage("toSetting",result);
            window.location.hash = "2";
        }
    })
}

function toCompletedList() {
    $.get({
        url: rootUrl + '/page/3',
        success: function (result) {
            switchPage("toCompletedList",result);
            window.location.hash = "3";
        }
    })
}

function toChangeList() {
    $.get({
        url: rootUrl + '/page/4',
        success: function (result) {
            switchPage("toChangeList",result);
            window.location.hash = "4";
        }
    })
}

function toChangeInfo() {
    $.get({
        url: rootUrl + '/page/5',
        success: function (result) {
            switchPage("toChangeInfo",result);
            window.location.hash = "5";
        }
    })
}

function switchPage(todo,result) {
    $('#navbarToggler').collapse('hide');
    $('#toIndexLi,#toSettingLi,#toCompletedListLi,#toChangeListLi,#toChangeInfoLi').removeClass().addClass('nav-item');
    $('#' + todo + 'Li').addClass('active');
    $('#navbar').collapse('hide');
    $('#content').fadeOut(150,function () {
        $("#content").html(result).fadeIn(150);
    });
}

function dangerOn() {
    $('#deleteOn').attr('type','hidden');
    $("[name='deleteTd']").css("display","table-cell");
    $('#initBtn').attr('type','button');
}

function changeCount() {
    var checkboxs = $('input[type="checkbox"]');
    var allCount,RequiredCount = 0;
    allCount = checkboxs.length;
    for (let i = 0; i < checkboxs.length; i++) {
        if(checkboxs[i].checked) RequiredCount++;
    }
    $('#requiredCount').text(RequiredCount);
}

