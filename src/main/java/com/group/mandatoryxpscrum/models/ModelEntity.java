package com.group.mandatoryxpscrum.models;

public abstract class ModelEntity {
    private int id;

    public ModelEntity(){};

    public ModelEntity(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
