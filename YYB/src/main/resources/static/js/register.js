var vUser = false;
var vPassword = false;
function register() {
    var userForm = $("#registerUserTable").serializeArray();
    var newPassword = hex_md5(userForm[1].value);
    userForm[1].value = newPassword.substring(6, 6 + userForm[1].value.length);
    $("input[name='password']").val(userForm[1].value);
    if (vUser && vPassword) {
        $.post("/registerUser", userForm, function (data) {
            alert("注册成功,请自行登录");
        });
    } else {
        alert("账号或密码或确认密码格式错误");
    }

}

function validationUserName(userName) {
    var uName = $(userName).val();
    var userParent = $(userName).parent();
    var regUserName = /^1[34578]\d{9}$/;
    if (regUserName.test(uName)) {
        if (userParent.hasClass("has-error")) {
            userParent.removeClass("has-error");
        }
        vUser = true;
        userParent.addClass("has-success");
        $("#UserError").empty();
    } else {
        if (userParent.hasClass("has-success")) {
            userParent.removeClass("has-success");
        }
        vUser = false;
        userParent.addClass("has-error");
        $("#UserError").html("手机号码格式错误");
    }
}

function validationPassword(password) {
    var ps = $(password).val();
    var psParent = $(password).parent();
    var regPassword = /^[a-z0-9_-]{6,15}$/;
    if (regPassword.test(ps)) {
        if (psParent.hasClass("has-error")) {
            psParent.removeClass("has-error");
        }
        vPassword = true;
        psParent.addClass("has-success");
    } else {
        if (psParent.hasClass("has-success")) {
            psParent.removeClass("has-success");
        }
        vPassword = false;
        psParent.addClass("has-error");
    }
}

var minute = -1;
var MSStatusHmtl = $("#MSStatus");
var showTimeInterval = null;

/*定时触发函数*/
function showTime() {
    if (minute < 0) {
        clearInterval(showTimeInterval);
        MSStatusHmtl.html("重新获取验证码");
        MSStatusHmtl.attr("onclick", "sendMSG()");
    } else {
        MSStatusHmtl.html("已发送&nbsp;&nbsp;(" + minute + "s)")
        minute = minute - 1;
    }
}

/*点击发送事件*/
function sendMSG() {
    if (vUser) {
        var phone = $("input[name='userName']").val();
        MSStatusHmtl.removeAttr("onclick");
        $.get("/sendMSG", {
            phone: phone
        }, function (data) {
            if(data.status!='error'){
              minute = 120;
              showTimeInterval = setInterval(showTime, 1000);
            }else {
                alert(data.error);
            }
        });
    }else{
        $("input[name='userName']").parent().addClass("has-error");
        $("#UserError").html("请先填写手机号码");
    }
}