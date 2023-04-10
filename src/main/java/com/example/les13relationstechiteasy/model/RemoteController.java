package com.example.les13relationstechiteasy.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class RemoteController {
    @Id
    @GeneratedValue
    private Long id;
    private String compatibleWith;
    private String batteryType;
    private String name;
    private String brand;
    private Double price;
    private Integer originalStock;

    // Deze annotatie geeft aan dat er een "one-to-one" relatie bestaat tussen de RemoteController entiteit en de Television entiteit.
    //In dit geval betekent het dat één RemoteController object gerelateerd is aan één Television object. De mappedBy parameter in de annotatie geeft aan dat de relatie wordt gedefinieerd in de Television klasse met het veld remotecontroller.
    @OneToOne(mappedBy = "remotecontroller")
    //Dit veld bevat een Television object dat gerelateerd is aan het huidige RemoteController object. Dit is de zogenaamde "target" kant van de one-to-one relatie.
    private Television television;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompatibleWith() {
        return compatibleWith;
    }

    public void setCompatibleWith(String compatibleWith) {
        this.compatibleWith = compatibleWith;
    }

    public String getBatteryType() {
        return batteryType;
    }

    public void setBatteryType(String batteryType) {
        this.batteryType = batteryType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getOriginalStock() {
        return originalStock;
    }

    public void setOriginalStock(Integer originalStock) {
        this.originalStock = originalStock;
    }

    public Television getTelevision() {
        return television;
    }

    public void setTelevision(Television television) {
        this.television = television;
    }
}
