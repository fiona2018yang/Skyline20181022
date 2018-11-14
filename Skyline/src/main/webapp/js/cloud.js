var ytClientData;

//角度查询
function GetAngleXXXX() {
    window.getAngleTimer = setInterval(function refreshfuyangjiao() {
        $.ajax({
            type: "post",
            url: "monitordata/GetAngleDouble?t=" + new Date().getTime(),
            dataType: "json",
            data: {},
            success: function (data) {
                if (data) {
                    var fuyangjiao = data.fuyangjiao;
                    var hangxiangjiao = data.hangxiangjiao;
                    $("#hangxiangjiao").html(hangxiangjiao);
                    $("#fuyangjiao").html(fuyangjiao);
                }
            }
        });
    }, 1000)
}

//获取焦距
function goGetVol() {
    $.ajax({
        type: "post",
        url: "monitordata/getVol",
        async: false,
        data: {},
        success: function (data) {
        }
    });
    $("#volQuery").attr("disabled", "disabled");
    $("#volQueryCloseBu").removeAttr("disabled");
    GetCurrentVol();
}

function closeVol() {
    $("#volQueryCloseBu").attr("disabled", "disabled");
    $("#volQuery").removeAttr("disabled");
    clearInterval(window.getCurrentVol);
    $.ajax({
        type: "post",
        url: "monitordata/closeVol"
    });
}

function GetCurrentVol() {
    clearInterval(window.getCurrentVol);
    window.getCurrentVol = setInterval(function refreshVol() {
        $.ajax({
            type: "post",
            url: "monitordata/getCurrentVod?t=" + new Date().getTime(),
            dataType: "json",
            data: {},
            success: function (data) {
                if (data) {

                    var bianjiao = data.bianjiao;
                    $("#volVar1").html(bianjiao);
                }
            }
        });
    }, 500)
}

//上
function GoUp() {
    $.ajax({
        type: "post",
        url: "monitordata/GoUp",
        async: false,
        data: {},
        success: function (data) {
            //       alert("success: "+ data);
        }
    });
}

// 停
function GoStop() {
    $.ajax({
        type: "POST",
        url: "monitordata/GoStop",
        data: {},
        success: function (data) {
        }
    });
}

//下
function GoDown() {
    $.ajax({
        type: "post",
        url: "monitordata/GoDown",
        async: false,
        data: {},
        success: function (data) {
        }
    });
}

//左
function GoLeft() {
    $.ajax({
        type: "post",
        url: "monitordata/GoLeft",
        dataType: "json",
        async: false,
        data: {},
        success: function (data) {
        }
    });
}

//右
function GoRight() {
    $.ajax({
        type: "post",
        url: "monitordata/GoRight",
        async: false,
        data: {},
        success: function (data) {
        }
    });
}

//左上
function GoLeftUp() {
    $.ajax({
        type: "post",
        url: "monitordata/GoLeftUp",
        async: false,
        data: {},
        success: function (data) {
        }
    });
}

//右上
function GoRightUp() {
    $.ajax({
        type: "post",
        url: "monitordata/GoRightUp",
        async: false,
        data: {},
        success: function (data) {
        }
    });
}

//左下
function GoLeftDown() {
    $.ajax({
        type: "post",
        url: "monitordata/GoLeftDown",
        async: false,
        data: {},
        success: function (data) {
        }
    });
}

//右下
function GoRightDown() {
    $.ajax({
        type: "post",
        url: "monitordata/GoRightDown",
        async: false,
        data: {},
        success: function (data) {
        }
    });
}

//焦距远
function GoZoomFar() {
    $.ajax({
        type: "post",
        url: "monitordata/GoZoomFar",
        async: false,
        data: {},
        success: function (data) {
            //     alert("success: "+ data);
        }
    });
}

//焦距近
function GoZoomNear() {
    $.ajax({
        type: "post",
        url: "monitordata/GoZoomNear",
        async: false,
        data: {},
        success: function (data) {
        }
    });
}

//变倍+
function GoFocusFar() {
    $.ajax({
        type: "post",
        url: "monitordata/GoFocusFar",
        async: false,
        data: {},
        success: function (data) {
        }
    });
}

//变倍-
function GoFocusNear() {
    $.ajax({
        type: "post",
        url: "monitordata/GoFocusNear",
        dataType: "json",
        async: false,
        data: {},
        success: function (data) {
        }
    });
}

