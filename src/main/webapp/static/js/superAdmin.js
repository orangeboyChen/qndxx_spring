var rootUrl;

$(document).ready(function () {
   rootUrl = $("#rootUrl").val()+"/superAdmin";
});

function onagree(obj) {
    var id = obj.name;
    $.post({
        url:rootUrl+"/agree",
        data:{
            email: $(".email[name='" + id + "']").text(),
            school: $(".school[name='" + id + "']").val(),
            institution: $(".institution[name='" + id + "']").val(),
            groupName: $(".groupName[name='" + id + "']").val()
        },
        success:function () {
            location.reload();
        }
    });
}

function onreject(obj) {
    var id = obj.name;
    $.post({
        url:rootUrl+"/reject",
        data:{
            email: $(".email[name='" + id + "']").text()
        },
        success:function () {
            location.reload();
        }
    });
}