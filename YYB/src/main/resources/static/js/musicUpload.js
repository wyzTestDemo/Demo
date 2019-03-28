var files = new FormData();
var myUFS = $("#myUFS");
var as = {};

function uCreateM(FName) {
    /*var divUp = $("<div class='col-md-2'></div>");
    var rowDiv = $("<div class='row' style='padding:10px 10px;'></div>");
    var panelDiv = $("<div class='panel panel-success'></div>").attr("title", FName);
    var deM = $("<div style='width: 10%;height: 10%;font-size:18px;cursor:pointer;font-weight:bold;position: absolute;right: 2px;top: 0px;'>&times;</div>");
    var panelBodyDiv = $("<div class='panel-body' style='position: relative;'></div>").append($("<img src='/static/img/music/musictest.png'  class='img-responsive' alt='...'/>")).append(deM);
    var panelFooterDiv = $("<div class='panel-footer' style='background-color: transparent;'></div>");
    divUp.append(rowDiv.append(panelDiv));
    panelDiv.append(panelBodyDiv).append(panelFooterDiv);
    var pName = $("<p style='overflow: hidden;text-overflow:ellipsis; color: blue; white-space: nowrap; '></p>").append(FName);
    panelFooterDiv.append(pName);
    myUFS.append(divUp);*/
}

function upFS() {
    var upFiles = $("#FS")[0].files;
    for (var index in upFiles) {
        var FName = upFiles[index].name;
        if (typeof (FName) != "undefined" && FName != "item") {
            if (!as[FName]) {
                as[FName] = true;
                files.append("files", upFiles[index]);
                filesUpload_infor();
                /*uCreateM(FName);*/
            }
        }
    }
}

function filesUpload_infor() {
   $.ajax({
        type: "post",
        async: true,  //这里要设置异步上传，才能成功调用myXhr.upload.addEventListener('progress',function(e){}),progress的回掉函数
        Accept: 'text/html;charset=UTF-8',
        data: files,
        contentType: undefined,
        url: '/upload',
        processData: false, // 告诉jQuery不要去处理发送的数据
        contentType: false, // 告诉jQuery不要去设置Content-Type请求头
        xhr: function () {
            myXhr = $.ajaxSettings.xhr();
            if (myXhr.upload) { // check if upload property exists
                myXhr.upload.addEventListener('progress', function (e) {
                    var loaded = e.loaded;                  //已经上传大小情况
                    var total = e.total;                      //附件总大小
                    var percent = Math.floor(100 * loaded / total) + "%";     //已经上传的百分比
                    console.log("已经上传了：" + percent);
                    $("#myProgress").removeClass("hide");
                    var myProgressBar = $("#myProgress .progress .progress-bar");
                    myProgressBar.css("width", percent+"%");
                    if(percent>=100){
                       alert("上传成功");
                    }
                }, false); // for handling the progress of the upload
            }
            return myXhr;
        },
        success: function (data) {
            console.log("上传成功!!!!");
        },
        error: function () {
            alert("上传失败！");
        }
    });
}