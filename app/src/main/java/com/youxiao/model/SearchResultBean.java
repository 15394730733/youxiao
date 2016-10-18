package com.youxiao.model;

import java.io.Serializable;
import java.util.List;

/**
 * 搜索结果对应的javabean
 * Created by Administrator on 2016/9/24.
 */
public class SearchResultBean implements Serializable{
    public CommodityBean data;
    public boolean flag;
    public String result;

    public class CommodityBean implements Serializable{
        public List<Commodity> commodity;

        public class Commodity implements Serializable{
            public String barCode;
            public double bigBuyPrice;
            public double buyPrice;
            public String cmdtyName;
            public String cmdtySpell;
            public String cmdtyType;
            public int distrId;
            public int id;
            public double sum;
            public int kindId;
            public String nonretailUnit;
            public long productionDate;//生产日期
            public int quantity;
            public double retailPrice;
            public String retailUnit;
        }
    }
}
