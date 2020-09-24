package com.group.mandatoryxpscrum.models;

import javax.persistence.*;

@Entity
@Table(name = "rules")
public class Rules{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_rules;

    @Column(name = "max_capacity")
    private int max_capacity;

    @Column(name = "duration")
    private int duration;

    @Column(name = "min_age")
    private int min_age;

    @Column(name = "min_height")
    private int min_height;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_activity")
    private Activity activity;

    public Rules(){}

    public Rules(int max_capacity, int duration, int min_age, int min_height, Activity activity) {
        this.max_capacity = max_capacity;
        this.duration = duration;
        this.min_age = min_age;
        this.min_height = min_height;
        this.activity = activity;
    }

    public int getId_rules() {
        return id_rules;
    }

    public void setId_rules(int id) {
        this.id_rules = id;
    }

    public int getMax_capacity() {
        return max_capacity;
    }

    public void setMax_capacity(int max_capacity) {
        this.max_capacity = max_capacity;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getMin_age() {
        return min_age;
    }

    public void setMin_age(int min_age) {
        this.min_age = min_age;
    }

    public int getMin_height() {
        return min_height;
    }

    public void setMin_height(int min_height) {
        this.min_height = min_height;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

}
