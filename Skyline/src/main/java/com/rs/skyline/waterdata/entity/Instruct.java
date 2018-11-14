package com.rs.skyline.waterdata.entity;

import com.rs.skyline.waterdata.client.ClientPool;
import com.rs.skyline.waterdata.tasks.ServerThread;

import javax.xml.bind.DatatypeConverter;
import java.io.DataOutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Auther: heyc
 * @Date: 2018/8/1 10:15
 * @Description: 云台指令抽取
 */
public class Instruct {

    private static int m_Speed = 1;

    public static List<String> messageList = new ArrayList<>();

    public static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    public static   String ip = null;
    public static  int  port = 0;

    //停
    public static void stop() {
        byte[] b = new byte[16];
        b[0] = (byte) 0xff;
        b[1] = (byte) 0xff;
        b[2] = (byte) 0x00;
        b[3] = (byte) 0x00;
        b[4] = (byte) 0x00;
        b[5] = (byte) 0x00;
        b[6] = (byte) 0x00;
        b[7] = (byte) 0x00;
        b[8] = (byte) 0x00;
        b[9] = (byte) 0x00;
        b[10] = (byte) 0x00;
        b[11] = (byte) 0x00;
        b[12] = (byte) 0x00;
        b[13] = (byte) 0xee;
        b[14] = (byte) 0xee;
        b[15] = (byte) 0x0f;
        GoSend(b);
        System.out.println("停");
        ServerThread.isStop = true;
    }

    public static void goSetSpeed(SetSpeedRequest setSpeedRequest) {
        messageList.add(getCurrentTime()+"云台速度已设置为"+setSpeedRequest.getSpeed());
        m_Speed = Integer.parseInt(setSpeedRequest.getSpeed());
    }

    //上
    public static void goUp() {
        byte[] b = new byte[16];
        b[0] = (byte) 0xff;
        b[1] = (byte) 0xff;
        b[2] = (byte) 0x00;
        b[3] = (byte) 0x07;
        b[4] = (byte) 0x00;
        b[5] = (byte) 0x00;
        b[6] = (byte) 0x00;
        b[7] = (byte) 0x00;
        b[8] = (byte) 0x00;
        b[9] = (byte) 0x00;
        b[10] = (byte) 0x00;
        b[11] = (byte) 0x20;
        b[12] = (byte) 0x00;
        b[13] = (byte) 0xee;
        b[14] = (byte) 0xee;
        b[15] = (byte) 0x28;

        Integer c = m_Speed;
        int j;
        j = 0x07 ^ m_Speed;
        //将j转换为字符串
        String str_j = c.toHexString(j);
        //存放高位字符
        String str_g = "";
        // 判断高位字符，取第一位字符做高位，只有一位用0作高位
        if (str_j.length() == 1) {
            str_g = "0";
        } else {
            str_g = str_j.substring(0, 1);
        }
        //取反
        String str_jj = c.toHexString((~j));
        //取最后一位字符做低位
        String str_d = str_jj.substring(str_jj.length() - 1);
        // 高位字符与低位字符拼接
        String jiaoyan = str_g + str_d;
        // k 16进制   校验转换成 int 16进制
        int k = Integer.parseInt(jiaoyan, 16);
        b[15] = (byte) k;
        b[11] = (byte) m_Speed;
        GoSend(b);
    }

    //下
    public static void goDown() {
        byte[] b = new byte[16];
        b[0] = (byte) 0xff;
        b[1] = (byte) 0xff;
        b[2] = (byte) 0x00;
        b[3] = (byte) 0x08;
        b[4] = (byte) 0x00;
        b[5] = (byte) 0x00;
        b[6] = (byte) 0x00;
        b[7] = (byte) 0x00;
        b[8] = (byte) 0x00;
        b[9] = (byte) 0x00;
        b[10] = (byte) 0x00;
        b[11] = (byte) 0x20;
        b[12] = (byte) 0x00;
        b[13] = (byte) 0xee;
        b[14] = (byte) 0xee;
        b[15] = (byte) 0x27;

        Integer c = m_Speed;
        int j;
        j = 0x08 ^ m_Speed;
        //将j转换为字符串
        String str_j = c.toHexString(j);
        //存放高位字符
        String str_g = "";
        // 判断高位字符，取第一位字符做高位，只有一位用0作高位
        if (str_j.length() == 1) {
            str_g = "0";
        } else {
            str_g = str_j.substring(0, 1);
        }
        //取反
        String str_jj = c.toHexString((~j));
        //取最后一位字符做低位
        String str_d = str_jj.substring(str_jj.length() - 1);
        // 高位字符与低位字符拼接
        String jiaoyan = str_g + str_d;
        // k 16进制   校验转换成 int 16进制
        int k = Integer.parseInt(jiaoyan, 16);
        b[15] = (byte) k;
        b[11] = (byte) m_Speed;
        GoSend(b);
        System.out.println("下");
    }

    //左下
    public static void goLeftDown() {
        byte[] b = new byte[16];
        b[0] = (byte) 0xff;
        b[1] = (byte) 0xff;
        b[2] = (byte) 0x00;
        b[3] = (byte) 0x0d;
        b[4] = (byte) 0x00;
        b[5] = (byte) 0x00;
        b[6] = (byte) 0x00;
        b[7] = (byte) 0x00;
        b[8] = (byte) 0x00;
        b[9] = (byte) 0x00;
        b[10] = (byte) 0x20;
        b[11] = (byte) 0x20;
        b[12] = (byte) 0x00;
        b[13] = (byte) 0xee;
        b[14] = (byte) 0xee;
        b[15] = (byte) 0x02;

        Integer c = m_Speed;
        int j;
        j = 0x0d ^ m_Speed ^ m_Speed;
        //将j转换为字符串
        String str_j = c.toHexString(j);
        //存放高位字符
        String str_g = "";
        // 判断高位字符，取第一位字符做高位，只有一位用0作高位
        if (str_j.length() == 1) {
            str_g = "0";
        } else {
            str_g = str_j.substring(0, 1);
        }
        //取反
        String str_jj = c.toHexString((~j));
        //取最后一位字符做低位
        String str_d = str_jj.substring(str_jj.length() - 1);
        // 高位字符与低位字符拼接
        String jiaoyan = str_g + str_d;
        // k 16进制   校验转换成 int 16进制
        int k = Integer.parseInt(jiaoyan, 16);
        b[15] = (byte) k;
        b[10] = (byte) m_Speed;
        b[11] = (byte) m_Speed;
        GoSend(b);
        System.out.println("左下");
    }

