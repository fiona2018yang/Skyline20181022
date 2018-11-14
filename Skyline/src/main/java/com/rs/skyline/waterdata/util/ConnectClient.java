package com.rs.skyline.waterdata.util;

import com.rs.skyline.waterdata.entity.Distinguish;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.List;


/**
 * @Auther: heyc
 * @Date: 2018/7/30 08:49
 * @Description: ServerSocket 客户端程序 连接识别端
 * @param ：
 */

public class ConnectClient {
    private Socket socket;
    private SocketAddress address;

    public static List<Distinguish> list_client = new ArrayList<>();

    public  ConnectClient() {
        try {
            socket = new Socket();
            address = new InetSocketAddress("192.168.1.205", 55555);
            socket.connect(address, 1000);
            System.out.println(address);
            Distinguish distinguish = new Distinguish(socket);
            list_client.add(distinguish);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
