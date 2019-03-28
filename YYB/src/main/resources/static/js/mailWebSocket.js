var websocket = null;
//判断当前浏览器是否支持WebSocket
/*游客判断*/
if (user != null) {
    var userId = user.id;
    if ('WebSocket' in window) {
        websocket = new WebSocket("ws://localhost:8600/websocket/" + userId);
    }
    else {
        alert('当前浏览器 不支持WebSocket')
    }

//连接发生错误的回调方法
websocket.onerror = function () {
    setMessageInnerHTML("连接发生错误");
};

//连接成功建立的回调方法
websocket.onopen = function () {
    /*setMessageInnerHTML("连接成功");*/
    console.log("连接成功")
}

//接收到消息的回调方法，此处添加处理接收消息方法，当前是将接收到的信息显示在网页上
websocket.onmessage = function (event) {
    setMessageInnerHTML(event.data);
}

//连接关闭的回调方法
websocket.onclose = function () {
    /*setMessageInnerHTML("连接关闭,如需登录请刷新页面。");*/
    console.log("连接关闭,如需登录请刷新页面。")
}

//监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
window.onbeforeunload = function () {
    closeWebSocket();
}
}
//将消息显示在网页上，如果不需要显示在网页上，则不调用该方法
function setMessageInnerHTML(innerHTML) {
    $(".mailNull")[0].src="/static/img/user/mail_2.png";
    $(".mailNull").addClass("mailRemind");
    var musicId = innerHTML.split("|")[1];
    updateMusicFileStatus(musicId);


    console.info("收到的消息有：" + innerHTML);
}
/*在我的上传页面时更改审核状态的一些改变*/
function updateMusicFileStatus(id) {
    if($("title").html()=='MyUpload'){
        var music_UpFS = $(".through"+id);
        music_UpFS.removeClass("notThroughColor");
        music_UpFS.addClass("throughColor");
    }
}