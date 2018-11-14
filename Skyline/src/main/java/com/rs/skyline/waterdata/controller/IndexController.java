package com.rs.skyline.waterdata.controller;

import com.rs.skyline.waterdata.entity.Instruct;
import com.rs.skyline.waterdata.entity.YtRelation;
import com.rs.skyline.waterdata.service.YtRelationService;
import com.rs.skyline.waterdata.util.Http;
import com.rs.skyline.waterdata.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * @Auther: heyc
 * @Date: 2018/8/31 15:47
 * @Description:
 */
@Controller
@RequestMapping("index")
public class IndexController {

    @Autowired
    private YtRelationService ytRelationService;

    @RequestMapping("toSettings")
    public String toSettings(){
        return "settings";
    }

    //重启云台
    @RequestMapping("restartCloud")
    public void restartCloud(){
        Instruct.restartCloud();
    }

    //恢复出厂设置
    @RequestMapping("restoreSetting")
    public void restoreSetting(){
        Instruct.restoreSetting();
    }

    //修改IP
    @RequestMapping("saveIp")
    public void saveIp(@RequestParam("IpType") String IpType,@RequestParam("LIP") String LIP){
        Instruct.saveIp(IpType,LIP);
    }
    //修改端口号
    @RequestMapping("savePort")
    public void savePort(@RequestParam("portType") String portType,@RequestParam("port") String port){
        Instruct.savePort(portType,port);
    }
    //保存补偿值
    @RequestMapping("saveBcValue")
    public void saveBcValue(@RequestParam("id") String id,@RequestParam("bcValue")String bcValue){
       Instruct.saveBcValue(id,bcValue);
    }

    //
    /**
     * 定位
     */
    @RequestMapping(value = "FisP", method = RequestMethod.GET)
    @ResponseBody
    public void FisP(){



    }

    /**
     * 获取云台和摄像头绑定关系数组
     * @return
     */
    @RequestMapping(value="getAllYtRelation")
    @ResponseBody
    public List<YtRelation> getAllYtRelation(){
        return ytRelationService.selectAllRelation();
    }

    /**
     * 保存云台和IP的关系
     * @param ytRelation
     * @return
     */
    @RequestMapping("saveYtRelation")
    @ResponseBody
    public Result saveYtRelation(@ModelAttribute YtRelation ytRelation){
        return ytRelationService.saveYtRelation(ytRelation);
    }

    /**
     * 删除云台和IP的关系
     * @param id
     * @return
     */
    @RequestMapping("delYtRelation")
    @ResponseBody
    public Result delYtRelation(@RequestParam("id")int id){
        return ytRelationService.delYtRelation(id);
    }
}
