package com.rs.skyline.waterdata.controller;

import com.rs.skyline.waterdata.entity.Information;
import com.rs.skyline.waterdata.service.InforMationService;
import com.rs.skyline.waterdata.util.Result;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: heyc
 * @Date: 2018/9/12 14:12
 * @Description:
 */
@Controller
@RequestMapping("/information")
public class InformationController {

    @Autowired
    private InforMationService informationService;

    /**
     * 查询数据
     * @param request
     * @return
     */
    @RequestMapping(value = "/selectInformationData",produces = "application/json",method = RequestMethod.GET)
    @ResponseBody
    public JSONObject selectInformationData(HttpServletRequest request) {
        Map map = new HashMap();
//        查询
        JSONObject r = informationService.selectInformationData();
        return r;
    }
    /**
     * 插入数据
     * @param request
     * @return
     */
  @RequestMapping(value = "insertInformationData",produces = "application/json",method = RequestMethod.POST)
  @ResponseBody
    public Result insertInformationData(Information information, HttpServletRequest request) {
//        插入数据
      Result r  = informationService.insertInformationData(information);
        return r;
    }
}
