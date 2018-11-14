//重启云台
function restartCloud(){
    var r=confirm("确认重启云台？");
    if (r==true) {
        $.ajax({
            type: "POST",
            url: "restartCloud",
            success: function (data) {
            }
        });
    }
}

//恢复出厂设置
function restoreSetting() {
    var r=confirm("确认恢复出厂设置？");
    if (r==true) {
        $.ajax({
            type: "POST",
            url: "restoreSetting",
            success: function (data) {
            }
        });
    }
}

//保存本地ip
function saveLocalIp() {
    var IpType = $("#localLoginip").val();
    var LIP = $("#LIP").val();
    if(IpType != "" && IpType != null){
        if(isIP(LIP)){
            $.ajax({
                type: "POST",
                data:{
                    IpType:IpType,
                    LIP:LIP
                },
                url: "saveIp",
                success: function (data) {
                }
            });
        }else{
            alert("请确认IP格式是否正确")
        }
    }else{
        alert("请选择指向IP");
    }
}
//保存端口号
function saveLocalPort() {
    var portType = $("#localPort").val();
    var port = $("#LPort").val();
    if(portType != null && IpType != ""){
        if(isPositiveNum(port)){
            $.ajax({
                type: "POST",
                data:{
                    portType:portType,
                    port:port
                },
                url: "savePort",
                success: function (data) {
                }
            });
        }else{
            alert("端口不符合规范");
        }
    }else{
        alert("请选择指向端口");
    }
}



//输入补偿值
function saveBc() {
    var bcValue = $("#bcValue").val();
    var bcId = $("#bcId").val();
    if(bcId != null && bcId != ""){
        if(bcIdLimit(bcValue)){
            $.ajax({
                type: "POST",
                data:{
                    bcValue:bcValue,
                    id:bcId
                },
                url: "saveBcValue",
                success: function (data) {
                }
            });
        }else {
            alert("补偿值格式不正确！")
        }
    }else{
        alert("请选择补偿值类型");
    }
}




//JS判断是否为ip
function isIP(ip) {
    var re =  /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/
    return re.test(ip);
}

function isPositiveNum(val){
    var regPos = /^[1-9]\d*|0$/; // 非负整数
    if(regPos.test(val)){
        return true;
    }
    return false;
}
//补偿值id限制
function  bcIdLimit(val) {
    var regid = /^[0-1]\d$/; // 非负整数
    if(regid.test(val)){
        return true;
    }
    return false;
}

function getX(obj){
    var parObj=obj;
    var left=obj.offsetLeft;
    while(parObj=parObj.offsetParent){
        left+=parObj.offsetLeft;
    }
    return left;
}
function getY(obj){
    var parObj=obj;
    var top=obj.offsetTop;
    while(parObj = parObj.offsetParent){
        top+=parObj.offsetTop;
    }
    return top;
}

function DisplayCoord(event){
    var top,left,oDiv;
    oDiv=document.getElementById("divPlugin");
    top=getY(oDiv);
    left=getX(oDiv);
    document.getElementById("mp_x").innerHTML = (event.clientX-left+document.documentElement.scrollLeft)*1920/1280+"px";
    document.getElementById("mp_y").innerHTML = (event.clientY-top+document.documentElement.scrollTop)*1080/720+"px";
}
