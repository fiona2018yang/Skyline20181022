package com.rs.skyline.waterdata.mapper;

import com.rs.skyline.waterdata.entity.Information;

import java.util.List;

/**
 * @Auther: heyc
 * @Date: 2018/9/12 13:57
 * @Description:
 */
public interface InformationMapper {
    //    查询数据
    List<InformationMapper> selectInformationData();
    //    批量插入数据
   int   insertInformationData(Information information);

}
