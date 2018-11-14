package com.rs.skyline.waterdata.tasks;

import com.rs.skyline.waterdata.client.ClientPool;
import com.rs.skyline.waterdata.controller.MonitorDataController;
import com.rs.skyline.waterdata.entity.Bean;
import com.rs.skyline.waterdata.entity.Instruct;
import com.rs.skyline.waterdata.service.ServerExecutor;
import com.rs.skyline.waterdata.util.Http;
//import org.apache.http.client.HttpClient;

import javax.validation.constraints.Null;
import java.io.*;
import java.net.Socket;
//import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 该类为多线程类，用于服务端
 */
public class ServerThread implements Runnable {

    private Socket client = null;
    public static int counts = 0;
    public static double ks = 0.0, js = 0.0;
    public static int d_bianjiao;
    public static boolean isStop = false;
    public static volatile boolean flag = false;
    public static volatile boolean flag_fous = false;       //

    public static int currentXunheTime = 1;
    public static int n_now = 1;

    public ServerThread(Socket client) {
        this.client = client;
    }
    //处理通信细节的静态方法，这里主要是方便线程池服务器的调用
    public static void execute(Socket client) {
        try {
            DataInputStream dis = new DataInputStream(client.getInputStream());
            DataOutputStream out = new DataOutputStream(client.getOutputStream());
            //YF修改
            byte[] bytes = new byte[128]; //yf修改大小
            String ret = "";

            while (dis.read(bytes) != -1) {
                //   ret += bytesToHexString(bytes) + " ";
                ret = bytesToHexString(bytes) + " ";
                SimpleDateFormat sdfHm=new SimpleDateFormat("hh:mm:ss:SSS");
                System.out.println(sdfHm.format(new Date()));
               // System.out.println("发送请求：" + ret);
                if (dis.available() == 0) { //一个请求
                    System.out.println("发送请求（没有问题）：" + ret);
                    String str = "";

                    //int len = ret.length()/16;
                     //分条解析
                    for (int j = 0; j < 8; j++) {
                        //每条单独解析
                        str = ret.substring(j*32, j*32+32);
                        if ((str.substring(0,4).equals("ffff")) && (str.substring(26,30).equals("eeee")) ){
                            DataAnalysis(str);

                            bytes = new byte[128];
                        }
                        str = "";
                       // DataAnalysis(str);
                    }
                    ret = "";
                }
            }
//            byte[] bytes = new byte[32]; //yf修改大小
//            String ret = "";
//
//            while (dis.read(bytes) != -1) {
//                //   ret += bytesToHexString(bytes) + " ";
//                ret = bytesToHexString(bytes) + " ";
//                SimpleDateFormat sdfHm=new SimpleDateFormat("hh:mm:ss:SSS");
//                System.out.println(sdfHm.format(new Date()));
//                System.out.println("发送请求：" + ret);
//                if (dis.available() == 0) { //一个请求
//                    System.out.println("发送请求（没有问题）：" + ret);
//                    List<String> str = new ArrayList<>();
//                    for (int j = 0; j < 16; j++) {
//                        str.add(ret.substring(j * 2, j * 2 + 2));
//                    }
//                    //设置预置位
//                    if (str.get(3).equals("20") && (str.get(12).equals("01"))){
//                        Instruct.messageList.add(Instruct.getCurrentTime()+"设置预置位成功");
//                    }
////                    if (!isStop) {
//                    if (str.get(3).equals("23") && str.get(12).equals("01")) {
//                        Instruct.messageList.add(Instruct.getCurrentTime()+"调用预置位成功");
//                    }else if (str.get(3).equals("23") && str.get(12).equals("10")){
//                        System.out.println("调用到无效预置位");
//                    }
////                    }
//                    //获取水平俯仰角
//                    if (str.get(3).equals("06") && str.get(12).equals("0e")) {
////                        if (flag) {
//                            System.out.println("获取俯仰航向返回值");
//                            //航向 course
//                            String hx;
//                            // get(5)，获取数组的位数
//                            hx = str.get(5) + str.get(6) + str.get(7);
//                            //16进制转10进制
//                            int k = Integer.parseInt(hx, 16);
//                            //计算角度
//                            ks = k / 3600.0;
//                            System.out.println(ks);
//                            //俯仰
//                            String fy;
//                            fy = str.get(9) + str.get(10) + str.get(11);
//                            int j = Integer.parseInt(fy, 16);
//                            js = j / 3600.0;
//                            //转换俯仰角，0~360°转换为 -40~40°
//                            if (js > 320.0 && js < 360.0){
//                                js = js - 360.0;
//                            }
//                            System.out.println(js);
//                           String postString = "航=" + String.valueOf(ServerThread.ks) + ",俯=" + String.valueOf(ServerThread.js) + ",IP="+Instruct.ip;
//                            // 发送地址、数据
//                           try {
//                               Http.doPostWithoutKey("http://192.168.1.208:8080", postString);
//                           } catch (Throwable e) {
//                                e.printStackTrace();
//                            }
////                        }
//                    }
//                    //获取变焦值
//                    if (str.get(3).equals("2d") && str.get(12).equals("19")) {
////                        if (flag_fous) {
//                            String bianjiao;
//                            bianjiao = str.get(8) + str.get(9);
//                            int k = Integer.parseInt(bianjiao, 16);
//                         //   System.out.println("变焦=" + k);
//                            d_bianjiao = k;
//                            // 发送地址、数据
//                            try {
//                               Http.doPostWithoutKey("http://192.168.1.208:8081", "变焦=" + k + ",IP="+Instruct.ip);
//                                System.out.println("变焦给Untiy发送----------------------="+d_bianjiao);
//                            } catch (Throwable e) {
//                               e.printStackTrace();
//                            }
////                        }
//                    }
//
//                    if(str.get(3).equals("32")){
//                        Instruct.sendQueryAngleCmd();//角度查询
//                        Instruct.getVod();      //查询变焦值
//                        //发送俯仰航向角IP
//                      /*  String postString = "航=" + String.valueOf(ServerThread.ks) + ",俯="+ String.valueOf(ServerThread.js) + ",IP="+Instruct.ip;
//                        System.out.println("========================="+postString);
//                        System.out.println("航向俯仰字符串长度:::::"+postString.length());
//                        try {
//
//                               Http.doPostWithoutKey("http://192.168.1.208:8080", postString);
//                            } catch (Throwable e) {
//                                e.printStackTrace();
//                        }*/
//                        try{
//                            //发送变焦值和IP
//                            String jj = "变焦="+d_bianjiao + ",IP="+Instruct.ip;
//                            Http.doPostWithoutKey("http://192.168.1.208:8081", jj);
//                            System.out.println("向Untiy发送焦距与IP" + jj);
//                            System.out.println("焦距字符串长度::::::"+jj.length());
//
//                        }catch (Exception e){
//                        }
//                    }
//                }
//            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                System.out.println("客户端已断开！");
                client.close();
                ClientPool.getInstance().removeSocket(client);
            } catch (IOException e) {

            }
        }
    }

    @Override
    public void run() {
        execute(client);
    }

    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    //YF添加，数据解析函数
    public static void DataAnalysis(String strData) {
        List<String> str = new ArrayList<>();
        System.out.println("分条数据：" + strData);

        //分条解析
        for (int j = 0; j < 16; j++) {
            //每条单独解析
            str.add(strData.substring(j * 2, j * 2 + 2));
        }
        String ResultStr = str.get(12);//结果位
        String str_zl = str.get(3);//指令位

        switch (ResultStr){
            case "01"://协议正常
                //设置预置位
                if (str_zl.equals("20")) {
                    Instruct.messageList.add(Instruct.getCurrentTime() + "设置预置位成功");
                }
//                    if (!isStop) {
                if (str_zl.equals("23")) {
                    Instruct.messageList.add(Instruct.getCurrentTime() + "调用预置位成功");
                }
                break;
            case "02"://校验和出错
                break;
            case "03"://协议不正确
                break;
            case "04"://无效命令
                break;
            case "05"://收到指令
                break;
            case "06"://激光测距自检错误
                break;
            case "07"://激光目标错误
                break;
            case "08"://激光收到无效数据
                break;
            case "09"://激光测距值
                break;
            case "0a"://激光测距光强值
                break;
            case "0b"://码盘读取错误
                break;
            case "0c"://航向角度
                break;
            case "0d"://俯仰角度
                break;
            case "0e"://航向俯仰角度
                //获取水平俯仰角
                if (str_zl.equals("06")) {
//                        if (flag) {
                    System.out.println("获取俯仰航向返回值");
                    //航向 course
                    String hx;
                    // get(5)，获取数组的位数
                    hx = str.get(5) + str.get(6) + str.get(7);
                    //16进制转10进制
                    int k = Integer.parseInt(hx, 16);
                    //计算角度
                    ks = k / 3600.0;
                    System.out.println(ks);
                    //俯仰
                    String fy;
                    fy = str.get(9) + str.get(10) + str.get(11);
                    int j = Integer.parseInt(fy, 16);
                    js = j / 3600.0;
                    //转换俯仰角，0~360°转换为 -40~40°
                    if (js > 320.0 && js < 360.0) {
                        js = js - 360.0;
                    }
                    System.out.println(js);
                    String postString = "航=" + String.valueOf(ServerThread.ks) + ",俯=" + String.valueOf(ServerThread.js) + ",IP=" + Instruct.ip;
                    // 发送地址、数据
                    try {
                        Http.doPostWithoutKey("http://192.168.1.208:8080", postString);
                    } catch (Throwable e) {
                        e.printStackTrace();
                    }
//                        }
                }
                break;
            case "0f"://获取预置位
                break;
            case "10"://无效预置位
                if (str_zl.equals("23")) {
                System.out.println("调用到无效预置位");
            }
                break;
            case "11"://预置位号超过范围、
                if (str_zl.equals("23")) {
                    System.out.println("预置位号超过范围");
                }
                break;
            case "12"://更新准备完毕
                break;
            case "13"://数据包错误，数据位8为错误包序号
                break;
            case "14"://数据包接收成功
                break;
            case "15"://角度未调整到位
                break;
            case "16"://航向角设置错误
                break;
            case "17"://调整超时
                break;
            case "18"://摄像头异常
                break;
            case "19"://摄像头参数
                //获取变焦值
                if (str_zl.equals("2d") ) {
//                        if (flag_fous) {
                    String bianjiao;
                    bianjiao = str.get(8) + str.get(9);
                    int k = Integer.parseInt(bianjiao, 16);
                    //   System.out.println("变焦=" + k);
                    d_bianjiao = k;
                    // 发送地址、数据
                    try {
                        Http.doPostWithoutKey("http://192.168.1.208:8081", "变焦=" + k + ",IP=" + Instruct.ip);
                        System.out.println("变焦给Untiy发送----------------------=" + d_bianjiao);
                    } catch (Throwable e) {
                        e.printStackTrace();
                    }
//                        }
                }
                break;
            case "20"://获取变焦实际值失败
                break;
            case "21"://激光测距出错
                break;
            case "22"://IP冲突
                break;
            case "23"://端口冲突
                break;
            case "24"://端口号设置不正确
                break;
            case "25"://IP设置错误
                break;
            case "26"://巡航位置设置错误
                break;
            case "30"://源程序区擦除失败
                break;
            case "31"://更新区擦除失败
                break;
            case "32"://备份区擦除失败
                break;
            case "33"://更新程序写入更新区失败
                break;
            case "34"://更新程序写入更新区成功
                break;
            case "35"://更新程序成功
                break;
            case "36"://更新程序失败
                break;
            case "37"://源程序备份失败
                break;
            case "38"://源程序恢复失败
                break;
            case "39"://更新包序号错误
                break;
            case "3a"://源代码损坏，只能在boot里操作
                break;
            default:
                break;
        }

        //开启自动巡河
            if (str.get(3).equals("32")) {
                Instruct.sendQueryAngleCmd();//角度查询
                Instruct.getVod();      //查询变焦值
                //发送俯仰航向角IP
                      /*  String postString = "航=" + String.valueOf(ServerThread.ks) + ",俯="+ String.valueOf(ServerThread.js) + ",IP="+Instruct.ip;
                        System.out.println("========================="+postString);
                        System.out.println("航向俯仰字符串长度:::::"+postString.length());
                        try {

                               Http.doPostWithoutKey("http://192.168.1.208:8080", postString);
                            } catch (Throwable e) {
                                e.printStackTrace();
                        }*/
                try {
                    //发送变焦值和IP
                    String jj = "变焦=" + d_bianjiao + ",IP=" + Instruct.ip;
                    Http.doPostWithoutKey("http://192.168.1.208:8081", jj);
                    System.out.println("向Untiy发送焦距与IP" + jj);
                    System.out.println("焦距字符串长度::::::" + jj.length());

                } catch (Exception e) {
                }
            }
    }
}