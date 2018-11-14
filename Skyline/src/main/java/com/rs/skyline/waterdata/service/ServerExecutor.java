package com.rs.skyline.waterdata.service;

import com.rs.skyline.waterdata.client.ClientPool;
import com.rs.skyline.waterdata.entity.Bean;
import com.rs.skyline.waterdata.tasks.Server5001Thread;
import com.rs.skyline.waterdata.tasks.ServerThread;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 该类通过Executor接口实现服务器
 */
@Service
public class ServerExecutor {
    /**
     * 初始化登录云台
     */

    @PostConstruct
    public void init() {
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            SoConnect();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
        ).start();
    }

    //ServerSocket 服务端连接云台
    public void SoConnect() throws IOException {
        System.out.println("Begin so connect");
        //服务端在6000端口监听客户端请求的TCP连接
        ServerSocket server = new ServerSocket(6000);
        Socket client = null;
        //通过调用Executors类的静态方法，创建一个ExecutorService实例
        //ExecutorService接口是Executor接口的子接口
        Executor service = Executors.newCachedThreadPool();
        boolean f = true;
        while (f) {
            //等待客户端的连接
            client = server.accept();
            //System.out.println("与客户端连接成功！");
            System.out.println("与客户端" + client.getInetAddress() + ":" + client.getPort() + "连接成功！");
            Bean bean = new Bean(client, client.getPort(), client.getInetAddress() + "");
            ClientPool.getInstance().addClient(bean);
            //调用execute()方法时，如果必要，会创建一个新的线程来处理任务，但它首先会尝试使用已有的线程，
            //如果一个线程空闲60秒以上，则将其移除线程池；
            //另外，任务是在Executor的内部排队，而不是在网络中排队
            //if (5000 == client.getPort()) {
                service.execute(new ServerThread(client));
            //} else if (5001 == client.getPort()) {
              //  service.execute(new Server5001Thread(client));
          //  } else {
             //   System.out.println("port error");
          //  }
            ClientPool.getInstance().printClients();
        }
        server.close();
    }
}
