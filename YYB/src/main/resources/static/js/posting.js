$(".mPostContent").each(function() {
    var mPostContent = $(this).text();
    $(this).empty();
    $(this).append(mPostContent);
});