package com.rs.skyline.waterdata.entity;

/**
 * @Auther: heyc
 * @Date: 2018/7/25 16:24
 * @Description:    俯仰角，航向角（角度）
 */
public class Angle {
    private String fuyangjiao = "0";
    private String hangxiangjiao = "0";


    public String getFuyangjiao() {
        return fuyangjiao;
    }

    public void setFuyangjiao(String fuyangjiao) {
        this.fuyangjiao = fuyangjiao;
    }

    public String getHangxiangjiao() {
        return hangxiangjiao;
    }

    public void setHangxiangjiao(String hangxiangjiao) {
        this.hangxiangjiao = hangxiangjiao;
    }
}
