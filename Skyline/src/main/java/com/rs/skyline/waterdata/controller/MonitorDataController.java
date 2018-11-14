package com.rs.skyline.waterdata.controller;

import com.rs.skyline.waterdata.client.ClientPool;
import com.rs.skyline.waterdata.entity.*;
import com.rs.skyline.waterdata.service.MonitorDataService;
import com.rs.skyline.waterdata.service.ServerExecutor;
import com.rs.skyline.waterdata.service.YtRelationService;
import com.rs.skyline.waterdata.tasks.ServerThread;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: heyc
 * @Date: 2018/6/8 10:04
 * @Description:
 */
@Controller
@RequestMapping("/monitordata")
public class MonitorDataController {

    @Autowired
    private MonitorDataService monitorDataService;
    @Autowired
    private YtRelationService ytRelationService;
    private static int m_Speed = 1;

    /**
     * @param: 查询数据
     * @return:
     * @auther: heyc
     * @date: ${DATE} ${HOUR}:${MINUTE}
     */
    @RequestMapping(value = "/selectMonitorData", produces = "application/json", method = RequestMethod.POST)
    @ResponseBody
    public List<MonitorData> selectMonitorData() {
        return monitorDataService.selectMonitorData();
    }

    @RequestMapping(value = "/getMonitorData", produces = "application/json", method = RequestMethod.POST)
    @ResponseBody
    public Map getMonitorData() {
        Map map = new HashMap();
        if (monitorDataService.getMonitorData().isEmpty()) {
            map.put("flag", false);
        } else {
            map = monitorDataService.getMonitorData();
            map.put("flag", true);
        }
        return map;
    }
    //

//    public static void GoSend(byte[] b) {
//        ClientPool.getInstance().sendData(5000, b);
//    }

    @RequestMapping(value = "/GoStop", method = RequestMethod.POST)
    @ResponseBody
    public void GoStop() {
        Instruct.stop();
    }

    // 上
    @RequestMapping(value = "/GoUp", method = RequestMethod.POST)
    @ResponseBody
    public void GoUp() {
        Instruct.goUp();
    }

    //下
    @RequestMapping(value = "/GoDown", method = RequestMethod.POST)
    @ResponseBody
    public void goDown() {
        Instruct.goDown();
    }
    //左
    @RequestMapping(value = "/GoLeft", method = RequestMethod.POST)
    @ResponseBody
    public void goLeft() {
        Instruct.goLeft();
    }

    //向右
    @RequestMapping(value = "/GoRight", method = RequestMethod.POST)
    @ResponseBody
    public void goRight() {
        Instruct.goRight();
    }

    /**
     * 左上
     */
    @RequestMapping(value = "/GoLeftUp", method = RequestMethod.POST)
    @ResponseBody
    public void goLeftUp() {
        Instruct.goLeftUp();
    }

    /**
     * 右上
     */
    @RequestMapping(value = "/GoRightUp", method = RequestMethod.POST)
    @ResponseBody
    public void goRightUp() {
        Instruct.goRightUp();
    }

    /**
     * 左下
     */
    @RequestMapping(value = "/GoLeftDown", method = RequestMethod.POST)
    @ResponseBody
    public void goLeftDown() {
        Instruct.goLeftDown();
    }

    /**
     * 右下
     */
    @RequestMapping(value = "/GoRightDown", method = RequestMethod.POST)
    @ResponseBody
    public void goRightDown() {
        Instruct.goRightDown();
    }


    //焦距远
    @RequestMapping(value = "/GoZoomFar", method = RequestMethod.POST)
    @ResponseBody
    public void GoZoomFar() {
        Instruct.goZoomFar();
    }

    //焦距近
    @RequestMapping(value = "/GoZoomNear", method = RequestMethod.POST)
    @ResponseBody
    public void GoZoomNear() {
        Instruct.goZoomNear();
    }

    //变倍+
    @RequestMapping(value = "/GoFocusFar", method = RequestMethod.POST)
    @ResponseBody
    public void GoFocusFar() {
        Instruct.goFocusFar();
    }

    //变倍-
    @RequestMapping(value = "/GoFocusNear", method = RequestMethod.POST)
    @ResponseBody
    public void GoFocusNear() {
        Instruct.goFocusNear();
    }

    //光圈开
    @RequestMapping(value = "/GoApertureBig", method = RequestMethod.POST)
    @ResponseBody
    public void GoApertureBig() {
        Instruct.goApertureBig();
    }

