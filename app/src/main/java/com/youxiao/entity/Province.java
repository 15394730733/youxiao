package com.youxiao.entity;

import java.util.List;

/**
 * @author StomHong on 2016/10/7.
 */

public class Province {

    private List<P> province;

    public  static class P {
        public String name;
        public List<String> cities;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<String> getCities() {
            return cities;
        }

        public void setCities(List<String> cities) {
            this.cities = cities;
        }
    }

    public List<P> getProvince() {
        return province;
    }

    public void setProvince(List<P> province) {
        this.province = province;
    }
}
