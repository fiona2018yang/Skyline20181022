<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html >
<HEAD>
    <TITLE> JS获取DIV相对坐标</TITLE>
    <script type="text/javascript">
  /*  function getX(obj){
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
        oDiv=document.getElementById("demo");
        top=getY(oDiv);
        left=getX(oDiv);
        document.getElementById("mp_x").innerHTML = (event.clientX-left+document.documentElement.scrollLeft) -2+"px";
        document.getElementById("mp_y").innerHTML = (event.clientY-top+document.documentElement.scrollTop) -2+"px";
    }*/
   </script>
</HEAD>

<BODY>
<div style="background-color:#000000;color:#0011FF;width:1488px;height:837px;position:absolute;top:80px;left:90px;margin:0px; border:0px;" id="demo" onmousemove="DisplayCoord(event)">
</div>
当前鼠标坐标为：
X：<span id="mp_x"></span>
Y：<span id="mp_y"></span>
</BODY>
</HTML>