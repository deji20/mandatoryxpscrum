package com.group.mandatoryxpscrum.models;

public class Rules extends ModelEntity{

    int max_capacity;
    int duration;
    int min_age;
    int min_height;

    public Rules(int max_capacity, int duration, int min_age, int min_height) {
        this.max_capacity = max_capacity;
        this.duration = duration;
        this.min_age = min_age;
        this.min_height = min_height;
    }

    public Rules(int id, int max_capacity, int duration, int min_age, int min_height) {
        super(id);
        this.max_capacity = max_capacity;
        this.duration = duration;
        this.min_age = min_age;
        this.min_height = min_height;
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

}
