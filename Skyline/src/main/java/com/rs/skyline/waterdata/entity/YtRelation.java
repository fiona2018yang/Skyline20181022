package com.rs.skyline.waterdata.entity;

/**
 * @Auther: gyz
 * @Date: 2018/9/15 10:47
 * @Description:   云台摄像头关系表
 */
public class YtRelation {
    //Id
    private Long id;
    //绑定名称
    private String relationName;
    //云台IP
    private String ytIp;
    //云台端口
    private String ytPort;
    //摄像头IP
    private String sxtIp;
    //摄像头端口
    private String sxtPort;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRelationName() {
        return relationName;
    }

    public void setRelationName(String relationName) {
        this.relationName = relationName;
    }

    public String getYtIp() {
        return ytIp;
    }

    public void setYtIp(String ytIp) {
        this.ytIp = ytIp;
    }

    public String getYtPort() {
        return ytPort;
    }

    public void setYtPort(String ytPort) {
        this.ytPort = ytPort;
    }

    public String getSxtIp() {
        return sxtIp;
    }

    public void setSxtIp(String sxtIp) {
        this.sxtIp = sxtIp;
    }

    public String getSxtPort() {
        return sxtPort;
    }

    public void setSxtPort(String sxtPort) {
        this.sxtPort = sxtPort;
    }
}
