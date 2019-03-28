angular.module('app',[]).controller('myCtr',function ($scope,$http) {
    $scope.myPlay = myPlay;
    var selStatus = {};
    $scope.uFileC = function uFileC() {
        $http({
            method: 'POST',
            url: '/upload',
            eventHandlers: {
                progress: function (c) {
                    console.log('Progress -> ' + c);
                    console.log(c);
                }
            },
            uploadEventHandlers: {
                progress: function (e) {
                    console.log('UploadProgress -> ' + e);
                    var upPro = e.loaded / e.total * 100;
                    console.log("进度：" + upPro);
                    if (upPro >= 100) {
                        alert("上传成功");
                    }
                }
            },
            data: files,
            headers: {'Content-Type': undefined},
            transformRequest: angular.identity
        }).then(function (response) {
            console.log('operation success');
            /*直接重新加载*/
            files = new FormData();
            myUFS.empty();
        }, function (response) {
            console.log('operation fail');
        });

    }
    $http({
        url: '/static/conf/myUploadMusic.json',
        method: 'GET'

    }).then(function (response) {
        $scope.myUploadMusic = response.data.myUploadMusic;

        $.each($scope.myUploadMusic, function (index, value) {
            selStatus[value.id] = false;
        });
    }, function (error) {
        console.log(error)
    });
    $scope.musicSelStatus = function (id) {
        return selStatus[id];
    }
    $scope.clickSel = function (id) {
        selStatus[id] = !selStatus[id];
        console.info(selStatus[id]);
    }
    $scope.allSel = function () {

        $.each(selStatus, function (index, value) {
            if ($scope.allS == true) {
                selStatus[index] = true;
            } else {
                selStatus[index] = false;
            }
        });


    }
});