//光圈大
function GoApertureBig() {
    $.ajax({
        type: "post",
        url: "monitordata/GoApertureBig",
        async: false,
        data: {},
        success: function (data) {
        }
    });
}

//光圈小
function GoApertureSmall() {
    $.ajax({
        type: "post",
        url: "monitordata/GoApertureSmall",
        async: false,
        data: {},
        success: function (data) {
        }
    });
}

//关闭俯仰查询
function GoGetAngleDoubleOFF() {
    $("#angleQueryCloseBu").attr("disabled", "disabled");
    $("#angleQueryBu").removeAttr("disabled");
    clearInterval(window.getAngleTimer);
    $.ajax({
        type: "post",
        url: "monitordata/GoGetAngleDoubleOFF",
        async: false,
        data: {},
        success: function (data) {
        }
    });
}

//俯仰查询
function GoGetAngleDouble() {
    $("#angleQueryBu").attr("disabled", "disabled");
    $("#angleQueryCloseBu").removeAttr("disabled");
    GetAngleXXXX();
    $.ajax({
        type: "post",
        url: "monitordata/GoGetAngleDouble",
        async: false,
        data: {},
        success: function (data) {
        }
    });
}

//雨刷开
function GoFzOpen() {
    $.ajax({
        type: "post",
        url: "monitordata/GoFzOpen",
        async: false,
        data: {},
        success: function (data) {
        }
    });
}

//雨刷关
function GoFzClose() {
    $.ajax({
        type: "post",
        url: "monitordata/GoFzClose",
        async: false,
        data: {},
        success: function (data) {
        }
    });
}

//设置预置位
function GoSetPreset() {
    //获取预置位文本框的值
    var param = $('#txtposition').val();
    $.ajax({
        url: 'monitordata/GoSetPreset',
        type: "POST",
        data: {
            "param": param
        },
        async: false,
        success: function () {
            //请求成功返回后执行的动作
            // alert("success");
        },
        error: function () {
            //请求失败后执行的动作
            alert("error");
        }
    });
}

function GoDelPreset() {
    var delPreset = $("#delPreset").val();
    if (delPreset != null && delPreset != "") {
        $.ajax({
            url: 'monitordata/GoDelPreset',
            type: "POST",
            data: {
                "delPreset": delPreset
            },
            success: function () {
            },
            error: function () {
                //请求失败后执行的动作
                alert("error");
            }
        });
    } else {
        alert("预置位不可为空");
    }
}

//调用预置位
function GoUsePreset() {
    //获取预置位文本框的值
    var preset = $('#info').val();
    $.ajax({
        url: 'monitordata/GoUsePreset?pam=' + Math.random(),
        type: "POST",
        data: {
            "preset": preset
        },
        async: false,
        success: function () {
            //请求成功返回后执行的动作
            //    alert("success");
        },
        error: function () {
            //请求失败后执行的动作
            alert("error");
        }
    });
}

//自动巡河
function GoUsePresetT() {
    var xunheTime = $("#xunheTime").val();
    var time = $("#time").val();
    var startPreset = $("#startPreset").val();
    var endPreset = $("#endPreset").val();
    console.log(startPreset);
    console.log(endPreset);
    if(startPreset == "" || endPreset == ""){
        alert("请输入首尾预置位");
        return;
    }
    $.ajax({
        type: "POST",
        data: {
            "number": xunheTime,
            "time": time,
            "startPreset":startPreset,
            "endPreset":endPreset
        },
        url: "monitordata/getToXunhe",
        async: false,
        success: function (data) {
        }
    });
}

function stopUsePresetT() {
    $.ajax({
        type: "POST",
        url: "monitordata/stopXunhe",
        async: false,
        success: function (data) {
        }
    });
}

//速度调节
function GoSetSpeed(obj) {
    var speedcontrol = $(obj).children('option:selected').val()
    if (speedcontrol != '') {
        var requestBody = "{\"speed\":\"" + speedcontrol + "\"}";
        $.ajax({
            url: 'monitordata/GoSetSpeed',
            type: "post",
            dataType: "json",
            contentType: 'application/json',
            data: requestBody,
            async: false,
            success: function () {
                //请求成功返回后执行的动作
            },
            error: function () {
                //请求失败后执行的动作
                //  alert("error");
            }
        });
    }
}

