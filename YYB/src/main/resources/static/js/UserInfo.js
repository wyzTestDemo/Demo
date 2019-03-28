/*更换头像*/
function userChange(m) {
    var formData = new FormData();
    formData.append("file", $("#FSUserIMG")[0].files[0]);
    postSendImg(formData);
    /*修改img的src*/
    $filePath=URL.createObjectURL(m.files[0]);
    $('#img').attr('src',$filePath);
    /*重置选择后的状态*/
    $(m).remove();
    $("#userImgD").append(" <input type='file' title='更换头像' id='FSUserIMG' onchange='userChange(this)' accept='image/png, image/jpeg, image/gif, image/jpg' style='opacity: 0;display: block;  width: 100%; height: 100%;position: absolute;top: 0;left: 0;cursor: pointer;'/>");
}

function postSendImg(formData) {
    $.ajax({
        url: "/changeUserImage",
        type: 'POST',
        cache: false,
        data:formData,
        processData: false,
        contentType: false,
        dataType:"json",
        beforeSend: function(){
            uploading = true;
        },
        success : function(data) {
            alert("成功更换头像")
        }
    });
}


/*用户信息修改成功回调函数*/
var successUpUserCallback = function successUpUserCallback(data) {
    var resutl;
    if(data.updateStatus==='success'){
        resutl = '成功修改用户信息';
        $("#newPassword").addClass("hide");
        $("input[name='oldPassword']").val($("input[name='password']").val());
        $("#password").empty();
    }else{
        resutl = '用户信息修改错误，详细内容请于管理员联系';
    }
    alert(resutl);
}
function editUser(m){
    $(m).addClass("hide");
    $(m).next().removeClass("hide");
    $("#editP").attr("onclick","editPassword()");
    $(".editUserInfo").each(function () {
        $(this).removeAttr("readonly");
    });
}
function updateUser(m) {
    $(m).addClass("hide");
    $(m).prev().removeClass("hide");
    $(".editUserInfo").each(function () {
        $(this).attr("readonly","readonly");
    });
    if($("#newPassword").hasClass("hide")==false){
        var password = $("input[name='password']").val();
        var count = password.length;
        var newPassword = hex_md5(password);
        newPassword = newPassword.substring(6, 6 + count);
        $("input[name='password']").val(newPassword);
    }
    postSend("/updateUserInfo",$("#userInfo").serializeArray(),successUpUserCallback);
}
/*post提交*/
function postSend(url,n,m) {
    $.post(url,n,function (data) {
        m(data);
    });
}
function editPassword() {
    if($("#newPassword").hasClass("hide")){
        $("#password").append("<input type='password' name='password' class='form-control editUserInfo' value=''/>");
        $("#newPassword").removeClass("hide");
    }else{
        $("#newPassword").addClass("hide");
        $("#password").empty();
    }
}