    public static void goLeft() {
        byte[] b = new byte[16];
        b[0] = (byte) 0xff;
        b[1] = (byte) 0xff;
        b[2] = (byte) 0x00;
        b[3] = (byte) 0x09;
        b[4] = (byte) 0x00;
        b[5] = (byte) 0x00;
        b[6] = (byte) 0x00;
        b[7] = (byte) 0x00;
        b[8] = (byte) 0x00;
        b[9] = (byte) 0x00;
        b[10] = (byte) 0x20;
        b[11] = (byte) 0x00;
        b[12] = (byte) 0x00;
        b[13] = (byte) 0xee;
        b[14] = (byte) 0xee;
        b[15] = (byte) 0x26;

        Integer c = m_Speed;
        int j;
        j = 0x09 ^ m_Speed;
        //将j转换为字符串
        String str_j = c.toHexString(j);
        //存放高位字符
        String str_g = "";
        // 判断高位字符，取第一位字符做高位，只有一位用0作高位
        if (str_j.length() == 1) {
            str_g = "0";
        } else {
            str_g = str_j.substring(0, 1);
        }
        //取反
        String str_jj = c.toHexString((~j));
        //取最后一位字符做低位
        String str_d = str_jj.substring(str_jj.length() - 1);
        // 高位字符与低位字符拼接
        String jiaoyan = str_g + str_d;
        // k 16进制   校验转换成 int 16进制
        int k = Integer.parseInt(jiaoyan, 16);
        b[15] = (byte) k;
        b[10] = (byte) m_Speed;
        GoSend(b);
        System.out.println("左");
    }

    //向右
    public static void goRight() {
        byte[] b = new byte[16];
        b[0] = (byte) 0xff;
        b[1] = (byte) 0xff;
        b[2] = (byte) 0x00;
        b[3] = (byte) 0x0a;
        b[4] = (byte) 0x00;
        b[5] = (byte) 0x00;
        b[6] = (byte) 0x00;
        b[7] = (byte) 0x00;
        b[8] = (byte) 0x00;
        b[9] = (byte) 0x00;
        b[10] = (byte) 0x20;
        b[11] = (byte) 0x00;
        b[12] = (byte) 0x00;
        b[13] = (byte) 0xee;
        b[14] = (byte) 0xee;
        b[15] = (byte) 0x25;

        Integer c = m_Speed;
        int j;
        j = 0x0a ^ m_Speed;
        //将j转换为字符串
        String str_j = c.toHexString(j);
        //存放高位字符
        String str_g = "";
        // 判断高位字符，取第一位字符做高位，只有一位用0作高位
        if (str_j.length() == 1) {
            str_g = "0";
        } else {
            str_g = str_j.substring(0, 1);
        }
        //取反
        String str_jj = c.toHexString((~j));
        //取最后一位字符做低位
        String str_d = str_jj.substring(str_jj.length() - 1);
        // 高位字符与低位字符拼接
        String jiaoyan = str_g + str_d;
        // k 16进制   校验转换成 int 16进制
        int k = Integer.parseInt(jiaoyan, 16);
        b[15] = (byte) k;
        b[10] = (byte) m_Speed;
        GoSend(b);
        System.out.println("右");
    }

    //左上
    public static void goLeftUp() {
        byte[] b = new byte[16];
        b[0] = (byte) 0xff;
        b[1] = (byte) 0xff;
        b[2] = (byte) 0x00;
        b[3] = (byte) 0x0b;
        b[4] = (byte) 0x00;
        b[5] = (byte) 0x00;
        b[6] = (byte) 0x00;
        b[7] = (byte) 0x00;
        b[8] = (byte) 0x00;
        b[9] = (byte) 0x00;
        b[10] = (byte) 0x20;
        b[11] = (byte) 0x20;
        b[12] = (byte) 0x00;
        b[13] = (byte) 0xee;
        b[14] = (byte) 0xee;
        b[15] = (byte) 0x04;

        Integer c = m_Speed;
        int j;
        j = 0x0b ^ m_Speed ^ m_Speed;
        //将j转换为字符串
        String str_j = c.toHexString(j);
        //存放高位字符
        String str_g = "";
        // 判断高位字符，取第一位字符做高位，只有一位用0作高位
        if (str_j.length() == 1) {
            str_g = "0";
        } else {
            str_g = str_j.substring(0, 1);
        }
        //取反
        String str_jj = c.toHexString((~j));
        //取最后一位字符做低位
        String str_d = str_jj.substring(str_jj.length() - 1);
        // 高位字符与低位字符拼接
        String jiaoyan = str_g + str_d;
        // k 16进制   校验转换成 int 16进制
        int k = Integer.parseInt(jiaoyan, 16);
        b[15] = (byte) k;
        b[10] = (byte) m_Speed;
        b[11] = (byte) m_Speed;
        GoSend(b);
        System.out.println("左上");
    }

    //右上
    public static void goRightUp() {
        byte[] b = new byte[16];
        b[0] = (byte) 0xff;
        b[1] = (byte) 0xff;
        b[2] = (byte) 0x00;
        b[3] = (byte) 0x0c;
        b[4] = (byte) 0x00;
        b[5] = (byte) 0x00;
        b[6] = (byte) 0x00;
        b[7] = (byte) 0x00;
        b[8] = (byte) 0x00;
        b[9] = (byte) 0x00;
        b[10] = (byte) 0x20;
        b[11] = (byte) 0x20;
        b[12] = (byte) 0x00;
        b[13] = (byte) 0xee;
        b[14] = (byte) 0xee;
        b[15] = (byte) 0x03;

        Integer c = m_Speed;
        int j;
        j = 0x0c ^ m_Speed ^ m_Speed;
        //将j转换为字符串
        String str_j = c.toHexString(j);
        //存放高位字符
        String str_g = "";
        // 判断高位字符，取第一位字符做高位，只有一位用0作高位
        if (str_j.length() == 1) {
            str_g = "0";
        } else {
            str_g = str_j.substring(0, 1);
        }
        //取反
        String str_jj = c.toHexString((~j));
        //取最后一位字符做低位
        String str_d = str_jj.substring(str_jj.length() - 1);
        // 高位字符与低位字符拼接
        String jiaoyan = str_g + str_d;
        // k 16进制   校验转换成 int 16进制
        int k = Integer.parseInt(jiaoyan, 16);
        b[15] = (byte) k;
        b[10] = (byte) m_Speed;
        b[11] = (byte) m_Speed;
        GoSend(b);
        System.out.println("右上");
    }

