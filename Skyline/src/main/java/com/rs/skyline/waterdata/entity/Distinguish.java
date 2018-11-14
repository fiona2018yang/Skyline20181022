package com.rs.skyline.waterdata.entity;

import java.net.Socket;

/**
 * @Auther: heyc
 * @Date: 2018/7/30 10:06
 * @Description:
 */
public class Distinguish {

    Socket client;

    public Distinguish(Socket client) {
        this.client = client;
    }

    public Socket getClient() {
        return client;
    }

    public void setClient(Socket client) {
        this.client = client;
    }
}
