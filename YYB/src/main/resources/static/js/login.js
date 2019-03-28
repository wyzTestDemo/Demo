$(function () {
    /*回车的动作执行*/
    $("input").each(function (index, val) {
        $(val).keydown(function () {
            if (event.keyCode == 13) {
                if ($("input").length != (index + 1)) {
                    $("input")[index + 1].focus();
                    return false;
                }
            }
        });
    });
    if ($.cookie("rmbMe") == "true") {
        $("#rmbMe").attr("checked", true);
        $("#userName").val($.cookie("userName"));
        $("#password").val($.cookie("userName"));
    }
});
// 记住用户名，默认不记住
var checkFlg = false;
// 记住用户名
$("#rmbMe").click(function () {
    checkFlg = $(this).is(':checked');
    console.info(checkFlg);
});

// 保存用户名
function setCookie() {
    if (checkFlg) {
        var userName = $("#userName").val();
        var password = $("#password").val();
        $.cookie("rmbMe", "true", {expires: 7}); // 记住我勾选
        $.cookie("userName", userName, {expires: 7}); // 存储一个带7天期限的 cookie
        $.cookie("password", password, {expires: 7}); // 存储一个带7天期限的 cookie
    } else {
        $.cookie("rmbMe", "false", {expires: -1}); // 删除 cookie
        $.cookie("userName", '', {expires: -1});
        $.cookie("password", '', {expires: -1});
    }
}

function login() {
    var password = $("#password").val();
    var count = password.length;
    var newPassword = hex_md5(password);
    newPassword = newPassword.substring(6, 6 + count);
    $("#password").val(newPassword);
    setCookie();

}