    //右下
    public static void goRightDown() {
        byte[] b = new byte[16];
        b[0] = (byte) 0xff;
        b[1] = (byte) 0xff;
        b[2] = (byte) 0x00;
        b[3] = (byte) 0x0e;
        b[4] = (byte) 0x00;
        b[5] = (byte) 0x00;
        b[6] = (byte) 0x00;
        b[7] = (byte) 0x00;
        b[8] = (byte) 0x00;
        b[9] = (byte) 0x00;
        b[10] = (byte) 0x20;
        b[11] = (byte) 0x20;
        b[12] = (byte) 0x00;
        b[13] = (byte) 0xee;
        b[14] = (byte) 0xee;
        b[15] = (byte) 0x01;

        Integer c = m_Speed;
        int j;
        j = 0x0e ^ m_Speed ^ m_Speed;
        //将j转换为字符串
        String str_j = c.toHexString(j);
        //存放高位字符
        String str_g = "";
        // 判断高位字符，取第一位字符做高位，只有一位用0作高位
        if (str_j.length() == 1) {
            str_g = "0";
        } else {
            str_g = str_j.substring(0, 1);
        }
        //取反
        String str_jj = c.toHexString((~j));
        //取最后一位字符做低位
        String str_d = str_jj.substring(str_jj.length() - 1);
        // 高位字符与低位字符拼接
        String jiaoyan = str_g + str_d;
        // k 16进制   校验转换成 int 16进制
        int k = Integer.parseInt(jiaoyan, 16);
        b[15] = (byte) k;
        b[10] = (byte) m_Speed;
        b[11] = (byte) m_Speed;
        GoSend(b);
        System.out.println("右下");
    }


    //变倍+
    public static void goFocusFar() {

        byte[] b = new byte[16];
        b[0] = (byte) 0xff;
        b[1] = (byte) 0xff;
        b[2] = (byte) 0x00;
        b[3] = (byte) 0x14;
        b[4] = (byte) 0x00;
        b[5] = (byte) 0x00;
        b[6] = (byte) 0x00;
        b[7] = (byte) 0x00;
        b[8] = (byte) 0x00;
        b[9] = (byte) 0x00;
        b[10] = (byte) 0x00;
        b[11] = (byte) 0x00;
        b[12] = (byte) 0x00;
        b[13] = (byte) 0xee;
        b[14] = (byte) 0xee;
        b[15] = (byte) 0x1b;
        GoSend(b);
    }

    //焦距近
    public static void goZoomNear() {
        byte[] b = new byte[16];
        b[0] = (byte) 0xff;
        b[1] = (byte) 0xff;
        b[2] = (byte) 0x00;
        b[3] = (byte) 0x15;
        b[4] = (byte) 0x00;
        b[5] = (byte) 0x00;
        b[6] = (byte) 0x00;
        b[7] = (byte) 0x00;
        b[8] = (byte) 0x00;
        b[9] = (byte) 0x00;
        b[10] = (byte) 0x00;
        b[11] = (byte) 0x00;
        b[12] = (byte) 0x00;
        b[13] = (byte) 0xee;
        b[14] = (byte) 0xee;
        b[15] = (byte) 0x1a;
        GoSend(b);
    }

    //焦距远
    public static void goZoomFar() {
        byte[] b = new byte[16];
        b[0] = (byte) 0xff;
        b[1] = (byte) 0xff;
        b[2] = (byte) 0x00;
        b[3] = (byte) 0x16;
        b[4] = (byte) 0x00;
        b[5] = (byte) 0x00;
        b[6] = (byte) 0x00;
        b[7] = (byte) 0x00;
        b[8] = (byte) 0x00;
        b[9] = (byte) 0x00;
        b[10] = (byte) 0x00;
        b[11] = (byte) 0x00;
        b[12] = (byte) 0x00;
        b[13] = (byte) 0xee;
        b[14] = (byte) 0xee;
        b[15] = (byte) 0x19;
        GoSend(b);
    }

    //变倍
    public static void goFocusNear() {
        byte[] b = new byte[16];
        b[0] = (byte) 0xff;
        b[1] = (byte) 0xff;
        b[2] = (byte) 0x00;
        b[3] = (byte) 0x13;
        b[4] = (byte) 0x00;
        b[5] = (byte) 0x00;
        b[6] = (byte) 0x00;
        b[7] = (byte) 0x00;
        b[8] = (byte) 0x00;
        b[9] = (byte) 0x00;
        b[10] = (byte) 0x00;
        b[11] = (byte) 0x00;
        b[12] = (byte) 0x00;
        b[13] = (byte) 0xee;
        b[14] = (byte) 0xee;
        b[15] = (byte) 0x1c;
        GoSend(b);
    }

    //光圈大
    public static void goApertureBig() {
        byte[] b = new byte[16];
        b[0] = (byte) 0xff;
        b[1] = (byte) 0xff;
        b[2] = (byte) 0x00;
        b[3] = (byte) 0x17;
        b[4] = (byte) 0x00;
        b[5] = (byte) 0x00;
        b[6] = (byte) 0x00;
        b[7] = (byte) 0x00;
        b[8] = (byte) 0x00;
        b[9] = (byte) 0x00;
        b[10] = (byte) 0x00;
        b[11] = (byte) 0x00;
        b[12] = (byte) 0x00;
        b[13] = (byte) 0xee;
        b[14] = (byte) 0xee;
        b[15] = (byte) 0x18;
        GoSend(b);
    }

    //光圈小
    public static void goApertureSmall() {
        byte[] b = new byte[16];
        b[0] = (byte) 0xff;
        b[1] = (byte) 0xff;
        b[2] = (byte) 0x00;
        b[3] = (byte) 0x18;
        b[4] = (byte) 0x00;
        b[5] = (byte) 0x00;
        b[6] = (byte) 0x00;
        b[7] = (byte) 0x00;
        b[8] = (byte) 0x00;
        b[9] = (byte) 0x00;
        b[10] = (byte) 0x00;
        b[11] = (byte) 0x00;
        b[12] = (byte) 0x00;
        b[13] = (byte) 0xee;
        b[14] = (byte) 0xee;
        b[15] = (byte) 0x17;
        GoSend(b);
    }

