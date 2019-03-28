/*上传文件的状态*/
var publishedStatus = false;

window.onbeforeunload = function(event) {
    if(publishedStatus) {
        event.returnValue = "我在这写点东西...";
    }
};
