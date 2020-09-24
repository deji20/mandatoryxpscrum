package com.group.mandatoryxpscrum.models;

import javax.persistence.*;

@Entity
@Table(name = "pricing")
public class Pricing{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_pricing;

    @Column(name = "standard")
    private int standard;

    @Column(name = "discount")
    private int discount;

    @Column(name = "extra_info")
    private String extra_info;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_activity")
    private Activity activity;

    public Pricing() { }

    public Pricing(int standard, int discount, String extra_info, Activity activity) {
        this.standard = standard;
        this.discount = discount;
        this.extra_info = extra_info;
        this.activity = activity;
    }

    public int getId_pricing() {
        return id_pricing;
    }

    public void setId_pricing(int id) {
        this.id_pricing = id;
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

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

}