    //查询俯仰角
    public static void sendQueryAngleCmd() {
        byte[] xb = new byte[16];
        xb[0] = (byte) 0xff;
        xb[1] = (byte) 0xff;
        xb[2] = (byte) 0x00;
        xb[3] = (byte) 0x06;
        xb[4] = (byte) 0x00;
        xb[5] = (byte) 0x00;
        xb[6] = (byte) 0x00;
        xb[7] = (byte) 0x00;
        xb[8] = (byte) 0x00;
        xb[9] = (byte) 0x00;
        xb[10] = (byte) 0x00;
        xb[11] = (byte) 0x00;
        xb[12] = (byte) 0x00;
        xb[13] = (byte) 0xee;
        xb[14] = (byte) 0xee;
        xb[15] = (byte) 0x09;
        GoSend(xb);
    }

    //雨刷开启
    public static void goFzOpen() {
        byte[] b = new byte[16];
        b[0] = (byte) 0xff;
        b[1] = (byte) 0xff;
        b[2] = (byte) 0x00;
        b[3] = (byte) 0x11;
        b[4] = (byte) 0x00;
        b[5] = (byte) 0x00;
        b[6] = (byte) 0x00;
        b[7] = (byte) 0x00;
        b[8] = (byte) 0x00;
        b[9] = (byte) 0x00;
        b[10] = (byte) 0x00;
        b[11] = (byte) 0x00;
        b[12] = (byte) 0x00;
        b[13] = (byte) 0xee;
        b[14] = (byte) 0xee;
        b[15] = (byte) 0x1e;
        GoSend(b);
    }

    //雨刷关闭
    public static void goFzClose() {
        byte[] b = new byte[16];
        b[0] = (byte) 0xff;
        b[1] = (byte) 0xff;
        b[2] = (byte) 0x00;
        b[3] = (byte) 0x12;
        b[4] = (byte) 0x00;
        b[5] = (byte) 0x00;
        b[6] = (byte) 0x00;
        b[7] = (byte) 0x00;
        b[8] = (byte) 0x00;
        b[9] = (byte) 0x00;
        b[10] = (byte) 0x00;
        b[11] = (byte) 0x00;
        b[12] = (byte) 0x00;
        b[13] = (byte) 0xee;
        b[14] = (byte) 0xee;
        b[15] = (byte) 0x1d;
        GoSend(b);
    }

    //设置预置位
    public static void goSetPreset(String param) {
        //将接受的字符串转换为Interger，将Integer转换为String类型
        System.out.println("接收到的设置预置位:" + param);
        messageList.add(getCurrentTime()+"设置预置位"+param);
        Integer c = Integer.parseInt(param);
        int m = c;
        byte[] b = new byte[16];
        b[0] = (byte) 0xff;
        b[1] = (byte) 0xff;
        b[2] = (byte) 0x00;
        b[3] = (byte) 0x20;
        b[4] = (byte) 0x00;
        b[5] = (byte) 0x00;
        b[6] = (byte) 0x00;
        b[7] = (byte) 0x00;
        b[8] = (byte) 0x00;
        b[9] = (byte) 0x00;
        b[10] = (byte) 0x00;
        b[11] = (byte) m;
        b[12] = (byte) 0x00;
        b[13] = (byte) 0xee;
        b[14] = (byte) 0xee;
        int j;
        j = 0x20 ^ m;
        //将j转换为字符串
        String str_j = c.toHexString(j);
        //存放高位字符
        String str_g = "";
        // 判断高位字符，取第一位字符做高位，只有一位用0作高位
        if (str_j.length() == 1) {
            str_g = "0";
        } else {
            str_g = str_j.substring(0, 1);
        }
        //取反
        String str_jj = c.toHexString((~j));
        //取最后一位字符做低位
        String str_d = str_jj.substring(str_jj.length() - 1);
        // 高位字符与低位字符拼接
        String jiaoyan = str_g + str_d;
        // k 16进制   校验转换成 int 16进制
        int k = Integer.parseInt(jiaoyan, 16);
        b[15] = (byte) k;
        System.out.println("发送数据" + b[11]);
        GoSend(b);
    }
    //删除预置位
    public static void goDelPreset(String delPreset) {
        messageList.add(getCurrentTime()+"删除预置位"+delPreset);
        //将接受的字符串转换为Interger，将Integer转换为String类型
        Integer c = Integer.parseInt(delPreset);
        int m = c;
        byte[] b = new byte[16];
        b[0] = (byte) 0xff;
        b[1] = (byte) 0xff;
        b[2] = (byte) 0x00;
        b[3] = (byte) 0x21;
        b[4] = (byte) 0x00;
        b[5] = (byte) 0x00;
        b[6] = (byte) 0x00;
        b[7] = (byte) 0x00;
        b[8] = (byte) 0x00;
        b[9] = (byte) 0x00;
        b[10] = (byte) 0x00;
        b[11] = (byte) m;
        b[12] = (byte) 0x00;
        b[13] = (byte) 0xee;
        b[14] = (byte) 0xee;
        int j;
        j = 0x21 ^ m;
        //将j转换为字符串
        String str_j = c.toHexString(j);
        //存放高位字符
        String str_g = "";
        // 判断高位字符，取第一位字符做高位，只有一位用0作高位
        if (str_j.length() == 1) {
            str_g = "0";
        } else {
            str_g = str_j.substring(0, 1);
        }
        //取反
        String str_jj = c.toHexString((~j));
        //取最后一位字符做低位
        String str_d = str_jj.substring(str_jj.length() - 1);
        // 高位字符与低位字符拼接
        String jiaoyan = str_g + str_d;
        // k 16进制   校验转换成 int 16进制
        int k = Integer.parseInt(jiaoyan, 16);
        b[15] = (byte) k;
        GoSend(b);
    }

