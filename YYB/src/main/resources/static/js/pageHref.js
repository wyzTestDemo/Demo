
function pageSoso(soso,url) {
    $(".pageHref").each(function () {
        var mHref = url + "?soso=" + soso+"&pn="+$(this).attr("href");
        $(this).attr("href", mHref);
    });
}
function myPage(url) {
    $(".pageHref").each(function () {
        var mHref = url + "?pn=" + $(this).attr("href");
        $(this).attr("href", mHref);
    });
}