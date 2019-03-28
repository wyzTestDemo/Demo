function sendPostCo(url, n, m) {
    $.ajax({
        url: url,
        type: 'POST',
        data: n,
        success: function (data) {
            m(data)
        },
        complete: function (xhr, statuus) {
            var REDIRECT = xhr.getResponseHeader("REDIRECT");
            if (REDIRECT == "REDIRECT") {
                var win = window;
                while (win != win.top) {
                    win = win.top;
                }
                win.location.href = xhr.getResponseHeader("CONTENTPATH");
            }
        }
    });
}
var collection ;
var successHeart = function successHeart(data) {
    collection.removeClass("fa-heart-o");
    collection.addClass("fa-heart");
}
var successCancel = function successCancel(data) {
    collection.removeClass("fa-heart");
    collection.addClass("fa-heart-o");
}

function heart(m, id) {
    /*找到当前点击的元素*/
    collection = $(m).children("i").first();
    if (collection.hasClass("fa-heart-o")) {
        sendPostCo("/collectionMusic", {uploadMusicId: id}, successHeart);
    } else {
        sendPostCo("/cancelCollection", {uploadMusicId: id}, successCancel);
    }
}