package com.youxiao.entity;

/**
 * @author StomHong on 2016/9/21.
 */

public class Sales {

    /**
     * 商品id
     */
    public String id;

    /**
     * 商品名称
     */
    public String name;
    /**
     * 商品重量
     */
    public String weight;
    /**
     * 商品数量
     */
    public String box;
    /**
     * 单价
     */
    public float unitPrice;
    /**
     * 箱价
     */
    public float boxPrice;
    /**
     * 散货数量
     */
    public String unitQuantity;
    /**
     * 整箱数量
     */
    public String boxQuantity;
    /**
     * 生产日期
     */
    public String produceDate;
    /**
     * 是否选中
     */
    public boolean isSelect;

    public Sales(String name, String weight, String box, float unitPrice, float boxPrice,
                 String unitQuantity, String boxQuantity, String produceDate, boolean isSelect) {
        this.name = name;
        this.weight = weight;
        this.box = box;
        this.unitPrice = unitPrice;
        this.boxPrice = boxPrice;
        this.unitQuantity = unitQuantity;
        this.boxQuantity = boxQuantity;
        this.produceDate = produceDate;
        this.isSelect = isSelect;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getBox() {
        return box;
    }

    public void setBox(String box) {
        this.box = box;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public float getBoxPrice() {
        return boxPrice;
    }

    public void setBoxPrice(float boxPrice) {
        this.boxPrice = boxPrice;
    }

    public String getUnitQuantity() {
        return unitQuantity;
    }

    public void setUnitQuantity(String unitQuantity) {
        this.unitQuantity = unitQuantity;
    }

    public String getBoxQuantity() {
        return boxQuantity;
    }

    public void setBoxQuantity(String boxQuantity) {
        this.boxQuantity = boxQuantity;
    }

    public String getProduceDate() {
        return produceDate;
    }

    public void setProduceDate(String produceDate) {
        this.produceDate = produceDate;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}
