package com.group.mandatoryxpscrum.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/** Annoterer at dette er et table i databasen */
@Entity
@Table(name = "activity")
public class Activity {

    /** Annoterer at dette er primary key (@Id)
     * med auto increment (@GeneratedValue)
     * og hvad navnet på dens kolonne i table er (@Column)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_activity")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "image")
    private String image;

    @Column(name = "description")
    private String description;

    /** Annoterer at der er et one to one relationship med Pricing objekter,
     * dvs. at for hver activity er det en pricing vice versa.
     * "mappedBy" fortæller navnet på Activity objektet i et Pricing objekt
     */
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "activity")
    private Pricing pricing;

    /** Samme som ovenstående */
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "activity")
    private Rules rules;

    /** Annoterer at der er et one to many relationship med Equipment objekter,
     * dvs. at for hver activity er der "uendeligt" meget equipment.
     * "orphanRemoval" sørger for at et Equipment objekt altid har et tilhørende Activity objekt
     * hvis ikke fjernes Equipment objektet fra databasen næste gang der bliver kaldt på databasen
     */
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "activity")
    private List<Equipment> equipment = new ArrayList<>();

    /** tilføjer et Equipment objekt til listen
     * og linker denne activity til equipment objektet
     */
    public void addEquipment(Equipment newEquipment){
        equipment.add(newEquipment);
        newEquipment.setActivity(this);
    }

    /** fjerner et Equipment objekt fra listen
     * og sætter activity i equipment objektet til null ("orphan")
     */
    public void removeEquipment(Equipment oldEquipment){
        equipment.remove(oldEquipment);
        oldEquipment.setActivity(null);
    }

    /** Constructors + getters og setters */
    public Activity() {

    }

    public Activity(String name, String image, String description, Pricing pricing, Rules rules, List<Equipment> equipment) {
        this.name = name;
        this.image = image;
        this.description = description;
        this.pricing = pricing;
        this.rules = rules;
        this.equipment = equipment;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Pricing getPricing() {
        return pricing;
    }

    public void setPricing(Pricing pricing) {
        this.pricing = pricing;
    }

    public Rules getRules() {
        return rules;
    }

    public void setRules(Rules rules) {
        this.rules = rules;
    }

    public List<Equipment> getEquipment() {
        return equipment;
    }

    /*-----------------------------------------------
    This method loops the equipment list and returns
    the number of available pieces of equipment
    ------------------------------------------------*/
    public Integer availableEquipment() {
        List<Equipment> available = equipment.stream()
                .filter(Equipment::isAvailable)
                .collect(Collectors.toList());
        return available.size();
    }


    public void setEquipment(List<Equipment> equipment) {
        this.equipment = equipment;
    }

}
