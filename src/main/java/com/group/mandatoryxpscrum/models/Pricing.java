package com.group.mandatoryxpscrum.models;

import javax.persistence.*;

@Entity
@Table(name = "pricing")
public class Pricing{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pricing")
    private Integer id;

    @Column(name = "standard")
    private Integer standard;

    @Column(name = "discount")
    private Integer discount;

    @Column(name = "extra_info")
    private String extraInfo;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pricing_activity_id")
    private Activity activity;

    /** Constructors + getters og setters */
    public Pricing() {
    }

    public Pricing(Integer standard, Integer discount, String extraInfo, Activity activity) {
        this.standard = standard;
        this.discount = discount;
        this.extraInfo = extraInfo;
        this.activity = activity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStandard() {
        return standard;
    }

    public void setStandard(Integer standard) {
        this.standard = standard;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public String getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    @Override
    public String toString() {
        return "Pricing{" +
                "id=" + id +
                ", standard=" + standard +
                ", discount=" + discount +
                ", extraInfo='" + extraInfo + '\'' +
                '}';
    }
}