//航向定位
function GoSetAngleHXJ() {
    var dwhxj = $("#dwHXJ").val();
    if (isNumber(dwhxj)) {
        $.ajax({
            type: "POST",
            data: {
                "hxj": dwhxj
            },
            url: "monitordata/goSetAngleHXJ",
            async: false,
            success: function (data) {
            }
        });
    } else {
        alert("角度格式不正确");
    }
}
//俯仰定位
function GoSetAngleFYJ() {
    var dwfyj = $("#dwFYJ").val();

    if (isNumber(dwfyj)) {
        if (-41 < dwfyj && 41 > dwfyj) {
            $.ajax({
                type: "POST",
                data: {
                    "fyj": dwfyj
                },
                url: "monitordata/goSetAngleFYJ",
                async: false,
                success: function (data) {
                }
            });
        } else {
            alert("请输入-40到40的数字");
        }
    } else {
        alert("请输入-40到40的数字");
    }
}
//航向俯仰共同定位
function GoSetAngleHXFY() {
    var dwhxj = $("#dwHXJ").val();
    var dwfyj = $("#dwFYJ").val();
    if (isNumber(dwhxj) && isNumber(dwfyj)) {
        $.ajax({
            type: "POST",
            data: {
                "hxj": dwhxj,
                "fyj": dwfyj
            },
            url: "monitordata/goSetAngleHXFY",
            async: false,
            success: function (data) {
            }
        });
    } else {
        alert("角度格式不正确");
    }
}
// 跟踪人
function People() {
    var peopleBuValue = $("#peopleBuId").val();
    var nextPeopleBuValue = "";         //点击之后的文字显示
    if (peopleBuValue == "跟踪人") {
        nextPeopleBuValue = "停止跟踪";
        $(".genzong:not(#peopleBuId)").attr("disabled", "disabled");
    }
    $("#peopleBuId").val(nextPeopleBuValue);
    $("#peopleBuId").attr("onclick","stopGenzong(0)");
    $.ajax({
        type: "get",
        url: "Cloud/getPeople",
        async: false,
        data: {},
        success: function (data) {
        }
    });
}
//调用发送识别车的指令
function Car() {
    var carBuValue = $("#carBuId").val();
    var nextCarBuValue = "";         //点击之后的文字显示
    if (carBuValue == "跟踪车") {
        nextCarBuValue = "停止跟踪";
        $(".genzong:not(#carBuId)").attr("disabled", "disabled");
    }
    console.log("fsdfsfsdf");
    $("#carBuId").val(nextCarBuValue);
    $("#carBuId").attr("onclick","stopGenzong(1)");
    $.ajax({
        type: "get",
        url: "Cloud/getCar",
        success: function (data) {
        }
    });
}
//调用发送识别船的指令
function Ship() {
    var nextShipBuValue = "停止跟踪";         //点击之后的文字显示
    $(".genzong:not(#shipBuId)").attr("disabled", "disabled");
    $("#shipBuId").val(nextShipBuValue);
    $("#shipBuId").attr("onclick","stopGenzong(2)");
    $.ajax({
        type: "get",
        url: "Cloud/getShip",
        async: false,
        data: {},
        success: function (data) {
        }
    });
}


//调用发送漂浮物的指令
function FloatingMatter() {
    // var floatBuValue = $("#FloatMBuId").val();
    var nextFloatBuValue = "停止跟踪";         //点击之后的文字显示
    $(".genzong:not(#FloatMBuId)").attr("disabled", "disabled");
    $("#FloatMBuId").val(nextFloatBuValue);
    $("#FloatMBuId").attr("onclick","stopGenzong(3)");
    $.ajax({
        type: "post",
        url: "Cloud/FloatingMatter",
        async: false,
        data: {},
        success: function (data) {
        }
    });
}
//识别
function FisP() {
    var nextFloatBuValue = "停止识别";         //点击之后的文字显示
    $(".genzong:not(#FisBuId)").attr("disabled", "disabled");
    $("#FisBuId").val(nextFloatBuValue);
    $("#FisBuId").attr("onclick","stopGenzong(4)");
    $.ajax({
        type: "get",
        url: "Cloud/FisP",
        async: false,
        data: {},
        success: function (data) {
        }
    });
}

