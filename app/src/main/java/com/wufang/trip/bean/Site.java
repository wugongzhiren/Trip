package com.wufang.trip.bean;

/**
 * 景点
 * Created by Administrator on 2017/3/14.
 */

public class Site {
    private Integer id;
    private String name;
    private String descByOneWord;
    private String desc;
    private String imageUrls;
    private Integer stars;
    private String location;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescByOneWord() {
        return descByOneWord;
    }

    public void setDescByOneWord(String descByOneWord) {
        this.descByOneWord = descByOneWord;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(String imageUrls) {
        this.imageUrls = imageUrls;
    }

    public double getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
