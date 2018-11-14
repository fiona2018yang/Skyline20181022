package com.rs.skyline.waterdata.util;

import com.google.gson.Gson;
import com.rs.skyline.waterdata.service.MonitorDataService;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: heyc
 * @Date: 2018/7/14 15:23
 * @Description:
 */
public class ConnSocket {

    public String conClient(){
        String serviceUrl = "http://192.168.1.207:8080";
           String str = "";
            str = doPost(serviceUrl, "测试");
            System.out.println(str);
          return str;
    }
    /**
     *
     * @param strUrl
     * @param postString
     * @return
     * String
     */
    @SuppressWarnings("deprecation")
    public static String doPost(String strUrl, String postString) {
        String receive = null;
        // 请求发布在本地 Tomcat上服务
        PostMethod method = new PostMethod(strUrl);
        try {
            HttpClient client = new HttpClient();
            //请求 网络上的服务, 用这种方式请求本地,返回一个Html页面
            method.setRequestHeader("Content-type", "application/json; charset=UTF-8");
            method.setRequestHeader("Accept", "application/json; charset=UTF-8");
            // 设置为默认的重试策略
            method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
            method.setRequestBody(postString);
            int rspCode = client.executeMethod(method);
            System.out.println("rspCode:" + rspCode);
            receive = method.getResponseBodyAsString();

            return receive;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            method.releaseConnection();
        }
        return receive;
    }


    public static void main(String[] args) throws IOException {
        Http.doPostWithoutKey("http://192.168.1.207:8080","测试");
    }
}
