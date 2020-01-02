function toHome() {
    $("body").fadeOut(100,function () {
        location.href='${pageContext.request.contextPath}';
    });
}