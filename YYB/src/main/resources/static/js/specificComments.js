$(".pageHref").each(function () {
    var mHref = "/specificComments" + "?spencificCommentsId=" + postM.id + "&pn=" + $(this).attr("href");
    $(this).attr("href", mHref);
    $(this).css("cursor", "pointer");
});
