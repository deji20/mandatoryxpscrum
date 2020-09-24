package com.group.mandatoryxpscrum.models;

public class Pricing extends ModelEntity{

    int standard;
    int discount;
    String extra_info;

    public Pricing(int standard, int discount, String extra_info) {
        super(0);
        this.standard = standard;
        this.discount = discount;
        this.extra_info = extra_info;
    }

    public Pricing(int id, int standard, int discount, String extra_info) {
        super(id);
        this.standard = standard;
        this.discount = standard;
        this.extra_info = extra_info;
    }

    public int getStandard() {
        return standard;
    }

    public void setStandard(int standard) {
        this.standard = standard;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getExtra_info() {
        return extra_info;
    }

    public void setExtra_info(String extra_info) {
        this.extra_info = extra_info;
    }

}
