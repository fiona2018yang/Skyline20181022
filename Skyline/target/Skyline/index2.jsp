<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    /*  String basePath =request.getServerName()+":"+request.getServerPort()+path+"/";*/
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="renderer" content="webkit" />
    <script src="cn/jquery-1.7.1.min.js"></script>
    <script src="js/jquery-1.10.2.js"></script>
    <script type="text/javascript" src="js/cloud.js"></script>
    <link type="text/css" href="hik/ui.css" rel="stylesheet">
    <style>
        .class1{width:150px;height:15px;line-height:15px; padding:5px;border:1px solid black;}
        .class2{display:inline-block;}
        .genzong{}
        .btn{
            width:45px;
            height:22px;
            line-height:18px;
        }
    </style>
</head>
<body>
<div id="leftDiv"  style="float:left; display:inline-block;width:100%;" >
    <!--引入视频控件-->
    <div id="leftDivPlugin" style="float:left;height: 95%;width: 70%;">
        <div id="divPlugin" class="plugin"></div>
        <div id="button_buttom" style="padding: 20px 25px;">
            <%--下方按钮--%>
            <%--<input  type="button"  value="角度查询" onclick="GoGetAngleDouble();" />--%>
            <%--<input  type="button"  value="查询关闭" onclick="GoGetAngleDoubleOFF();" />--%>
            <!---登陆-->
                IP地址:<input id="loginip" type="text" class="txt" value="192.168.1.211" />
                端  口:<input id="port" type="text" class="txt" value="80" />
                用户名:<input id="username" type="text" class="txt" value="admin" />
                密 码:<input id="password" type="password" class="txt" value="admin12345" />
            <input style="margin-left: 30px;" type="button"  value="自动巡河" onclick="GoUsePresetT();"/>
            <select id="xunheTime" class="love"  style="width: 110px; height: 25px;text-align:center;text-align-last:center;">
                <option value="1" selected="selected">选择巡河次数</option>
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
                <option value="5">5</option>
                <option value="6">6</option>
                <option value="7">7</option>
            </select>

            <input type="button"  class="love genzong" style="margin-left: 30px" id="carBuId"  value="跟踪车"  onclick="Car();"/>
            <input type="button"  class="love genzong" id="shipBuId"  value="跟踪船" onclick="Ship()"/>
            <input type="button"  class="love genzong" id="peopleBuId" value="跟踪人"  onclick = "People();"/>

        </div>
    </div>
    <table id="rightTable" style=" float: right; margin-left: 10px;width:20%;">
        <tr>
            <td style="padding-left: 30px">
                <div class="ptz-name"><h3>云台</h3></div>
                <div class="ptz-ctrl" >
                    <div class="ptz-ctrl-l" >
                        <span class="direction" onmousedown="GoLeftUp()" onmouseup="GoStop();"><i title="左上" class="icon-ptz-left-up"   style="background-position: 0 0;" ></i></span>
                        <span class="direction" onmousedown="GoUp()" onmouseup="GoStop();"><i  title="上" class="icon-ptz-up" style="background-position: -30px 0px;"></i></span>
                        <span class="direction" onmousedown="GoRightUp()" onmouseup="GoStop();"><i title="右上" class="icon-ptz-right-up" style="background-position: -60px 0px;"></i></span>
                        </br>
                        </br>
                        <span class="direction" onmousedown="GoLeft();" onmouseup="GoStop();"><i title="左" class="icon-ptz-left" style="background-position: 0 -30px;"></i></span>
                        <span class="direction" onclick="GoStop();"><i title="停止" ng-class="{true:'icon-ptz-auto-sel', false:'icon-ptz-auto'}[oParams.bAuto]" class="icon-ptz-auto" style="background-position:-30px -30px;"></i></span>
                        <span class="direction" onmousedown="GoRight()" onmouseup="GoStop();"><i title="右" class="icon-ptz-right" style="background-position: -60px -30px;"></i></span>
                        </br>
                        </br>
                        <span class="direction"  onmousedown="GoLeftDown()" onmouseup="GoStop()"><i title="左下" class="icon-ptz-left-down" style="background-position: 0px -60px;" ></i></span>
                        <span class="direction" onmousedown="GoDown()" onmouseup="GoStop()"><i title="下" class="icon-ptz-down" style="background-position: -30px -60px;"></i></span>
                        <span class="direction" onmousedown="GoRightDown()" onmouseup="GoStop()"><i title="右下" class="icon-ptz-right-down" style="background-position:-60px -60px;"></i></span>
                        <select id="speed" style="width: 100px; height: 30px;text-align:center;text-align-last:center;"onchange="GoSetSpeed(this)">
                            <option value="" selected="selected" >请设置速度</option>
                            <option value="1">1</option>
                            <option value="16">16</option>
                            <option value="48">48</option>
                            <option value="64">64</option>
                            <%--<option value="16">16</option>--%>
                            <%--<option value="32">32</option>--%>
                            <%--<option value="64">64</option>--%>
                        </select>
                        </br>
                        </br>
                    </div>
                    <div class="ptz-ctrl-r">
                    <span class="operation">
                        <i class="icon-ptz-zoomout" title="焦距远"  onmousedown="GoZoomFar()" onmouseup="GoStop()" style="background-position:-90px -30px;"></i>
                        <i class="icon-ptz-zoomin" title="焦距近" onmousedown="GoZoomNear()" onmouseup="GoStop()" style="background-position:-126px -30px;" ></i>
                    </span>
                        <span class="operation">
                        <i class="icon-ptz-focusout" title="变倍-" onmousedown="GoFocusNear()" onmouseup="GoStop()" style="background-position:-90px 0;" ></i>
                        <i class="icon-ptz-focusin" title="变倍+" onmousedown="GoFocusFar()" onmouseup="GoStop()" style="background-position:-126px 0;" ></i>
                    </span>
                        <span class="operation">
                        <i class="icon-ptz-irisout" title="光圈大"  onmousedown="GoApertureBig()" onmouseup="GoStop()" style="background-position:-126px -60px; "></i>
                        <i class="icon-ptz-irisin" title="光圈小" onmousedown="GoApertureSmall()" onmouseup="GoStop()" style="background-position:-90px -60px;" ></i>
                    </span>
                        <span class="operation">
                        <i class="icon-ptz-irisout" title="雨刷开" onclick="GoFzOpen();"  style="background-position:-199px -120px;" ></i>
                        <i class="icon-ptz-irisin" title="雨刷关"  onclick="GoFzClose();"  style="background-position:-199px -120px;"  ></i>
                    </span>
                    </div>
                </div>
                <div style="clear: both;"></div>
                <div>

        <input class="love" type="button" value="设置路径" onclick="clickOpenFileDlg('recordPath', 0);" />
                    <input class="love" id="recordPath" type="text" style="width: 110px;" />
                    <input class="love" id="startRecord" type="button"  value="录像" onclick="clickStartRecord();" />
                    <input class="love" id="stopRecord" type="button" disabled="disabled"  value="停止录像" onclick="clickStopRecord();" />
                    <input class="love" type="button"  value="抓图" onclick="clickCapturePic();" />
                    <div class="ptz-name"></div>
                    <input class="love" style="margin-top: 3px" type="button" value="航向定位" onclick="GoSetAngleHXJ();" />
                    <input class="love" type="text" id ="dwHXJ" style="width: 110px;margin-top: 3px" placeholder="航向角" ></br>
                    <input class="love" type="button" value="俯仰定位" onclick="GoSetAngleFYJ();" />
                    <input class="love" type="text" id ="dwFYJ" style="width: 110px" placeholder="俯仰角"></br>
                    <input class="love" type="button" value="俯仰航向定位" onclick="GoSetAngleHXFY();" /></br>

                    <input class="love" id="bianjiaoBu" type="button"  value="变焦定位" onclick="bianjiaoFix();" />
                    <input class="love" id="bianjiaoInput" type="text" style="width: 110px;" />
                    <input class="love"  type="button" value="设置预置位" onclick="GoSetPreset();" />
                    <input class="love" type="text" id ="txtposition" style="width: 110px;" name="param" onkeyup="value=value.replace(/[^\d]/g,'')" ></br>
                    <input class="love" type="button" value="调用预置位"  id="preset"   onclick="GoUsePreset();"/>
                    <input class="love" type="text" value="" style="width: 110px" id="info" name="preset" >
                </div>
                <div class="ptz-name ng-scope ng-binding" ng-bind="oLan.ptz" ng-if="szType != 'ptzLock'"><h3>信息</h3></div>
                <div>
                    <input class="love" id="angleQueryBu" type="button"  value="角度查询" onclick="GoGetAngleDouble();" />
                    <input class="love" id="angleQueryCloseBu" type="button"  value="查询关闭" disabled="disabled" onclick="GoGetAngleDoubleOFF();" /></br>
                    <div style="margin-top: 6px;">航向:<span class="class1 class2" id="hangxiangjiao">&nbsp;</span></div>
                    <div style="margin-top:6px">俯仰:<span class="class1 class2"  id="fuyangjiao">&nbsp;</span></div>
                    <input class="love" id="volQuery" type="button"  value="焦距值查询" onclick="goGetVol();" />
                    <div style="margin-top: 6px;">变焦:<span class="class1 class2" id="volVar1">&nbsp;</span></div>
                    <div style="margin-top:6px">聚焦:<span class="class1 class2"  id="volVar2">&nbsp;</span></div>
                </div>
            </td>
        </tr>
    </table>
</div>

</body>
<script src="codebase/webVideoCtrl.js"></script>
<script src="cn/test.js"></script>
</html>