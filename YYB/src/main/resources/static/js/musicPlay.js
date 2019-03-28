var proBar;
var proDot;
var curTime;
var myBtnPlay;
var auTotal;
var muDH;
var statusId;
/*02/18*/
var newDH = $("#musicDH");
var newMSNAME = $(".musicName span");
var myAudio;
function initAudioBtn(id) {
    proBar = $("#progressBar" + id);
    proDot = $("#progressDot" + id);
    curTime = $("#audioCurTime" + id);
    myBtnPlay = $("#pl" + id);
    auTotal = $("#audioTotal" + id);
    muDH = $("#musicDH" + id);
}

function changeMusicStatus() {
    if (myAudio.paused) {
        myAudio.play();
    } else {
        myAudio.pause();
    }
}

function myPlay(id ,musicName,musicType) {

    /*原来
    var currentFile = '/player/' +id;*/
    var currentFile = 'http://192.168.1.101:8650/myResources/'+musicName+'.'+musicType;
    if (id != statusId) {
        if (myAudio.src.length > 0) {
            myAudio.pause();
            muDH.removeClass("dh-running");
            muDH.addClass("dh-paused");
            myBtnPlay.addClass("fa-play-circle");
            myBtnPlay.removeClass("fa-pause-circle");
            newDH.removeClass("dh-running");
            newDH.addClass("dh-paused");
            newMSNAME.html(musicName);
        }
        newDH.removeClass("dh-paused");
        newDH.addClass("dh-running");
        newMSNAME.html(musicName);
        initAudioBtn(id);
        myAudio.src = currentFile;
        myAudio.load();
        statusId = id;
    }
    changeMusicStatus();
}

/*------------------------------------------------------------*/
//判断浏览器是否支持audio
if (!window.HTMLAudioElement) {
    alert('您的浏览器不支持audio标签');
} else {
    myAudio= $('#myAudio')[0];
    myAudio.volume = 0.6;
    //监听事件
    myAudio.oncanplay = function () {
        auTotal.html(transTime(myAudio.duration));
        console.info('进入可播放状态,音频总长度:' + myAudio.duration);
    }
    myAudio.onplay = function () {

        muDH.addClass("dh");
        muDH.removeClass("dh-paused");
        muDH.addClass("dh-running");
        myBtnPlay.removeClass("fa-play-circle");
        myBtnPlay.addClass("fa-pause-circle");

        //0219
        newDH.removeClass("dh-paused");
        newDH.addClass("dh-running");
        console.info('开始播放：' + myAudio.currentTime);
    }
    myAudio.onpause = function () {
        muDH.removeClass("dh-running");
        muDH.addClass("dh-paused");
        myBtnPlay.addClass("fa-play-circle");
        myBtnPlay.removeClass("fa-pause-circle");
        console.info(statusId + '暂停播放：' + myAudio.currentTime);

        //0219
        newDH.removeClass("dh-running");
        newDH.addClass("dh-paused");
    }
    //结束事件
    myAudio.onended = function () {
        muDH.removeClass("dh dh-running dh-paused");
        myBtnPlay.addClass("fa-play-circle");
        myBtnPlay.removeClass("fa-pause-circle");

        //0219
        muDH.removeClass("dh dh-running dh-paused");
    }
    myAudio.onprogress = function () {
        //console.info(myAudio.buffered);
        //console.info('正在播放：' + myAudio.currentTime);
    }
    myAudio.ontimeupdate = function (e) {
        updateProgress(myAudio);
        console.info('播放时间发生改变：' + myAudio.currentTime);
        console.info("当前缓存有---------------------------》" + myAudio.duration);
    }
}

function updateProgress(audio) {
    console.info("-----------------------------------++++++++++++++++++++");
    var value = audio.currentTime / audio.duration;
    proBar.css('width', value * 100 + '%');
    proDot.css('left', value * 100 + '%');
    curTime.html(transTime(audio.currentTime));
}

function transTime(value) {
    var time = "";
    var h = parseInt(value / 3600);
    value %= 3600;
    var m = parseInt(value / 60);
    var s = parseInt(value % 60);
    if (h > 0) {
        time = formatTime(h + ":" + m + ":" + s);
    } else {
        time = formatTime(m + ":" + s);
    }

    return time;
}

function formatTime(value) {
    var time = "";
    var s = value.split(':');
    var i = 0;
    for (; i < s.length - 1; i++) {
        time += s[i].length == 1 ? ("0" + s[i]) : s[i];
        time += ":";
    }
    time += s[i].length == 1 ? ("0" + s[i]) : s[i];

    return time;
}