function stopGenzong(type) {
        var nextValue = "";         //点击之后的文字显示
        $(".genzong").removeAttr("disabled");
        if(type == 0){
            nextValue = "跟踪人"
            $("#peopleBuId").val(nextValue);
            $("#peopleBuId").attr("onclick","People()");
        } else if(type == 1){
            nextValue = "跟踪车"
            $("#carBuId").val(nextValue);
            $("#carBuId").attr("onclick","Car()");
        } else if(type == 2){
            nextValue = "跟踪船"
            $("#shipBuId").val(nextValue);
            $("#shipBuId").attr("onclick","Ship()");
        }else if (type==3){
            nextValue = "漂浮物"
            $("#FloatMBuId").val(nextValue);
            $("#FloatMBuId").attr("onclick","FloatingMatter()");
        }else if(type==4){
            nextValue = "识别"
            $("#FisBuId").val(nextValue);
            $("#FisBuId").attr("onclick","FisP()");
        }
        $.ajax({
            type: "get",
            url: "Cloud/stopGenzong",
            async: false,
            data: {
                type: type
            },
            success: function (data) {
            }
        });
}


function bianjiaoFix() {
    var bianjiaoInput = $("#bianjiaoInput").val();
    if (bianjiaoInput != "" && bianjiaoInput != null) {
        $.ajax({
            type: "post",
            url: "monitordata/goBianjiaoFix",
            async: false,
            data: {
                bianjiao: bianjiaoInput
            },
            success: function (data) {
            }
        });
    }
}
function getClientTree() {
    $.ajax({
        type: "post",
        url: "monitordata/getClientList",
        async: false,
        dataType:'json',
        success: function (data) {
            console.log(data);
            ytClientData = data;
            //通过tags判断层级
            var treeData = [];
            data.map(function (item) {
                var flag = false;
                treeData.map(function (treeItem) {
                    if(item.ytIp == treeItem.text){
                        // treeItem.nodes.push({text:item.port,tags:[2]})
                        flag = true;
                    }
                })
                if(!flag){
                    //为该节点存放数据
                    treeData.push({text:item.relationName,tags:[item.sxtIp,item.sxtPort,item.ytIp,item.ytPort]});
                }
            });
            $('#tree').treeview({
                data:treeData,
                highlightSelected: true,
                //是否高亮选中
                nodeIcon: 'glyphicon glyphicon-user',    //节点上的图标
                emptyIcon: '',//没有子节点的节点图标
                multiSelect: false,//多选
                onNodeSelected:function (event,node) {
                    console.log(node);
                    //将该节点的值放到输入用户名/密码的地方
                    $("#loginip").val(node.tags[0]);//摄像头IP
                    $("#port").val(node.tags[1]);
                    console.log(isExistOption("ip",node.tags[0]));
                    if(isExistOption("ip",node.tags[0])){
                        $("#ip").val(node.tags[0]);
                        getChannelInfo();
                    }
                }
            }).treeview('collapseAll', { silent:true });
        }
    });
}
//及时获取消息
function getMessage() {
    setInterval(function refreshMessage() {
        $.ajax({
            type: "post",
            url: "monitordata/getMessage",
            dataType: "json",
            success: function (data) {
                if (data) {
                    $("#messageDiv").prepend(data + '</br>');
                }
            }
        });
    }, 1000)
}








//传递控件像素点
function PxPulg() {
    var x = $("#mp_x").html();
    var y = $("#mp_y").html();

    $.ajax({
        type: "GET",
        data: {
            "x": x,
            "y": y
        },
        url: "Cloud/ControlPx",
        async: false,
        success: function (data) {

        }
    });
}


// 调用底层巡河




//数字判断
function isNumber(val) {
    var regPos = /^\d+(\.\d+)?$/; //非负浮点数
    var regNeg = /^(-(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*)))$/; //负浮点数
    if (regPos.test(val) || regNeg.test(val)) {
        return true;
    } else {
        return false;
    }
}

//判断下拉框是否存在某值
function isExistOption(id,value) {
    var isExist = false;
    var count = $('#'+id).find('option').length;

    for(var i=0;i<count;i++)
    {
        if($('#'+id).get(0).options[i].value == value)
        {
            isExist = true;
            break;
        }
    }
    return isExist;
}



