package com.rs.skyline.waterdata.mapper;

import com.rs.skyline.waterdata.entity.YtRelation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Auther: gyz
 * @Date: 2018/9/12 13:57
 * @Description: 云台摄像头关系映射表
 */
public interface YtRelationMapper {
    /**
     * 获取所有云台摄像头关系
     * @return
     */
    List<YtRelation> selectAllRelation();

    /**
     * 插入云台摄像头关系
     * @return
     */
    int insertRelation(YtRelation ytRelation);

    /**
     * 删除云台摄像头关系
     * @param id
     */
    void deleteRelation(@Param("id")Integer id);

    /**
     * 根据云台IP和端口查找完整关系
     * @param ytIp
     * @param ytPort
     * @return
     */
    YtRelation selectByIpAndPort(@Param("ytIp") String ytIp, @Param("ytPort")Integer ytPort);

    /**
     * 根据摄像头IP获取摄像头
     * @param sxtIp
     * @return
     */
    YtRelation getYtrealtionBySxtip(@Param("sxtIp")String sxtIp);

    /**
     * 根据云台IP获取摄像头
     * @param ytIp
     * @return
     */
    YtRelation getYtrelationByYtip(@Param("ytip") String ytIp);
}
