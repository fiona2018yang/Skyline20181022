package com.rs.skyline.waterdata.tasks;

//import org.apache.commons.httpclient.HttpClient;

import com.rs.skyline.waterdata.entity.Bean;
import com.rs.skyline.waterdata.entity.Instruct;
import com.rs.skyline.waterdata.service.ServerExecutor;
import com.rs.skyline.waterdata.util.Http;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//import org.apache.http.client.HttpClient;
//import java.nio.charset.Charset;

/**
 * 该类为多线程类，用于服务端
 */
public class Server5001Thread implements Runnable {

    private Socket client = null;
    public static int counts = 0;
    public static double ks = 0.0, js = 0.0;
    public static int d_bianjiao, d_jujiao;
    public static boolean isStop = false;
    public static volatile boolean flag = false;
    public static volatile boolean flag_fous = false;

    public Server5001Thread(Socket client) {
        this.client = client;
    }

    //处理通信细节的静态方法，这里主要是方便线程池服务器的调用
    public static void execute(Socket client) {
    }


    @Override
    public void run() {
        execute(client);
    }

}