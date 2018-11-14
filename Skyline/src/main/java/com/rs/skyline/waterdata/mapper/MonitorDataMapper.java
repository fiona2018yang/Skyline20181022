package com.rs.skyline.waterdata.mapper;

import com.rs.skyline.waterdata.entity.MonitorData;

import java.util.List;
import java.util.Map;


/**
 * @Auther: heyc
 * @Date: 2018/6/7 16:04
 * @Description:
 */
public interface MonitorDataMapper {
   /**
    * @param: 查询monitordata
    * @return:
    * @auther: heyc
    * @date: 2018年6月7日 16:09:25
    */
    public List<MonitorData>selectMonitorData();

    public Map  getMonitorData();




}
