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
    <script src="../cn/jquery-1.7.1.min.js"></script>
    <script src="../js/jquery-1.10.2.js"></script>
    <script type="text/javascript" src="../js/settings.js"></script>
    <script src="../js/bootstrap/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="../js/bootstrap/bootstrap-3.3.7-dist/css/bootstrap.min.css">
    <script type="text/javascript" src="../js/bootstrapTable/bootstrap-table.min.js"></script>
    <link type="text/css" href="../js/bootstrapTable/bootstrap-table.min.css" rel="stylesheet">
    <link type="text/css" href="../hik/ui.css" rel="stylesheet">
    <style>
    </style>
</head>
<body>
    <div style="width: 100%;height: 100%;text-align: center">
        <table  style="width: 80%;margin-top: 10px;margin-left: auto;margin-right: auto;border-collapse:separate; border-spacing:0px 30px;">
            <tr>
                <td colspan="7">
                    <input style="margin-left: 30px;" type="button"  value="重启云台" onclick="restartCloud();"/>
                </td>
                <td>
                    <input style="margin-left: 30px;" type="button"  value="恢复出厂设置" onclick="restoreSetting();"/>
                </td>
            </tr>
            <tr>
                <td colspan="7">
                    修改IP地址:
                    <select id="localLoginip" style="width: 100px; height: 30px;text-align:center;text-align-last:center;">
                        <option value="" selected="selected" >请选择指向IP</option>
                        <option value="00">用户本地IP</option>
                        <option value="01">用户目标IP</option>
                    </select>
                </td>
                <td>
                    <input style="margin-right: 105px" id="LIP" type="text" />
                </td>
                <td>
                    <input style="margin-left: 30px;" type="button"  value="保存" onclick="saveLocalIp();"/>
                </td>
            </tr>
            <tr>
                <td  colspan="7">
                    修改指向端口:
                    <select id="localPort" style="width: 100px; height: 30px;text-align:center;text-align-last:center;">
                        <option value="" selected="selected" >请选择指向端口</option>
                        <option value="00">用户本地端口</option>
                        <option value="01">用户目标端口</option>
                    </select>
                </td>
                <td>
                    <input style="margin-right: 105px" id="LPort" type="text" />
                </td>
                <td>
                    <input style="margin-left: 30px;" type="button"  value="保存" onclick="saveLocalPort();"/>
                </td>
            </tr>
            <tr>
                <td  colspan="7">
                    修改指向补偿:
                    <select id="bcId" style="width: 100px; height: 30px;text-align:center;text-align-last:center;">
                        <option value="" selected="selected" >请选择指向补偿值</option>
                        <option value="00">本地补偿值</option>
                    </select>
                </td>
                <td>
                    <input style="margin-right: 105px" id="bcValue" type="text" />
                </td>
                <td>
                    <input style="margin-left: 30px;" type="button"  value="保存" onclick="saveBc();"/>
                </td>
            </tr>
        </table>
        <%--保存摄像头和云台关联关系的表格--%>
        <div id="tableButton" class="btn-group">
            <h4>云台和摄像头关系列表</h4></br>
            <input style="margin-left: 30px;" type="button"  value="新增" onclick="addRelation()"/>
            <input id="relationNameInput" class="relationTableInput" style="width:180px;margin-left:5px" type="text" placeholder="名称"/>
            <input id="ytIpInput" type="text" class="relationTableInput" style="width:110px" placeholder="云台IP"/>
            <input id="ytPortInput" type="text" class="relationTableInput" style="width:50px" placeholder="端口"/>
            <input id="sxtIpInput" type="text" class="relationTableInput" style="width:110px" placeholder="摄像头IP"/>
            <input id="sxtPortInput" type="text" class="relationTableInput" style="width:50px" placeholder="端口"/>
        </div>
        <div style="width:80%;margin-left: 100px;margin-top: 10px;">
            <table id="linkTable"></table>
        </div>
    </div>
</body>
<script>
    $(function () {
        //初始化云台摄像头关系列表
        $("#linkTable").bootstrapTable({
                // toolbar: '#tableButton',
            url: "getAllYtRelation",
            striped: true,
            singleSelect:true,
            pagination: false,
            clickToSelect:true,
            columns:[{
                checkbox:true,
            },{
                field: 'relationName',
                sortable:true,
                title: '名称',
            },{
                field: 'ytIp',
                title: '云台IP',
                sortable: true,
            },{
                field: 'ytPort',
                title: '云台端口',
                sortable: true
            },{
                field: 'sxtIp',
                title: '摄像头IP',
                sortable: true
            }, {
                field: 'sxtPort',
                title: '摄像头端口',
                sortable: true
            }, {
                field: 'operate',
                title: '操作',
                align: 'center',
                formatter: operateFormatter
            }],
            onClickRow:function (row) {
                console.log(row);
            }
        })
    })

    function addRelation() {
        var relationNameInput = $("#relationNameInput").val();
        var ytIpInput = $("#ytIpInput").val();
        var ytPortInput = $("#ytPortInput").val();
        var sxtIpInput = $("#sxtIpInput").val();
        var sxtPortInput = $("#sxtPortInput").val();
        if(relationNameInput != "" &&
            ytIpInput != "" &&
            ytPortInput != "" &&
            sxtIpInput != "" &&
            sxtPortInput != ""
        ){
            $.ajax({
                type: "POST",
                data: {
                    "relationName": relationNameInput,
                    "ytIp":ytIpInput,
                    "ytPort":ytPortInput,
                    "sxtIp":sxtIpInput,
                    "sxtPort":sxtPortInput,
                },
                url: "saveYtRelation",
                dataType:"json",
                async: false,
                success: function (data) {
                    if(data.code == "0"){
                        //刷新远程服务器数据，可以设置{silent: true}以静默方式刷新数据
                        $("#linkTable").bootstrapTable("refresh",{silent:true});
                        //清除数据
                        $(".relationTableInput").val("");
                        alert("操作成功！");
                    }
                }
            });
        }else{
            alert("请补全需要添加的信息")
        }
    }

    //删除云台绑定关系
    function deleteRelation(id) {
        var r=confirm("确定删除该云台和摄像头的绑定么？");
        if(r){
            $.ajax({
                type: "POST",
                data: {
                    id:id
                },
                url: "delYtRelation",
                dataType:"json",
                async: false,
                success: function (data) {
                    if(data.code == "0"){
                        //刷新远程服务器数据，可以设置{silent: true}以静默方式刷新数据
                        $("#linkTable").bootstrapTable("refresh",{silent:true});
                    }
                }
            });
        }
    }
    
    function operateFormatter(value, row, index){
        console.log(row);
        return '<a type="button" onclick="deleteRelation('+row.id+')">删除</a>';
    }
</script>
</html>