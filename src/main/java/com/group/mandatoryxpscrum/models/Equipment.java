package com.group.mandatoryxpscrum.models;

public class Equipment extends ModelEntity{

    private String name;
    private boolean available;
    private String avail_desc;

    public Equipment(int id, String name, boolean available, String avail_desc) {
        super(id);
        this.name = name;
        this.available = available;
        this.avail_desc = avail_desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getAvail_desc() {
        return avail_desc;
    }

    public void setAvail_desc(String avail_desc) {
        this.avail_desc = avail_desc;
    }
}
