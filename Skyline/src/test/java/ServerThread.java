/**
 * @Auther: heyc
 * @Date: 2018/6/23 10:04
 * @Description:
 */
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * 该类为多线程类，用于服务端
 */
public class ServerThread implements Runnable {

    private Socket client = null;
    public ServerThread(Socket client){
        this.client = client;
    }

    //处理通信细节的静态方法，这里主要是方便线程池服务器的调用
    public static void execute(Socket client){
        try{
            //获取Socket的输出流，用来向客户端发送数据
            PrintStream out = new PrintStream(client.getOutputStream());
            //获取Socket的输入流，用来接收从客户端发送过来的数据
            BufferedReader buf = new BufferedReader(new InputStreamReader(client.getInputStream()));
            InputStream inputStream = client.getInputStream();
            boolean flag =true;
            String str = "";
            while(flag){
                //接收从客户端发送过来的数据
                byte[] buf5 = new byte[1024];
                int len = inputStream.read(buf5);
                str =  new String(buf5, 0, len);
                if(str == null || "".equals(str)){
                    flag = false;
                }else{
                    if("bye".equals(str)){
                        flag = false;
                    }else{
                        //将接收到的字符串前面加上echo，发送到对应的客户端
                        System.out.println("说：" + str);
                        out.println("echo:" + str);
                    }
                }
            }
            out.close();
            buf.close();
            client.close();
            inputStream.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        execute(client);
    }

}