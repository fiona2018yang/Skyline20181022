package com.rs.skyline.waterdata.entity;

/**
 * @Auther: heyc
 * @Date: 2018/9/12 13:54
 * @Description:
 */
public class Information {
    private int id;//主键
    private String pid;//类别id
    private String personId;//人员编号
    private String longitude;//经度
    private String latitude;//纬度
    private String createTime;//接收时间

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }


    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }


}
