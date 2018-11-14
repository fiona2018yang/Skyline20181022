package com.rs.skyline.waterdata.service;

import com.rs.skyline.waterdata.entity.Information;
import com.rs.skyline.waterdata.mapper.InformationMapper;
import com.rs.skyline.waterdata.util.Result;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Auther: heyc
 * @Date: 2018/9/12 13:58
 * @Description:
 */
@Service
public class InforMationService {

    @Autowired
    private InformationMapper  informationMapper;


    /***
     * @date
     * 查询数据
     *
     **/

    public JSONObject selectInformationData(){
        Result r = new Result();
//       获取数据
        List<InformationMapper> list = informationMapper.selectInformationData();
        JSONArray ja = JSONArray.fromObject(list);
        if (list.size() > 0 ){
            r.setCode("1");
            r.setDetail(ja);
        }else {
            r.setCode("0");
        }
        net.sf.json.JSONObject jo = net.sf.json.JSONObject.fromObject(r);
        return jo;
    }

    /**
     * 批量插入数据
     * @return
     */
  @Transactional
    public Result insertInformationData(Information information){

        Date d = new Date();
        Result r = new Result();
        SimpleDateFormat df = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        if(information!=null){
            String data[] = information.getLatitude().split(",");//截取
            for (String str : data){
                String s[] = str.split("=");
                if(s.length > 1){
                    if(("longitude").equals(s[0])){
                        information.setLongitude(s[1]);
                    }
                    if(("personId").equals(s[0])){
                        information.setPersonId(s[1]);
                    }
                    if(("pid").equals(s[0])){
                        information.setPid(s[1]);
                    }
                }
            }
            information.setLatitude(data[0]);
        }
        System.out.println("经度:"+information.getLatitude());
        System.out.println("纬度:"+information.getLongitude());
        System.out.println("人员id:"+information.getPersonId());
        information.setCreateTime(df.format(d));
        //        批量插入
      //  int i  = informationMapper.insertInformationData(information);
        int i = informationMapper.insertInformationData(information);
        //        判断
        if(i > 0){
            r.setCode("1");
            r.setDescription("插入成功！");
        }else {
            r.setDescription("插入失败！");
            r.setCode("0");
        }
        return r;
    }




}
