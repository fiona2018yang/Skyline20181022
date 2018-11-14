package com.rs.skyline.waterdata.util;

import com.sun.org.apache.xpath.internal.operations.Bool;

/**
 * @Auther: heyc
 * @Date: 2018/6/8 09:42
 * @Description: 返回结果bean
 */
public class Result {

    private Boolean success;
    //返回值
    private  String code;
    //数据
    private  Object detail;
    private String description;

    public Result success(Boolean success){
        this.success = success;
        if(success){
            this.code = "0";
        }else{
            this.code = "-1";
        }
        return this;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getDetail() {
        return detail;
    }

    public void setDetail(Object detail) {
        this.detail = detail;
    }
}