    //调用预置位
    public static void goUsePreset(String preset) {
        try {
            messageList.add(getCurrentTime()+"调用预置位:" + preset);
            System.out.println(getCurrentTime()+"调用预置位:" + preset);
            ServerThread.isStop = true;
            String str = preset;
            //将接受的字符串转换为Interger，将Integer转换为String类型
            int i = Integer.parseInt(str);
            Integer c = i;
            int m = i;

            byte[] b = new byte[16];
            b[0] = (byte) 0xff;
            b[1] = (byte) 0xff;
            b[2] = (byte) 0x00;
            b[3] = (byte) 0x23;
            b[4] = (byte) 0x00;
            b[5] = (byte) 0x00;
            b[6] = (byte) 0x00;
            b[7] = (byte) 0x00;
            b[8] = (byte) 0x00;
            b[9] = (byte) 0x00;
            b[10] = (byte) 0x00;
            b[11] = (byte) m;
            b[12] = (byte) 0x00;
            b[13] = (byte) 0xee;
            b[14] = (byte) 0xee;
            int j;
            j = 0x23 ^ m;
            //将j转换为字符串
            String str_j = c.toHexString(j);
            //存放高位字符
            String str_g = "";
            // 判断高位字符，取第一位字符做高位，只有一位用0作高位
            if (str_j.length() == 1) {
                str_g = "0";
            } else {
                str_g = str_j.substring(0, 1);
            }
            //取反
            String str_jj = c.toHexString((~j));
            //取最后一位字符做低位
            String str_d = str_jj.substring(str_jj.length() - 1);
            // 高位字符与低位字符拼接
            String jiaoyan = str_g + str_d;
            // k 16进制   校验转换成 int 16进制
            int k = Integer.parseInt(jiaoyan, 16);
            b[15] = (byte) k;
            GoSend(b);
            System.out.println("发送调用预置位指令" +DatatypeConverter.printHexBinary(b));
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    //自动巡河
    public static void goUsePresetT(String xunhe, String time, String numbers) {
        ServerThread.isStop = false;
        //new巡河进程，第二次点击时关闭该线程
        Thread xunheThread = new Thread(new Runnable() {
            @Override
            public void run() {

                int i = Integer.parseInt(xunhe);  //巡河次数
                int j = 0;
                int m_time = Integer.parseInt(time) * 1000;  //间隔时间
                int num = Integer.parseInt(numbers) + 1;   //巡河的预置位个数

                while (!ServerThread.isStop){
                    if (j < i){
                        for (int p = 1; p < num; p++){
                            toXunHe(p);
                            try {
                                Thread.sleep(m_time);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        j++;
                    }
                }
            }
        });
        xunheThread.setName("xunheThread");
        xunheThread.start();
    }

    private static void toXunHe(int m){
        byte[] b = new byte[16];
        b[0] = (byte) 0xff;
        b[1] = (byte) 0xff;
        b[2] = (byte) 0x00;
        b[3] = (byte) 0x23;
        b[4] = (byte) 0x00;
        b[5] = (byte) 0x00;
        b[6] = (byte) 0x00;
        b[7] = (byte) 0x00;
        b[8] = (byte) 0x00;
        b[9] = (byte) 0x00;
        b[10] = (byte) 0x00;
        b[11] = (byte) m;
        b[12] = (byte) 0x00;
        b[13] = (byte) 0xee;
        b[14] = (byte) 0xee;
        int j;
        j = 0x23 ^ m;
        //将j转换为字符串
        String str_j = Integer.toHexString(j);
        //存放高位字符
        String str_g = "";
        // 判断高位字符，取第一位字符做高位，只有一位用0作高位
        if (str_j.length() == 1) {
            str_g = "0";
        } else {
            str_g = str_j.substring(0, 1);
        }
        //取反
        String str_jj = Integer.toHexString((~j));
        //取最后一位字符做低位
        String str_d = str_jj.substring(str_jj.length() - 1);
        // 高位字符与低位字符拼接
        String jiaoyan = str_g + str_d;
        // k 16进制   校验转换成 int 16进制
        int k = Integer.parseInt(jiaoyan, 16);
        b[15] = (byte) k;
        GoSend(b);
        System.out.println("预置位:" + m);
        System.out.println("预置位:" +DatatypeConverter.printHexBinary(b));
    }

    //发送指令
    public static void GoSend(byte[] b) {
        //System.out.println("发送指令:" +DatatypeConverter.printHexBinary(b));
        Socket client = null;
        DataOutputStream out = null;
        ClientPool.getInstance().sendData(ip, port, b);
        //ClientPool.getInstance().sendDataToAll(b);
    }

    //设置航向角
    public static void goSetAngleHXJ(String hxj) {
        messageList.add(getCurrentTime()+"设置航向角"+hxj);
        int[] angleArr = getAngleArray(hxj, 0x03);
        byte[] b = new byte[16];
        b[0] = (byte) 0xff;
        b[1] = (byte) 0xff;
        b[2] = (byte) 0x00;
        b[3] = (byte) 0x03;
        b[4] = (byte) 0x00;
        b[5] = (byte) angleArr[0];
        b[6] = (byte) angleArr[1];
        b[7] = (byte) angleArr[2];
        b[8] = (byte) 0x00;
        b[9] = (byte) 0x00;
        b[10] = (byte) 0x00;
        b[11] = (byte) 0x00;
        b[12] = (byte) 0x00;
        b[13] = (byte) 0xee;
        b[14] = (byte) 0xee;
        b[15] = (byte) angleArr[3];
        GoSend(b);
        System.out.println("发送航向定位指令" +DatatypeConverter.printHexBinary(b));
    }

    //设置俯仰角
    public static void goSetAngleFYJ(String fyj) {
        messageList.add(getCurrentTime()+"设置俯仰角"+fyj);
        int[] angleArr = getAngleArray(fyj, 0x01);

        byte[] b = new byte[16];
        b[0] = (byte) 0xff;
        b[1] = (byte) 0xff;
        b[2] = (byte) 0x00;
        b[3] = (byte) 0x01;
        b[4] = (byte) 0x00;
        b[5] = (byte) 0x00;
        b[6] = (byte) 0x00;
        b[7] = (byte) 0x00;
        b[8] = (byte) 0x00;
        b[9] = (byte) angleArr[0];
        b[10] = (byte) angleArr[1];
        b[11] = (byte) angleArr[2];
        b[12] = (byte) 0x00;
        b[13] = (byte) 0xee;
        b[14] = (byte) 0xee;
        b[15] = (byte) angleArr[3];
        GoSend(b);
    }

    //航向角俯仰角共同设置
    public static void goSetAngleHXFY(String hxj, String fyj) {
        messageList.add(getCurrentTime()+"设置航向角:"+hxj+" 俯仰角:"+fyj);
        int[] angleHxjArr = getAngleArray(hxj, 0x05);
        int[] angleFyjArr = getAngleArray(fyj, 0x05);
        int j = 0x05 ^ angleHxjArr[0] ^ angleHxjArr[1] ^ angleHxjArr[2] ^ angleFyjArr[0] ^ angleFyjArr[1] ^ angleFyjArr[2];
        //将j转换为字符串
        String str_j = Integer.toHexString(j);
        //存放高位字符
        String str_g = "";
        // 判断高位字符，取第一位字符做高位，只有一位用0作高位
        if (str_j.length() == 1) {
            str_g = "0";
        } else {
            str_g = str_j.substring(0, 1);
        }
        //取反
        String str_jj = Integer.toHexString((~j));
        //取最后一位字符做低位
        String str_d = str_jj.substring(str_jj.length() - 1);
        // 高位字符与低位字符拼接
        String jiaoyan = str_g + str_d;
        // k 16进制   校验转换成 int 16进制
        int k = Integer.parseInt(jiaoyan, 16);
        byte[] b = new byte[16];
        b[0] = (byte) 0xff;
        b[1] = (byte) 0xff;
        b[2] = (byte) 0x00;
        b[3] = (byte) 0x05;
        b[4] = (byte) 0x00;
        b[5] = (byte) angleHxjArr[0];
        b[6] = (byte) angleHxjArr[1];
        b[7] = (byte) angleHxjArr[2];
        b[8] = (byte) 0x00;
        b[9] = (byte) angleFyjArr[0];
        b[10] = (byte) angleFyjArr[1];
        b[11] = (byte) angleFyjArr[2];
        b[12] = (byte) 0x00;
        b[13] = (byte) 0xee;
        b[14] = (byte) 0xee;
        b[15] = (byte) k;
        GoSend(b);
        System.out.println("航向角俯仰角共同设置" +DatatypeConverter.printHexBinary(b));
    }

    // 变焦查询
    public static void getVod() {
        byte[] b = new byte[16];
        b[0] = (byte) 0xff;
        b[1] = (byte) 0xff;
        b[2] = (byte) 0x00;
        b[3] = (byte) 0x2d;
        b[4] = (byte) 0x00;
        b[5] = (byte) 0x00;
        b[6] = (byte) 0x00;
        b[7] = (byte) 0x00;
        b[8] = (byte) 0x00;
        b[9] = (byte) 0x00;
        b[10] = (byte) 0x00;
        b[11] = (byte) 0x00;
        b[12] = (byte) 0x00;
        b[13] = (byte) 0xee;
        b[14] = (byte) 0xee;
        b[15] = (byte) 0x22;
       // System.out.println("焦距指令:" +DatatypeConverter.printHexBinary(b));
        GoSend(b);
    }

    //变焦定位
    public static void bianJiaoFix(String str) {
        messageList.add(getCurrentTime()+"变焦定位"+str);
        System.out.println("接收到的变焦值:" + str);
        int i = Integer.parseInt(str);
        str = Integer.toHexString(i);
        String gao = "", di = "";
        if (str.length() == 4) {
            gao = str.substring(0, 2);
            di = str.substring(2, 4);
        } else if (str.length() == 3) {
            gao = str.substring(0, 1);
            di = str.substring(1, 3);
        } else if (str.length() == 2) {
            gao = "0";
            di = str.substring(0, 2);
        } else if (str.length() == 1) {
            gao = "0";
            di = str;
        } else if (str.length() == 0) {
            gao = "0";
            di = "0";
        }
        int i_gao, i_di;
        i_gao = Integer.parseInt(gao, 16);
        i_di = Integer.parseInt(di, 16);

        byte[] b = new byte[16];
        b[0] = (byte) 0xff;
        b[1] = (byte) 0xff;
        b[2] = (byte) 0x00;
        b[3] = (byte) 0x2f;
        b[4] = (byte) 0x00;
        b[5] = (byte) 0x00;
        b[6] = (byte) 0x00;
        b[7] = (byte) 0x00;
        b[8] = (byte) i_gao;
        b[9] = (byte) i_di;
        b[10] = (byte) 0x00;
        b[11] = (byte) 0x00;
        b[12] = (byte) 0x00;
        b[13] = (byte) 0xee;
        b[14] = (byte) 0xee;
        int j;
        j = 0x2f ^ i_gao ^ i_di;
        //将j转换为字符串
        String str_j = Integer.toHexString(j);
        //存放高位字符
        String str_g = "";
        // 判断高位字符，取第一位字符做高位，只有一位用0作高位
        if (str_j.length() == 1) {
            str_g = "0";
        } else {
            str_g = str_j.substring(0, 1);
        }
        //取反
        String str_jj = Integer.toHexString((~j));
        //取最后一位字符做低位
        String str_d = str_jj.substring(str_jj.length() - 1);
        // 高位字符与低位字符拼接
        String jiaoyan = str_g + str_d;
        // k 16进制   校验转换成 int 16进制
        int k = Integer.parseInt(jiaoyan, 16);
        b[15] = (byte) k;
        GoSend(b);
    }

    //
    public static int[] getAngleArray(String angle, int parm) {
        double d = Double.parseDouble(angle);
        angle = Integer.toHexString((int) (d * 3600)) + "";
        String gao = "0", zhong = "0", di = "0";
        if (angle.length() == 6) {
            gao = angle.substring(0, 2);
            zhong = angle.substring(2, 4);
            di = angle.substring(4, 6);
        } else if (angle.length() == 5) {
            gao = angle.substring(0, 1);
            zhong = angle.substring(1, 3);
            di = angle.substring(3, 5);
        } else if (angle.length() == 4) {
            zhong = angle.substring(0, 2);
            di = angle.substring(2, 4);
        } else if (angle.length() == 3) {
            zhong = angle.substring(0, 1);
            di = angle.substring(1, 3);
        } else if (angle.length() == 2) {
            di = angle.substring(0, 2);
        } else if (angle.length() == 1) {
            di = angle;
        }
        int i_gao, i_zhong, i_di;
        i_gao = Integer.parseInt(gao, 16);
        i_zhong = Integer.parseInt(zhong, 16);
        i_di = Integer.parseInt(di, 16);
        int j = parm ^ i_gao ^ i_zhong ^ i_di;
        //将j转换为字符串
        String str_j = Integer.toHexString(j);
        //存放高位字符
        String str_g = "";
        // 判断高位字符，取第一位字符做高位，只有一位用0作高位
        if (str_j.length() == 1) {
            str_g = "0";
        } else {
            str_g = str_j.substring(0, 1);
        }
        //取反
        String str_jj = Integer.toHexString((~j));
        //取最后一位字符做低位
        String str_d = str_jj.substring(str_jj.length() - 1);
        // 高位字符与低位字符拼接
        String jiaoyan = str_g + str_d;
        // k 16进制   校验转换成 int 16进制
        int k = Integer.parseInt(jiaoyan, 16);

        int[] result = {i_gao, i_zhong, i_di, k};
        return result;
    }


    //重启云台
    public static void restartCloud() {
        messageList.add(getCurrentTime()+"重启云台");
        byte[] b = new byte[16];
        b[0] = (byte) 0xff;
        b[1] = (byte) 0xff;
        b[2] = (byte) 0x00;
        b[3] = (byte) 0x19;
        b[4] = (byte) 0x00;
        b[5] = (byte) 0x00;
        b[6] = (byte) 0x00;
        b[7] = (byte) 0x00;
        b[8] = (byte) 0x00;
        b[9] = (byte) 0x00;
        b[10] = (byte) 0x00;
        b[11] = (byte) 0x00;
        b[12] = (byte) 0x00;
        b[13] = (byte) 0xee;
        b[14] = (byte) 0xee;
        b[15] = (byte) 0x16;
        GoSend(b);
    }

    //恢复出厂设置
    public static void restoreSetting() {
        messageList.add(getCurrentTime()+"恢复出厂设置");
        byte[] b = new byte[16];
        b[0] = (byte) 0xff;
        b[1] = (byte) 0xff;
        b[2] = (byte) 0x00;
        b[3] = (byte) 0x26;
        b[4] = (byte) 0x00;
        b[5] = (byte) 0x00;
        b[6] = (byte) 0x00;
        b[7] = (byte) 0x00;
        b[8] = (byte) 0x00;
        b[9] = (byte) 0x00;
        b[10] = (byte) 0x00;
        b[11] = (byte) 0x00;
        b[12] = (byte) 0x00;
        b[13] = (byte) 0xee;
        b[14] = (byte) 0xee;
        b[15] = (byte) 0x29;
        GoSend(b);
        System.out.println(b);
    }

    //保存本地IP
    public static void saveIp(String IpType, String LIP) {
        messageList.add(getCurrentTime()+"保存IP");
        String[] ipArray = LIP.split("\\.");
        //保存IP
        byte[] b = new byte[16];
        b[0] = (byte) 0xff;
        b[1] = (byte) 0xff;
        b[2] = (byte) 0x00;
        b[3] = (byte) 0x1c;
        int b4 = Integer.parseInt(IpType);
        b[4] = (byte) b4;
        b[5] = (byte) 0x00;
        b[6] = (byte) 0x00;
        b[7] = (byte) 0x00;
        //Ip取HEX
        int b8 = Integer.parseInt(ipArray[0]);
        int b9 = Integer.parseInt(ipArray[1]);
        int b10 = Integer.parseInt(ipArray[2]);
        int b11 = Integer.parseInt(ipArray[3]);
        b[8] = (byte) b8;
        b[9] = (byte) b9;
        b[10] = (byte) b10;
        b[11] = (byte) b11;
        b[12] = (byte) 0x00;
        b[13] = (byte) 0xee;
        b[14] = (byte) 0xee;
        int j = 0x1c ^ b4 ^ b8 ^ b9 ^ b10 ^ b11;
        //将j转换为字符串
        String str_j = Integer.toHexString(j);
        //存放高位字符
        String str_g = "";
        // 判断高位字符，取第一位字符做高位，只有一位用0作高位
        if (str_j.length() == 1) {
            str_g = "0";
        } else {
            str_g = str_j.substring(0, 1);
        }
        //取反
        String str_jj = Integer.toHexString((~j));
        //取最后一位字符做低位
        String str_d = str_jj.substring(str_jj.length() - 1);
        // 高位字符与低位字符拼接
        String jiaoyan = str_g + str_d;
        // k 16进制   校验转换成 int 16进制
        int k = Integer.parseInt(jiaoyan, 16);

        b[15] = (byte) k;
        GoSend(b);
    }

    public static void savePort(String portType, String port) {
        messageList.add(getCurrentTime()+"保存端口号");
        //保存IP
        byte[] b = new byte[16];
        b[0] = (byte) 0xff;
        b[1] = (byte) 0xff;
        b[2] = (byte) 0x00;
        b[3] = (byte) 0x1d;
        int b4 = Integer.parseInt(portType);
        b[4] = (byte) b4;
        b[5] = (byte) 0x00;
        b[6] = (byte) 0x00;
        b[7] = (byte) 0x00;
        b[8] = (byte) 0x00;
        b[9] = (byte) 0x00;
        //Ip取HEX
        int b10 = 0, b11 = 0;
        int m_port = Integer.parseInt(port);
        port = Integer.toHexString(m_port);
        if (port.length() <= 2){
            b10 = 0;
            b11 = Integer.parseInt(port);
        }else if (port.length() == 3){
            b10 = Integer.parseInt(port.substring(0, 1));
            b11 = Integer.parseInt(port.substring(1, 3));
        }else if (port.length() == 4){
            b10 = Integer.parseInt(port.substring(0, 2));
            b11 = Integer.parseInt(port.substring(2, 4));
        }
        b[10] = (byte) b10;
        b[11] = (byte) b11;
        b[12] = (byte) 0x00;
        b[13] = (byte) 0xee;
        b[14] = (byte) 0xee;
        int j = 0x1d ^ b4 ^ b10 ^ b11;
        //将j转换为字符串
        String str_j = Integer.toHexString(j);
        //存放高位字符
        String str_g = "";
        // 判断高位字符，取第一位字符做高位，只有一位用0作高位
        if (str_j.length() == 1) {
            str_g = "0";
        } else {
            str_g = str_j.substring(0, 1);
        }
        //取反
        String str_jj = Integer.toHexString((~j));
        //取最后一位字符做低位
        String str_d = str_jj.substring(str_jj.length() - 1);
        // 高位字符与低位字符拼接
        String jiaoyan = str_g + str_d;
        // k 16进制   校验转换成 int 16进制
        int k = Integer.parseInt(jiaoyan, 16);

        b[15] = (byte) k;
        GoSend(b);
        System.out.println("保存端口号指令" +DatatypeConverter.printHexBinary(b));
    }

    //保存补偿值
    public static void saveBcValue(String id,String bcValue) {
        messageList.add(getCurrentTime()+"修改补偿值");
        double d = Double.parseDouble(bcValue);
        bcValue = Integer.toHexString((int) (d * 3600)) + "";
        String gao = "0", zhong = "0", di = "0";
        if (bcValue.length() == 6) {
            gao = bcValue.substring(0, 2);
            zhong = bcValue.substring(2, 4);
            di = bcValue.substring(4, 6);
            System.out.println(di);
        } else if (bcValue.length() == 5) {
            gao = bcValue.substring(0, 1);
            zhong = bcValue.substring(1, 3);
            di = bcValue.substring(3, 5);
            System.out.println(di);
        } else if (bcValue.length() == 4) {
            zhong = bcValue.substring(0, 2);
            di = bcValue.substring(2, 4);
            System.out.println(di);
        } else if (bcValue.length() == 3) {
            zhong = bcValue.substring(0, 1);
            di = bcValue.substring(1, 3);
            System.out.println(di);
        } else if (bcValue.length() == 2) {
            di = bcValue.substring(0, 2);
        } else if (bcValue.length() == 1) {
            di = bcValue;
        }

        int i_gao, i_zhong, i_di;
        i_gao = Integer.parseInt(gao, 16);
        i_zhong = Integer.parseInt(zhong, 16);
        i_di = Integer.parseInt(di, 16);
        int ids = Integer.parseInt(id, 16);
        int j = 0x24 ^ ids ^ i_gao ^ i_zhong ^ i_di;
        //将j转换为字符串
        String str_j = Integer.toHexString(j);
        //存放高位字符
        String str_g = "";
        // 判断高位字符，取第一位字符做高位，只有一位用0作高位
        if (str_j.length() == 1) {
            str_g = "0";
        } else {
            str_g = str_j.substring(0, 1);
        }
        //取反
        String str_jj = Integer.toHexString((~j));
        //取最后一位字符做低位
        String str_d = str_jj.substring(str_jj.length() - 1);
        // 高位字符与低位字符拼接
        String jiaoyan = str_g + str_d;
        // k 16进制   校验转换成 int 16进制
        int k = Integer.parseInt(jiaoyan, 16);

        byte[] b = new byte[16];
        b[0] = (byte) 0xff;
        b[1] = (byte) 0xff;
        b[2] = (byte) 0x00;
        b[3] = (byte) 0x24;
        b[4] = (byte) ids;
        b[5] = (byte) 0x00;
        b[6] = (byte) 0x00;
        b[7] = (byte) 0x00;
        b[8] = (byte) 0x00;
        b[9] = (byte) i_gao;
        b[10] = (byte) i_zhong;
        b[11] = (byte) i_di;
        b[12] = (byte) 0x00;
        b[13] = (byte) 0xee;
        b[14] = (byte) 0xee;
        b[15] = (byte) k;
        GoSend(b);
        System.out.println("保存补偿值" +DatatypeConverter.printHexBinary(b));
    }

    //调用云台底层自动巡河
    public  static void getToXunhe(int number,int time, int startPreset,int endPreset ){
        String i_timedi,  i_timegao, i_xgao, i_xdi;
        String m_time = Integer.toHexString(time);
        if (m_time.length() <= 2){
            i_timedi = m_time;
            i_timegao = "0";
        }else if (m_time.length() == 3){
            i_timedi = m_time.substring(1, 3);
            i_timegao = m_time.substring(0, 1);
        }else {
            i_timedi = m_time.substring(2, 4);
            i_timegao = m_time.substring(0, 2);
        }
        String m_num = Integer.toHexString(number);
        if (m_num.length() <= 2){
            i_xdi = m_num;
            i_xgao = "0";
        }else if (m_num.length() == 3){
            i_xdi = m_num.substring(1, 3);
            i_xgao = m_num.substring(0, 1);
        }else {
            i_xdi = m_num.substring(2, 4);
            i_xgao = m_num.substring(0, 2);
        }

        //gao ：i_timegao    di:i_timedi gao2:i_xgao  di2:i_xdi
        int gao, di, gao2, di2;
        gao = Integer.parseInt(i_timegao, 16);
        di = Integer.parseInt(i_timedi, 16);
        gao2 = Integer.parseInt(i_xgao, 16);
        di2 = Integer.parseInt(i_xdi, 16);

        int j = 0x32 ^ gao ^ di ^ gao2 ^ di2 ^ startPreset ^ endPreset;
        //将j转换为字符串
        String str_j = Integer.toHexString(j);
        //存放高位字符
        String str_g = "";
        // 判断高位字符，取第一位字符做高位，只有一位用0作高位
        if (str_j.length() == 1) {
            str_g = "0";
        } else {
            str_g = str_j.substring(0, 1);
        }
        //取反
        String str_jj = Integer.toHexString((~j));
        //取最后一位字符做低位
        String str_d = str_jj.substring(str_jj.length() - 1);
        // 高位字符与低位字符拼接
        String jiaoyan = str_g + str_d;
        // k 16进制   校验转换成 int 16进制
        int k = Integer.parseInt(jiaoyan, 16);

        byte[] b = new byte[16];
        b[0] = (byte) 0xff;
        b[1] = (byte) 0xff;
        b[2] = (byte) 0x00;
        b[3] = (byte) 0x32;
        b[4] = (byte) startPreset;
        b[5] = (byte) endPreset;
        b[6] = (byte) gao;
        b[7] = (byte) di;
        b[8] = (byte) gao2;
        b[9] = (byte) di2;
        b[10] = (byte) 0x00;
        b[11] = (byte) 0x00;
        b[12] = (byte) 0x00;
        b[13] = (byte) 0xee;
        b[14] = (byte) 0xee;
        b[15] = (byte) k;
        GoSend(b);
        System.out.println("发送自动巡河指令" +DatatypeConverter.printHexBinary(b));
    }

    //关闭自动巡河
    public  static  void OFFxunhe(){
        byte[] b = new byte[16];
        b[0] = (byte) 0xff;
        b[1] = (byte) 0xff;
        b[2] = (byte) 0x00;
        b[3] = (byte) 0x33;
        b[4] = (byte) 0x00;
        b[5] = (byte) 0x00;
        b[6] = (byte) 0x00;
        b[7] = (byte) 0x00;
        b[8] = (byte) 0x00;
        b[9] = (byte) 0x00;
        b[10] = (byte) 0x00;
        b[11] = (byte) 0x00;
        b[12] = (byte) 0x00;
        b[13] = (byte) 0xee;
        b[14] = (byte) 0xee;
        b[15] = (byte) 0x3c;
        GoSend(b);
        System.out.println("关闭巡河指令" +DatatypeConverter.printHexBinary(b));
    }






    //返回头一个值，并去掉
    public static String getMessage(){
        if(messageList != null && messageList.size()>0){
            String messageSingle = messageList.get(0);
            messageList.remove(0);
            return messageSingle;
        }else{
            return null;
        }
    }

    public static String getCurrentTime(){
        return sdf.format(new Date())+":";
    }


}
