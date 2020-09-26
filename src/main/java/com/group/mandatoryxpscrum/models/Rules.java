package com.group.mandatoryxpscrum.models;

import javax.persistence.*;

@Entity
@Table(name = "rules")
public class Rules{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rules")
    private Integer id;

    @Column(name = "max_capacity")
    private Integer maxCapacity;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "age_limit")
    private Integer ageLimit;

    @Column(name = "height_limit")
    private Integer heightLimit;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rules_activity_id", referencedColumnName = "id_activity")
    private Activity activity;

    /** Constructors + getters og setters */
    public Rules(){}
    public Rules(Integer maxCapacity, Integer duration, Integer ageLimit, Integer heightLimit, Activity activity) {
        this.maxCapacity = maxCapacity;
        this.duration = duration;
        this.ageLimit = ageLimit;
        this.heightLimit = heightLimit;
        this.activity = activity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(Integer maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(Integer ageLimit) {
        this.ageLimit = ageLimit;
    }

    public Integer getHeightLimit() {
        return heightLimit;
    }

    public void setHeightLimit(Integer heightLimit) {
        this.heightLimit = heightLimit;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    @Override
    public String toString() {
        return "Rules{" +
                "id=" + id +
                ", maxCapacity=" + maxCapacity +
                ", duration=" + duration +
                ", ageLimit=" + ageLimit +
                ", heightLimit=" + heightLimit +
                '}';
    }
}
