package com.rs.skyline.waterdata.client;

import com.rs.skyline.waterdata.entity.Bean;
import com.rs.skyline.waterdata.entity.Constant;
import com.rs.skyline.waterdata.service.ServerExecutor;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.io.IOUtils;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @Auther: heyc
 * @Date: 2018/8/11 09:20
 * @Description:
 */
public class ClientPool {
    private static ClientPool clientPool = new ClientPool();

    private List<Bean> list_client = new ArrayList<>();

    private Object object = new Object();

    public static ClientPool getInstance() {
        return clientPool;
    }

    public void printClients() {
        synchronized (object) {
            Iterator<Bean> beanIterator = list_client.iterator();
            while (beanIterator.hasNext()) {
                Bean bean = beanIterator.next();
                System.out.println("已有端口号：" + bean.getPort());

            }
        }
    }

    public void removeSocket(Socket socket) {
        synchronized (object) {
            Iterator<Bean> beanIterator = list_client.iterator();
            while (beanIterator.hasNext()) {
                Bean bean = beanIterator.next();
                if (socket == bean.getClient()) {
                    beanIterator.remove();
                    break;
                }
            }
        }
    }

    //指定设备发送指令
    public void sendData(String ip, int port, byte[] b) {
        synchronized (object) {
            Iterator<Bean> beanIterator = list_client.iterator();
            while (beanIterator.hasNext()) {
                Bean bean = beanIterator.next();
                if (bean.getAddress().equals(ip) && port == bean.getPort()) {
                    try {
                        bean.getClient().getOutputStream().write(b);
                        bean.getClient().getOutputStream().flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                    }
                }
            }
        }
    }

    //同时发送指令给所有的客户端
    public void sendDataToAll(byte[] b) {
        synchronized (object) {
            Iterator<Bean> beanIterator = list_client.iterator();
            while (beanIterator.hasNext()) {
                Bean bean = beanIterator.next();
                try {
                    bean.getClient().getOutputStream().write(b);
                    bean.getClient().getOutputStream().flush();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                }
            }
        }
    }

    public void addClient(Bean bean) {
        synchronized (object) {
            list_client.add(bean);
            Constant.clientList = list_client;
        }
    }

}
