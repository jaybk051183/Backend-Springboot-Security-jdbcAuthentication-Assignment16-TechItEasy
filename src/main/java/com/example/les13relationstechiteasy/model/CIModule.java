package com.example.les13relationstechiteasy.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

//@Entity: Deze annotatie geeft aan dat deze klasse een entiteit is in het Java Persistence API (JPA) systeem. Dit betekent dat het objecten van deze klasse zal opslaan en ophalen uit de database.
@Entity
public class CIModule {
    //Deze annotaties worden gebruikt om aan te geven dat het id veld de primaire sleutel is voor deze entiteit en dat de waarde automatisch gegenereerd wordt (meestal door de database).
    @Id
    @GeneratedValue
    private  Long id;
    private String name;
    private String type;
    private Double price;

    // Dit is de target kant van de relatie. Er staat niks in de database
    //Deze annotatie geeft aan dat er een "one-to-many" relatie bestaat tussen de CIModule entiteit en de Television entiteit. In dit geval betekent het dat één CIModule object gerelateerd kan zijn aan meerdere Television objecten. De mappedBy parameter in de annotatie geeft aan dat de relatie wordt gedefinieerd in de Television klasse met het veld ciModule.
    @OneToMany(mappedBy = "ciModule")
    //@JsonIgnore: Deze annotatie wordt gebruikt om het veld televisions te negeren tijdens het serialiseren van een CIModule object naar JSON-formaat. Dit voorkomt oneindige recursieproblemen wanneer er een bi-directionele relatie bestaat tussen twee entiteiten.
    @JsonIgnore
    List<Television> televisions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

}
