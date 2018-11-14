package com.rs.skyline.waterdata.service;

import com.rs.skyline.waterdata.entity.Bean;
import com.rs.skyline.waterdata.entity.MonitorData;
import com.rs.skyline.waterdata.entity.YtRelation;
import com.rs.skyline.waterdata.mapper.MonitorDataMapper;
import com.rs.skyline.waterdata.mapper.YtRelationMapper;
import com.rs.skyline.waterdata.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Auther: gyz
 * @Date: 2018/6/8 09:45
 * @Description: 云台摄像头关系service
 */
@Service
public class YtRelationService {
    @Autowired
     private YtRelationMapper ytRelationMapper;

    /**
     * 获取云台和IP关系
     * @return
     */
    public  List<YtRelation>selectAllRelation(){
        return  ytRelationMapper.selectAllRelation();
    }

    /**
     * 新增云台IP关系
     * @param ytRelation
     * @return
     */
    public Result saveYtRelation(YtRelation ytRelation){
        Result result = new Result();

        int insertResult = ytRelationMapper.insertRelation(ytRelation);
        if(insertResult>0){
            result.success(true);
        }else{
            result.success(false);
        }
        return result;
    }

    public Result delYtRelation(Integer id){
        Result result = new Result();
        ytRelationMapper.deleteRelation(id);
        return result.success(true);
    }

    /**
     * 根据云台批量获取摄像头
     * @param clientList
     * @return
     */
    public List<YtRelation> getYtClient(List<Bean> clientList) {
        List<YtRelation> ytMapList = new ArrayList<>();
        if(clientList != null && clientList.size()>0){
            for(Bean client:clientList){
                YtRelation ytRelation =  ytRelationMapper.selectByIpAndPort(client.getAddress().replace("/",""),client.getPort());
                if(ytRelation != null){
                    ytMapList.add(ytRelation);
                }
            }
        }
        return ytMapList;
    }

    /**
     * 根据摄像头IP获取对象
     * @param sxtIp
     * @return
     */
    public YtRelation getYtrealtionBySxtip(String sxtIp) {
        return ytRelationMapper.getYtrealtionBySxtip(sxtIp);
    }

    /**
     * 根据云台IP获取对象
     * @param ytIp
     * @return
     */
    public YtRelation getYtrelationByYtip(String ytIp){
        return ytRelationMapper.getYtrelationByYtip(ytIp);
    }
}
