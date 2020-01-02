function toHome() {
    $("body").fadeOut(100,function () {
        location.href=homeUrl+"/";
    });
}