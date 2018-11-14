package com.rs.skyline.waterdata.entity;


/**
 * @Auther: heyc
 * @Date: 2018/6/7 16:04
 * @Description:
 */

public class MonitorData {

    /**序号*/
    private  int id;

    /**变量名称*/
    private  String tagname;

    /**变量值*/
    private String tagvalue;

    /**获取变量时间*/
    private String time;

    /**变量质量*/
    private String quality;

    /**变量编号*/
    private String number;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTagname() {
        return tagname;
    }

    public void setTagname(String tagname) {
        this.tagname = tagname;
    }

    public String getTagvalue() {
        return tagvalue;
    }

    public void setTagvalue(String tagvalue) {
        this.tagvalue = tagvalue;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
