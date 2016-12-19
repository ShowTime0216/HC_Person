package com.hsy.hc.entity;

/**
 * Created by hsy on 16/9/5.
 */
public class Citys {

    private int city_id;
    private String city_name;
    private String initial;
    private int province_id;

    public Citys() {
    }

    public Citys(int city_id, String city_name, String initial, int province_id) {
        this.city_id = city_id;
        this.city_name = city_name;
        this.initial = initial;
        this.province_id = province_id;
    }

    public int getCity_id() {
        return city_id;
    }

    public void setCity_id(int city_id) {
        this.city_id = city_id;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getInitial() {
        return initial;
    }

    public void setInitial(String initial) {
        this.initial = initial;
    }

    public int getProvince_id() {
        return province_id;
    }

    public void setProvince_id(int province_id) {
        this.province_id = province_id;
    }

    @Override
    public String toString() {
        return "Citys{" +
                "city_id=" + city_id +
                ", city_name='" + city_name + '\'' +
                ", initial='" + initial + '\'' +
                ", province_id=" + province_id +
                '}';
    }
}
