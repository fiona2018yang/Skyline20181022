package com.rs.skyline.waterdata.controller;

import com.rs.skyline.waterdata.entity.Connect;
import com.rs.skyline.waterdata.entity.Instruct;
import com.rs.skyline.waterdata.entity.YtRelation;
import com.rs.skyline.waterdata.service.YtRelationService;
import com.rs.skyline.waterdata.tasks.ServerThread;
import com.rs.skyline.waterdata.util.ConnectClient;
import com.rs.skyline.waterdata.util.Http;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.nio.cs.UTF_32;

import javax.servlet.http.HttpServletRequest;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @Auther: heyc
 * @Date: 2018/7/21 16:52
 * @Description:
 *
 * 接收参数 自动跟踪
 */

@Controller
@RequestMapping("/Cloud")
public class CloudController {

    @Autowired
    private YtRelationService ytRelationService;

    //ip人车船共用地址
    String udpIp ="192.168.1.203";

    @RequestMapping(value = "/Track", method = RequestMethod.POST)
    @ResponseBody
    public  String Track(HttpServletRequest request ) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        // 接收键值对值
        String name = request.getParameter("state");
        System.out.println("接收到的数据"+ name );
        String result = "";
        if (!name.equals("") && name.length() > 0) {
            int i = Integer.parseInt(name);
            System.out.println("接受到的区域块-----" + i);
            switch (i) {
                case 0:
                    result = "停止";
                    Instruct.stop();
                    ServerThread.isStop = true;
                    break;
                case 1:
                    result = "上";
                    Instruct.goUp();
                    ServerThread.isStop = true;
                    break;
                case 2:
                    result = "下";
                    Instruct.goDown();
                    ServerThread.isStop = true;
                    break;
                case 3:
                    result = "左";
                    Instruct.goLeft();
                    ServerThread.isStop = true;
                    break;
                case 4:
                    result = "右";
                    Instruct.goRight();
                    ServerThread.isStop = true;
                    break;
                case 5:
                    result = "左上";
                    Instruct.goLeftUp();
                    ServerThread.isStop = true;
                    break;
                case 6:
                    result = "右上";
                    Instruct.goRightUp();
                    ServerThread.isStop = true;
                    break;
                case 7:
                    result = "左下";
                    Instruct.goLeftDown();
                    ServerThread.isStop = true;
                    break;
                case 8:
                    result = "右下";
                    Instruct.goRightDown();
                    ServerThread.isStop = true;
                    break;
                case 9:
                    Instruct.messageList.add(Instruct.getCurrentTime()+"停止跟踪");
                    Instruct.stop();
                    break;
                default:
                    break;
            }
           /* String postString = "航=" + String.valueOf(ServerThread.ks) + ",俯=" + String.valueOf(ServerThread.js) + ",IP="+Instruct.ip;
            // 发送地址、数据
            try {
                Http.doPostWithoutKey("http://192.168.1.208:8080", postString);
                System.out.println("接收到区域块给Untiy发送数据"+postString);
            } catch (Throwable e) {
                e.printStackTrace();
            }*/
           //发送变焦值到Untiy
            startMonitor();

        }
        return null;
    }

    //在区域块识别的同时给Untiy 发送变焦值
    public void startMonitor() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    //发送变焦值和IP
                    String jj = "变焦="+ServerThread.d_bianjiao + ",IP="+Instruct.ip;
                    Http.doPostWithoutKey("http://192.168.1.208:8081", jj);
                    System.out.println("接收到区域块给Untiy发送" + jj);
                }catch (Exception e){
                }
            }
        }).start();
    }
    /***
     *  前端页面查询 连接状态
     * */
    @RequestMapping(value = "/GetConnectstate", method = RequestMethod.GET)
    @ResponseBody
    public void State(){
        state();
     }

    public static Connect state(){
         Connect connect  = new Connect();
         int state = connect.getHttp_state();
         //counts:成功打印次数    counts_fail:失败打印次数
         int counts = 0, counts_fail = 0;
         if (counts == 0 && counts_fail == 0) {
             if (state == 0) {
                 counts++;
                 System.out.println("成功");
                 counts_fail = 0;
             }else {
                 counts_fail++;
                 System.out.println("失败");
                 counts = 0;
             }
         }else if (counts == 0 && counts_fail != 0){
             if (state == 0){
                 counts++;
                 System.out.println("成功");
                 counts_fail = 0;
             }else {
                 counts = 0;
                 counts_fail++;
             }
         }else if (counts != 0 && counts_fail == 0){
             if (state == 0){
                 counts++;
                 counts_fail = 0;
             }else {
                 counts_fail++;
                 System.out.println("失败");
                 counts = 0;
             }
         }
           return  null;
     }
    /**
     * @Description: 发送跟踪人指令
     * */
      @RequestMapping(value = "/getPeople", method = RequestMethod.GET)
          public void People(){
          setUpd("0");
      }
    /**
     * @Description: 发送指令调用 服务端识别车指令
     * */
    @RequestMapping(value = "/getCar",method =RequestMethod.GET)
    @ResponseBody
    public void Car(){
        setUpd("1");
    }
    /**
     * @Description: 服务端识别船指令
     *
     * */

    @RequestMapping(value = "/getShip",method = RequestMethod.GET)
    public void ship(){
        setUpd("2");
    }
    /**
     * @Description: 服务端识别漂浮物指令
     * */
    @RequestMapping(value = "/FloatingMatter",method =RequestMethod.GET)
    public  void FloatingMatter(){
        setUpd("3");
    }

    /**
     * @Auther :heyc
     *   2018年10月19日 19:32:56
     * @param :发送4号标识打开图像端程序画面
     */
    @RequestMapping(value = "/FisP",method =RequestMethod.GET)
    public  void FisP(){
        getUpd();
    }



    /**
     * @Auther :heyc
     *   2018年7月28日 17:08:37
     * @param :发送
     */

    public  void  Distinguish(byte[] b){
        DataOutputStream out = null;
        ConnectClient connectClient = new ConnectClient();
        Socket socket = ConnectClient.list_client.get(0).getClient();
        System.out.println(socket);
        try {
            out = new DataOutputStream(socket.getOutputStream());
            out.write(b);
            System.out.println(b);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //接收前端控件像素点位置
    @RequestMapping(value = "/ControlPx",method = RequestMethod.GET)
    @ResponseBody
    public void  ControlPx(@RequestParam(value = "x") String x, @RequestParam(value = "y") String y) {
        //转换俯仰角，0~360°转换为 -40~40°
        String Px = x +"：X轴，" + y +"：Y轴";
        System.out.println("像素点 "+ Px);

     /*   try {
            Http.doPostWithoutKey("http://192.168.1.209:8080",Px );
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
    public  void setUpd(String type){
        try {
            //获取当前云台对应摄像头IP
            YtRelation ytRelation = ytRelationService.getYtrelationByYtip(Instruct.ip.replace("/",""));
            String  data = "{'ip':'rtsp://admin:admin12345@"+ytRelation.getSxtIp()+"/Streaming/Channels/1','control':'on','object_id':["+type+",]}";
            String   data1 = new String(data.getBytes(),"UTF-8");
            InetAddress local = InetAddress.getByName(udpIp);     //IP端口
            //建立udp的服务
            DatagramSocket datagramSocket = new DatagramSocket();
            //准备数据，把数据封装到数据包中
            //创建了一个数据包
            DatagramPacket packet = new DatagramPacket(data1.getBytes(), data1.getBytes().length,local , 9997);
            //调用udp的服务发送数据包
            datagramSocket.send(packet);
            System.out.println("发送成功"+data1);
            //关闭资源 ---实际上就是释放占用的端口号
            datagramSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  void getUpd(){
        try {
            //获取当前云台对应摄像头IP
            YtRelation ytRelation = ytRelationService.getYtrelationByYtip(Instruct.ip.replace("/",""));
            String  data = "{'ip':'rtsp://admin:admin12345@"+ytRelation.getSxtIp()+"/Streaming/Channels/1','control':'on'}";
            String   data1 = new String(data.getBytes(),"UTF-8");
            InetAddress local = InetAddress.getByName(udpIp);     //IP端口
            //建立udp的服务
            DatagramSocket datagramSocket = new DatagramSocket();
            //准备数据，把数据封装到数据包中
            //创建了一个数据包
            DatagramPacket packet = new DatagramPacket(data1.getBytes(), data1.getBytes().length,local , 9997);
            //调用udp的服务发送数据包
            datagramSocket.send(packet);
            System.out.println("发送成功"+data1);
            //关闭资源 ---实际上就是释放占用的端口号
            datagramSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }






    /**
     * 停止跟踪
     */
    @RequestMapping(value="stopGenzong",method =RequestMethod.GET)
    @ResponseBody
    public void stopGenzong(@RequestParam(value = "type")Integer type){
        try {
            //获取当前云台对应摄像头IP
            YtRelation ytRelation = ytRelationService.getYtrelationByYtip(Instruct.ip.replace("/",""));
            String  data = "{'ip':'rtsp://admin:admin12345@"+ytRelation.getSxtIp()+"/Streaming/Channels/1','control':'off','object_id':["+type+",]}";
            String   data1 = new String(data.getBytes(),"UTF-8");
            InetAddress local = InetAddress.getByName(udpIp);     //IP端口
            //建立udp的服务
            DatagramSocket datagramSocket = new DatagramSocket();
            //准备数据，把数据封装到数据包中
            //创建了一个数据包
            DatagramPacket packet = new DatagramPacket(data1.getBytes(), data1.getBytes().length,local , 9997);
            //调用udp的服务发送数据包
            datagramSocket.send(packet);
            System.out.println("发送成功"+data1);

            //关闭资源 ---实际上就是释放占用的端口号
            datagramSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    /**
     * 停止识别
     */
    @RequestMapping(value="stopShibie",method =RequestMethod.GET)
    @ResponseBody
    public void stopShibie(){
        try {
            //获取当前云台对应摄像头IP
            YtRelation ytRelation = ytRelationService.getYtrelationByYtip(Instruct.ip.replace("/",""));
            String  data = "{'ip':'rtsp://admin:admin12345@"+ytRelation.getSxtIp()+"/Streaming/Channels/1','control':'off'}";
            String   data1 = new String(data.getBytes(),"UTF-8");
            InetAddress local = InetAddress.getByName(udpIp);     //IP端口
            //建立udp的服务
            DatagramSocket datagramSocket = new DatagramSocket();
            //准备数据，把数据封装到数据包中
            //创建了一个数据包
            DatagramPacket packet = new DatagramPacket(data1.getBytes(), data1.getBytes().length,local , 9997);
            //调用udp的服务发送数据包
            datagramSocket.send(packet);
            System.out.println("发送成功"+data1);
            //关闭资源 ---实际上就是释放占用的端口号
            datagramSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