    //光圈关
    @RequestMapping(value = "/GoApertureSmall", method = RequestMethod.POST)
    @ResponseBody
    public void GoApertureSmall() {
        Instruct.goApertureSmall();
    }

    //水平俯仰查询 前端页面展示
    @RequestMapping(value = "/GetAngleDouble", method = RequestMethod.POST)
    @ResponseBody
    public Angle getAngle() {
        Angle angle = new Angle();
        if (0 != ServerThread.js) {
            angle.setFuyangjiao(String.format("%.4f", ServerThread.js));
            angle.setFuyangjiao(String.format("%.4f", ServerThread.js));
        }
        if (0 != ServerThread.ks) {
            angle.setHangxiangjiao(String.format("%.4f", ServerThread.ks));
        }
        return angle;
    }

    // 关闭俯仰查询
    @RequestMapping(value = "/GoGetAngleDoubleOFF", method = RequestMethod.POST)
    @ResponseBody
    public void GoGetAngleDoubleOFF() {
        ServerThread.flag = false;
        System.out.println(ServerThread.flag + "ServerThread.flag");
        System.out.println("关闭查询");
    }

    //水平俯仰查询
    @RequestMapping(value = "/GoGetAngleDouble", method = RequestMethod.POST)
    @ResponseBody
    public static void GoGetAngleDouble() {
        System.out.println("GoGetAngleDoubleXXXXXXXX");
        ServerThread.flag = true;
        //Instruct.sendQueryAngleCmd();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (ServerThread.flag) {
                        Instruct.sendQueryAngleCmd();
                        Thread.sleep(200);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    //关闭变焦查询
    @RequestMapping(value = "closeVol",method = RequestMethod.POST)
    @ResponseBody
    public void closeVol(){
        ServerThread.flag_fous = false;
    }

    //焦距查询
    @RequestMapping(value = "/getVol", method = RequestMethod.POST)
    @ResponseBody
    public void getVol() {
        ServerThread.flag_fous = true;
        Instruct.messageList.add(Instruct.getCurrentTime()+"开启变焦查询");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (ServerThread.flag_fous) {
                        Instruct.getVod();
                        Thread.sleep(200);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    //变焦值查询
    @RequestMapping(value = "/getCurrentVod", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> getCurrentVod() {
        int bianjiao = ServerThread.d_bianjiao;
        Map<String, String> map = new HashedMap();
        map.put("bianjiao", String.valueOf(bianjiao));
        return map;
    }

    //变焦定位
    @RequestMapping(value = "/goBianjiaoFix", method = RequestMethod.POST)
    @ResponseBody
    public void goBianjiaoFix(@RequestParam String bianjiao) {
        Instruct.bianJiaoFix(bianjiao);
    }

    //雨刷开启
    @RequestMapping(value = "/GoFzOpen", method = RequestMethod.POST)
    @ResponseBody
    public void GoFzOpen() {
        Instruct.goFzOpen();
    }

    //雨刷关闭
    @RequestMapping(value = "/GoFzClose", method = RequestMethod.POST)
    @ResponseBody
    public void GoFzClose() {
        Instruct.goFzClose();
    }

    //设置预置位  int  n
    @RequestMapping(value = "/GoSetPreset", method = RequestMethod.POST)
    @ResponseBody
    public void GoSetPreset(@RequestParam(value = "param") String param) {
        Instruct.goSetPreset(param);
    }


    //调用预置位   int n
    @RequestMapping(value = "/GoUsePreset", method = RequestMethod.POST)
    @ResponseBody
    public void GoUsePreset(@RequestParam(value = "preset") String preset) {
        Instruct.goUsePreset(preset);
    }

    //删除预置位
    @RequestMapping(value = "/GoDelPreset", method = RequestMethod.POST)
    @ResponseBody
    public void GoDelPreset(@RequestParam(value = "delPreset") String delPreset){
        Instruct.goDelPreset(delPreset);
    }

    //自动巡河
    @RequestMapping(value = "/GoUsePresetT", method = RequestMethod.POST)
    @ResponseBody
    public void GoUsePresetT(@RequestParam(value = "xunhe") String xunhe,@RequestParam(value = "time") String time,@RequestParam(value = "numbers") String numbers) {
        Instruct.goUsePresetT(xunhe,time,numbers);
        ServerThread.n_now = Integer.parseInt(xunhe);
        System.out.println("巡河次数：" + ServerThread.n_now);
    }

    //开始巡河
    @RequestMapping(value = "/getToXunhe", method = RequestMethod.POST)
    @ResponseBody
    public void getToXunhe(int number,int time, int startPreset,int endPreset){
        Instruct.sendQueryAngleCmd();//角度查询
        Instruct.getVod();      //查询变焦值
        Instruct.getToXunhe(number,time,startPreset,endPreset);
    }

    //停止巡河
    @RequestMapping(value = "/stopXunhe", method = RequestMethod.POST)
    @ResponseBody
    public void stopXunhe(){
        Instruct.OFFxunhe();
    }

    //设置航向角
    @RequestMapping(value = "/goSetAngleHXJ", method = RequestMethod.POST)
    @ResponseBody
    public void goSetAngleHXJ(@RequestParam(value = "hxj") String hxj) {
        Instruct.goSetAngleHXJ(hxj);
    }

    //设置俯仰角
    @RequestMapping(value = "/goSetAngleFYJ", method = RequestMethod.POST)
    @ResponseBody
    public void goSetAngleFYJ(@RequestParam(value = "fyj") String fyj) {
        //转换俯仰角，0~360°转换为 -40~40°
        double fy = Double.parseDouble(fyj);
        if (fy < 0){
            fy = fy + 360.0;
        }
        fyj = String.valueOf(fy);
        Instruct.goSetAngleFYJ(fyj);
    }

    //航向角和俯仰角共同设置
    @RequestMapping(value = "/goSetAngleHXFY", method = RequestMethod.POST)
    @ResponseBody
    public void goSetAngleHXFY(@RequestParam(value = "hxj") String hxj, @RequestParam(value = "fyj") String fyj) {
        //转换俯仰角，0~360°转换为 -40~40°
        double fy = Double.parseDouble(fyj);
        if (fy < 0){
            fy = fy + 360.0;
        }
        fyj = String.valueOf(fy);
        Instruct.goSetAngleHXFY(hxj, fyj);
    }

    /**
     * 摄像头速度控制
     *
     * @date : 2018年7月10日 15点46分
     */
    @RequestMapping(value = "/GoSetSpeed", method = RequestMethod.POST)
    @ResponseBody
    public void GoSetSpeed(@RequestBody SetSpeedRequest setSpeedRequest) {
        System.out.println("接收到的速度指:" + setSpeedRequest.getSpeed());
        m_Speed = Integer.parseInt(setSpeedRequest.getSpeed());
        Instruct.goSetSpeed(setSpeedRequest);
    }

    /**
     * 获取操作消息
     * @return
     */
    @RequestMapping("getMessage")
    @ResponseBody
    public String getMessage(){
        return Instruct.getMessage();
    }

    /**
     *
     * @return 客户端云台连接数组
     */
    @RequestMapping("getClientList")
    @ResponseBody
    public List<YtRelation> getClientList(){
        return ytRelationService.getYtClient(Constant.clientList);
//        return Constant.clientList;
    }

    @RequestMapping("changeIpPort")
    @ResponseBody
    public void changeIpPort(@RequestParam("sxtIp")String sxtIp){

        YtRelation ytRelation = ytRelationService.getYtrealtionBySxtip(sxtIp);
        if(ytRelation != null){
            Instruct.ip = "/"+ytRelation.getYtIp();
            Instruct.port = Integer.parseInt(ytRelation.getYtPort());
            Instruct.messageList.add(Instruct.getCurrentTime()+"修改当前ip端口号为"+Instruct.ip+":"+Instruct.port);
        }
    }

    /**
     * @param: 查询数据
     * @return:
     * @auther: heyc
     * @date: ${DATE} ${HOUR}:${MINUTE}
     */
    @RequestMapping(value = "/recieveData", produces = "application/json", method = RequestMethod.POST)
    @ResponseBody
    public String recieveData(HttpServletRequest request, HttpServletResponse httpServletResponse) {

        InputStream is = null;
        StringBuilder sb = null;
        try {
            is = request.getInputStream();
            sb = new StringBuilder();
            byte[] b = new byte[4096];
            for (int n; (n = is.read(b)) != -1; ) {
                sb.append(new String(b, 0, n));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //   System.out.println("-------------------->>"+sb.toString());
        return sb.toString();
    }
    //
}
