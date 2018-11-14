

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by YUAN on 2016-09-17.
 */
public class TalkServer4Byte {

    private ServerSocket server;
    private int port = 5020;

    public TalkServer4Byte() {
        try {
            server = new ServerSocket(port);
        } catch (IOException e) {
        }
    }

    public void talk() {
        System.out.println("监控端口：" + port);
        Socket socket = null;
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

        while (true) {
            try {
                // 阻塞等待，每接收到一个请求就创建一个新的连接实例
                socket = server.accept();
                System.out.println("连接客户端地址：" + socket.getRemoteSocketAddress());

                // 装饰流BufferedReader封装输入流（接收客户端的流）
//                BufferedInputStream bis = new BufferedInputStream(
//                        socket.getInputStream());

                DataInputStream dis = new DataInputStream(socket.getInputStream());
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                byte[] bytes = new byte[16]; // 一次读取一个byte
                String ret = "";
                while (dis.read(bytes) != -1) {
                 //   ret += bytesToHexString(bytes) + " ";
                    ret = bytesToHexString(bytes) + " ";
                    if (dis.available() == 0) { //一个请求
                        System.out.println("说：" + ret);
                        List<String> str = new ArrayList<>();
                        for (int i = 0; i < 16; i++) {
                            str.add(ret.substring(i * 2, i * 2 + 2));
                        }

                        if (str.get(3).equals("23")){
                            if (str.get(12).equals("01")){
                                try{
                                    out.write(b);
                                }catch (IOException e){
                                    System.out.println("失败");
                                }
                                System.out.println("send");
                            }
                        }
                    }
                }
            } catch (IOException e) {
                System.out.println("断开");
            } finally {
                try {
                    System.out.println("断开5");
                    socket.close();
                } catch (IOException e) {
                    System.out.println("断开2");
                }
            }
        }

    }

    public static void doSomething(String ret) {
        System.out.println(ret);
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

    public static String BytesHexString(byte[] b) {
        String ret = "";
        for (int i = 0; i < b.length; i++) {
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            ret += hex.toUpperCase();
        }
        return ret;
    }

    public static void main(String[] args) {
        TalkServer4Byte server = new TalkServer4Byte();
        server.talk();
    }
}