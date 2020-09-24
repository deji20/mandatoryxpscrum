package com.group.mandatoryxpscrum.models;

import javax.persistence.*;

@Entity
@Table(name = "equipment")
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_equipment;

    @Column(name = "name")
    private String name;

    @Column(name = "available")
    private boolean available;

    @Column(name = "comment")
    private String comment;

    public Equipment() {

    }

    public Equipment(String name, boolean available, String comment, Activity activity) {
        this.name = name;
        this.available = available;
        this.comment = comment;
    }

    public int getId_equipment() {
        return id_equipment;
    }

    public void setId_equipment(int id_equipment) {
        this.id_equipment = id_equipment;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
