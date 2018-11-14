package com.rs.skyline.waterdata.entity;

import java.net.Socket;

public class Bean {

    Socket client;
    int port;
    String address;

    public Bean(Socket client, int port, String address) {
        this.client = client;
        this.port = port;
        this.address = address;
    }

    public Socket getClient() {
        return client;
    }

    public void setClient(Socket client) {
        this.client = client;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
