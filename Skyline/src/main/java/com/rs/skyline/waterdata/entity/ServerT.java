package com.rs.skyline.waterdata.entity;

import com.rs.skyline.waterdata.tasks.ServerThread;

/**
 * @Auther: heyc
 * @Date: 2018/7/25 10:14
 * @Description:
 */
public class ServerT extends Thread{
    public void run(){
        byte[] b = new byte[16];
        b[0] = (byte) 0xff;
        b[1] = (byte) 0xff;
        b[2] = (byte) 0x00;
        b[3] = (byte) 0x06;
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
        b[15] = (byte) 0x09;

            System.out.println("threadName="+Thread.currentThread().getName());
            Instruct.GoSend(b);
            String postString = String.valueOf(ServerThread.ks) + "_" + String.valueOf(ServerThread.js);
            // 发送地址、数据
            try {
                //
                          /*  String result =  Http.doPostWithoutKey("http://192.168.1.207:8080",postString);
                            System.out.println(result);*/
            }catch (Exception e){
                e.printStackTrace();
            }
    }
}
