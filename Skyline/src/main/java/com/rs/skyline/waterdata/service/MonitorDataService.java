package com.rs.skyline.waterdata.service;

import com.google.gson.Gson;
import com.rs.skyline.waterdata.entity.MonitorData;
import com.rs.skyline.waterdata.mapper.MonitorDataMapper;
import com.rs.skyline.waterdata.util.Result;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import  java.util.Map;

/**
 * @Auther: heyc
 * @Date: 2018/6/8 09:45
 * @Description:
 */
@Service
public class MonitorDataService {
    @Autowired
     private  MonitorDataMapper  monitorDataMapper;

/**
 * @param: 查询数据
 * @return:
 * @auther: heyc
 * @date: ${DATE} ${HOUR}:${MINUTE}
 */
    public  List<MonitorData>selectMonitorData(){
        return  monitorDataMapper.selectMonitorData();
    }

     public Map getMonitorData(){
        return  monitorDataMapper.getMonitorData();
     }

}
